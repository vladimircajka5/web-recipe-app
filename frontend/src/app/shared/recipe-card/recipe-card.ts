import { Component, input } from '@angular/core';
import { RouterLink } from '@angular/router';
import { RecipeCardData } from '../../recipes/recipe.model';

@Component({
  selector: 'app-recipe-card',
  imports: [RouterLink],
  templateUrl: './recipe-card.html',
  styleUrl: './recipe-card.css',
})
export class RecipeCard {
  readonly recipe = input.required<RecipeCardData>();
}