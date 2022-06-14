import dayjs from 'dayjs/esm';

export interface ICronogramaDiario {
  dia?: dayjs.Dayjs | null;
}

export class CronogramaDiario implements ICronogramaDiario {
  constructor(
    dia?: dayjs.Dayjs | null
  ) {
  }
}
