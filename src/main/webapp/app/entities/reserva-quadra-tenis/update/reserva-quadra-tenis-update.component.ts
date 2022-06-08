import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';

import { IReservaQuadraTenis, ReservaQuadraTenis } from '../reserva-quadra-tenis.model';
import { ReservaQuadraTenisService } from '../service/reserva-quadra-tenis.service';

@Component({
  selector: 'jhi-reserva-quadra-tenis-update',
  templateUrl: './reserva-quadra-tenis-update.component.html',
})
export class ReservaQuadraTenisUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    emailOrigem: [null, [Validators.required]],
    emailDestino: [null, [Validators.required]],
    templateEmail: [null, [Validators.required]],
    semana: [],
    segundafeira: [],
    tercafeira: [],
    quartafeira: [],
    quintafeira: [],
    sextafeira: [],
    sabado: [],
    domingo: [],
  });

  constructor(
    protected reservaQuadraTenisService: ReservaQuadraTenisService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ reservaQuadraTenis }) => {
      if (reservaQuadraTenis.id === undefined) {
        const today = dayjs().startOf('day');
        reservaQuadraTenis.segundafeira = today;
        reservaQuadraTenis.tercafeira = today;
        reservaQuadraTenis.quartafeira = today;
        reservaQuadraTenis.quintafeira = today;
        reservaQuadraTenis.sextafeira = today;
        reservaQuadraTenis.sabado = today;
        reservaQuadraTenis.domingo = today;
      }

      this.updateForm(reservaQuadraTenis);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const reservaQuadraTenis = this.createFromForm();
    if (reservaQuadraTenis.id !== undefined) {
      this.subscribeToSaveResponse(this.reservaQuadraTenisService.update(reservaQuadraTenis));
    } else {
      this.subscribeToSaveResponse(this.reservaQuadraTenisService.create(reservaQuadraTenis));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IReservaQuadraTenis>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(reservaQuadraTenis: IReservaQuadraTenis): void {
    this.editForm.patchValue({
      id: reservaQuadraTenis.id,
      emailOrigem: reservaQuadraTenis.emailOrigem,
      emailDestino: reservaQuadraTenis.emailDestino,
      templateEmail: reservaQuadraTenis.templateEmail,
      semana: reservaQuadraTenis.semana,
      segundafeira: reservaQuadraTenis.segundafeira ? reservaQuadraTenis.segundafeira.format(DATE_TIME_FORMAT) : null,
      tercafeira: reservaQuadraTenis.tercafeira ? reservaQuadraTenis.tercafeira.format(DATE_TIME_FORMAT) : null,
      quartafeira: reservaQuadraTenis.quartafeira ? reservaQuadraTenis.quartafeira.format(DATE_TIME_FORMAT) : null,
      quintafeira: reservaQuadraTenis.quintafeira ? reservaQuadraTenis.quintafeira.format(DATE_TIME_FORMAT) : null,
      sextafeira: reservaQuadraTenis.sextafeira ? reservaQuadraTenis.sextafeira.format(DATE_TIME_FORMAT) : null,
      sabado: reservaQuadraTenis.sabado ? reservaQuadraTenis.sabado.format(DATE_TIME_FORMAT) : null,
      domingo: reservaQuadraTenis.domingo ? reservaQuadraTenis.domingo.format(DATE_TIME_FORMAT) : null,
    });
  }

  protected createFromForm(): IReservaQuadraTenis {
    return {
      ...new ReservaQuadraTenis(),
      id: this.editForm.get(['id'])!.value,
      emailOrigem: this.editForm.get(['emailOrigem'])!.value,
      emailDestino: this.editForm.get(['emailDestino'])!.value,
      templateEmail: this.editForm.get(['templateEmail'])!.value,
      semana: this.editForm.get(['semana'])!.value,
      segundafeira: this.editForm.get(['segundafeira'])!.value
        ? dayjs(this.editForm.get(['segundafeira'])!.value, DATE_TIME_FORMAT)
        : undefined,
      tercafeira: this.editForm.get(['tercafeira'])!.value ? dayjs(this.editForm.get(['tercafeira'])!.value, DATE_TIME_FORMAT) : undefined,
      quartafeira: this.editForm.get(['quartafeira'])!.value
        ? dayjs(this.editForm.get(['quartafeira'])!.value, DATE_TIME_FORMAT)
        : undefined,
      quintafeira: this.editForm.get(['quintafeira'])!.value
        ? dayjs(this.editForm.get(['quintafeira'])!.value, DATE_TIME_FORMAT)
        : undefined,
      sextafeira: this.editForm.get(['sextafeira'])!.value ? dayjs(this.editForm.get(['sextafeira'])!.value, DATE_TIME_FORMAT) : undefined,
      sabado: this.editForm.get(['sabado'])!.value ? dayjs(this.editForm.get(['sabado'])!.value, DATE_TIME_FORMAT) : undefined,
      domingo: this.editForm.get(['domingo'])!.value ? dayjs(this.editForm.get(['domingo'])!.value, DATE_TIME_FORMAT) : undefined,
    };
  }
}
