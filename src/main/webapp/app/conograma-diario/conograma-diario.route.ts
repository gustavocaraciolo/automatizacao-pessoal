import { Route } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ConogramaDiarioComponent } from './conograma-diario.component';
import { CronogramaDiarioRoutingResolveService } from './cronograma-diario-routing-resolve.service';

export const CONOGRAMA_DIARIO_ROUTE: Route = {
  path: 'conograma-diario',
  component: ConogramaDiarioComponent,
  data: {
    authorities: [],
    pageTitle: 'conograma-diario.title',
  },
  resolve: {
      conogramaDiario: CronogramaDiarioRoutingResolveService,
    },
  canActivate: [UserRouteAccessService],
};
