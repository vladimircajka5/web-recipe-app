import { HttpErrorResponse } from '@angular/common/http';
import { Component, computed, effect, inject, input, signal } from '@angular/core';
import { FormField, form, maxLength, required } from '@angular/forms/signals';
import { Router, RouterLink } from '@angular/router';

import { UserService } from '../user.service';
import type { UpdateUserProfileData } from '../user.model';

@Component({
  selector: 'app-edit-profile',
  imports: [FormField, RouterLink],
  templateUrl: './edit-profile.html',
  styleUrl: './edit-profile.css',
})
export class EditProfile {

  private readonly userService = inject(UserService);
  private readonly router = inject(Router);

  readonly id = input.required<string>();

  protected readonly profile = this.userService.loadProfile(this.id);

  private readonly model = signal({
    profilePictureUrl: '',
    region: '',
    biography: '',
  });

  protected readonly form = form(this.model, (path) => {
    required(path.region, { message: 'Region is required.' });
    maxLength(path.region, 255);
    maxLength(path.profilePictureUrl, 255);
    maxLength(path.biography, 2000);
  });

  protected readonly pending = signal(false);
  protected readonly saveError = signal<string | null>(null);

  protected readonly notFound = computed(() => {
    const error = this.profile.error();
    return error instanceof HttpErrorResponse && error.status === 404;
  });

  
  
  constructor() {
    effect(() => {
      if (this.profile.hasValue()) {
        const user = this.profile.value();

        this.model.set({
          profilePictureUrl: user.profilePictureUrl ?? '',
          region: user.region,
          biography: user.biography ?? '',
        });
      }
    });
  }



  protected submit(event: Event): void {
    event.preventDefault();

    if (!this.form().valid() || this.pending()) {
      return;
    }

    this.pending.set(true);
    this.saveError.set(null);

    const data: UpdateUserProfileData = {
      profilePictureUrl: this.model().profilePictureUrl.trim() || null,
      region: this.model().region.trim(),
      biography: this.model().biography.trim() || null,
    };

    this.userService.updateProfile(this.id(), data).subscribe({
      next: (updatedUser) => {
        this.router.navigate(['/users', updatedUser.id]);
      },
      error: (error: HttpErrorResponse) => {
        if (error.status === 400) {
          this.saveError.set('Invalid profile data. Please check your entries.');
        } else if (error.status === 404) {
          this.saveError.set('User not found.');
        } else {
          this.saveError.set('Could not save profile changes.');
        }

        this.pending.set(false);
      },
    });
  }
}
