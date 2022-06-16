import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { BlocosComponent } from './list/blocos.component';
import { BlocosDetailComponent } from './detail/blocos-detail.component';
import { BlocosUpdateComponent } from './update/blocos-update.component';
import { BlocosDeleteDialogComponent } from './delete/blocos-delete-dialog.component';
import { BlocosRoutingModule } from './route/blocos-routing.module';

@NgModule({
  imports: [SharedModule, BlocosRoutingModule],
  declarations: [BlocosComponent, BlocosDetailComponent, BlocosUpdateComponent, BlocosDeleteDialogComponent],
  entryComponents: [BlocosDeleteDialogComponent],
})
export class BlocosModule {}
