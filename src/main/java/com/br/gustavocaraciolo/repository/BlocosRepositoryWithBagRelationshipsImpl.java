package com.br.gustavocaraciolo.repository;

import com.br.gustavocaraciolo.domain.Blocos;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.annotations.QueryHints;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

/**
 * Utility repository to load bag relationships based on https://vladmihalcea.com/hibernate-multiplebagfetchexception/
 */
public class BlocosRepositoryWithBagRelationshipsImpl implements BlocosRepositoryWithBagRelationships {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Blocos> fetchBagRelationships(Optional<Blocos> blocos) {
        return blocos.map(this::fetchAtividades);
    }

    @Override
    public Page<Blocos> fetchBagRelationships(Page<Blocos> blocos) {
        return new PageImpl<>(fetchBagRelationships(blocos.getContent()), blocos.getPageable(), blocos.getTotalElements());
    }

    @Override
    public List<Blocos> fetchBagRelationships(List<Blocos> blocos) {
        return Optional.of(blocos).map(this::fetchAtividades).orElse(Collections.emptyList());
    }

    Blocos fetchAtividades(Blocos result) {
        return entityManager
            .createQuery("select blocos from Blocos blocos left join fetch blocos.atividades where blocos is :blocos", Blocos.class)
            .setParameter("blocos", result)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getSingleResult();
    }

    List<Blocos> fetchAtividades(List<Blocos> blocos) {
        return entityManager
            .createQuery(
                "select distinct blocos from Blocos blocos left join fetch blocos.atividades where blocos in :blocos",
                Blocos.class
            )
            .setParameter("blocos", blocos)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getResultList();
    }
}
