import { Component, inject } from '@angular/core';
import { ActivatedRoute, RouterLink } from '@angular/router';

import { UserService } from '../user.service';
import { UserProfileData } from '../user.model';

@Component({
  selector: 'app-user-profile',
  imports: [RouterLink],
  templateUrl: './user-profile.html',
  styleUrl: './user-profile.css',
})
export class UserProfile {

  private readonly route = inject(ActivatedRoute);
  private readonly userService = inject(UserService);

  protected profile: UserProfileData | null = null;

  constructor() {
    this.route.paramMap.subscribe((params) => {

      const id = Number(params.get('id'));

      if (Number.isInteger(id) && id > 0) {
        this.profile = this.userService.getById(id);
      } else {
        this.profile = null;
      }
    });
  }
}