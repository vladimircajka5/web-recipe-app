import { Component, computed, inject, signal } from '@angular/core';
import { RouterLink } from '@angular/router';

import type {
  ClassificationGroup,
  ClassificationQuery,
} from '../classification.model';
import { ClassificationService } from '../classification.service';

@Component({
  selector: 'app-classification-management',
  imports: [RouterLink],
  templateUrl: './classification-management.html',
  styleUrl: './classification-management.css',
})
export class ClassificationManagement {

  private readonly classificationService = inject(ClassificationService);

  protected readonly groups: {
    value: ClassificationGroup;
    label: string;
  }[] = [
    { value: 'meal-types', label: 'Meal types' },
    { value: 'dish-types', label: 'Dish types' },
    { value: 'dietary-types', label: 'Dietary types' },
    { value: 'ingredient-categories', label: 'Ingredient categories' },
  ];

  protected readonly pageSizes = [5, 10, 20, 50];

  protected readonly group = signal<ClassificationGroup>('meal-types');
  protected readonly searchText = signal('');

  protected readonly query = signal<ClassificationQuery>({
    search: '',
    page: 0,
    size: 10,
    sortBy: 'name',
    sortDirection: 'asc',
  });

  protected readonly request = this.classificationService.loadPage(
    this.group,
    this.query
  );

  protected readonly result = computed(() => {
    return this.request.hasValue() ? this.request.value() : null;
  });

  protected changeGroup(value: string): void {
    const selected = this.groups.find((item) => item.value === value);

    if (!selected) {
      return;
    }

    this.group.set(selected.value);
    this.searchText.set('');
    this.query.update((query) => ({ ...query, search: '', page: 0 }));
  }

  protected applySearch(event: Event): void {
    event.preventDefault();

    this.query.update((query) => ({
      ...query,
      search: this.searchText().trim(),
      page: 0,
    }));
  }

  protected changeSize(value: string): void {
    const size = Number(value);

    if (!this.pageSizes.includes(size)) {
      return;
    }

    this.query.update((query) => ({ ...query, size, page: 0 }));
  }

  protected sortBy(column: ClassificationQuery['sortBy']): void {
    this.query.update((query) => ({
      ...query,
      sortBy: column,
      sortDirection:
        query.sortBy === column && query.sortDirection === 'asc'
          ? 'desc'
          : 'asc',
      page: 0,
    }));
  }

  protected sortState(
    column: ClassificationQuery['sortBy']
  ): 'none' | 'ascending' | 'descending' {
    const query = this.query();

    if (query.sortBy !== column) {
      return 'none';
    }

    return query.sortDirection === 'asc' ? 'ascending' : 'descending';
  }

  protected changePage(page: number): void {
    const result = this.result();

    if (
      !result ||
      this.request.isLoading() ||
      page < 0 ||
      page >= result.totalPages
    ) {
      return;
    }

    this.query.update((query) => ({ ...query, page }));
  }
}