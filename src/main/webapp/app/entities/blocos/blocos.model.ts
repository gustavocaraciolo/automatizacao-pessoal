import dayjs from 'dayjs/esm';
import { ICronogramaDiario } from 'app/entities/cronograma-diario/cronograma-diario.model';
import { IAtividade } from 'app/entities/atividade/atividade.model';

export interface IBlocos {
  id?: number;
  zeroAM?: dayjs.Dayjs | null;
  zeroAMeDez?: dayjs.Dayjs | null;
  zeroAMeVinte?: dayjs.Dayjs | null;
  zeroAMeTrinta?: dayjs.Dayjs | null;
  zeroAMeQuarenta?: dayjs.Dayjs | null;
  zeroAMeCinquenta?: dayjs.Dayjs | null;
  zeroPM?: dayjs.Dayjs | null;
  zeroPMeDez?: dayjs.Dayjs | null;
  zeroPMeVinte?: dayjs.Dayjs | null;
  zeroPMeTrinta?: dayjs.Dayjs | null;
  zeroPMeQuarenta?: dayjs.Dayjs | null;
  zeroPMeCinquenta?: dayjs.Dayjs | null;
  umAM?: dayjs.Dayjs | null;
  umAMeDez?: dayjs.Dayjs | null;
  umAMeVinte?: dayjs.Dayjs | null;
  umAMeTrinta?: dayjs.Dayjs | null;
  umAMeQuarenta?: dayjs.Dayjs | null;
  umAMeCinquenta?: dayjs.Dayjs | null;
  umPM?: dayjs.Dayjs | null;
  umPMeDez?: dayjs.Dayjs | null;
  umPMeVinte?: dayjs.Dayjs | null;
  umPMeTrinta?: dayjs.Dayjs | null;
  umPMeQuarenta?: dayjs.Dayjs | null;
  umPMeCinquenta?: dayjs.Dayjs | null;
  doisAM?: dayjs.Dayjs | null;
  doisAMeDez?: dayjs.Dayjs | null;
  doisAMeVinte?: dayjs.Dayjs | null;
  doisAMeTrinta?: dayjs.Dayjs | null;
  doisAMeQuarenta?: dayjs.Dayjs | null;
  doisAMeCinquenta?: dayjs.Dayjs | null;
  doisPM?: dayjs.Dayjs | null;
  doisPMeDez?: dayjs.Dayjs | null;
  doisPMeVinte?: dayjs.Dayjs | null;
  doisPMeTrinta?: dayjs.Dayjs | null;
  doisPMeQuarenta?: dayjs.Dayjs | null;
  doisPMeCinquenta?: dayjs.Dayjs | null;
  tresAM?: dayjs.Dayjs | null;
  tresAMeDez?: dayjs.Dayjs | null;
  tresAMeVinte?: dayjs.Dayjs | null;
  tresAMeTrinta?: dayjs.Dayjs | null;
  tresAMeQuarenta?: dayjs.Dayjs | null;
  tresAMeCinquenta?: dayjs.Dayjs | null;
  tresPM?: dayjs.Dayjs | null;
  tresPMeDez?: dayjs.Dayjs | null;
  tresPMeVinte?: dayjs.Dayjs | null;
  tresPMeTrinta?: dayjs.Dayjs | null;
  tresPMeQuarenta?: dayjs.Dayjs | null;
  tresPMeCinquenta?: dayjs.Dayjs | null;
  quatroAM?: dayjs.Dayjs | null;
  quatroAMeDez?: dayjs.Dayjs | null;
  quatroAMeVinte?: dayjs.Dayjs | null;
  quatroAMeTrinta?: dayjs.Dayjs | null;
  quatroAMeQuarenta?: dayjs.Dayjs | null;
  quatroAMeCinquenta?: dayjs.Dayjs | null;
  quatroPM?: dayjs.Dayjs | null;
  quatroPMeDez?: dayjs.Dayjs | null;
  quatroPMeVinte?: dayjs.Dayjs | null;
  quatroPMeTrinta?: dayjs.Dayjs | null;
  quatroPMeQuarenta?: dayjs.Dayjs | null;
  quatroPMeCinquenta?: dayjs.Dayjs | null;
  cincoAM?: dayjs.Dayjs | null;
  cincoAMeDez?: dayjs.Dayjs | null;
  cincoAMeVinte?: dayjs.Dayjs | null;
  cincoAMeTrinta?: dayjs.Dayjs | null;
  cincoAMeQuarenta?: dayjs.Dayjs | null;
  cincoAMeCinquenta?: dayjs.Dayjs | null;
  cincoPM?: dayjs.Dayjs | null;
  cincoPMeDez?: dayjs.Dayjs | null;
  cincoPMeVinte?: dayjs.Dayjs | null;
  cincoPMeTrinta?: dayjs.Dayjs | null;
  cincoPMeQuarenta?: dayjs.Dayjs | null;
  cincoPMeCinquenta?: dayjs.Dayjs | null;
  seisAM?: dayjs.Dayjs | null;
  seisAMeDez?: dayjs.Dayjs | null;
  seisAMeVinte?: dayjs.Dayjs | null;
  seisAMeTrinta?: dayjs.Dayjs | null;
  seisAMeQuarenta?: dayjs.Dayjs | null;
  seisAMeCinquenta?: dayjs.Dayjs | null;
  seisPM?: dayjs.Dayjs | null;
  seisPMeDez?: dayjs.Dayjs | null;
  seisPMeVinte?: dayjs.Dayjs | null;
  seisPMeTrinta?: dayjs.Dayjs | null;
  seisPMeQuarenta?: dayjs.Dayjs | null;
  seisPMeCinquenta?: dayjs.Dayjs | null;
  seteAM?: dayjs.Dayjs | null;
  seteAMeDez?: dayjs.Dayjs | null;
  seteAMeVinte?: dayjs.Dayjs | null;
  seteAMeTrinta?: dayjs.Dayjs | null;
  seteAMeQuarenta?: dayjs.Dayjs | null;
  seteAMeCinquenta?: dayjs.Dayjs | null;
  setePM?: dayjs.Dayjs | null;
  setePMeDez?: dayjs.Dayjs | null;
  setePMeVinte?: dayjs.Dayjs | null;
  setePMeTrinta?: dayjs.Dayjs | null;
  setePMeQuarenta?: dayjs.Dayjs | null;
  setePMeCinquenta?: dayjs.Dayjs | null;
  oitoAM?: dayjs.Dayjs | null;
  oitoAMeDez?: dayjs.Dayjs | null;
  oitoAMeVinte?: dayjs.Dayjs | null;
  oitoAMeTrinta?: dayjs.Dayjs | null;
  oitoAMeQuarenta?: dayjs.Dayjs | null;
  oitoAMeCinquenta?: dayjs.Dayjs | null;
  oitoPM?: dayjs.Dayjs | null;
  oitoPMeDez?: dayjs.Dayjs | null;
  oitoPMeVinte?: dayjs.Dayjs | null;
  oitoPMeTrinta?: dayjs.Dayjs | null;
  oitoPMeQuarenta?: dayjs.Dayjs | null;
  oitoPMeCinquenta?: dayjs.Dayjs | null;
  noveAM?: dayjs.Dayjs | null;
  noveAMeDez?: dayjs.Dayjs | null;
  noveAMeVinte?: dayjs.Dayjs | null;
  noveAMeTrinta?: dayjs.Dayjs | null;
  noveAMeQuarenta?: dayjs.Dayjs | null;
  noveAMeCinquenta?: dayjs.Dayjs | null;
  novePM?: dayjs.Dayjs | null;
  novePMeDez?: dayjs.Dayjs | null;
  novePMeVinte?: dayjs.Dayjs | null;
  novePMeTrinta?: dayjs.Dayjs | null;
  novePMeQuarenta?: dayjs.Dayjs | null;
  novePMeCinquenta?: dayjs.Dayjs | null;
  dezAM?: dayjs.Dayjs | null;
  dezAMeDez?: dayjs.Dayjs | null;
  dezAMeVinte?: dayjs.Dayjs | null;
  dezAMeTrinta?: dayjs.Dayjs | null;
  dezAMeQuarenta?: dayjs.Dayjs | null;
  dezAMeCinquenta?: dayjs.Dayjs | null;
  dezPM?: dayjs.Dayjs | null;
  dezPMeDez?: dayjs.Dayjs | null;
  dezPMeVinte?: dayjs.Dayjs | null;
  dezPMeTrinta?: dayjs.Dayjs | null;
  dezPMeQuarenta?: dayjs.Dayjs | null;
  dezPMeCinquenta?: dayjs.Dayjs | null;
  onzeAM?: dayjs.Dayjs | null;
  onzeAMeDez?: dayjs.Dayjs | null;
  onzeAMeVinte?: dayjs.Dayjs | null;
  onzeAMeTrinta?: dayjs.Dayjs | null;
  onzeAMeQuarenta?: dayjs.Dayjs | null;
  onzeAMeCinquenta?: dayjs.Dayjs | null;
  onzePM?: dayjs.Dayjs | null;
  onzePMeDez?: dayjs.Dayjs | null;
  onzePMeVinte?: dayjs.Dayjs | null;
  onzePMeTrinta?: dayjs.Dayjs | null;
  onzePMeQuarenta?: dayjs.Dayjs | null;
  onzePMeCinquenta?: dayjs.Dayjs | null;
  cronogramaDiario?: ICronogramaDiario | null;
  atividades?: IAtividade[] | null;
}

export class Blocos implements IBlocos {
  constructor(
    public id?: number,
    public zeroAM?: dayjs.Dayjs | null,
    public zeroAMeDez?: dayjs.Dayjs | null,
    public zeroAMeVinte?: dayjs.Dayjs | null,
    public zeroAMeTrinta?: dayjs.Dayjs | null,
    public zeroAMeQuarenta?: dayjs.Dayjs | null,
    public zeroAMeCinquenta?: dayjs.Dayjs | null,
    public zeroPM?: dayjs.Dayjs | null,
    public zeroPMeDez?: dayjs.Dayjs | null,
    public zeroPMeVinte?: dayjs.Dayjs | null,
    public zeroPMeTrinta?: dayjs.Dayjs | null,
    public zeroPMeQuarenta?: dayjs.Dayjs | null,
    public zeroPMeCinquenta?: dayjs.Dayjs | null,
    public umAM?: dayjs.Dayjs | null,
    public umAMeDez?: dayjs.Dayjs | null,
    public umAMeVinte?: dayjs.Dayjs | null,
    public umAMeTrinta?: dayjs.Dayjs | null,
    public umAMeQuarenta?: dayjs.Dayjs | null,
    public umAMeCinquenta?: dayjs.Dayjs | null,
    public umPM?: dayjs.Dayjs | null,
    public umPMeDez?: dayjs.Dayjs | null,
    public umPMeVinte?: dayjs.Dayjs | null,
    public umPMeTrinta?: dayjs.Dayjs | null,
    public umPMeQuarenta?: dayjs.Dayjs | null,
    public umPMeCinquenta?: dayjs.Dayjs | null,
    public doisAM?: dayjs.Dayjs | null,
    public doisAMeDez?: dayjs.Dayjs | null,
    public doisAMeVinte?: dayjs.Dayjs | null,
    public doisAMeTrinta?: dayjs.Dayjs | null,
    public doisAMeQuarenta?: dayjs.Dayjs | null,
    public doisAMeCinquenta?: dayjs.Dayjs | null,
    public doisPM?: dayjs.Dayjs | null,
    public doisPMeDez?: dayjs.Dayjs | null,
    public doisPMeVinte?: dayjs.Dayjs | null,
    public doisPMeTrinta?: dayjs.Dayjs | null,
    public doisPMeQuarenta?: dayjs.Dayjs | null,
    public doisPMeCinquenta?: dayjs.Dayjs | null,
    public tresAM?: dayjs.Dayjs | null,
    public tresAMeDez?: dayjs.Dayjs | null,
    public tresAMeVinte?: dayjs.Dayjs | null,
    public tresAMeTrinta?: dayjs.Dayjs | null,
    public tresAMeQuarenta?: dayjs.Dayjs | null,
    public tresAMeCinquenta?: dayjs.Dayjs | null,
    public tresPM?: dayjs.Dayjs | null,
    public tresPMeDez?: dayjs.Dayjs | null,
    public tresPMeVinte?: dayjs.Dayjs | null,
    public tresPMeTrinta?: dayjs.Dayjs | null,
    public tresPMeQuarenta?: dayjs.Dayjs | null,
    public tresPMeCinquenta?: dayjs.Dayjs | null,
    public quatroAM?: dayjs.Dayjs | null,
    public quatroAMeDez?: dayjs.Dayjs | null,
    public quatroAMeVinte?: dayjs.Dayjs | null,
    public quatroAMeTrinta?: dayjs.Dayjs | null,
    public quatroAMeQuarenta?: dayjs.Dayjs | null,
    public quatroAMeCinquenta?: dayjs.Dayjs | null,
    public quatroPM?: dayjs.Dayjs | null,
    public quatroPMeDez?: dayjs.Dayjs | null,
    public quatroPMeVinte?: dayjs.Dayjs | null,
    public quatroPMeTrinta?: dayjs.Dayjs | null,
    public quatroPMeQuarenta?: dayjs.Dayjs | null,
    public quatroPMeCinquenta?: dayjs.Dayjs | null,
    public cincoAM?: dayjs.Dayjs | null,
    public cincoAMeDez?: dayjs.Dayjs | null,
    public cincoAMeVinte?: dayjs.Dayjs | null,
    public cincoAMeTrinta?: dayjs.Dayjs | null,
    public cincoAMeQuarenta?: dayjs.Dayjs | null,
    public cincoAMeCinquenta?: dayjs.Dayjs | null,
    public cincoPM?: dayjs.Dayjs | null,
    public cincoPMeDez?: dayjs.Dayjs | null,
    public cincoPMeVinte?: dayjs.Dayjs | null,
    public cincoPMeTrinta?: dayjs.Dayjs | null,
    public cincoPMeQuarenta?: dayjs.Dayjs | null,
    public cincoPMeCinquenta?: dayjs.Dayjs | null,
    public seisAM?: dayjs.Dayjs | null,
    public seisAMeDez?: dayjs.Dayjs | null,
    public seisAMeVinte?: dayjs.Dayjs | null,
    public seisAMeTrinta?: dayjs.Dayjs | null,
    public seisAMeQuarenta?: dayjs.Dayjs | null,
    public seisAMeCinquenta?: dayjs.Dayjs | null,
    public seisPM?: dayjs.Dayjs | null,
    public seisPMeDez?: dayjs.Dayjs | null,
    public seisPMeVinte?: dayjs.Dayjs | null,
    public seisPMeTrinta?: dayjs.Dayjs | null,
    public seisPMeQuarenta?: dayjs.Dayjs | null,
    public seisPMeCinquenta?: dayjs.Dayjs | null,
    public seteAM?: dayjs.Dayjs | null,
    public seteAMeDez?: dayjs.Dayjs | null,
    public seteAMeVinte?: dayjs.Dayjs | null,
    public seteAMeTrinta?: dayjs.Dayjs | null,
    public seteAMeQuarenta?: dayjs.Dayjs | null,
    public seteAMeCinquenta?: dayjs.Dayjs | null,
    public setePM?: dayjs.Dayjs | null,
    public setePMeDez?: dayjs.Dayjs | null,
    public setePMeVinte?: dayjs.Dayjs | null,
    public setePMeTrinta?: dayjs.Dayjs | null,
    public setePMeQuarenta?: dayjs.Dayjs | null,
    public setePMeCinquenta?: dayjs.Dayjs | null,
    public oitoAM?: dayjs.Dayjs | null,
    public oitoAMeDez?: dayjs.Dayjs | null,
    public oitoAMeVinte?: dayjs.Dayjs | null,
    public oitoAMeTrinta?: dayjs.Dayjs | null,
    public oitoAMeQuarenta?: dayjs.Dayjs | null,
    public oitoAMeCinquenta?: dayjs.Dayjs | null,
    public oitoPM?: dayjs.Dayjs | null,
    public oitoPMeDez?: dayjs.Dayjs | null,
    public oitoPMeVinte?: dayjs.Dayjs | null,
    public oitoPMeTrinta?: dayjs.Dayjs | null,
    public oitoPMeQuarenta?: dayjs.Dayjs | null,
    public oitoPMeCinquenta?: dayjs.Dayjs | null,
    public noveAM?: dayjs.Dayjs | null,
    public noveAMeDez?: dayjs.Dayjs | null,
    public noveAMeVinte?: dayjs.Dayjs | null,
    public noveAMeTrinta?: dayjs.Dayjs | null,
    public noveAMeQuarenta?: dayjs.Dayjs | null,
    public noveAMeCinquenta?: dayjs.Dayjs | null,
    public novePM?: dayjs.Dayjs | null,
    public novePMeDez?: dayjs.Dayjs | null,
    public novePMeVinte?: dayjs.Dayjs | null,
    public novePMeTrinta?: dayjs.Dayjs | null,
    public novePMeQuarenta?: dayjs.Dayjs | null,
    public novePMeCinquenta?: dayjs.Dayjs | null,
    public dezAM?: dayjs.Dayjs | null,
    public dezAMeDez?: dayjs.Dayjs | null,
    public dezAMeVinte?: dayjs.Dayjs | null,
    public dezAMeTrinta?: dayjs.Dayjs | null,
    public dezAMeQuarenta?: dayjs.Dayjs | null,
    public dezAMeCinquenta?: dayjs.Dayjs | null,
    public dezPM?: dayjs.Dayjs | null,
    public dezPMeDez?: dayjs.Dayjs | null,
    public dezPMeVinte?: dayjs.Dayjs | null,
    public dezPMeTrinta?: dayjs.Dayjs | null,
    public dezPMeQuarenta?: dayjs.Dayjs | null,
    public dezPMeCinquenta?: dayjs.Dayjs | null,
    public onzeAM?: dayjs.Dayjs | null,
    public onzeAMeDez?: dayjs.Dayjs | null,
    public onzeAMeVinte?: dayjs.Dayjs | null,
    public onzeAMeTrinta?: dayjs.Dayjs | null,
    public onzeAMeQuarenta?: dayjs.Dayjs | null,
    public onzeAMeCinquenta?: dayjs.Dayjs | null,
    public onzePM?: dayjs.Dayjs | null,
    public onzePMeDez?: dayjs.Dayjs | null,
    public onzePMeVinte?: dayjs.Dayjs | null,
    public onzePMeTrinta?: dayjs.Dayjs | null,
    public onzePMeQuarenta?: dayjs.Dayjs | null,
    public onzePMeCinquenta?: dayjs.Dayjs | null,
    public cronogramaDiario?: ICronogramaDiario | null,
    public atividades?: IAtividade[] | null
  ) {}
}

export function getBlocosIdentifier(blocos: IBlocos): number | undefined {
  return blocos.id;
}
