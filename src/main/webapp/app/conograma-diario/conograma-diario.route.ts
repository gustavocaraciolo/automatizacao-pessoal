import { Route } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ConogramaDiarioComponent } from './conograma-diario.component';

export const CONOGRAMA_DIARIO_ROUTE: Route = {
  path: 'conograma-diario',
  component: ConogramaDiarioComponent,
  data: {
    authorities: [],
    pageTitle: 'conograma-diario.title',
  },
  canActivate: [UserRouteAccessService],
};
