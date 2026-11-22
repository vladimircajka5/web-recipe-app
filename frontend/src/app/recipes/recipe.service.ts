import { Service } from '@angular/core';
import { RecipeCardData } from './recipe.model';

@Service()
export class RecipeService {
  readonly recipes: readonly RecipeCardData[] = [
    {
      id: 1,
      name: 'Banana Pancakes',
      authorName: 'demo_chef',
      mealType: 'Breakfast',
      dishType: 'Dessert',
      dietaryType: 'Vegetarian',
      allergens: ['Wheat', 'Eggs', 'Milk'],
      servings: 4,
      preparationTimeMinutes: 20,
      mainPhotoUrl: 'recipe_photos/banana_pancakes.jpg',
      averageRating: 4.5,
    },
    {
      id: 2,
      name: 'Vegetable Pasta',
      authorName: 'demo_chef',
      mealType: 'Dinner',
      dishType: 'Main Course',
      dietaryType: 'Vegetarian',
      allergens: ['Wheat'],
      servings: 2,
      preparationTimeMinutes: 25,
      mainPhotoUrl: 'recipe_photos/vegetable_pasta.jpg',
      averageRating: null,
    },
    
    {
      id: 3,
      name: 'Tomato Soup',
      authorName: 'demo_chef',
      mealType: 'Lunch',
      dishType: 'Soup',
      dietaryType: 'Vegan',
      allergens: [],
      servings: 4,
      preparationTimeMinutes: 30,
      mainPhotoUrl: 'recipe_photos/tomato_soup.jpg',
      averageRating: null,
    },
    {
      id: 4,
      name: 'Chicken and Rice',
      authorName: 'demo_chef',
      mealType: 'Lunch',
      dishType: 'Main Course',
      dietaryType: 'Regular',
      allergens: [],
      servings: 3,
      preparationTimeMinutes: 45,
      mainPhotoUrl: 'recipe_photos/chicken_rice.jpg',
      averageRating: null,
    },
    {
      id: 5,
      name: 'Fruit Salad',
      authorName: 'demo_chef',
      mealType: 'Snack',
      dishType: 'Salad',
      dietaryType: 'Vegan',
      allergens: [],
      servings: 2,
      preparationTimeMinutes: 10,
      mainPhotoUrl: 'recipe_photos/fruit_salad.jpg',
      averageRating: null,
    },
  ];
}