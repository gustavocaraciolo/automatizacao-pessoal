import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IAtividade, Atividade } from '../atividade.model';
import { AtividadeService } from '../service/atividade.service';

@Injectable({ providedIn: 'root' })
export class AtividadeRoutingResolveService implements Resolve<IAtividade> {
  constructor(protected service: AtividadeService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IAtividade> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((atividade: HttpResponse<Atividade>) => {
          if (atividade.body) {
            return of(atividade.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Atividade());
  }
}
