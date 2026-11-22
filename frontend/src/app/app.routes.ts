import { Routes } from '@angular/router';
import { Home } from './home/home';
import { RecipeDetail } from './recipes/recipe-detail/recipe-detail';

export const routes: Routes = [
  { path: '', component: Home },
  { path: 'recipes/:id', component: RecipeDetail },
];