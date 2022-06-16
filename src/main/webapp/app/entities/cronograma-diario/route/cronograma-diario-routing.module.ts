import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { CronogramaDiarioComponent } from '../list/cronograma-diario.component';
import { CronogramaDiarioDetailComponent } from '../detail/cronograma-diario-detail.component';
import { CronogramaDiarioUpdateComponent } from '../update/cronograma-diario-update.component';
import { CronogramaDiarioRoutingResolveService } from './cronograma-diario-routing-resolve.service';

const cronogramaDiarioRoute: Routes = [
  {
    path: '',
    component: CronogramaDiarioComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CronogramaDiarioDetailComponent,
    resolve: {
      cronogramaDiario: CronogramaDiarioRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CronogramaDiarioUpdateComponent,
    resolve: {
      cronogramaDiario: CronogramaDiarioRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: CronogramaDiarioUpdateComponent,
    resolve: {
      cronogramaDiario: CronogramaDiarioRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(cronogramaDiarioRoute)],
  exports: [RouterModule],
})
export class CronogramaDiarioRoutingModule {}
