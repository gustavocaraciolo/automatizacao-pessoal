package com.br.gustavocaraciolo.repository;

import com.br.gustavocaraciolo.domain.Atividade;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Atividade entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AtividadeRepository extends JpaRepository<Atividade, Long> {}
