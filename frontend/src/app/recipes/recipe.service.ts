import { httpResource } from '@angular/common/http';
import { Injectable, type Signal } from '@angular/core';
import { environment } from '../../env/environment';
import type { RecipeDetailData, RecipeDto, RecipeFeed } from './recipe.model';

@Injectable({ providedIn: 'root' })
export class RecipeService {
  loadFeed(feed: Signal<RecipeFeed>) {
    return httpResource<RecipeDto[]>(() => ({
      url: environment.apiHost + '/api/recipes',
      params: { sort: feed() },
    }));
  }

  toRecipeDetail(dto: RecipeDto): RecipeDetailData {
    return {
      id: dto.id,
      name: dto.name,
      authorName: dto.authorUsername,
      mealType: dto.mealTypeName,
      dishType: dto.dishTypeName,
      dietaryType: dto.dietaryTypeName,
      servings: dto.servings,
      preparationTimeMinutes: dto.preparationTime,
      mainPhotoUrl: dto.mainPhotoUrl,
      averageRating: dto.averageRating,
      creationDate: dto.creationDate,
      description: dto.description,
      allergens: null,
      ingredients: [],
      steps: [],
    };
  }
}