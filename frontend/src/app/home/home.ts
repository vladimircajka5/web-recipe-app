import { Component, inject } from '@angular/core';
import { RecipeService } from '../recipes/recipe.service';
import { RecipeCard } from '../shared/recipe-card/recipe-card';

@Component({
  selector: 'app-home',
  imports: [RecipeCard],
  styleUrl: './home.css',
  templateUrl: './home.html',
})
export class Home {
  protected readonly recipes = inject(RecipeService).recipes;
}