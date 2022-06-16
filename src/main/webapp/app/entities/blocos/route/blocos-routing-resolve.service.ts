import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IBlocos, Blocos } from '../blocos.model';
import { BlocosService } from '../service/blocos.service';

@Injectable({ providedIn: 'root' })
export class BlocosRoutingResolveService implements Resolve<IBlocos> {
  constructor(protected service: BlocosService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IBlocos> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((blocos: HttpResponse<Blocos>) => {
          if (blocos.body) {
            return of(blocos.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Blocos());
  }
}
