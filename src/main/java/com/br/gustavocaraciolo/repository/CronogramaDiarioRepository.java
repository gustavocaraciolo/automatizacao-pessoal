package com.br.gustavocaraciolo.repository;

import com.br.gustavocaraciolo.domain.CronogramaDiario;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the CronogramaDiario entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CronogramaDiarioRepository extends JpaRepository<CronogramaDiario, Long> {}
