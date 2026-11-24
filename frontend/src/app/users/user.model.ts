export interface UserProfileData {
  id: number;
  username: string;
  profilePictureUrl: string | null;
  region: string;
  biography: string | null;
  publishedRecipeCount: number;
}

export interface UpdateUserProfileData {
  profilePictureUrl: string | null;
  region: string;
  biography: string | null;
}
