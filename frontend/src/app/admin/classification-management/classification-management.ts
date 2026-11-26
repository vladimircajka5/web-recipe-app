import { HttpErrorResponse } from '@angular/common/http';
import {Component,DestroyRef,ElementRef,computed,inject,signal,viewChild,} from '@angular/core';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import {FormField,disabled,form,maxLength,required,validate,} from '@angular/forms/signals';
import { RouterLink } from '@angular/router';
import { finalize } from 'rxjs';

import type {ClassificationData,ClassificationGroup,ClassificationQuery,CreateClassificationData,} from '../classification.model';
import { ClassificationService } from '../classification.service';

type ConfirmationAction = {
  kind: 'update' | 'delete';
  id: number;
  name: string;
};

@Component({
  selector: 'app-classification-management',
  imports: [RouterLink, FormField],
  templateUrl: './classification-management.html',
  styleUrl: './classification-management.css',
})
export class ClassificationManagement {

  private readonly classificationService = inject(ClassificationService);
  private readonly destroyRef = inject(DestroyRef);

  private readonly confirmationDialog =viewChild.required<ElementRef<HTMLDialogElement>>('confirmationDialog');

  private readonly nameInput = viewChild.required<ElementRef<HTMLInputElement>>('nameInput');

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

  protected readonly pending = signal(false);
  protected readonly saveError = signal<string | null>(null);
  protected readonly actionError = signal<string | null>(null);
  protected readonly message = signal<string | null>(null);

  protected readonly editingItem = signal<ClassificationData | null>(null);
  protected readonly confirmation = signal<ConfirmationAction | null>(null);

  private readonly editorModel = signal<CreateClassificationData>({
    name: '',
  });

  protected readonly editorForm = form(this.editorModel, (path) => {
    required(path.name, { message: 'Name is required.' });

    maxLength(path.name, 255, {
      message: 'Name must not exceed 255 characters.',
    });

    validate(path.name, ({ value }) => {
      if (value().length > 0 && value().trim().length === 0) {
        return {
          kind: 'blank',
          message: 'Name cannot contain only spaces.',
        };
      }

      return null;
    });

    disabled(path.name, () => this.pending());
  });

  protected readonly query = signal<ClassificationQuery>({
    search: '',
    page: 0,
    size: 10,
    sortBy: 'name',
    sortDirection: 'asc',
  });

  protected readonly request = this.classificationService.loadPage(this.group,this.query);

  protected readonly result = computed(() => {
    return this.request.hasValue() ? this.request.value() : null;
  });

  protected changeGroup(value: string): void {
    if (this.pending()) {
      return;
    }

    const selected = this.groups.find((item) => item.value === value);

    if (!selected) {
      return;
    }

    this.group.set(selected.value);
    this.searchText.set('');
    this.query.update((query) => ({ ...query, search: '', page: 0 }));
    this.resetEditor();
    this.clearMessages();
  }

  protected applySearch(event: Event): void {
    event.preventDefault();

    if (this.pending()) {
      return;
    }

    this.query.update((query) => ({
      ...query,
      search: this.searchText().trim(),
      page: 0,
    }));
  }

  protected changeSize(value: string): void {
    const size = Number(value);

    if (this.pending() || !this.pageSizes.includes(size)) {
      return;
    }

    this.query.update((query) => ({ ...query, size, page: 0 }));
  }

  protected sortBy(column: ClassificationQuery['sortBy']): void {
    if (this.pending()) {
      return;
    }

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

    if (!result || this.pending() || this.request.isLoading() || page < 0 || page >= result.totalPages) {
      return;
    }

    this.query.update((query) => ({ ...query, page }));
  }

  protected startEdit(item: ClassificationData): void {
    if (this.pending() || this.request.isLoading()) {
      return;
    }

    this.clearMessages();
    this.editingItem.set({ ...item });
    this.editorForm().reset({ name: item.name });
    this.nameInput().nativeElement.focus();
  }

  protected cancelEdit(): void {
    if (this.pending()) {
      return;
    }

    this.resetEditor();
    this.clearMessages();
  }

  protected submitEditor(event: Event): void {
    event.preventDefault();

    if (this.pending() || this.request.isLoading()) {
      return;
    }

    this.editorForm.name().markAsTouched();

    if (!this.editorForm().valid()) {
      return;
    }

    const data: CreateClassificationData = {
      name: this.editorModel().name.trim(),
    };

    const editing = this.editingItem();

    if (editing) {
      this.openConfirmation({
        kind: 'update',
        id: editing.id,
        name: data.name,
      });
      return;
    }

    this.saveItem(data, null);
  }

  protected requestDelete(item: ClassificationData): void {
    if (this.pending() || this.request.isLoading()) {
      return;
    }

    this.openConfirmation({
      kind: 'delete',
      id: item.id,
      name: item.name,
    });
  }

  protected cancelConfirmation(): void {
    this.confirmationDialog().nativeElement.close();
    this.confirmation.set(null);
  }

  protected confirmAction(): void {
    const action = this.confirmation();

    if (!action || this.pending()) {
      return;
    }

    this.cancelConfirmation();

    if (action.kind === 'update') {
      this.saveItem({ name: action.name }, action.id);
    } else {
      this.deleteItem(action.id, action.name);
    }
  }

  private openConfirmation(action: ConfirmationAction): void {
    this.confirmation.set(action);
    this.confirmationDialog().nativeElement.showModal();
  }

  private saveItem(
    data: CreateClassificationData,
    id: number | null
  ): void {
    this.clearMessages();
    this.pending.set(true);

    const operation = id === null
      ? this.classificationService.create(this.group(), data)
      : this.classificationService.update(this.group(), id, data);

    operation
      .pipe(
        takeUntilDestroyed(this.destroyRef),
        finalize(() => this.pending.set(false))
      )
      .subscribe({
        next: (saved) => {
          this.resetEditor();
          this.message.set(
            id === null
              ? `"${saved.name}" was added successfully.`
              : `"${saved.name}" was updated successfully.`
          );
          this.refreshList();
        },
        error: (error: HttpErrorResponse) => {
          this.saveError.set(
            this.errorMessage(error, 'Could not save the item.')
          );

          if (error.status === 404) {
            this.refreshList();
          }
        },
      });
  }

  private deleteItem(id: number, name: string): void {
    this.clearMessages();
    this.pending.set(true);

    this.classificationService.remove(this.group(), id)
      .pipe(
        takeUntilDestroyed(this.destroyRef),
        finalize(() => this.pending.set(false))
      )
      .subscribe({
        next: () => {
          if (this.editingItem()?.id === id) {
            this.resetEditor();
          }

          this.message.set(`"${name}" was deleted successfully.`);
          this.refreshList();
        },
        error: (error: HttpErrorResponse) => {
          this.actionError.set(
            this.errorMessage(error, 'Could not delete the item.')
          );

          if (error.status === 404) {
            this.refreshList();
          }
        },
      });
  }

  private refreshList(): void {
    if (this.query().page === 0) {
      this.request.reload();
    } else {
      this.query.update((query) => ({ ...query, page: 0 }));
    }
  }

  private resetEditor(): void {
    this.editingItem.set(null);
    this.editorForm().reset({ name: '' });
    this.saveError.set(null);
  }

  private clearMessages(): void {
    this.saveError.set(null);
    this.actionError.set(null);
    this.message.set(null);
  }

  private errorMessage(
    error: HttpErrorResponse,
    fallback: string
  ): string {
    return typeof error.error?.message === 'string'
      ? error.error.message
      : fallback;
  }
}