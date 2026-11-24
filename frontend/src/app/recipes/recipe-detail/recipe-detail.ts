import { HttpErrorResponse, httpResource } from '@angular/common/http';
import { Component, computed, inject, input } from '@angular/core';
import { RouterLink } from '@angular/router';
import { environment } from '../../../env/environment';
import type { RecipeDto } from '../recipe.model';
import { RecipeService } from '../recipe.service';

@Component({
  selector: 'app-recipe-detail',
  imports: [RouterLink],
  templateUrl: './recipe-detail.html',
  styleUrl: './recipe-detail.css',
})
export class RecipeDetail {
  private readonly recipeService = inject(RecipeService);

  readonly id = input.required<string>();

  private readonly validId = computed(() => /^[1-9]\d*$/.test(this.id()));

  protected readonly recipeRequest = httpResource<RecipeDto>(() =>
    this.validId() ? environment.apiHost + '/api/recipes/' + this.id() : undefined,
  );

  protected readonly notFound = computed(() => {
    const error = this.recipeRequest.error();
    return !this.validId() || (error instanceof HttpErrorResponse && error.status === 404);
  });

  protected readonly recipe = computed(() =>
    this.recipeRequest.hasValue()
      ? this.recipeService.toRecipeDetail(this.recipeRequest.value())
      : undefined,
  );
}