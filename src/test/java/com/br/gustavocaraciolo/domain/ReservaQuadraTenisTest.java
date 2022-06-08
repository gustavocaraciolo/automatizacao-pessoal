package com.br.gustavocaraciolo.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.br.gustavocaraciolo.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ReservaQuadraTenisTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ReservaQuadraTenis.class);
        ReservaQuadraTenis reservaQuadraTenis1 = new ReservaQuadraTenis();
        reservaQuadraTenis1.setId(1L);
        ReservaQuadraTenis reservaQuadraTenis2 = new ReservaQuadraTenis();
        reservaQuadraTenis2.setId(reservaQuadraTenis1.getId());
        assertThat(reservaQuadraTenis1).isEqualTo(reservaQuadraTenis2);
        reservaQuadraTenis2.setId(2L);
        assertThat(reservaQuadraTenis1).isNotEqualTo(reservaQuadraTenis2);
        reservaQuadraTenis1.setId(null);
        assertThat(reservaQuadraTenis1).isNotEqualTo(reservaQuadraTenis2);
    }
}
