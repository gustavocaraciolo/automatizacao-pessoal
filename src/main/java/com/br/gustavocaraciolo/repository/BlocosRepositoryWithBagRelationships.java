package com.br.gustavocaraciolo.repository;

import com.br.gustavocaraciolo.domain.Blocos;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;

public interface BlocosRepositoryWithBagRelationships {
    Optional<Blocos> fetchBagRelationships(Optional<Blocos> blocos);

    List<Blocos> fetchBagRelationships(List<Blocos> blocos);

    Page<Blocos> fetchBagRelationships(Page<Blocos> blocos);
}
