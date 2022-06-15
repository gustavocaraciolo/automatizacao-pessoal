import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IAtividade } from '../atividade.model';
import { AtividadeService } from '../service/atividade.service';

@Component({
  templateUrl: './atividade-delete-dialog.component.html',
})
export class AtividadeDeleteDialogComponent {
  atividade?: IAtividade;

  constructor(protected atividadeService: AtividadeService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.atividadeService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
