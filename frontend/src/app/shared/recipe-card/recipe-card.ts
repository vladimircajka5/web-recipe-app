import { Component, input } from '@angular/core';
import { RouterLink } from '@angular/router';
import { RecipeCardData } from '../../recipes/recipe.model';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-recipe-card',
  imports: [DatePipe, RouterLink],
  templateUrl: './recipe-card.html',
  styleUrl: './recipe-card.css',
})
export class RecipeCard {
  readonly recipe = input.required<RecipeCardData>();
  readonly publishedAt = input<string | null>(null);
}