import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IAtividade, getAtividadeIdentifier } from '../atividade.model';

export type EntityResponseType = HttpResponse<IAtividade>;
export type EntityArrayResponseType = HttpResponse<IAtividade[]>;

@Injectable({ providedIn: 'root' })
export class AtividadeService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/atividades');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(atividade: IAtividade): Observable<EntityResponseType> {
    return this.http.post<IAtividade>(this.resourceUrl, atividade, { observe: 'response' });
  }

  update(atividade: IAtividade): Observable<EntityResponseType> {
    return this.http.put<IAtividade>(`${this.resourceUrl}/${getAtividadeIdentifier(atividade) as number}`, atividade, {
      observe: 'response',
    });
  }

  partialUpdate(atividade: IAtividade): Observable<EntityResponseType> {
    return this.http.patch<IAtividade>(`${this.resourceUrl}/${getAtividadeIdentifier(atividade) as number}`, atividade, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IAtividade>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IAtividade[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addAtividadeToCollectionIfMissing(
    atividadeCollection: IAtividade[],
    ...atividadesToCheck: (IAtividade | null | undefined)[]
  ): IAtividade[] {
    const atividades: IAtividade[] = atividadesToCheck.filter(isPresent);
    if (atividades.length > 0) {
      const atividadeCollectionIdentifiers = atividadeCollection.map(atividadeItem => getAtividadeIdentifier(atividadeItem)!);
      const atividadesToAdd = atividades.filter(atividadeItem => {
        const atividadeIdentifier = getAtividadeIdentifier(atividadeItem);
        if (atividadeIdentifier == null || atividadeCollectionIdentifiers.includes(atividadeIdentifier)) {
          return false;
        }
        atividadeCollectionIdentifiers.push(atividadeIdentifier);
        return true;
      });
      return [...atividadesToAdd, ...atividadeCollection];
    }
    return atividadeCollection;
  }
}
