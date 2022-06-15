import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IBlocos } from '../blocos.model';
import { BlocosService } from '../service/blocos.service';

@Component({
  templateUrl: './blocos-delete-dialog.component.html',
})
export class BlocosDeleteDialogComponent {
  blocos?: IBlocos;

  constructor(protected blocosService: BlocosService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.blocosService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
