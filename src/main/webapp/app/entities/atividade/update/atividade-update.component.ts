import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { IAtividade, Atividade } from '../atividade.model';
import { AtividadeService } from '../service/atividade.service';

@Component({
  selector: 'jhi-atividade-update',
  templateUrl: './atividade-update.component.html',
})
export class AtividadeUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    cor: [],
    descricao: [],
  });

  constructor(protected atividadeService: AtividadeService, protected activatedRoute: ActivatedRoute, protected fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ atividade }) => {
      this.updateForm(atividade);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const atividade = this.createFromForm();
    if (atividade.id !== undefined) {
      this.subscribeToSaveResponse(this.atividadeService.update(atividade));
    } else {
      this.subscribeToSaveResponse(this.atividadeService.create(atividade));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAtividade>>): void {
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

  protected updateForm(atividade: IAtividade): void {
    this.editForm.patchValue({
      id: atividade.id,
      cor: atividade.cor,
      descricao: atividade.descricao,
    });
  }

  protected createFromForm(): IAtividade {
    return {
      ...new Atividade(),
      id: this.editForm.get(['id'])!.value,
      cor: this.editForm.get(['cor'])!.value,
      descricao: this.editForm.get(['descricao'])!.value,
    };
  }
}
