export type ClassificationGroup =
| 'meal-types'
| 'dish-types'
| 'dietary-types'
| 'ingredient-categories';

export interface ClassificationData {
  id: number;
  name: string;
}

export interface CreateClassificationData {
    name: string;
}

export interface ClassificationQuery {
  search: string;
  page: number;
  size: number;
  sortBy: 'id' | 'name';
  sortDirection: 'asc' | 'desc';
}

export interface PageResponse<T> {
  content: T[];
  page: number;
  size: number;
  totalElements: number;
  totalPages: number;
}