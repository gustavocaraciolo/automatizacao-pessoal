package com.br.gustavocaraciolo.repository;

import com.br.gustavocaraciolo.domain.ReservaQuadraTenis;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the ReservaQuadraTenis entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ReservaQuadraTenisRepository extends JpaRepository<ReservaQuadraTenis, Long> {}
