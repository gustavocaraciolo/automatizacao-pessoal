import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { IAtividade, Atividade } from '../atividade.model';
import { AtividadeService } from '../service/atividade.service';
import { IBlocos } from 'app/entities/blocos/blocos.model';
import { BlocosService } from 'app/entities/blocos/service/blocos.service';

@Component({
  selector: 'jhi-atividade-update',
  templateUrl: './atividade-update.component.html',
})
export class AtividadeUpdateComponent implements OnInit {
  isSaving = false;

  blocosSharedCollection: IBlocos[] = [];

  editForm = this.fb.group({
    id: [],
    cor: [],
    descricao: [],
    blocos: [],
  });

  constructor(
    protected atividadeService: AtividadeService,
    protected blocosService: BlocosService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ atividade }) => {
      this.updateForm(atividade);

      this.loadRelationshipsOptions();
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

  trackBlocosById(_index: number, item: IBlocos): number {
    return item.id!;
  }

  getSelectedBlocos(option: IBlocos, selectedVals?: IBlocos[]): IBlocos {
    if (selectedVals) {
      for (const selectedVal of selectedVals) {
        if (option.id === selectedVal.id) {
          return selectedVal;
        }
      }
    }
    return option;
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
      blocos: atividade.blocos,
    });

    this.blocosSharedCollection = this.blocosService.addBlocosToCollectionIfMissing(
      this.blocosSharedCollection,
      ...(atividade.blocos ?? [])
    );
  }

  protected loadRelationshipsOptions(): void {
    this.blocosService
      .query()
      .pipe(map((res: HttpResponse<IBlocos[]>) => res.body ?? []))
      .pipe(
        map((blocos: IBlocos[]) => this.blocosService.addBlocosToCollectionIfMissing(blocos, ...(this.editForm.get('blocos')!.value ?? [])))
      )
      .subscribe((blocos: IBlocos[]) => (this.blocosSharedCollection = blocos));
  }

  protected createFromForm(): IAtividade {
    return {
      ...new Atividade(),
      id: this.editForm.get(['id'])!.value,
      cor: this.editForm.get(['cor'])!.value,
      descricao: this.editForm.get(['descricao'])!.value,
      blocos: this.editForm.get(['blocos'])!.value,
    };
  }
}