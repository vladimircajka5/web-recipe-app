import { HttpClient, httpResource } from '@angular/common/http';
import { Inject, Injectable, inject, type Signal } from '@angular/core';
import { Observable } from 'rxjs';

import { environment } from '../../env/environment';
import type { ClassificationData, ClassificationGroup, ClassificationQuery, CreateClassificationData, PageResponse} from './classification.model';

@Injectable({ providedIn: 'root'})
export class ClassificationService{
    private readonly http = inject(HttpClient);

    loadPage(
        group: Signal<ClassificationGroup>,
        query: Signal<ClassificationQuery>
    ){
        return httpResource<PageResponse<ClassificationData>>(() =>({
            url: `${environment.apiHost}/api/admin/${group()}`,
            params: {
                search: query().search,
                page: query().page,
                size: query().size,
                sortBy: query().sortBy,
                sortDirection: query().sortDirection,
            },
        }));
    }

    create(
        group: ClassificationGroup,
        data: CreateClassificationData
    ): Observable<ClassificationData> {
        return this.http.post<ClassificationData>(
            `${environment.apiHost}/api/${group}`,
            data
        );
    }

    update(
        group: ClassificationGroup,
        id: number,
        data: CreateClassificationData
    ): Observable<ClassificationData> {
        return this.http.put<ClassificationData>(
        `${environment.apiHost}/api/${group}/${id}`,
        data
        );
    }

    remove(
        group: ClassificationGroup,
        id: number
    ): Observable<void> {
        return this.http.delete<void>(
        `${environment.apiHost}/api/${group}/${id}`
        );
    }
}