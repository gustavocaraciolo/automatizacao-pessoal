export interface IAtividade {
  id?: number;
  cor?: string | null;
  descricao?: string | null;
  blocos?: IAtividade | null;
  atividades?: IAtividade[] | null;
}

export class Atividade implements IAtividade {
  constructor(
    public id?: number,
    public cor?: string | null,
    public descricao?: string | null,
    public blocos?: IAtividade | null,
    public atividades?: IAtividade[] | null
  ) {}
}

export function getAtividadeIdentifier(atividade: IAtividade): number | undefined {
  return atividade.id;
}
