package com.br.gustavocaraciolo.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.br.gustavocaraciolo.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class BlocosTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Blocos.class);
        Blocos blocos1 = new Blocos();
        blocos1.setId(1L);
        Blocos blocos2 = new Blocos();
        blocos2.setId(blocos1.getId());
        assertThat(blocos1).isEqualTo(blocos2);
        blocos2.setId(2L);
        assertThat(blocos1).isNotEqualTo(blocos2);
        blocos1.setId(null);
        assertThat(blocos1).isNotEqualTo(blocos2);
    }
}
