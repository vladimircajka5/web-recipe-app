// Angularov nacin za ucitavanje HTTP podataka
import { httpResource } from '@angular/common/http';
// Podrska za ubacivanje zavisnosti
import { Injectable, type Signal } from '@angular/core';
// Obican napsani fajl iz kojeg se cita localhost:port
import { environment } from '../../env/environment';
// Fajl za podatke o useru
import type { UserProfileData } from './user.model';

// Servis za ubacivanje zavisnosti u aplikaciji
@Injectable({ providedIn: 'root' })
export class UserService {

  // Signal tekstualne vrednosti
  loadProfile(id: Signal<string>) {

    // Vraca http resurs, koji ocekuje podatke oblika UserProfileData
    return httpResource<UserProfileData>(
      () => `${environment.apiHost}/api/users/${id()}`
    );
  }
}
