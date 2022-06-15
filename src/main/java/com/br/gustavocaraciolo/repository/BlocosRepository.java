package com.br.gustavocaraciolo.repository;

import com.br.gustavocaraciolo.domain.Blocos;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Blocos entity.
 */
@Repository
public interface BlocosRepository extends JpaRepository<Blocos, Long> {
    default Optional<Blocos> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<Blocos> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<Blocos> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    Optional<Blocos> findByCronogramaDiario_DiaEquals(LocalDate dia);

    @Query(
        value = "select distinct blocos from Blocos blocos left join fetch blocos.cronogramaDiario",
        countQuery = "select count(distinct blocos) from Blocos blocos"
    )
    Page<Blocos> findAllWithToOneRelationships(Pageable pageable);

    @Query("select distinct blocos from Blocos blocos left join fetch blocos.cronogramaDiario")
    List<Blocos> findAllWithToOneRelationships();

    @Query("select blocos from Blocos blocos left join fetch blocos.cronogramaDiario where blocos.id =:id")
    Optional<Blocos> findOneWithToOneRelationships(@Param("id") Long id);
}
