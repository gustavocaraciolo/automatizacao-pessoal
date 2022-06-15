import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ICronogramaDiario, CronogramaDiario } from '../cronograma-diario.model';
import { CronogramaDiarioService } from '../service/cronograma-diario.service';

@Injectable({ providedIn: 'root' })
export class CronogramaDiarioRoutingResolveService implements Resolve<ICronogramaDiario> {
  constructor(protected service: CronogramaDiarioService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICronogramaDiario> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((cronogramaDiario: HttpResponse<CronogramaDiario>) => {
          if (cronogramaDiario.body) {
            return of(cronogramaDiario.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new CronogramaDiario());
  }
}
