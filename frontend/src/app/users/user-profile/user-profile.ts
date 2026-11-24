
import { HttpErrorResponse } from '@angular/common/http';
import { Component, computed, inject, input } from '@angular/core';
import { RouterLink } from '@angular/router';

import { UserService } from '../user.service';

// Podaci za prikaz stranice, ovde se povezuje
@Component({
  selector: 'app-user-profile',       // naziv za koriscenje u drugim sablonima
  imports: [RouterLink],              // funkcionalnosti i komponente koje ovaj sablon koristi
  templateUrl: './user-profile.html', // html veza
  styleUrl: './user-profile.css',     // css veza
})
export class UserProfile {

  private readonly userService = inject(UserService);

  // input - ulazni signal komponente, dobija ga spolja
  // required - input je obavezan
  // <string> - bice string
  readonly id = input.required<string>();

  // Veza komponente i servisa
  protected readonly profile = this.userService.loadProfile(this.id);

  // computed automatski menja vrednost kada se promeni signal
  // ne racumano je rucno, nego se odredjuje na osnovu postojeceg HTTP resursa
  protected readonly notFound = computed(() => {
    const error = this.profile.error();
    return error instanceof HttpErrorResponse && error.status === 404;
  });
}
