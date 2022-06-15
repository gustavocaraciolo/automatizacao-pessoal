import { Component, Inject, LOCALE_ID, OnInit } from '@angular/core';
import dayjs from 'dayjs/esm';
import { DATE_FORMAT, DATE_FORMAT_DESC, DATE_TIME_FORMAT } from '../config/input.constants';
import { CronogramaDiario, ICronogramaDiario } from '../entities/cronograma-diario/cronograma-diario.model';
import { BlocosService } from '../entities/blocos/service/blocos.service';
import { CronogramaDiarioService } from '../entities/cronograma-diario/service/cronograma-diario.service';
import { ActivatedRoute } from '@angular/router';
import { FormBuilder } from '@angular/forms';
import { Observable } from 'rxjs';
import { HttpResponse } from '@angular/common/http';
import { Blocos, IBlocos } from '../entities/blocos/blocos.model';
import { finalize, map } from 'rxjs/operators';

@Component({
  selector: 'jhi-conograma-diario',
  templateUrl: './conograma-diario.component.html',
  styleUrls: ['./conograma-diario.component.scss'],
})
export class ConogramaDiarioComponent implements OnInit {
  dia: string;
  data: dayjs.Dayjs;

  isSaving = false;
  isLoading = false;

  cronogramaDiariosSharedCollection: ICronogramaDiario[] = [];
  cronogramaDiario: ICronogramaDiario | null = null;

  editFormBlocos = this.fb.group({
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
    cronogramaDiario: [],
  });
  editFormCronogramaDiario = this.fb.group({
    id: [],
    dia: [],
  });

  constructor(@Inject(LOCALE_ID) private locale: string,
              protected blocosService: BlocosService,
              protected cronogramaDiarioService: CronogramaDiarioService,
              protected activatedRoute: ActivatedRoute,
              protected fb: FormBuilder) {
    this.data = dayjs().startOf('day');
    this.dia = this.data.format(DATE_FORMAT_DESC);
  }

  ngOnInit(): void {
    this.loadCronogramaDiario();
    this.activatedRoute.data.subscribe(({blocos}) => {
      /*if (blocos.id === undefined) {
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
      }*/

      // this.updateFormBlocos(blocos);

      // this.loadRelationshipsOptions();
    });

  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.saveCronogramaDiario();
    this.saveBlocos();
  }

  //* BLOCOS */
  saveBlocos(): void {
    this.isSaving = true;
    const blocos = this.createFromFormBlocos();
    if (blocos.id !== undefined) {
      this.subscribeToSaveResponseCronogramaDiario(this.blocosService.update(blocos));
    } else {
      this.subscribeToSaveResponseCronogramaDiario(this.blocosService.create(blocos));
    }
  }

  protected onSaveSuccess(): void {
    console.log('sucesso');
    // this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateFormBlocos(blocos: IBlocos): void {
    this.editFormBlocos.patchValue({
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
      cronogramaDiario: blocos.cronogramaDiario,
    });

    this.cronogramaDiariosSharedCollection = this.cronogramaDiarioService.addCronogramaDiarioToCollectionIfMissing(
      this.cronogramaDiariosSharedCollection,
      blocos.cronogramaDiario
    );
  }

  protected loadRelationshipsOptions(): void {
    this.cronogramaDiarioService
      .query()
      .pipe(map((res: HttpResponse<ICronogramaDiario[]>) => res.body ?? []))
      .pipe(
        map((cronogramaDiarios: ICronogramaDiario[]) =>
          this.cronogramaDiarioService.addCronogramaDiarioToCollectionIfMissing(
            cronogramaDiarios,
            this.editFormBlocos.get('cronogramaDiario')!.value
          )
        )
      )
      .subscribe((cronogramaDiarios: ICronogramaDiario[]) => (this.cronogramaDiariosSharedCollection = cronogramaDiarios));
  }

  protected createFromFormBlocos(): IBlocos {
    return {
      ...new Blocos(),
      id: this.editFormBlocos.get(['id'])!.value,
      zeroAM: dayjs().startOf('day'),
      cronogramaDiario: this.cronogramaDiario,
    };
  }

  //* CRONOGRAMA DIÁRIO */
  loadCronogramaDiario(): void {
    this.isLoading = true;
    // '2022-06-14'
    const findByDate = this.data.format(DATE_FORMAT);
    this.cronogramaDiarioService.findByDate(findByDate).subscribe({
      next: (res: HttpResponse<ICronogramaDiario>) => {
        this.isLoading = false;
        this.cronogramaDiario = res.body;
      },
      error: () => {
        this.cronogramaDiario = null;
        this.isLoading = false;
      },
    });
  }

  saveCronogramaDiario(): void {
    this.isSaving = true;
    const cronogramaDiario = this.createFromFormCronogramaDiario();
    if (cronogramaDiario.id !== undefined) {
      this.subscribeToSaveResponseCronogramaDiario(this.cronogramaDiarioService.update(cronogramaDiario));
    } else {
      this.subscribeToSaveResponseCronogramaDiario(this.cronogramaDiarioService.create(cronogramaDiario));
    }
  }

  protected subscribeToSaveResponseCronogramaDiario(result: Observable<HttpResponse<ICronogramaDiario>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected createFromFormCronogramaDiario(): ICronogramaDiario {
    return {
      ...new CronogramaDiario(),
      id: this.cronogramaDiario?.id,
      dia: this.data,
    };
  }

  addDays(): void {
    this.data = this.data.add(1, 'day');
    this.loadCronogramaDiario();
    this.dia = this.data.format(DATE_FORMAT_DESC);
  }

  subDays(): void {
    this.data = this.data.add(-1, 'day');
    this.loadCronogramaDiario();
    this.dia = this.data.format(DATE_FORMAT_DESC);
  }
}
