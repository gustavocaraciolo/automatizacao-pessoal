import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IAtividade } from '../atividade.model';
import { AtividadeService } from '../service/atividade.service';
import { AtividadeDeleteDialogComponent } from '../delete/atividade-delete-dialog.component';

@Component({
  selector: 'jhi-atividade',
  templateUrl: './atividade.component.html',
})
export class AtividadeComponent implements OnInit {
  atividades?: IAtividade[];
  isLoading = false;

  constructor(protected atividadeService: AtividadeService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.atividadeService.query().subscribe({
      next: (res: HttpResponse<IAtividade[]>) => {
        this.isLoading = false;
        this.atividades = res.body ?? [];
      },
      error: () => {
        this.isLoading = false;
      },
    });
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackId(_index: number, item: IAtividade): number {
    return item.id!;
  }

  delete(atividade: IAtividade): void {
    const modalRef = this.modalService.open(AtividadeDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.atividade = atividade;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
