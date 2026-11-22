import { Component, input } from '@angular/core';
import { RecipeCardData } from '../../recipes/recipe.model';

@Component({
  selector: 'app-recipe-card',
  templateUrl: './recipe-card.html',
  styleUrl: './recipe-card.css',
})
export class RecipeCard {
  readonly recipe = input.required<RecipeCardData>();
}