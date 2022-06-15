import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { AtividadeComponent } from '../list/atividade.component';
import { AtividadeDetailComponent } from '../detail/atividade-detail.component';
import { AtividadeUpdateComponent } from '../update/atividade-update.component';
import { AtividadeRoutingResolveService } from './atividade-routing-resolve.service';

const atividadeRoute: Routes = [
  {
    path: '',
    component: AtividadeComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: AtividadeDetailComponent,
    resolve: {
      atividade: AtividadeRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: AtividadeUpdateComponent,
    resolve: {
      atividade: AtividadeRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: AtividadeUpdateComponent,
    resolve: {
      atividade: AtividadeRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(atividadeRoute)],
  exports: [RouterModule],
})
export class AtividadeRoutingModule {}
