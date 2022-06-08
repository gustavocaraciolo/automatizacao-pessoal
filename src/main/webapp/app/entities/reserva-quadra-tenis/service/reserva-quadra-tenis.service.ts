import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IReservaQuadraTenis, getReservaQuadraTenisIdentifier } from '../reserva-quadra-tenis.model';

export type EntityResponseType = HttpResponse<IReservaQuadraTenis>;
export type EntityArrayResponseType = HttpResponse<IReservaQuadraTenis[]>;

@Injectable({ providedIn: 'root' })
export class ReservaQuadraTenisService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/reserva-quadra-tenis');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(reservaQuadraTenis: IReservaQuadraTenis): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(reservaQuadraTenis);
    return this.http
      .post<IReservaQuadraTenis>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(reservaQuadraTenis: IReservaQuadraTenis): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(reservaQuadraTenis);
    return this.http
      .put<IReservaQuadraTenis>(`${this.resourceUrl}/${getReservaQuadraTenisIdentifier(reservaQuadraTenis) as number}`, copy, {
        observe: 'response',
      })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  partialUpdate(reservaQuadraTenis: IReservaQuadraTenis): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(reservaQuadraTenis);
    return this.http
      .patch<IReservaQuadraTenis>(`${this.resourceUrl}/${getReservaQuadraTenisIdentifier(reservaQuadraTenis) as number}`, copy, {
        observe: 'response',
      })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IReservaQuadraTenis>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IReservaQuadraTenis[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addReservaQuadraTenisToCollectionIfMissing(
    reservaQuadraTenisCollection: IReservaQuadraTenis[],
    ...reservaQuadraTenisToCheck: (IReservaQuadraTenis | null | undefined)[]
  ): IReservaQuadraTenis[] {
    const reservaQuadraTenis: IReservaQuadraTenis[] = reservaQuadraTenisToCheck.filter(isPresent);
    if (reservaQuadraTenis.length > 0) {
      const reservaQuadraTenisCollectionIdentifiers = reservaQuadraTenisCollection.map(
        reservaQuadraTenisItem => getReservaQuadraTenisIdentifier(reservaQuadraTenisItem)!
      );
      const reservaQuadraTenisToAdd = reservaQuadraTenis.filter(reservaQuadraTenisItem => {
        const reservaQuadraTenisIdentifier = getReservaQuadraTenisIdentifier(reservaQuadraTenisItem);
        if (reservaQuadraTenisIdentifier == null || reservaQuadraTenisCollectionIdentifiers.includes(reservaQuadraTenisIdentifier)) {
          return false;
        }
        reservaQuadraTenisCollectionIdentifiers.push(reservaQuadraTenisIdentifier);
        return true;
      });
      return [...reservaQuadraTenisToAdd, ...reservaQuadraTenisCollection];
    }
    return reservaQuadraTenisCollection;
  }

  protected convertDateFromClient(reservaQuadraTenis: IReservaQuadraTenis): IReservaQuadraTenis {
    return Object.assign({}, reservaQuadraTenis, {
      semana: reservaQuadraTenis.semana?.isValid() ? reservaQuadraTenis.semana.format(DATE_FORMAT) : undefined,
      segundafeira: reservaQuadraTenis.segundafeira?.isValid() ? reservaQuadraTenis.segundafeira.toJSON() : undefined,
      tercafeira: reservaQuadraTenis.tercafeira?.isValid() ? reservaQuadraTenis.tercafeira.toJSON() : undefined,
      quartafeira: reservaQuadraTenis.quartafeira?.isValid() ? reservaQuadraTenis.quartafeira.toJSON() : undefined,
      quintafeira: reservaQuadraTenis.quintafeira?.isValid() ? reservaQuadraTenis.quintafeira.toJSON() : undefined,
      sextafeira: reservaQuadraTenis.sextafeira?.isValid() ? reservaQuadraTenis.sextafeira.toJSON() : undefined,
      sabado: reservaQuadraTenis.sabado?.isValid() ? reservaQuadraTenis.sabado.toJSON() : undefined,
      domingo: reservaQuadraTenis.domingo?.isValid() ? reservaQuadraTenis.domingo.toJSON() : undefined,
    });
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.semana = res.body.semana ? dayjs(res.body.semana) : undefined;
      res.body.segundafeira = res.body.segundafeira ? dayjs(res.body.segundafeira) : undefined;
      res.body.tercafeira = res.body.tercafeira ? dayjs(res.body.tercafeira) : undefined;
      res.body.quartafeira = res.body.quartafeira ? dayjs(res.body.quartafeira) : undefined;
      res.body.quintafeira = res.body.quintafeira ? dayjs(res.body.quintafeira) : undefined;
      res.body.sextafeira = res.body.sextafeira ? dayjs(res.body.sextafeira) : undefined;
      res.body.sabado = res.body.sabado ? dayjs(res.body.sabado) : undefined;
      res.body.domingo = res.body.domingo ? dayjs(res.body.domingo) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((reservaQuadraTenis: IReservaQuadraTenis) => {
        reservaQuadraTenis.semana = reservaQuadraTenis.semana ? dayjs(reservaQuadraTenis.semana) : undefined;
        reservaQuadraTenis.segundafeira = reservaQuadraTenis.segundafeira ? dayjs(reservaQuadraTenis.segundafeira) : undefined;
        reservaQuadraTenis.tercafeira = reservaQuadraTenis.tercafeira ? dayjs(reservaQuadraTenis.tercafeira) : undefined;
        reservaQuadraTenis.quartafeira = reservaQuadraTenis.quartafeira ? dayjs(reservaQuadraTenis.quartafeira) : undefined;
        reservaQuadraTenis.quintafeira = reservaQuadraTenis.quintafeira ? dayjs(reservaQuadraTenis.quintafeira) : undefined;
        reservaQuadraTenis.sextafeira = reservaQuadraTenis.sextafeira ? dayjs(reservaQuadraTenis.sextafeira) : undefined;
        reservaQuadraTenis.sabado = reservaQuadraTenis.sabado ? dayjs(reservaQuadraTenis.sabado) : undefined;
        reservaQuadraTenis.domingo = reservaQuadraTenis.domingo ? dayjs(reservaQuadraTenis.domingo) : undefined;
      });
    }
    return res;
  }
}
