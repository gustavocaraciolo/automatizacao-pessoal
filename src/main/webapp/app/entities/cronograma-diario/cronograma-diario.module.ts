import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { CronogramaDiarioComponent } from './list/cronograma-diario.component';
import { CronogramaDiarioDetailComponent } from './detail/cronograma-diario-detail.component';
import { CronogramaDiarioUpdateComponent } from './update/cronograma-diario-update.component';
import { CronogramaDiarioDeleteDialogComponent } from './delete/cronograma-diario-delete-dialog.component';
import { CronogramaDiarioRoutingModule } from './route/cronograma-diario-routing.module';

@NgModule({
  imports: [SharedModule, CronogramaDiarioRoutingModule],
  declarations: [
    CronogramaDiarioComponent,
    CronogramaDiarioDetailComponent,
    CronogramaDiarioUpdateComponent,
    CronogramaDiarioDeleteDialogComponent,
  ],
  entryComponents: [CronogramaDiarioDeleteDialogComponent],
})
export class CronogramaDiarioModule {}
