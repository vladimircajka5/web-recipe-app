import { Routes } from '@angular/router';
import { Home } from './home/home';
import { RecipeDetail } from './recipes/recipe-detail/recipe-detail';
import { EditProfile } from './users/edit-profile/edit-profile';
import { UserProfile } from './users/user-profile/user-profile';

export const routes: Routes = [
  { path: '', component: Home },
  { path: 'recipes/:id', component: RecipeDetail },
  { path: 'users/:id/edit', component: EditProfile },
  { path: 'users/:id', component: UserProfile },
  { path: 'admin', loadChildren: () =>
      import('./admin/classification-management/admin-module').then((module) => module.AdminModule),
  },
];
