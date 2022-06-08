import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ReservaQuadraTenisComponent } from '../list/reserva-quadra-tenis.component';
import { ReservaQuadraTenisDetailComponent } from '../detail/reserva-quadra-tenis-detail.component';
import { ReservaQuadraTenisUpdateComponent } from '../update/reserva-quadra-tenis-update.component';
import { ReservaQuadraTenisRoutingResolveService } from './reserva-quadra-tenis-routing-resolve.service';

const reservaQuadraTenisRoute: Routes = [
  {
    path: '',
    component: ReservaQuadraTenisComponent,
    data: {
      defaultSort: 'id,asc',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ReservaQuadraTenisDetailComponent,
    resolve: {
      reservaQuadraTenis: ReservaQuadraTenisRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ReservaQuadraTenisUpdateComponent,
    resolve: {
      reservaQuadraTenis: ReservaQuadraTenisRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ReservaQuadraTenisUpdateComponent,
    resolve: {
      reservaQuadraTenis: ReservaQuadraTenisRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(reservaQuadraTenisRoute)],
  exports: [RouterModule],
})
export class ReservaQuadraTenisRoutingModule {}
