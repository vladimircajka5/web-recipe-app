import { HttpClient, httpResource } from '@angular/common/http';
import { Injectable, inject, type Signal } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../env/environment';
import type { UpdateUserProfileData, UserProfileData } from './user.model';

@Injectable({ providedIn: 'root' })
export class UserService {

  private readonly http = inject(HttpClient);

  loadProfile(id: Signal<string>) {
    return httpResource<UserProfileData>(
      () => `${environment.apiHost}/api/users/${id()}`
    );
  }

  updateProfile(id: string, data: UpdateUserProfileData): Observable<UserProfileData> {
    return this.http.put<UserProfileData>(
      `${environment.apiHost}/api/users/${id}`,
      data
    );
  }
}
