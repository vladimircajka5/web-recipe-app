import { Service, inject } from '@angular/core';
import { RecipeService } from '../recipes/recipe.service';
import { UserProfileData } from './user.model';

@Service()
export class UserService {

  private readonly recipeService = inject(RecipeService);

  private readonly users: readonly UserProfileData[] = [
    {
      id: 1,
      username: 'demo_chef',
      profilePictureUrl: null,
      region: 'Novi Sad',
      biography: 'Demo profile used for testing the Recipes application.',
      publishedRecipeCount: 0,
    },
    {
      id: 2,
      username: 'second_chef',
      profilePictureUrl: null,
      region: 'Belgrade',
      biography: 'Another demo user for testing public profiles.',
      publishedRecipeCount: 0,
    },
  ];

  getById(id: number): UserProfileData | null {

    for (const user of this.users) {

      if (user.id === id) {

        let count = 0;

        for (const recipe of this.recipeService.recipes) {
          if (recipe.authorName === user.username) {
            count++;
          }
        }

        return {
          ...user,
          publishedRecipeCount: count,
        };
      }
    }

    return null;
  }
}