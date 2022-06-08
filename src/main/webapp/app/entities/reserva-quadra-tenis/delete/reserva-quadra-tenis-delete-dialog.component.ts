import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IReservaQuadraTenis } from '../reserva-quadra-tenis.model';
import { ReservaQuadraTenisService } from '../service/reserva-quadra-tenis.service';

@Component({
  templateUrl: './reserva-quadra-tenis-delete-dialog.component.html',
})
export class ReservaQuadraTenisDeleteDialogComponent {
  reservaQuadraTenis?: IReservaQuadraTenis;

  constructor(protected reservaQuadraTenisService: ReservaQuadraTenisService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.reservaQuadraTenisService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
