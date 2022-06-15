import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICronogramaDiario } from '../cronograma-diario.model';

@Component({
  selector: 'jhi-cronograma-diario-detail',
  templateUrl: './cronograma-diario-detail.component.html',
})
export class CronogramaDiarioDetailComponent implements OnInit {
  cronogramaDiario: ICronogramaDiario | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ cronogramaDiario }) => {
      this.cronogramaDiario = cronogramaDiario;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
