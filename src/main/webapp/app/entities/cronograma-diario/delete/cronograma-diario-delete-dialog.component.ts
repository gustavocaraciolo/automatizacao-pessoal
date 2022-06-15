import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ICronogramaDiario } from '../cronograma-diario.model';
import { CronogramaDiarioService } from '../service/cronograma-diario.service';

@Component({
  templateUrl: './cronograma-diario-delete-dialog.component.html',
})
export class CronogramaDiarioDeleteDialogComponent {
  cronogramaDiario?: ICronogramaDiario;

  constructor(protected cronogramaDiarioService: CronogramaDiarioService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.cronogramaDiarioService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
