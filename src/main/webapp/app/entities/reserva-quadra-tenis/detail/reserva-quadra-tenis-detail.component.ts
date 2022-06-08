import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IReservaQuadraTenis } from '../reserva-quadra-tenis.model';
import { DataUtils } from 'app/core/util/data-util.service';

@Component({
  selector: 'jhi-reserva-quadra-tenis-detail',
  templateUrl: './reserva-quadra-tenis-detail.component.html',
})
export class ReservaQuadraTenisDetailComponent implements OnInit {
  reservaQuadraTenis: IReservaQuadraTenis | null = null;

  constructor(protected dataUtils: DataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ reservaQuadraTenis }) => {
      this.reservaQuadraTenis = reservaQuadraTenis;
    });
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(base64String: string, contentType: string | null | undefined): void {
    this.dataUtils.openFile(base64String, contentType);
  }

  previousState(): void {
    window.history.back();
  }
}
