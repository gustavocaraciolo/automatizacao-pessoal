import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ICronogramaDiario } from '../cronograma-diario.model';
import { CronogramaDiarioService } from '../service/cronograma-diario.service';
import { CronogramaDiarioDeleteDialogComponent } from '../delete/cronograma-diario-delete-dialog.component';

@Component({
  selector: 'jhi-cronograma-diario',
  templateUrl: './cronograma-diario.component.html',
})
export class CronogramaDiarioComponent implements OnInit {
  cronogramaDiarios?: ICronogramaDiario[];
  isLoading = false;

  constructor(protected cronogramaDiarioService: CronogramaDiarioService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.cronogramaDiarioService.query().subscribe({
      next: (res: HttpResponse<ICronogramaDiario[]>) => {
        this.isLoading = false;
        this.cronogramaDiarios = res.body ?? [];
      },
      error: () => {
        this.isLoading = false;
      },
    });
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackId(_index: number, item: ICronogramaDiario): number {
    return item.id!;
  }

  delete(cronogramaDiario: ICronogramaDiario): void {
    const modalRef = this.modalService.open(CronogramaDiarioDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.cronogramaDiario = cronogramaDiario;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
