import { Component, computed, inject, signal } from '@angular/core';
import { RecipeService } from '../recipes/recipe.service';
import { RecipeCard } from '../shared/recipe-card/recipe-card';

@Component({
  selector: 'app-home',
  imports: [RecipeCard],
  styleUrl: './home.css',
  templateUrl: './home.html',
})
export class Home {
  private readonly sampleRecipes = inject(RecipeService).recipes;

  protected readonly feed = signal<'popular' | 'newest'>('popular');

  protected readonly recipes = computed(() => {
    if (this.feed() === 'newest') {
      return [...this.sampleRecipes].sort((first, second) =>
        second.creationDate.localeCompare(first.creationDate),
      );
    }

    return this.sampleRecipes;
  });
}