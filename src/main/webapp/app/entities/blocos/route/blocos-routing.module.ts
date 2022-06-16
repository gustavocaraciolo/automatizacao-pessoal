import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { BlocosComponent } from '../list/blocos.component';
import { BlocosDetailComponent } from '../detail/blocos-detail.component';
import { BlocosUpdateComponent } from '../update/blocos-update.component';
import { BlocosRoutingResolveService } from './blocos-routing-resolve.service';

const blocosRoute: Routes = [
  {
    path: '',
    component: BlocosComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: BlocosDetailComponent,
    resolve: {
      blocos: BlocosRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: BlocosUpdateComponent,
    resolve: {
      blocos: BlocosRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: BlocosUpdateComponent,
    resolve: {
      blocos: BlocosRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(blocosRoute)],
  exports: [RouterModule],
})
export class BlocosRoutingModule {}
