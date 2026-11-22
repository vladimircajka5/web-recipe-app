export interface RecipeCardData {
  id: number;
  name: string;
  authorName: string;
  mealType: string;
  dishType: string;
  dietaryType: string;
  allergens: readonly string[];
  servings: number;
  preparationTimeMinutes: number;
  mainPhotoUrl: string | null;
  averageRating: number | null;
}

export interface RecipeDetailData extends RecipeCardData {
  description: string;
  ingredients: readonly string[];
  steps: readonly string[];
}