import { IBlocos } from 'app/entities/blocos/blocos.model';

export interface IAtividade {
  id?: number;
  cor?: string | null;
  descricao?: string | null;
  blocos?: IBlocos | null;
}

export class Atividade implements IAtividade {
  constructor(public id?: number, public cor?: string | null, public descricao?: string | null, public blocos?: IBlocos | null) {}
}

export function getAtividadeIdentifier(atividade: IAtividade): number | undefined {
  return atividade.id;
}
