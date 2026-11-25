import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ClassificationManagement } from './classification-management';

const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'classifications' },
  { path: 'classifications', component: ClassificationManagement },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
})
export class AdminModule {}