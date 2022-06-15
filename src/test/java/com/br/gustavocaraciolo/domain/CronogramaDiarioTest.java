package com.br.gustavocaraciolo.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.br.gustavocaraciolo.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CronogramaDiarioTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CronogramaDiario.class);
        CronogramaDiario cronogramaDiario1 = new CronogramaDiario();
        cronogramaDiario1.setId(1L);
        CronogramaDiario cronogramaDiario2 = new CronogramaDiario();
        cronogramaDiario2.setId(cronogramaDiario1.getId());
        assertThat(cronogramaDiario1).isEqualTo(cronogramaDiario2);
        cronogramaDiario2.setId(2L);
        assertThat(cronogramaDiario1).isNotEqualTo(cronogramaDiario2);
        cronogramaDiario1.setId(null);
        assertThat(cronogramaDiario1).isNotEqualTo(cronogramaDiario2);
    }
}
