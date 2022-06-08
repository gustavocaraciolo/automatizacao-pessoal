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
  instant?: dayjs.Dayjs | null;
  duration?: string | null;
  uuid?: string | null;
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
    public instant?: dayjs.Dayjs | null,
    public duration?: string | null,
    public uuid?: string | null
  ) {}
}

export function getReservaQuadraTenisIdentifier(reservaQuadraTenis: IReservaQuadraTenis): number | undefined {
  return reservaQuadraTenis.id;
}
