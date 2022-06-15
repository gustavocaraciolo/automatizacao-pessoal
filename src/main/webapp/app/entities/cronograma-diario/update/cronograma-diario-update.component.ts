import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { ICronogramaDiario, CronogramaDiario } from '../cronograma-diario.model';
import { CronogramaDiarioService } from '../service/cronograma-diario.service';

@Component({
  selector: 'jhi-cronograma-diario-update',
  templateUrl: './cronograma-diario-update.component.html',
})
export class CronogramaDiarioUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    dia: [],
  });

  constructor(
    protected cronogramaDiarioService: CronogramaDiarioService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ cronogramaDiario }) => {
      this.updateForm(cronogramaDiario);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const cronogramaDiario = this.createFromForm();
    if (cronogramaDiario.id !== undefined) {
      this.subscribeToSaveResponse(this.cronogramaDiarioService.update(cronogramaDiario));
    } else {
      this.subscribeToSaveResponse(this.cronogramaDiarioService.create(cronogramaDiario));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICronogramaDiario>>): void {
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

  protected updateForm(cronogramaDiario: ICronogramaDiario): void {
    this.editForm.patchValue({
      id: cronogramaDiario.id,
      dia: cronogramaDiario.dia,
    });
  }

  protected createFromForm(): ICronogramaDiario {
    return {
      ...new CronogramaDiario(),
      id: this.editForm.get(['id'])!.value,
      dia: this.editForm.get(['dia'])!.value,
    };
  }
}
