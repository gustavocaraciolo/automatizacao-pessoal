import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IReservaQuadraTenis } from '../reserva-quadra-tenis.model';

@Component({
  selector: 'jhi-reserva-quadra-tenis-detail',
  templateUrl: './reserva-quadra-tenis-detail.component.html',
})
export class ReservaQuadraTenisDetailComponent implements OnInit {
  reservaQuadraTenis: IReservaQuadraTenis | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ reservaQuadraTenis }) => {
      this.reservaQuadraTenis = reservaQuadraTenis;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
