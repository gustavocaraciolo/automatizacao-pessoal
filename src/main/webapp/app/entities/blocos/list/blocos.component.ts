import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IBlocos } from '../blocos.model';
import { BlocosService } from '../service/blocos.service';
import { BlocosDeleteDialogComponent } from '../delete/blocos-delete-dialog.component';

@Component({
  selector: 'jhi-blocos',
  templateUrl: './blocos.component.html',
})
export class BlocosComponent implements OnInit {
  blocos?: IBlocos[];
  isLoading = false;

  constructor(protected blocosService: BlocosService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.blocosService.query().subscribe({
      next: (res: HttpResponse<IBlocos[]>) => {
        this.isLoading = false;
        this.blocos = res.body ?? [];
      },
      error: () => {
        this.isLoading = false;
      },
    });
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackId(_index: number, item: IBlocos): number {
    return item.id!;
  }

  delete(blocos: IBlocos): void {
    const modalRef = this.modalService.open(BlocosDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.blocos = blocos;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
