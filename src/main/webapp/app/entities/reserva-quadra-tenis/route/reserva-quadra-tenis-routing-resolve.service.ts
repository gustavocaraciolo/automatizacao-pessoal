import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IReservaQuadraTenis, ReservaQuadraTenis } from '../reserva-quadra-tenis.model';
import { ReservaQuadraTenisService } from '../service/reserva-quadra-tenis.service';

@Injectable({ providedIn: 'root' })
export class ReservaQuadraTenisRoutingResolveService implements Resolve<IReservaQuadraTenis> {
  constructor(protected service: ReservaQuadraTenisService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IReservaQuadraTenis> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((reservaQuadraTenis: HttpResponse<ReservaQuadraTenis>) => {
          if (reservaQuadraTenis.body) {
            return of(reservaQuadraTenis.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ReservaQuadraTenis());
  }
}
