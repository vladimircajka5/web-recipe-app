import { Component, computed, inject, signal } from '@angular/core';
import type { RecipeFeed } from '../recipes/recipe.model';
import { RecipeService } from '../recipes/recipe.service';
import { RecipeCard } from '../shared/recipe-card/recipe-card';

@Component({
  selector: 'app-home',
  imports: [RecipeCard],
  styleUrl: './home.css',
  templateUrl: './home.html',
})
export class Home {
  private readonly recipeService = inject(RecipeService);

  protected readonly feed = signal<RecipeFeed>('popular');
  protected readonly recipesRequest = this.recipeService.loadFeed(this.feed);

  protected readonly recipes = computed(() => {
    if (!this.recipesRequest.hasValue()) {
      return [];
    }

    return this.recipesRequest.value().map((dto) => this.recipeService.toRecipeDetail(dto));
  });
}