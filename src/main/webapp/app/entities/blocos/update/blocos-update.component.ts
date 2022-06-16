import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';

import { IBlocos, Blocos } from '../blocos.model';
import { BlocosService } from '../service/blocos.service';
import { IAtividade } from 'app/entities/atividade/atividade.model';
import { AtividadeService } from 'app/entities/atividade/service/atividade.service';
import { ICronogramaDiario } from 'app/entities/cronograma-diario/cronograma-diario.model';
import { CronogramaDiarioService } from 'app/entities/cronograma-diario/service/cronograma-diario.service';

@Component({
  selector: 'jhi-blocos-update',
  templateUrl: './blocos-update.component.html',
})
export class BlocosUpdateComponent implements OnInit {
  isSaving = false;

  atividadesSharedCollection: IAtividade[] = [];
  cronogramaDiariosSharedCollection: ICronogramaDiario[] = [];

  editForm = this.fb.group({
    id: [],
    zeroAM: [],
    zeroAMeDez: [],
    zeroAMeVinte: [],
    zeroAMeTrinta: [],
    zeroAMeQuarenta: [],
    zeroAMeCinquenta: [],
    zeroPM: [],
    zeroPMeDez: [],
    zeroPMeVinte: [],
    zeroPMeTrinta: [],
    zeroPMeQuarenta: [],
    zeroPMeCinquenta: [],
    umAM: [],
    umAMeDez: [],
    umAMeVinte: [],
    umAMeTrinta: [],
    umAMeQuarenta: [],
    umAMeCinquenta: [],
    umPM: [],
    umPMeDez: [],
    umPMeVinte: [],
    umPMeTrinta: [],
    umPMeQuarenta: [],
    umPMeCinquenta: [],
    doisAM: [],
    doisAMeDez: [],
    doisAMeVinte: [],
    doisAMeTrinta: [],
    doisAMeQuarenta: [],
    doisAMeCinquenta: [],
    doisPM: [],
    doisPMeDez: [],
    doisPMeVinte: [],
    doisPMeTrinta: [],
    doisPMeQuarenta: [],
    doisPMeCinquenta: [],
    tresAM: [],
    tresAMeDez: [],
    tresAMeVinte: [],
    tresAMeTrinta: [],
    tresAMeQuarenta: [],
    tresAMeCinquenta: [],
    tresPM: [],
    tresPMeDez: [],
    tresPMeVinte: [],
    tresPMeTrinta: [],
    tresPMeQuarenta: [],
    tresPMeCinquenta: [],
    quatroAM: [],
    quatroAMeDez: [],
    quatroAMeVinte: [],
    quatroAMeTrinta: [],
    quatroAMeQuarenta: [],
    quatroAMeCinquenta: [],
    quatroPM: [],
    quatroPMeDez: [],
    quatroPMeVinte: [],
    quatroPMeTrinta: [],
    quatroPMeQuarenta: [],
    quatroPMeCinquenta: [],
    cincoAM: [],
    cincoAMeDez: [],
    cincoAMeVinte: [],
    cincoAMeTrinta: [],
    cincoAMeQuarenta: [],
    cincoAMeCinquenta: [],
    cincoPM: [],
    cincoPMeDez: [],
    cincoPMeVinte: [],
    cincoPMeTrinta: [],
    cincoPMeQuarenta: [],
    cincoPMeCinquenta: [],
    seisAM: [],
    seisAMeDez: [],
    seisAMeVinte: [],
    seisAMeTrinta: [],
    seisAMeQuarenta: [],
    seisAMeCinquenta: [],
    seisPM: [],
    seisPMeDez: [],
    seisPMeVinte: [],
    seisPMeTrinta: [],
    seisPMeQuarenta: [],
    seisPMeCinquenta: [],
    seteAM: [],
    seteAMeDez: [],
    seteAMeVinte: [],
    seteAMeTrinta: [],
    seteAMeQuarenta: [],
    seteAMeCinquenta: [],
    setePM: [],
    setePMeDez: [],
    setePMeVinte: [],
    setePMeTrinta: [],
    setePMeQuarenta: [],
    setePMeCinquenta: [],
    oitoAM: [],
    oitoAMeDez: [],
    oitoAMeVinte: [],
    oitoAMeTrinta: [],
    oitoAMeQuarenta: [],
    oitoAMeCinquenta: [],
    oitoPM: [],
    oitoPMeDez: [],
    oitoPMeVinte: [],
    oitoPMeTrinta: [],
    oitoPMeQuarenta: [],
    oitoPMeCinquenta: [],
    noveAM: [],
    noveAMeDez: [],
    noveAMeVinte: [],
    noveAMeTrinta: [],
    noveAMeQuarenta: [],
    noveAMeCinquenta: [],
    novePM: [],
    novePMeDez: [],
    novePMeVinte: [],
    novePMeTrinta: [],
    novePMeQuarenta: [],
    novePMeCinquenta: [],
    dezAM: [],
    dezAMeDez: [],
    dezAMeVinte: [],
    dezAMeTrinta: [],
    dezAMeQuarenta: [],
    dezAMeCinquenta: [],
    dezPM: [],
    dezPMeDez: [],
    dezPMeVinte: [],
    dezPMeTrinta: [],
    dezPMeQuarenta: [],
    dezPMeCinquenta: [],
    onzeAM: [],
    onzeAMeDez: [],
    onzeAMeVinte: [],
    onzeAMeTrinta: [],
    onzeAMeQuarenta: [],
    onzeAMeCinquenta: [],
    onzePM: [],
    onzePMeDez: [],
    onzePMeVinte: [],
    onzePMeTrinta: [],
    onzePMeQuarenta: [],
    onzePMeCinquenta: [],
    atividades: [],
    cronogramaDiario: [],
  });

  constructor(
    protected blocosService: BlocosService,
    protected atividadeService: AtividadeService,
    protected cronogramaDiarioService: CronogramaDiarioService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ blocos }) => {
      if (blocos.id === undefined) {
        const today = dayjs().startOf('day');
        blocos.zeroAM = today;
        blocos.zeroAMeDez = today;
        blocos.zeroAMeVinte = today;
        blocos.zeroAMeTrinta = today;
        blocos.zeroAMeQuarenta = today;
        blocos.zeroAMeCinquenta = today;
        blocos.zeroPM = today;
        blocos.zeroPMeDez = today;
        blocos.zeroPMeVinte = today;
        blocos.zeroPMeTrinta = today;
        blocos.zeroPMeQuarenta = today;
        blocos.zeroPMeCinquenta = today;
        blocos.umAM = today;
        blocos.umAMeDez = today;
        blocos.umAMeVinte = today;
        blocos.umAMeTrinta = today;
        blocos.umAMeQuarenta = today;
        blocos.umAMeCinquenta = today;
        blocos.umPM = today;
        blocos.umPMeDez = today;
        blocos.umPMeVinte = today;
        blocos.umPMeTrinta = today;
        blocos.umPMeQuarenta = today;
        blocos.umPMeCinquenta = today;
        blocos.doisAM = today;
        blocos.doisAMeDez = today;
        blocos.doisAMeVinte = today;
        blocos.doisAMeTrinta = today;
        blocos.doisAMeQuarenta = today;
        blocos.doisAMeCinquenta = today;
        blocos.doisPM = today;
        blocos.doisPMeDez = today;
        blocos.doisPMeVinte = today;
        blocos.doisPMeTrinta = today;
        blocos.doisPMeQuarenta = today;
        blocos.doisPMeCinquenta = today;
        blocos.tresAM = today;
        blocos.tresAMeDez = today;
        blocos.tresAMeVinte = today;
        blocos.tresAMeTrinta = today;
        blocos.tresAMeQuarenta = today;
        blocos.tresAMeCinquenta = today;
        blocos.tresPM = today;
        blocos.tresPMeDez = today;
        blocos.tresPMeVinte = today;
        blocos.tresPMeTrinta = today;
        blocos.tresPMeQuarenta = today;
        blocos.tresPMeCinquenta = today;
        blocos.quatroAM = today;
        blocos.quatroAMeDez = today;
        blocos.quatroAMeVinte = today;
        blocos.quatroAMeTrinta = today;
        blocos.quatroAMeQuarenta = today;
        blocos.quatroAMeCinquenta = today;
        blocos.quatroPM = today;
        blocos.quatroPMeDez = today;
        blocos.quatroPMeVinte = today;
        blocos.quatroPMeTrinta = today;
        blocos.quatroPMeQuarenta = today;
        blocos.quatroPMeCinquenta = today;
        blocos.cincoAM = today;
        blocos.cincoAMeDez = today;
        blocos.cincoAMeVinte = today;
        blocos.cincoAMeTrinta = today;
        blocos.cincoAMeQuarenta = today;
        blocos.cincoAMeCinquenta = today;
        blocos.cincoPM = today;
        blocos.cincoPMeDez = today;
        blocos.cincoPMeVinte = today;
        blocos.cincoPMeTrinta = today;
        blocos.cincoPMeQuarenta = today;
        blocos.cincoPMeCinquenta = today;
        blocos.seisAM = today;
        blocos.seisAMeDez = today;
        blocos.seisAMeVinte = today;
        blocos.seisAMeTrinta = today;
        blocos.seisAMeQuarenta = today;
        blocos.seisAMeCinquenta = today;
        blocos.seisPM = today;
        blocos.seisPMeDez = today;
        blocos.seisPMeVinte = today;
        blocos.seisPMeTrinta = today;
        blocos.seisPMeQuarenta = today;
        blocos.seisPMeCinquenta = today;
        blocos.seteAM = today;
        blocos.seteAMeDez = today;
        blocos.seteAMeVinte = today;
        blocos.seteAMeTrinta = today;
        blocos.seteAMeQuarenta = today;
        blocos.seteAMeCinquenta = today;
        blocos.setePM = today;
        blocos.setePMeDez = today;
        blocos.setePMeVinte = today;
        blocos.setePMeTrinta = today;
        blocos.setePMeQuarenta = today;
        blocos.setePMeCinquenta = today;
        blocos.oitoAM = today;
        blocos.oitoAMeDez = today;
        blocos.oitoAMeVinte = today;
        blocos.oitoAMeTrinta = today;
        blocos.oitoAMeQuarenta = today;
        blocos.oitoAMeCinquenta = today;
        blocos.oitoPM = today;
        blocos.oitoPMeDez = today;
        blocos.oitoPMeVinte = today;
        blocos.oitoPMeTrinta = today;
        blocos.oitoPMeQuarenta = today;
        blocos.oitoPMeCinquenta = today;
        blocos.noveAM = today;
        blocos.noveAMeDez = today;
        blocos.noveAMeVinte = today;
        blocos.noveAMeTrinta = today;
        blocos.noveAMeQuarenta = today;
        blocos.noveAMeCinquenta = today;
        blocos.novePM = today;
        blocos.novePMeDez = today;
        blocos.novePMeVinte = today;
        blocos.novePMeTrinta = today;
        blocos.novePMeQuarenta = today;
        blocos.novePMeCinquenta = today;
        blocos.dezAM = today;
        blocos.dezAMeDez = today;
        blocos.dezAMeVinte = today;
        blocos.dezAMeTrinta = today;
        blocos.dezAMeQuarenta = today;
        blocos.dezAMeCinquenta = today;
        blocos.dezPM = today;
        blocos.dezPMeDez = today;
        blocos.dezPMeVinte = today;
        blocos.dezPMeTrinta = today;
        blocos.dezPMeQuarenta = today;
        blocos.dezPMeCinquenta = today;
        blocos.onzeAM = today;
        blocos.onzeAMeDez = today;
        blocos.onzeAMeVinte = today;
        blocos.onzeAMeTrinta = today;
        blocos.onzeAMeQuarenta = today;
        blocos.onzeAMeCinquenta = today;
        blocos.onzePM = today;
        blocos.onzePMeDez = today;
        blocos.onzePMeVinte = today;
        blocos.onzePMeTrinta = today;
        blocos.onzePMeQuarenta = today;
        blocos.onzePMeCinquenta = today;
      }

      this.updateForm(blocos);

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const blocos = this.createFromForm();
    if (blocos.id !== undefined) {
      this.subscribeToSaveResponse(this.blocosService.update(blocos));
    } else {
      this.subscribeToSaveResponse(this.blocosService.create(blocos));
    }
  }

  trackAtividadeById(_index: number, item: IAtividade): number {
    return item.id!;
  }

  trackCronogramaDiarioById(_index: number, item: ICronogramaDiario): number {
    return item.id!;
  }

  getSelectedAtividade(option: IAtividade, selectedVals?: IAtividade[]): IAtividade {
    if (selectedVals) {
      for (const selectedVal of selectedVals) {
        if (option.id === selectedVal.id) {
          return selectedVal;
        }
      }
    }
    return option;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBlocos>>): void {
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

  protected updateForm(blocos: IBlocos): void {
    this.editForm.patchValue({
      id: blocos.id,
      zeroAM: blocos.zeroAM ? blocos.zeroAM.format(DATE_TIME_FORMAT) : null,
      zeroAMeDez: blocos.zeroAMeDez ? blocos.zeroAMeDez.format(DATE_TIME_FORMAT) : null,
      zeroAMeVinte: blocos.zeroAMeVinte ? blocos.zeroAMeVinte.format(DATE_TIME_FORMAT) : null,
      zeroAMeTrinta: blocos.zeroAMeTrinta ? blocos.zeroAMeTrinta.format(DATE_TIME_FORMAT) : null,
      zeroAMeQuarenta: blocos.zeroAMeQuarenta ? blocos.zeroAMeQuarenta.format(DATE_TIME_FORMAT) : null,
      zeroAMeCinquenta: blocos.zeroAMeCinquenta ? blocos.zeroAMeCinquenta.format(DATE_TIME_FORMAT) : null,
      zeroPM: blocos.zeroPM ? blocos.zeroPM.format(DATE_TIME_FORMAT) : null,
      zeroPMeDez: blocos.zeroPMeDez ? blocos.zeroPMeDez.format(DATE_TIME_FORMAT) : null,
      zeroPMeVinte: blocos.zeroPMeVinte ? blocos.zeroPMeVinte.format(DATE_TIME_FORMAT) : null,
      zeroPMeTrinta: blocos.zeroPMeTrinta ? blocos.zeroPMeTrinta.format(DATE_TIME_FORMAT) : null,
      zeroPMeQuarenta: blocos.zeroPMeQuarenta ? blocos.zeroPMeQuarenta.format(DATE_TIME_FORMAT) : null,
      zeroPMeCinquenta: blocos.zeroPMeCinquenta ? blocos.zeroPMeCinquenta.format(DATE_TIME_FORMAT) : null,
      umAM: blocos.umAM ? blocos.umAM.format(DATE_TIME_FORMAT) : null,
      umAMeDez: blocos.umAMeDez ? blocos.umAMeDez.format(DATE_TIME_FORMAT) : null,
      umAMeVinte: blocos.umAMeVinte ? blocos.umAMeVinte.format(DATE_TIME_FORMAT) : null,
      umAMeTrinta: blocos.umAMeTrinta ? blocos.umAMeTrinta.format(DATE_TIME_FORMAT) : null,
      umAMeQuarenta: blocos.umAMeQuarenta ? blocos.umAMeQuarenta.format(DATE_TIME_FORMAT) : null,
      umAMeCinquenta: blocos.umAMeCinquenta ? blocos.umAMeCinquenta.format(DATE_TIME_FORMAT) : null,
      umPM: blocos.umPM ? blocos.umPM.format(DATE_TIME_FORMAT) : null,
      umPMeDez: blocos.umPMeDez ? blocos.umPMeDez.format(DATE_TIME_FORMAT) : null,
      umPMeVinte: blocos.umPMeVinte ? blocos.umPMeVinte.format(DATE_TIME_FORMAT) : null,
      umPMeTrinta: blocos.umPMeTrinta ? blocos.umPMeTrinta.format(DATE_TIME_FORMAT) : null,
      umPMeQuarenta: blocos.umPMeQuarenta ? blocos.umPMeQuarenta.format(DATE_TIME_FORMAT) : null,
      umPMeCinquenta: blocos.umPMeCinquenta ? blocos.umPMeCinquenta.format(DATE_TIME_FORMAT) : null,
      doisAM: blocos.doisAM ? blocos.doisAM.format(DATE_TIME_FORMAT) : null,
      doisAMeDez: blocos.doisAMeDez ? blocos.doisAMeDez.format(DATE_TIME_FORMAT) : null,
      doisAMeVinte: blocos.doisAMeVinte ? blocos.doisAMeVinte.format(DATE_TIME_FORMAT) : null,
      doisAMeTrinta: blocos.doisAMeTrinta ? blocos.doisAMeTrinta.format(DATE_TIME_FORMAT) : null,
      doisAMeQuarenta: blocos.doisAMeQuarenta ? blocos.doisAMeQuarenta.format(DATE_TIME_FORMAT) : null,
      doisAMeCinquenta: blocos.doisAMeCinquenta ? blocos.doisAMeCinquenta.format(DATE_TIME_FORMAT) : null,
      doisPM: blocos.doisPM ? blocos.doisPM.format(DATE_TIME_FORMAT) : null,
      doisPMeDez: blocos.doisPMeDez ? blocos.doisPMeDez.format(DATE_TIME_FORMAT) : null,
      doisPMeVinte: blocos.doisPMeVinte ? blocos.doisPMeVinte.format(DATE_TIME_FORMAT) : null,
      doisPMeTrinta: blocos.doisPMeTrinta ? blocos.doisPMeTrinta.format(DATE_TIME_FORMAT) : null,
      doisPMeQuarenta: blocos.doisPMeQuarenta ? blocos.doisPMeQuarenta.format(DATE_TIME_FORMAT) : null,
      doisPMeCinquenta: blocos.doisPMeCinquenta ? blocos.doisPMeCinquenta.format(DATE_TIME_FORMAT) : null,
      tresAM: blocos.tresAM ? blocos.tresAM.format(DATE_TIME_FORMAT) : null,
      tresAMeDez: blocos.tresAMeDez ? blocos.tresAMeDez.format(DATE_TIME_FORMAT) : null,
      tresAMeVinte: blocos.tresAMeVinte ? blocos.tresAMeVinte.format(DATE_TIME_FORMAT) : null,
      tresAMeTrinta: blocos.tresAMeTrinta ? blocos.tresAMeTrinta.format(DATE_TIME_FORMAT) : null,
      tresAMeQuarenta: blocos.tresAMeQuarenta ? blocos.tresAMeQuarenta.format(DATE_TIME_FORMAT) : null,
      tresAMeCinquenta: blocos.tresAMeCinquenta ? blocos.tresAMeCinquenta.format(DATE_TIME_FORMAT) : null,
      tresPM: blocos.tresPM ? blocos.tresPM.format(DATE_TIME_FORMAT) : null,
      tresPMeDez: blocos.tresPMeDez ? blocos.tresPMeDez.format(DATE_TIME_FORMAT) : null,
      tresPMeVinte: blocos.tresPMeVinte ? blocos.tresPMeVinte.format(DATE_TIME_FORMAT) : null,
      tresPMeTrinta: blocos.tresPMeTrinta ? blocos.tresPMeTrinta.format(DATE_TIME_FORMAT) : null,
      tresPMeQuarenta: blocos.tresPMeQuarenta ? blocos.tresPMeQuarenta.format(DATE_TIME_FORMAT) : null,
      tresPMeCinquenta: blocos.tresPMeCinquenta ? blocos.tresPMeCinquenta.format(DATE_TIME_FORMAT) : null,
      quatroAM: blocos.quatroAM ? blocos.quatroAM.format(DATE_TIME_FORMAT) : null,
      quatroAMeDez: blocos.quatroAMeDez ? blocos.quatroAMeDez.format(DATE_TIME_FORMAT) : null,
      quatroAMeVinte: blocos.quatroAMeVinte ? blocos.quatroAMeVinte.format(DATE_TIME_FORMAT) : null,
      quatroAMeTrinta: blocos.quatroAMeTrinta ? blocos.quatroAMeTrinta.format(DATE_TIME_FORMAT) : null,
      quatroAMeQuarenta: blocos.quatroAMeQuarenta ? blocos.quatroAMeQuarenta.format(DATE_TIME_FORMAT) : null,
      quatroAMeCinquenta: blocos.quatroAMeCinquenta ? blocos.quatroAMeCinquenta.format(DATE_TIME_FORMAT) : null,
      quatroPM: blocos.quatroPM ? blocos.quatroPM.format(DATE_TIME_FORMAT) : null,
      quatroPMeDez: blocos.quatroPMeDez ? blocos.quatroPMeDez.format(DATE_TIME_FORMAT) : null,
      quatroPMeVinte: blocos.quatroPMeVinte ? blocos.quatroPMeVinte.format(DATE_TIME_FORMAT) : null,
      quatroPMeTrinta: blocos.quatroPMeTrinta ? blocos.quatroPMeTrinta.format(DATE_TIME_FORMAT) : null,
      quatroPMeQuarenta: blocos.quatroPMeQuarenta ? blocos.quatroPMeQuarenta.format(DATE_TIME_FORMAT) : null,
      quatroPMeCinquenta: blocos.quatroPMeCinquenta ? blocos.quatroPMeCinquenta.format(DATE_TIME_FORMAT) : null,
      cincoAM: blocos.cincoAM ? blocos.cincoAM.format(DATE_TIME_FORMAT) : null,
      cincoAMeDez: blocos.cincoAMeDez ? blocos.cincoAMeDez.format(DATE_TIME_FORMAT) : null,
      cincoAMeVinte: blocos.cincoAMeVinte ? blocos.cincoAMeVinte.format(DATE_TIME_FORMAT) : null,
      cincoAMeTrinta: blocos.cincoAMeTrinta ? blocos.cincoAMeTrinta.format(DATE_TIME_FORMAT) : null,
      cincoAMeQuarenta: blocos.cincoAMeQuarenta ? blocos.cincoAMeQuarenta.format(DATE_TIME_FORMAT) : null,
      cincoAMeCinquenta: blocos.cincoAMeCinquenta ? blocos.cincoAMeCinquenta.format(DATE_TIME_FORMAT) : null,
      cincoPM: blocos.cincoPM ? blocos.cincoPM.format(DATE_TIME_FORMAT) : null,
      cincoPMeDez: blocos.cincoPMeDez ? blocos.cincoPMeDez.format(DATE_TIME_FORMAT) : null,
      cincoPMeVinte: blocos.cincoPMeVinte ? blocos.cincoPMeVinte.format(DATE_TIME_FORMAT) : null,
      cincoPMeTrinta: blocos.cincoPMeTrinta ? blocos.cincoPMeTrinta.format(DATE_TIME_FORMAT) : null,
      cincoPMeQuarenta: blocos.cincoPMeQuarenta ? blocos.cincoPMeQuarenta.format(DATE_TIME_FORMAT) : null,
      cincoPMeCinquenta: blocos.cincoPMeCinquenta ? blocos.cincoPMeCinquenta.format(DATE_TIME_FORMAT) : null,
      seisAM: blocos.seisAM ? blocos.seisAM.format(DATE_TIME_FORMAT) : null,
      seisAMeDez: blocos.seisAMeDez ? blocos.seisAMeDez.format(DATE_TIME_FORMAT) : null,
      seisAMeVinte: blocos.seisAMeVinte ? blocos.seisAMeVinte.format(DATE_TIME_FORMAT) : null,
      seisAMeTrinta: blocos.seisAMeTrinta ? blocos.seisAMeTrinta.format(DATE_TIME_FORMAT) : null,
      seisAMeQuarenta: blocos.seisAMeQuarenta ? blocos.seisAMeQuarenta.format(DATE_TIME_FORMAT) : null,
      seisAMeCinquenta: blocos.seisAMeCinquenta ? blocos.seisAMeCinquenta.format(DATE_TIME_FORMAT) : null,
      seisPM: blocos.seisPM ? blocos.seisPM.format(DATE_TIME_FORMAT) : null,
      seisPMeDez: blocos.seisPMeDez ? blocos.seisPMeDez.format(DATE_TIME_FORMAT) : null,
      seisPMeVinte: blocos.seisPMeVinte ? blocos.seisPMeVinte.format(DATE_TIME_FORMAT) : null,
      seisPMeTrinta: blocos.seisPMeTrinta ? blocos.seisPMeTrinta.format(DATE_TIME_FORMAT) : null,
      seisPMeQuarenta: blocos.seisPMeQuarenta ? blocos.seisPMeQuarenta.format(DATE_TIME_FORMAT) : null,
      seisPMeCinquenta: blocos.seisPMeCinquenta ? blocos.seisPMeCinquenta.format(DATE_TIME_FORMAT) : null,
      seteAM: blocos.seteAM ? blocos.seteAM.format(DATE_TIME_FORMAT) : null,
      seteAMeDez: blocos.seteAMeDez ? blocos.seteAMeDez.format(DATE_TIME_FORMAT) : null,
      seteAMeVinte: blocos.seteAMeVinte ? blocos.seteAMeVinte.format(DATE_TIME_FORMAT) : null,
      seteAMeTrinta: blocos.seteAMeTrinta ? blocos.seteAMeTrinta.format(DATE_TIME_FORMAT) : null,
      seteAMeQuarenta: blocos.seteAMeQuarenta ? blocos.seteAMeQuarenta.format(DATE_TIME_FORMAT) : null,
      seteAMeCinquenta: blocos.seteAMeCinquenta ? blocos.seteAMeCinquenta.format(DATE_TIME_FORMAT) : null,
      setePM: blocos.setePM ? blocos.setePM.format(DATE_TIME_FORMAT) : null,
      setePMeDez: blocos.setePMeDez ? blocos.setePMeDez.format(DATE_TIME_FORMAT) : null,
      setePMeVinte: blocos.setePMeVinte ? blocos.setePMeVinte.format(DATE_TIME_FORMAT) : null,
      setePMeTrinta: blocos.setePMeTrinta ? blocos.setePMeTrinta.format(DATE_TIME_FORMAT) : null,
      setePMeQuarenta: blocos.setePMeQuarenta ? blocos.setePMeQuarenta.format(DATE_TIME_FORMAT) : null,
      setePMeCinquenta: blocos.setePMeCinquenta ? blocos.setePMeCinquenta.format(DATE_TIME_FORMAT) : null,
      oitoAM: blocos.oitoAM ? blocos.oitoAM.format(DATE_TIME_FORMAT) : null,
      oitoAMeDez: blocos.oitoAMeDez ? blocos.oitoAMeDez.format(DATE_TIME_FORMAT) : null,
      oitoAMeVinte: blocos.oitoAMeVinte ? blocos.oitoAMeVinte.format(DATE_TIME_FORMAT) : null,
      oitoAMeTrinta: blocos.oitoAMeTrinta ? blocos.oitoAMeTrinta.format(DATE_TIME_FORMAT) : null,
      oitoAMeQuarenta: blocos.oitoAMeQuarenta ? blocos.oitoAMeQuarenta.format(DATE_TIME_FORMAT) : null,
      oitoAMeCinquenta: blocos.oitoAMeCinquenta ? blocos.oitoAMeCinquenta.format(DATE_TIME_FORMAT) : null,
      oitoPM: blocos.oitoPM ? blocos.oitoPM.format(DATE_TIME_FORMAT) : null,
      oitoPMeDez: blocos.oitoPMeDez ? blocos.oitoPMeDez.format(DATE_TIME_FORMAT) : null,
      oitoPMeVinte: blocos.oitoPMeVinte ? blocos.oitoPMeVinte.format(DATE_TIME_FORMAT) : null,
      oitoPMeTrinta: blocos.oitoPMeTrinta ? blocos.oitoPMeTrinta.format(DATE_TIME_FORMAT) : null,
      oitoPMeQuarenta: blocos.oitoPMeQuarenta ? blocos.oitoPMeQuarenta.format(DATE_TIME_FORMAT) : null,
      oitoPMeCinquenta: blocos.oitoPMeCinquenta ? blocos.oitoPMeCinquenta.format(DATE_TIME_FORMAT) : null,
      noveAM: blocos.noveAM ? blocos.noveAM.format(DATE_TIME_FORMAT) : null,
      noveAMeDez: blocos.noveAMeDez ? blocos.noveAMeDez.format(DATE_TIME_FORMAT) : null,
      noveAMeVinte: blocos.noveAMeVinte ? blocos.noveAMeVinte.format(DATE_TIME_FORMAT) : null,
      noveAMeTrinta: blocos.noveAMeTrinta ? blocos.noveAMeTrinta.format(DATE_TIME_FORMAT) : null,
      noveAMeQuarenta: blocos.noveAMeQuarenta ? blocos.noveAMeQuarenta.format(DATE_TIME_FORMAT) : null,
      noveAMeCinquenta: blocos.noveAMeCinquenta ? blocos.noveAMeCinquenta.format(DATE_TIME_FORMAT) : null,
      novePM: blocos.novePM ? blocos.novePM.format(DATE_TIME_FORMAT) : null,
      novePMeDez: blocos.novePMeDez ? blocos.novePMeDez.format(DATE_TIME_FORMAT) : null,
      novePMeVinte: blocos.novePMeVinte ? blocos.novePMeVinte.format(DATE_TIME_FORMAT) : null,
      novePMeTrinta: blocos.novePMeTrinta ? blocos.novePMeTrinta.format(DATE_TIME_FORMAT) : null,
      novePMeQuarenta: blocos.novePMeQuarenta ? blocos.novePMeQuarenta.format(DATE_TIME_FORMAT) : null,
      novePMeCinquenta: blocos.novePMeCinquenta ? blocos.novePMeCinquenta.format(DATE_TIME_FORMAT) : null,
      dezAM: blocos.dezAM ? blocos.dezAM.format(DATE_TIME_FORMAT) : null,
      dezAMeDez: blocos.dezAMeDez ? blocos.dezAMeDez.format(DATE_TIME_FORMAT) : null,
      dezAMeVinte: blocos.dezAMeVinte ? blocos.dezAMeVinte.format(DATE_TIME_FORMAT) : null,
      dezAMeTrinta: blocos.dezAMeTrinta ? blocos.dezAMeTrinta.format(DATE_TIME_FORMAT) : null,
      dezAMeQuarenta: blocos.dezAMeQuarenta ? blocos.dezAMeQuarenta.format(DATE_TIME_FORMAT) : null,
      dezAMeCinquenta: blocos.dezAMeCinquenta ? blocos.dezAMeCinquenta.format(DATE_TIME_FORMAT) : null,
      dezPM: blocos.dezPM ? blocos.dezPM.format(DATE_TIME_FORMAT) : null,
      dezPMeDez: blocos.dezPMeDez ? blocos.dezPMeDez.format(DATE_TIME_FORMAT) : null,
      dezPMeVinte: blocos.dezPMeVinte ? blocos.dezPMeVinte.format(DATE_TIME_FORMAT) : null,
      dezPMeTrinta: blocos.dezPMeTrinta ? blocos.dezPMeTrinta.format(DATE_TIME_FORMAT) : null,
      dezPMeQuarenta: blocos.dezPMeQuarenta ? blocos.dezPMeQuarenta.format(DATE_TIME_FORMAT) : null,
      dezPMeCinquenta: blocos.dezPMeCinquenta ? blocos.dezPMeCinquenta.format(DATE_TIME_FORMAT) : null,
      onzeAM: blocos.onzeAM ? blocos.onzeAM.format(DATE_TIME_FORMAT) : null,
      onzeAMeDez: blocos.onzeAMeDez ? blocos.onzeAMeDez.format(DATE_TIME_FORMAT) : null,
      onzeAMeVinte: blocos.onzeAMeVinte ? blocos.onzeAMeVinte.format(DATE_TIME_FORMAT) : null,
      onzeAMeTrinta: blocos.onzeAMeTrinta ? blocos.onzeAMeTrinta.format(DATE_TIME_FORMAT) : null,
      onzeAMeQuarenta: blocos.onzeAMeQuarenta ? blocos.onzeAMeQuarenta.format(DATE_TIME_FORMAT) : null,
      onzeAMeCinquenta: blocos.onzeAMeCinquenta ? blocos.onzeAMeCinquenta.format(DATE_TIME_FORMAT) : null,
      onzePM: blocos.onzePM ? blocos.onzePM.format(DATE_TIME_FORMAT) : null,
      onzePMeDez: blocos.onzePMeDez ? blocos.onzePMeDez.format(DATE_TIME_FORMAT) : null,
      onzePMeVinte: blocos.onzePMeVinte ? blocos.onzePMeVinte.format(DATE_TIME_FORMAT) : null,
      onzePMeTrinta: blocos.onzePMeTrinta ? blocos.onzePMeTrinta.format(DATE_TIME_FORMAT) : null,
      onzePMeQuarenta: blocos.onzePMeQuarenta ? blocos.onzePMeQuarenta.format(DATE_TIME_FORMAT) : null,
      onzePMeCinquenta: blocos.onzePMeCinquenta ? blocos.onzePMeCinquenta.format(DATE_TIME_FORMAT) : null,
      atividades: blocos.atividades,
      cronogramaDiario: blocos.cronogramaDiario,
    });

    this.atividadesSharedCollection = this.atividadeService.addAtividadeToCollectionIfMissing(
      this.atividadesSharedCollection,
      ...(blocos.atividades ?? [])
    );
    this.cronogramaDiariosSharedCollection = this.cronogramaDiarioService.addCronogramaDiarioToCollectionIfMissing(
      this.cronogramaDiariosSharedCollection,
      blocos.cronogramaDiario
    );
  }

  protected loadRelationshipsOptions(): void {
    this.atividadeService
      .query()
      .pipe(map((res: HttpResponse<IAtividade[]>) => res.body ?? []))
      .pipe(
        map((atividades: IAtividade[]) =>
          this.atividadeService.addAtividadeToCollectionIfMissing(atividades, ...(this.editForm.get('atividades')!.value ?? []))
        )
      )
      .subscribe((atividades: IAtividade[]) => (this.atividadesSharedCollection = atividades));

    this.cronogramaDiarioService
      .query()
      .pipe(map((res: HttpResponse<ICronogramaDiario[]>) => res.body ?? []))
      .pipe(
        map((cronogramaDiarios: ICronogramaDiario[]) =>
          this.cronogramaDiarioService.addCronogramaDiarioToCollectionIfMissing(
            cronogramaDiarios,
            this.editForm.get('cronogramaDiario')!.value
          )
        )
      )
      .subscribe((cronogramaDiarios: ICronogramaDiario[]) => (this.cronogramaDiariosSharedCollection = cronogramaDiarios));
  }

  protected createFromForm(): IBlocos {
    return {
      ...new Blocos(),
      id: this.editForm.get(['id'])!.value,
      zeroAM: this.editForm.get(['zeroAM'])!.value ? dayjs(this.editForm.get(['zeroAM'])!.value, DATE_TIME_FORMAT) : undefined,
      zeroAMeDez: this.editForm.get(['zeroAMeDez'])!.value ? dayjs(this.editForm.get(['zeroAMeDez'])!.value, DATE_TIME_FORMAT) : undefined,
      zeroAMeVinte: this.editForm.get(['zeroAMeVinte'])!.value
        ? dayjs(this.editForm.get(['zeroAMeVinte'])!.value, DATE_TIME_FORMAT)
        : undefined,
      zeroAMeTrinta: this.editForm.get(['zeroAMeTrinta'])!.value
        ? dayjs(this.editForm.get(['zeroAMeTrinta'])!.value, DATE_TIME_FORMAT)
        : undefined,
      zeroAMeQuarenta: this.editForm.get(['zeroAMeQuarenta'])!.value
        ? dayjs(this.editForm.get(['zeroAMeQuarenta'])!.value, DATE_TIME_FORMAT)
        : undefined,
      zeroAMeCinquenta: this.editForm.get(['zeroAMeCinquenta'])!.value
        ? dayjs(this.editForm.get(['zeroAMeCinquenta'])!.value, DATE_TIME_FORMAT)
        : undefined,
      zeroPM: this.editForm.get(['zeroPM'])!.value ? dayjs(this.editForm.get(['zeroPM'])!.value, DATE_TIME_FORMAT) : undefined,
      zeroPMeDez: this.editForm.get(['zeroPMeDez'])!.value ? dayjs(this.editForm.get(['zeroPMeDez'])!.value, DATE_TIME_FORMAT) : undefined,
      zeroPMeVinte: this.editForm.get(['zeroPMeVinte'])!.value
        ? dayjs(this.editForm.get(['zeroPMeVinte'])!.value, DATE_TIME_FORMAT)
        : undefined,
      zeroPMeTrinta: this.editForm.get(['zeroPMeTrinta'])!.value
        ? dayjs(this.editForm.get(['zeroPMeTrinta'])!.value, DATE_TIME_FORMAT)
        : undefined,
      zeroPMeQuarenta: this.editForm.get(['zeroPMeQuarenta'])!.value
        ? dayjs(this.editForm.get(['zeroPMeQuarenta'])!.value, DATE_TIME_FORMAT)
        : undefined,
      zeroPMeCinquenta: this.editForm.get(['zeroPMeCinquenta'])!.value
        ? dayjs(this.editForm.get(['zeroPMeCinquenta'])!.value, DATE_TIME_FORMAT)
        : undefined,
      umAM: this.editForm.get(['umAM'])!.value ? dayjs(this.editForm.get(['umAM'])!.value, DATE_TIME_FORMAT) : undefined,
      umAMeDez: this.editForm.get(['umAMeDez'])!.value ? dayjs(this.editForm.get(['umAMeDez'])!.value, DATE_TIME_FORMAT) : undefined,
      umAMeVinte: this.editForm.get(['umAMeVinte'])!.value ? dayjs(this.editForm.get(['umAMeVinte'])!.value, DATE_TIME_FORMAT) : undefined,
      umAMeTrinta: this.editForm.get(['umAMeTrinta'])!.value
        ? dayjs(this.editForm.get(['umAMeTrinta'])!.value, DATE_TIME_FORMAT)
        : undefined,
      umAMeQuarenta: this.editForm.get(['umAMeQuarenta'])!.value
        ? dayjs(this.editForm.get(['umAMeQuarenta'])!.value, DATE_TIME_FORMAT)
        : undefined,
      umAMeCinquenta: this.editForm.get(['umAMeCinquenta'])!.value
        ? dayjs(this.editForm.get(['umAMeCinquenta'])!.value, DATE_TIME_FORMAT)
        : undefined,
      umPM: this.editForm.get(['umPM'])!.value ? dayjs(this.editForm.get(['umPM'])!.value, DATE_TIME_FORMAT) : undefined,
      umPMeDez: this.editForm.get(['umPMeDez'])!.value ? dayjs(this.editForm.get(['umPMeDez'])!.value, DATE_TIME_FORMAT) : undefined,
      umPMeVinte: this.editForm.get(['umPMeVinte'])!.value ? dayjs(this.editForm.get(['umPMeVinte'])!.value, DATE_TIME_FORMAT) : undefined,
      umPMeTrinta: this.editForm.get(['umPMeTrinta'])!.value
        ? dayjs(this.editForm.get(['umPMeTrinta'])!.value, DATE_TIME_FORMAT)
        : undefined,
      umPMeQuarenta: this.editForm.get(['umPMeQuarenta'])!.value
        ? dayjs(this.editForm.get(['umPMeQuarenta'])!.value, DATE_TIME_FORMAT)
        : undefined,
      umPMeCinquenta: this.editForm.get(['umPMeCinquenta'])!.value
        ? dayjs(this.editForm.get(['umPMeCinquenta'])!.value, DATE_TIME_FORMAT)
        : undefined,
      doisAM: this.editForm.get(['doisAM'])!.value ? dayjs(this.editForm.get(['doisAM'])!.value, DATE_TIME_FORMAT) : undefined,
      doisAMeDez: this.editForm.get(['doisAMeDez'])!.value ? dayjs(this.editForm.get(['doisAMeDez'])!.value, DATE_TIME_FORMAT) : undefined,
      doisAMeVinte: this.editForm.get(['doisAMeVinte'])!.value
        ? dayjs(this.editForm.get(['doisAMeVinte'])!.value, DATE_TIME_FORMAT)
        : undefined,
      doisAMeTrinta: this.editForm.get(['doisAMeTrinta'])!.value
        ? dayjs(this.editForm.get(['doisAMeTrinta'])!.value, DATE_TIME_FORMAT)
        : undefined,
      doisAMeQuarenta: this.editForm.get(['doisAMeQuarenta'])!.value
        ? dayjs(this.editForm.get(['doisAMeQuarenta'])!.value, DATE_TIME_FORMAT)
        : undefined,
      doisAMeCinquenta: this.editForm.get(['doisAMeCinquenta'])!.value
        ? dayjs(this.editForm.get(['doisAMeCinquenta'])!.value, DATE_TIME_FORMAT)
        : undefined,
      doisPM: this.editForm.get(['doisPM'])!.value ? dayjs(this.editForm.get(['doisPM'])!.value, DATE_TIME_FORMAT) : undefined,
      doisPMeDez: this.editForm.get(['doisPMeDez'])!.value ? dayjs(this.editForm.get(['doisPMeDez'])!.value, DATE_TIME_FORMAT) : undefined,
      doisPMeVinte: this.editForm.get(['doisPMeVinte'])!.value
        ? dayjs(this.editForm.get(['doisPMeVinte'])!.value, DATE_TIME_FORMAT)
        : undefined,
      doisPMeTrinta: this.editForm.get(['doisPMeTrinta'])!.value
        ? dayjs(this.editForm.get(['doisPMeTrinta'])!.value, DATE_TIME_FORMAT)
        : undefined,
      doisPMeQuarenta: this.editForm.get(['doisPMeQuarenta'])!.value
        ? dayjs(this.editForm.get(['doisPMeQuarenta'])!.value, DATE_TIME_FORMAT)
        : undefined,
      doisPMeCinquenta: this.editForm.get(['doisPMeCinquenta'])!.value
        ? dayjs(this.editForm.get(['doisPMeCinquenta'])!.value, DATE_TIME_FORMAT)
        : undefined,
      tresAM: this.editForm.get(['tresAM'])!.value ? dayjs(this.editForm.get(['tresAM'])!.value, DATE_TIME_FORMAT) : undefined,
      tresAMeDez: this.editForm.get(['tresAMeDez'])!.value ? dayjs(this.editForm.get(['tresAMeDez'])!.value, DATE_TIME_FORMAT) : undefined,
      tresAMeVinte: this.editForm.get(['tresAMeVinte'])!.value
        ? dayjs(this.editForm.get(['tresAMeVinte'])!.value, DATE_TIME_FORMAT)
        : undefined,
      tresAMeTrinta: this.editForm.get(['tresAMeTrinta'])!.value
        ? dayjs(this.editForm.get(['tresAMeTrinta'])!.value, DATE_TIME_FORMAT)
        : undefined,
      tresAMeQuarenta: this.editForm.get(['tresAMeQuarenta'])!.value
        ? dayjs(this.editForm.get(['tresAMeQuarenta'])!.value, DATE_TIME_FORMAT)
        : undefined,
      tresAMeCinquenta: this.editForm.get(['tresAMeCinquenta'])!.value
        ? dayjs(this.editForm.get(['tresAMeCinquenta'])!.value, DATE_TIME_FORMAT)
        : undefined,
      tresPM: this.editForm.get(['tresPM'])!.value ? dayjs(this.editForm.get(['tresPM'])!.value, DATE_TIME_FORMAT) : undefined,
      tresPMeDez: this.editForm.get(['tresPMeDez'])!.value ? dayjs(this.editForm.get(['tresPMeDez'])!.value, DATE_TIME_FORMAT) : undefined,
      tresPMeVinte: this.editForm.get(['tresPMeVinte'])!.value
        ? dayjs(this.editForm.get(['tresPMeVinte'])!.value, DATE_TIME_FORMAT)
        : undefined,
      tresPMeTrinta: this.editForm.get(['tresPMeTrinta'])!.value
        ? dayjs(this.editForm.get(['tresPMeTrinta'])!.value, DATE_TIME_FORMAT)
        : undefined,
      tresPMeQuarenta: this.editForm.get(['tresPMeQuarenta'])!.value
        ? dayjs(this.editForm.get(['tresPMeQuarenta'])!.value, DATE_TIME_FORMAT)
        : undefined,
      tresPMeCinquenta: this.editForm.get(['tresPMeCinquenta'])!.value
        ? dayjs(this.editForm.get(['tresPMeCinquenta'])!.value, DATE_TIME_FORMAT)
        : undefined,
      quatroAM: this.editForm.get(['quatroAM'])!.value ? dayjs(this.editForm.get(['quatroAM'])!.value, DATE_TIME_FORMAT) : undefined,
      quatroAMeDez: this.editForm.get(['quatroAMeDez'])!.value
        ? dayjs(this.editForm.get(['quatroAMeDez'])!.value, DATE_TIME_FORMAT)
        : undefined,
      quatroAMeVinte: this.editForm.get(['quatroAMeVinte'])!.value
        ? dayjs(this.editForm.get(['quatroAMeVinte'])!.value, DATE_TIME_FORMAT)
        : undefined,
      quatroAMeTrinta: this.editForm.get(['quatroAMeTrinta'])!.value
        ? dayjs(this.editForm.get(['quatroAMeTrinta'])!.value, DATE_TIME_FORMAT)
        : undefined,
      quatroAMeQuarenta: this.editForm.get(['quatroAMeQuarenta'])!.value
        ? dayjs(this.editForm.get(['quatroAMeQuarenta'])!.value, DATE_TIME_FORMAT)
        : undefined,
      quatroAMeCinquenta: this.editForm.get(['quatroAMeCinquenta'])!.value
        ? dayjs(this.editForm.get(['quatroAMeCinquenta'])!.value, DATE_TIME_FORMAT)
        : undefined,
      quatroPM: this.editForm.get(['quatroPM'])!.value ? dayjs(this.editForm.get(['quatroPM'])!.value, DATE_TIME_FORMAT) : undefined,
      quatroPMeDez: this.editForm.get(['quatroPMeDez'])!.value
        ? dayjs(this.editForm.get(['quatroPMeDez'])!.value, DATE_TIME_FORMAT)
        : undefined,
      quatroPMeVinte: this.editForm.get(['quatroPMeVinte'])!.value
        ? dayjs(this.editForm.get(['quatroPMeVinte'])!.value, DATE_TIME_FORMAT)
        : undefined,
      quatroPMeTrinta: this.editForm.get(['quatroPMeTrinta'])!.value
        ? dayjs(this.editForm.get(['quatroPMeTrinta'])!.value, DATE_TIME_FORMAT)
        : undefined,
      quatroPMeQuarenta: this.editForm.get(['quatroPMeQuarenta'])!.value
        ? dayjs(this.editForm.get(['quatroPMeQuarenta'])!.value, DATE_TIME_FORMAT)
        : undefined,
      quatroPMeCinquenta: this.editForm.get(['quatroPMeCinquenta'])!.value
        ? dayjs(this.editForm.get(['quatroPMeCinquenta'])!.value, DATE_TIME_FORMAT)
        : undefined,
      cincoAM: this.editForm.get(['cincoAM'])!.value ? dayjs(this.editForm.get(['cincoAM'])!.value, DATE_TIME_FORMAT) : undefined,
      cincoAMeDez: this.editForm.get(['cincoAMeDez'])!.value
        ? dayjs(this.editForm.get(['cincoAMeDez'])!.value, DATE_TIME_FORMAT)
        : undefined,
      cincoAMeVinte: this.editForm.get(['cincoAMeVinte'])!.value
        ? dayjs(this.editForm.get(['cincoAMeVinte'])!.value, DATE_TIME_FORMAT)
        : undefined,
      cincoAMeTrinta: this.editForm.get(['cincoAMeTrinta'])!.value
        ? dayjs(this.editForm.get(['cincoAMeTrinta'])!.value, DATE_TIME_FORMAT)
        : undefined,
      cincoAMeQuarenta: this.editForm.get(['cincoAMeQuarenta'])!.value
        ? dayjs(this.editForm.get(['cincoAMeQuarenta'])!.value, DATE_TIME_FORMAT)
        : undefined,
      cincoAMeCinquenta: this.editForm.get(['cincoAMeCinquenta'])!.value
        ? dayjs(this.editForm.get(['cincoAMeCinquenta'])!.value, DATE_TIME_FORMAT)
        : undefined,
      cincoPM: this.editForm.get(['cincoPM'])!.value ? dayjs(this.editForm.get(['cincoPM'])!.value, DATE_TIME_FORMAT) : undefined,
      cincoPMeDez: this.editForm.get(['cincoPMeDez'])!.value
        ? dayjs(this.editForm.get(['cincoPMeDez'])!.value, DATE_TIME_FORMAT)
        : undefined,
      cincoPMeVinte: this.editForm.get(['cincoPMeVinte'])!.value
        ? dayjs(this.editForm.get(['cincoPMeVinte'])!.value, DATE_TIME_FORMAT)
        : undefined,
      cincoPMeTrinta: this.editForm.get(['cincoPMeTrinta'])!.value
        ? dayjs(this.editForm.get(['cincoPMeTrinta'])!.value, DATE_TIME_FORMAT)
        : undefined,
      cincoPMeQuarenta: this.editForm.get(['cincoPMeQuarenta'])!.value
        ? dayjs(this.editForm.get(['cincoPMeQuarenta'])!.value, DATE_TIME_FORMAT)
        : undefined,
      cincoPMeCinquenta: this.editForm.get(['cincoPMeCinquenta'])!.value
        ? dayjs(this.editForm.get(['cincoPMeCinquenta'])!.value, DATE_TIME_FORMAT)
        : undefined,
      seisAM: this.editForm.get(['seisAM'])!.value ? dayjs(this.editForm.get(['seisAM'])!.value, DATE_TIME_FORMAT) : undefined,
      seisAMeDez: this.editForm.get(['seisAMeDez'])!.value ? dayjs(this.editForm.get(['seisAMeDez'])!.value, DATE_TIME_FORMAT) : undefined,
      seisAMeVinte: this.editForm.get(['seisAMeVinte'])!.value
        ? dayjs(this.editForm.get(['seisAMeVinte'])!.value, DATE_TIME_FORMAT)
        : undefined,
      seisAMeTrinta: this.editForm.get(['seisAMeTrinta'])!.value
        ? dayjs(this.editForm.get(['seisAMeTrinta'])!.value, DATE_TIME_FORMAT)
        : undefined,
      seisAMeQuarenta: this.editForm.get(['seisAMeQuarenta'])!.value
        ? dayjs(this.editForm.get(['seisAMeQuarenta'])!.value, DATE_TIME_FORMAT)
        : undefined,
      seisAMeCinquenta: this.editForm.get(['seisAMeCinquenta'])!.value
        ? dayjs(this.editForm.get(['seisAMeCinquenta'])!.value, DATE_TIME_FORMAT)
        : undefined,
      seisPM: this.editForm.get(['seisPM'])!.value ? dayjs(this.editForm.get(['seisPM'])!.value, DATE_TIME_FORMAT) : undefined,
      seisPMeDez: this.editForm.get(['seisPMeDez'])!.value ? dayjs(this.editForm.get(['seisPMeDez'])!.value, DATE_TIME_FORMAT) : undefined,
      seisPMeVinte: this.editForm.get(['seisPMeVinte'])!.value
        ? dayjs(this.editForm.get(['seisPMeVinte'])!.value, DATE_TIME_FORMAT)
        : undefined,
      seisPMeTrinta: this.editForm.get(['seisPMeTrinta'])!.value
        ? dayjs(this.editForm.get(['seisPMeTrinta'])!.value, DATE_TIME_FORMAT)
        : undefined,
      seisPMeQuarenta: this.editForm.get(['seisPMeQuarenta'])!.value
        ? dayjs(this.editForm.get(['seisPMeQuarenta'])!.value, DATE_TIME_FORMAT)
        : undefined,
      seisPMeCinquenta: this.editForm.get(['seisPMeCinquenta'])!.value
        ? dayjs(this.editForm.get(['seisPMeCinquenta'])!.value, DATE_TIME_FORMAT)
        : undefined,
      seteAM: this.editForm.get(['seteAM'])!.value ? dayjs(this.editForm.get(['seteAM'])!.value, DATE_TIME_FORMAT) : undefined,
      seteAMeDez: this.editForm.get(['seteAMeDez'])!.value ? dayjs(this.editForm.get(['seteAMeDez'])!.value, DATE_TIME_FORMAT) : undefined,
      seteAMeVinte: this.editForm.get(['seteAMeVinte'])!.value
        ? dayjs(this.editForm.get(['seteAMeVinte'])!.value, DATE_TIME_FORMAT)
        : undefined,
      seteAMeTrinta: this.editForm.get(['seteAMeTrinta'])!.value
        ? dayjs(this.editForm.get(['seteAMeTrinta'])!.value, DATE_TIME_FORMAT)
        : undefined,
      seteAMeQuarenta: this.editForm.get(['seteAMeQuarenta'])!.value
        ? dayjs(this.editForm.get(['seteAMeQuarenta'])!.value, DATE_TIME_FORMAT)
        : undefined,
      seteAMeCinquenta: this.editForm.get(['seteAMeCinquenta'])!.value
        ? dayjs(this.editForm.get(['seteAMeCinquenta'])!.value, DATE_TIME_FORMAT)
        : undefined,
      setePM: this.editForm.get(['setePM'])!.value ? dayjs(this.editForm.get(['setePM'])!.value, DATE_TIME_FORMAT) : undefined,
      setePMeDez: this.editForm.get(['setePMeDez'])!.value ? dayjs(this.editForm.get(['setePMeDez'])!.value, DATE_TIME_FORMAT) : undefined,
      setePMeVinte: this.editForm.get(['setePMeVinte'])!.value
        ? dayjs(this.editForm.get(['setePMeVinte'])!.value, DATE_TIME_FORMAT)
        : undefined,
      setePMeTrinta: this.editForm.get(['setePMeTrinta'])!.value
        ? dayjs(this.editForm.get(['setePMeTrinta'])!.value, DATE_TIME_FORMAT)
        : undefined,
      setePMeQuarenta: this.editForm.get(['setePMeQuarenta'])!.value
        ? dayjs(this.editForm.get(['setePMeQuarenta'])!.value, DATE_TIME_FORMAT)
        : undefined,
      setePMeCinquenta: this.editForm.get(['setePMeCinquenta'])!.value
        ? dayjs(this.editForm.get(['setePMeCinquenta'])!.value, DATE_TIME_FORMAT)
        : undefined,
      oitoAM: this.editForm.get(['oitoAM'])!.value ? dayjs(this.editForm.get(['oitoAM'])!.value, DATE_TIME_FORMAT) : undefined,
      oitoAMeDez: this.editForm.get(['oitoAMeDez'])!.value ? dayjs(this.editForm.get(['oitoAMeDez'])!.value, DATE_TIME_FORMAT) : undefined,
      oitoAMeVinte: this.editForm.get(['oitoAMeVinte'])!.value
        ? dayjs(this.editForm.get(['oitoAMeVinte'])!.value, DATE_TIME_FORMAT)
        : undefined,
      oitoAMeTrinta: this.editForm.get(['oitoAMeTrinta'])!.value
        ? dayjs(this.editForm.get(['oitoAMeTrinta'])!.value, DATE_TIME_FORMAT)
        : undefined,
      oitoAMeQuarenta: this.editForm.get(['oitoAMeQuarenta'])!.value
        ? dayjs(this.editForm.get(['oitoAMeQuarenta'])!.value, DATE_TIME_FORMAT)
        : undefined,
      oitoAMeCinquenta: this.editForm.get(['oitoAMeCinquenta'])!.value
        ? dayjs(this.editForm.get(['oitoAMeCinquenta'])!.value, DATE_TIME_FORMAT)
        : undefined,
      oitoPM: this.editForm.get(['oitoPM'])!.value ? dayjs(this.editForm.get(['oitoPM'])!.value, DATE_TIME_FORMAT) : undefined,
      oitoPMeDez: this.editForm.get(['oitoPMeDez'])!.value ? dayjs(this.editForm.get(['oitoPMeDez'])!.value, DATE_TIME_FORMAT) : undefined,
      oitoPMeVinte: this.editForm.get(['oitoPMeVinte'])!.value
        ? dayjs(this.editForm.get(['oitoPMeVinte'])!.value, DATE_TIME_FORMAT)
        : undefined,
      oitoPMeTrinta: this.editForm.get(['oitoPMeTrinta'])!.value
        ? dayjs(this.editForm.get(['oitoPMeTrinta'])!.value, DATE_TIME_FORMAT)
        : undefined,
      oitoPMeQuarenta: this.editForm.get(['oitoPMeQuarenta'])!.value
        ? dayjs(this.editForm.get(['oitoPMeQuarenta'])!.value, DATE_TIME_FORMAT)
        : undefined,
      oitoPMeCinquenta: this.editForm.get(['oitoPMeCinquenta'])!.value
        ? dayjs(this.editForm.get(['oitoPMeCinquenta'])!.value, DATE_TIME_FORMAT)
        : undefined,
      noveAM: this.editForm.get(['noveAM'])!.value ? dayjs(this.editForm.get(['noveAM'])!.value, DATE_TIME_FORMAT) : undefined,
      noveAMeDez: this.editForm.get(['noveAMeDez'])!.value ? dayjs(this.editForm.get(['noveAMeDez'])!.value, DATE_TIME_FORMAT) : undefined,
      noveAMeVinte: this.editForm.get(['noveAMeVinte'])!.value
        ? dayjs(this.editForm.get(['noveAMeVinte'])!.value, DATE_TIME_FORMAT)
        : undefined,
      noveAMeTrinta: this.editForm.get(['noveAMeTrinta'])!.value
        ? dayjs(this.editForm.get(['noveAMeTrinta'])!.value, DATE_TIME_FORMAT)
        : undefined,
      noveAMeQuarenta: this.editForm.get(['noveAMeQuarenta'])!.value
        ? dayjs(this.editForm.get(['noveAMeQuarenta'])!.value, DATE_TIME_FORMAT)
        : undefined,
      noveAMeCinquenta: this.editForm.get(['noveAMeCinquenta'])!.value
        ? dayjs(this.editForm.get(['noveAMeCinquenta'])!.value, DATE_TIME_FORMAT)
        : undefined,
      novePM: this.editForm.get(['novePM'])!.value ? dayjs(this.editForm.get(['novePM'])!.value, DATE_TIME_FORMAT) : undefined,
      novePMeDez: this.editForm.get(['novePMeDez'])!.value ? dayjs(this.editForm.get(['novePMeDez'])!.value, DATE_TIME_FORMAT) : undefined,
      novePMeVinte: this.editForm.get(['novePMeVinte'])!.value
        ? dayjs(this.editForm.get(['novePMeVinte'])!.value, DATE_TIME_FORMAT)
        : undefined,
      novePMeTrinta: this.editForm.get(['novePMeTrinta'])!.value
        ? dayjs(this.editForm.get(['novePMeTrinta'])!.value, DATE_TIME_FORMAT)
        : undefined,
      novePMeQuarenta: this.editForm.get(['novePMeQuarenta'])!.value
        ? dayjs(this.editForm.get(['novePMeQuarenta'])!.value, DATE_TIME_FORMAT)
        : undefined,
      novePMeCinquenta: this.editForm.get(['novePMeCinquenta'])!.value
        ? dayjs(this.editForm.get(['novePMeCinquenta'])!.value, DATE_TIME_FORMAT)
        : undefined,
      dezAM: this.editForm.get(['dezAM'])!.value ? dayjs(this.editForm.get(['dezAM'])!.value, DATE_TIME_FORMAT) : undefined,
      dezAMeDez: this.editForm.get(['dezAMeDez'])!.value ? dayjs(this.editForm.get(['dezAMeDez'])!.value, DATE_TIME_FORMAT) : undefined,
      dezAMeVinte: this.editForm.get(['dezAMeVinte'])!.value
        ? dayjs(this.editForm.get(['dezAMeVinte'])!.value, DATE_TIME_FORMAT)
        : undefined,
      dezAMeTrinta: this.editForm.get(['dezAMeTrinta'])!.value
        ? dayjs(this.editForm.get(['dezAMeTrinta'])!.value, DATE_TIME_FORMAT)
        : undefined,
      dezAMeQuarenta: this.editForm.get(['dezAMeQuarenta'])!.value
        ? dayjs(this.editForm.get(['dezAMeQuarenta'])!.value, DATE_TIME_FORMAT)
        : undefined,
      dezAMeCinquenta: this.editForm.get(['dezAMeCinquenta'])!.value
        ? dayjs(this.editForm.get(['dezAMeCinquenta'])!.value, DATE_TIME_FORMAT)
        : undefined,
      dezPM: this.editForm.get(['dezPM'])!.value ? dayjs(this.editForm.get(['dezPM'])!.value, DATE_TIME_FORMAT) : undefined,
      dezPMeDez: this.editForm.get(['dezPMeDez'])!.value ? dayjs(this.editForm.get(['dezPMeDez'])!.value, DATE_TIME_FORMAT) : undefined,
      dezPMeVinte: this.editForm.get(['dezPMeVinte'])!.value
        ? dayjs(this.editForm.get(['dezPMeVinte'])!.value, DATE_TIME_FORMAT)
        : undefined,
      dezPMeTrinta: this.editForm.get(['dezPMeTrinta'])!.value
        ? dayjs(this.editForm.get(['dezPMeTrinta'])!.value, DATE_TIME_FORMAT)
        : undefined,
      dezPMeQuarenta: this.editForm.get(['dezPMeQuarenta'])!.value
        ? dayjs(this.editForm.get(['dezPMeQuarenta'])!.value, DATE_TIME_FORMAT)
        : undefined,
      dezPMeCinquenta: this.editForm.get(['dezPMeCinquenta'])!.value
        ? dayjs(this.editForm.get(['dezPMeCinquenta'])!.value, DATE_TIME_FORMAT)
        : undefined,
      onzeAM: this.editForm.get(['onzeAM'])!.value ? dayjs(this.editForm.get(['onzeAM'])!.value, DATE_TIME_FORMAT) : undefined,
      onzeAMeDez: this.editForm.get(['onzeAMeDez'])!.value ? dayjs(this.editForm.get(['onzeAMeDez'])!.value, DATE_TIME_FORMAT) : undefined,
      onzeAMeVinte: this.editForm.get(['onzeAMeVinte'])!.value
        ? dayjs(this.editForm.get(['onzeAMeVinte'])!.value, DATE_TIME_FORMAT)
        : undefined,
      onzeAMeTrinta: this.editForm.get(['onzeAMeTrinta'])!.value
        ? dayjs(this.editForm.get(['onzeAMeTrinta'])!.value, DATE_TIME_FORMAT)
        : undefined,
      onzeAMeQuarenta: this.editForm.get(['onzeAMeQuarenta'])!.value
        ? dayjs(this.editForm.get(['onzeAMeQuarenta'])!.value, DATE_TIME_FORMAT)
        : undefined,
      onzeAMeCinquenta: this.editForm.get(['onzeAMeCinquenta'])!.value
        ? dayjs(this.editForm.get(['onzeAMeCinquenta'])!.value, DATE_TIME_FORMAT)
        : undefined,
      onzePM: this.editForm.get(['onzePM'])!.value ? dayjs(this.editForm.get(['onzePM'])!.value, DATE_TIME_FORMAT) : undefined,
      onzePMeDez: this.editForm.get(['onzePMeDez'])!.value ? dayjs(this.editForm.get(['onzePMeDez'])!.value, DATE_TIME_FORMAT) : undefined,
      onzePMeVinte: this.editForm.get(['onzePMeVinte'])!.value
        ? dayjs(this.editForm.get(['onzePMeVinte'])!.value, DATE_TIME_FORMAT)
        : undefined,
      onzePMeTrinta: this.editForm.get(['onzePMeTrinta'])!.value
        ? dayjs(this.editForm.get(['onzePMeTrinta'])!.value, DATE_TIME_FORMAT)
        : undefined,
      onzePMeQuarenta: this.editForm.get(['onzePMeQuarenta'])!.value
        ? dayjs(this.editForm.get(['onzePMeQuarenta'])!.value, DATE_TIME_FORMAT)
        : undefined,
      onzePMeCinquenta: this.editForm.get(['onzePMeCinquenta'])!.value
        ? dayjs(this.editForm.get(['onzePMeCinquenta'])!.value, DATE_TIME_FORMAT)
        : undefined,
      atividades: this.editForm.get(['atividades'])!.value,
      cronogramaDiario: this.editForm.get(['cronogramaDiario'])!.value,
    };
  }
}
