export type RecipeFeed = 'popular' | 'newest';

export interface RecipeDto {
  id: number;
  name: string;
  description: string;
  authorId: number;
  authorUsername: string;
  mealTypeId: number;
  mealTypeName: string;
  dishTypeId: number;
  dishTypeName: string;
  dietaryTypeId: number;
  dietaryTypeName: string;
  servings: number;
  preparationTime: number;
  mainPhotoUrl: string | null;
  creationDate: string;
  originStory: string | null;
  averageRating: number | null;
}

export interface RecipeCardData {
  id: number;
  name: string;
  authorName: string;
  mealType: string;
  dishType: string;
  dietaryType: string;
  allergens: readonly string[] | null;
  servings: number;
  preparationTimeMinutes: number;
  mainPhotoUrl: string | null;
  averageRating: number | null;
}

export interface RecipeDetailData extends RecipeCardData {
  creationDate: string;
  description: string;
  ingredients: readonly string[];
  steps: readonly string[];
}