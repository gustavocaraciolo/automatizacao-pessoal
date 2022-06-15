import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { AtividadeComponent } from './list/atividade.component';
import { AtividadeDetailComponent } from './detail/atividade-detail.component';
import { AtividadeUpdateComponent } from './update/atividade-update.component';
import { AtividadeDeleteDialogComponent } from './delete/atividade-delete-dialog.component';
import { AtividadeRoutingModule } from './route/atividade-routing.module';

@NgModule({
  imports: [SharedModule, AtividadeRoutingModule],
  declarations: [AtividadeComponent, AtividadeDetailComponent, AtividadeUpdateComponent, AtividadeDeleteDialogComponent],
  entryComponents: [AtividadeDeleteDialogComponent],
})
export class AtividadeModule {}
