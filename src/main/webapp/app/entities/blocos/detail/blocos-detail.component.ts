import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBlocos } from '../blocos.model';

@Component({
  selector: 'jhi-blocos-detail',
  templateUrl: './blocos-detail.component.html',
})
export class BlocosDetailComponent implements OnInit {
  blocos: IBlocos | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ blocos }) => {
      this.blocos = blocos;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
