import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { ReservaQuadraTenisComponent } from './list/reserva-quadra-tenis.component';
import { ReservaQuadraTenisDetailComponent } from './detail/reserva-quadra-tenis-detail.component';
import { ReservaQuadraTenisUpdateComponent } from './update/reserva-quadra-tenis-update.component';
import { ReservaQuadraTenisDeleteDialogComponent } from './delete/reserva-quadra-tenis-delete-dialog.component';
import { ReservaQuadraTenisRoutingModule } from './route/reserva-quadra-tenis-routing.module';

@NgModule({
  imports: [SharedModule, ReservaQuadraTenisRoutingModule],
  declarations: [
    ReservaQuadraTenisComponent,
    ReservaQuadraTenisDetailComponent,
    ReservaQuadraTenisUpdateComponent,
    ReservaQuadraTenisDeleteDialogComponent,
  ],
  entryComponents: [ReservaQuadraTenisDeleteDialogComponent],
})
export class ReservaQuadraTenisModule {}
