import dayjs from 'dayjs/esm';

export interface IReservaQuadraTenis {
  id?: number;
  emailDestino?: string;
  templateEmail?: string;
  semana?: dayjs.Dayjs | null;
  segundafeira?: dayjs.Dayjs | null;
  tercafeira?: dayjs.Dayjs | null;
  quartafeira?: dayjs.Dayjs | null;
  quintafeira?: dayjs.Dayjs | null;
  sextafeira?: dayjs.Dayjs | null;
  sabado?: dayjs.Dayjs | null;
  domingo?: dayjs.Dayjs | null;
  segundaFeiraEmailEnviado?: boolean | null;
  tercaFeiraEmailEnviado?: boolean | null;
  quartaFeiraEmailEnviado?: boolean | null;
  quintaFeiraEmailEnviado?: boolean | null;
  sextaFeiraEmailEnviado?: boolean | null;
  sabadoEmailEnviado?: boolean | null;
  domingoEmailEnviado?: boolean | null;
}

export class ReservaQuadraTenis implements IReservaQuadraTenis {
  constructor(
    public id?: number,
    public emailDestino?: string,
    public templateEmail?: string,
    public semana?: dayjs.Dayjs | null,
    public segundafeira?: dayjs.Dayjs | null,
    public tercafeira?: dayjs.Dayjs | null,
    public quartafeira?: dayjs.Dayjs | null,
    public quintafeira?: dayjs.Dayjs | null,
    public sextafeira?: dayjs.Dayjs | null,
    public sabado?: dayjs.Dayjs | null,
    public domingo?: dayjs.Dayjs | null,
    public segundaFeiraEmailEnviado?: boolean | null,
    public tercaFeiraEmailEnviado?: boolean | null,
    public quartaFeiraEmailEnviado?: boolean | null,
    public quintaFeiraEmailEnviado?: boolean | null,
    public sextaFeiraEmailEnviado?: boolean | null,
    public sabadoEmailEnviado?: boolean | null,
    public domingoEmailEnviado?: boolean | null
  ) {
    this.segundaFeiraEmailEnviado = this.segundaFeiraEmailEnviado ?? false;
    this.tercaFeiraEmailEnviado = this.tercaFeiraEmailEnviado ?? false;
    this.quartaFeiraEmailEnviado = this.quartaFeiraEmailEnviado ?? false;
    this.quintaFeiraEmailEnviado = this.quintaFeiraEmailEnviado ?? false;
    this.sextaFeiraEmailEnviado = this.sextaFeiraEmailEnviado ?? false;
    this.sabadoEmailEnviado = this.sabadoEmailEnviado ?? false;
    this.domingoEmailEnviado = this.domingoEmailEnviado ?? false;
  }
}

export function getReservaQuadraTenisIdentifier(reservaQuadraTenis: IReservaQuadraTenis): number | undefined {
  return reservaQuadraTenis.id;
}
