import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ICronogramaDiario, getCronogramaDiarioIdentifier } from '../cronograma-diario.model';

export type EntityResponseType = HttpResponse<ICronogramaDiario>;
export type EntityArrayResponseType = HttpResponse<ICronogramaDiario[]>;

@Injectable({ providedIn: 'root' })
export class CronogramaDiarioService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/cronograma-diarios');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(cronogramaDiario: ICronogramaDiario): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(cronogramaDiario);
    return this.http
      .post<ICronogramaDiario>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(cronogramaDiario: ICronogramaDiario): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(cronogramaDiario);
    return this.http
      .put<ICronogramaDiario>(`${this.resourceUrl}/${getCronogramaDiarioIdentifier(cronogramaDiario) as number}`, copy, {
        observe: 'response',
      })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  partialUpdate(cronogramaDiario: ICronogramaDiario): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(cronogramaDiario);
    return this.http
      .patch<ICronogramaDiario>(`${this.resourceUrl}/${getCronogramaDiarioIdentifier(cronogramaDiario) as number}`, copy, {
        observe: 'response',
      })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ICronogramaDiario>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ICronogramaDiario[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addCronogramaDiarioToCollectionIfMissing(
    cronogramaDiarioCollection: ICronogramaDiario[],
    ...cronogramaDiariosToCheck: (ICronogramaDiario | null | undefined)[]
  ): ICronogramaDiario[] {
    const cronogramaDiarios: ICronogramaDiario[] = cronogramaDiariosToCheck.filter(isPresent);
    if (cronogramaDiarios.length > 0) {
      const cronogramaDiarioCollectionIdentifiers = cronogramaDiarioCollection.map(
        cronogramaDiarioItem => getCronogramaDiarioIdentifier(cronogramaDiarioItem)!
      );
      const cronogramaDiariosToAdd = cronogramaDiarios.filter(cronogramaDiarioItem => {
        const cronogramaDiarioIdentifier = getCronogramaDiarioIdentifier(cronogramaDiarioItem);
        if (cronogramaDiarioIdentifier == null || cronogramaDiarioCollectionIdentifiers.includes(cronogramaDiarioIdentifier)) {
          return false;
        }
        cronogramaDiarioCollectionIdentifiers.push(cronogramaDiarioIdentifier);
        return true;
      });
      return [...cronogramaDiariosToAdd, ...cronogramaDiarioCollection];
    }
    return cronogramaDiarioCollection;
  }

  protected convertDateFromClient(cronogramaDiario: ICronogramaDiario): ICronogramaDiario {
    return Object.assign({}, cronogramaDiario, {
      dia: cronogramaDiario.dia?.isValid() ? cronogramaDiario.dia.format(DATE_FORMAT) : undefined,
    });
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dia = res.body.dia ? dayjs(res.body.dia) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((cronogramaDiario: ICronogramaDiario) => {
        cronogramaDiario.dia = cronogramaDiario.dia ? dayjs(cronogramaDiario.dia) : undefined;
      });
    }
    return res;
  }
}
