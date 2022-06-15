package com.br.gustavocaraciolo.repository;

import com.br.gustavocaraciolo.domain.Atividade;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Atividade entity.
 */
@Repository
public interface AtividadeRepository extends AtividadeRepositoryWithBagRelationships, JpaRepository<Atividade, Long> {
    default Optional<Atividade> findOneWithEagerRelationships(Long id) {
        return this.fetchBagRelationships(this.findById(id));
    }

    default List<Atividade> findAllWithEagerRelationships() {
        return this.fetchBagRelationships(this.findAll());
    }

    default Page<Atividade> findAllWithEagerRelationships(Pageable pageable) {
        return this.fetchBagRelationships(this.findAll(pageable));
    }
}
