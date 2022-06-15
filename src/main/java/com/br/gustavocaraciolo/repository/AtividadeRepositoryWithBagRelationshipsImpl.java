package com.br.gustavocaraciolo.repository;

import com.br.gustavocaraciolo.domain.Atividade;
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
public class AtividadeRepositoryWithBagRelationshipsImpl implements AtividadeRepositoryWithBagRelationships {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Atividade> fetchBagRelationships(Optional<Atividade> atividade) {
        return atividade.map(this::fetchBlocos);
    }

    @Override
    public Page<Atividade> fetchBagRelationships(Page<Atividade> atividades) {
        return new PageImpl<>(fetchBagRelationships(atividades.getContent()), atividades.getPageable(), atividades.getTotalElements());
    }

    @Override
    public List<Atividade> fetchBagRelationships(List<Atividade> atividades) {
        return Optional.of(atividades).map(this::fetchBlocos).orElse(Collections.emptyList());
    }

    Atividade fetchBlocos(Atividade result) {
        return entityManager
            .createQuery(
                "select atividade from Atividade atividade left join fetch atividade.blocos where atividade is :atividade",
                Atividade.class
            )
            .setParameter("atividade", result)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getSingleResult();
    }

    List<Atividade> fetchBlocos(List<Atividade> atividades) {
        return entityManager
            .createQuery(
                "select distinct atividade from Atividade atividade left join fetch atividade.blocos where atividade in :atividades",
                Atividade.class
            )
            .setParameter("atividades", atividades)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getResultList();
    }
}
