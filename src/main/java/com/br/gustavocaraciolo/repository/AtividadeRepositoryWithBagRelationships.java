package com.br.gustavocaraciolo.repository;

import com.br.gustavocaraciolo.domain.Atividade;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;

public interface AtividadeRepositoryWithBagRelationships {
    Optional<Atividade> fetchBagRelationships(Optional<Atividade> atividade);

    List<Atividade> fetchBagRelationships(List<Atividade> atividades);

    Page<Atividade> fetchBagRelationships(Page<Atividade> atividades);
}
