import dayjs from 'dayjs/esm';
import { IBlocos } from 'app/entities/blocos/blocos.model';

export interface ICronogramaDiario {
  id?: number;
  dia?: dayjs.Dayjs | null;
  blocos?: IBlocos[] | null;
}

export class CronogramaDiario implements ICronogramaDiario {
  constructor(public id?: number, public dia?: dayjs.Dayjs | null, public blocos?: IBlocos[] | null) {}
}

export function getCronogramaDiarioIdentifier(cronogramaDiario: ICronogramaDiario): number | undefined {
  return cronogramaDiario.id;
}
