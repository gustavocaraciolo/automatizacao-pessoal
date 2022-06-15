package com.br.gustavocaraciolo.web.rest;

import static com.br.gustavocaraciolo.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.br.gustavocaraciolo.IntegrationTest;
import com.br.gustavocaraciolo.domain.Blocos;
import com.br.gustavocaraciolo.repository.BlocosRepository;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link BlocosResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class BlocosResourceIT {

    private static final ZonedDateTime DEFAULT_ZERO_AM = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_ZERO_AM = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_ZERO_A_ME_DEZ = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_ZERO_A_ME_DEZ = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_ZERO_A_ME_VINTE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_ZERO_A_ME_VINTE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_ZERO_A_ME_TRINTA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_ZERO_A_ME_TRINTA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_ZERO_A_ME_QUARENTA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_ZERO_A_ME_QUARENTA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_ZERO_A_ME_CINQUENTA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_ZERO_A_ME_CINQUENTA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_ZERO_PM = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_ZERO_PM = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_ZERO_P_ME_DEZ = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_ZERO_P_ME_DEZ = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_ZERO_P_ME_VINTE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_ZERO_P_ME_VINTE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_ZERO_P_ME_TRINTA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_ZERO_P_ME_TRINTA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_ZERO_P_ME_QUARENTA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_ZERO_P_ME_QUARENTA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_ZERO_P_ME_CINQUENTA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_ZERO_P_ME_CINQUENTA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_UM_AM = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UM_AM = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_UM_A_ME_DEZ = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UM_A_ME_DEZ = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_UM_A_ME_VINTE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UM_A_ME_VINTE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_UM_A_ME_TRINTA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UM_A_ME_TRINTA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_UM_A_ME_QUARENTA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UM_A_ME_QUARENTA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_UM_A_ME_CINQUENTA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UM_A_ME_CINQUENTA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_UM_PM = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UM_PM = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_UM_P_ME_DEZ = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UM_P_ME_DEZ = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_UM_P_ME_VINTE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UM_P_ME_VINTE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_UM_P_ME_TRINTA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UM_P_ME_TRINTA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_UM_P_ME_QUARENTA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UM_P_ME_QUARENTA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_UM_P_ME_CINQUENTA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UM_P_ME_CINQUENTA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DOIS_AM = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DOIS_AM = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DOIS_A_ME_DEZ = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DOIS_A_ME_DEZ = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DOIS_A_ME_VINTE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DOIS_A_ME_VINTE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DOIS_A_ME_TRINTA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DOIS_A_ME_TRINTA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DOIS_A_ME_QUARENTA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DOIS_A_ME_QUARENTA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DOIS_A_ME_CINQUENTA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DOIS_A_ME_CINQUENTA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DOIS_PM = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DOIS_PM = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DOIS_P_ME_DEZ = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DOIS_P_ME_DEZ = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DOIS_P_ME_VINTE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DOIS_P_ME_VINTE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DOIS_P_ME_TRINTA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DOIS_P_ME_TRINTA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DOIS_P_ME_QUARENTA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DOIS_P_ME_QUARENTA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DOIS_P_ME_CINQUENTA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DOIS_P_ME_CINQUENTA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_TRES_AM = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_TRES_AM = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_TRES_A_ME_DEZ = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_TRES_A_ME_DEZ = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_TRES_A_ME_VINTE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_TRES_A_ME_VINTE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_TRES_A_ME_TRINTA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_TRES_A_ME_TRINTA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_TRES_A_ME_QUARENTA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_TRES_A_ME_QUARENTA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_TRES_A_ME_CINQUENTA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_TRES_A_ME_CINQUENTA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_TRES_PM = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_TRES_PM = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_TRES_P_ME_DEZ = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_TRES_P_ME_DEZ = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_TRES_P_ME_VINTE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_TRES_P_ME_VINTE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_TRES_P_ME_TRINTA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_TRES_P_ME_TRINTA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_TRES_P_ME_QUARENTA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_TRES_P_ME_QUARENTA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_TRES_P_ME_CINQUENTA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_TRES_P_ME_CINQUENTA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_QUATRO_AM = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_QUATRO_AM = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_QUATRO_A_ME_DEZ = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_QUATRO_A_ME_DEZ = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_QUATRO_A_ME_VINTE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_QUATRO_A_ME_VINTE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_QUATRO_A_ME_TRINTA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_QUATRO_A_ME_TRINTA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_QUATRO_A_ME_QUARENTA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_QUATRO_A_ME_QUARENTA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_QUATRO_A_ME_CINQUENTA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_QUATRO_A_ME_CINQUENTA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_QUATRO_PM = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_QUATRO_PM = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_QUATRO_P_ME_DEZ = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_QUATRO_P_ME_DEZ = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_QUATRO_P_ME_VINTE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_QUATRO_P_ME_VINTE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_QUATRO_P_ME_TRINTA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_QUATRO_P_ME_TRINTA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_QUATRO_P_ME_QUARENTA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_QUATRO_P_ME_QUARENTA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_QUATRO_P_ME_CINQUENTA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_QUATRO_P_ME_CINQUENTA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_CINCO_AM = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CINCO_AM = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_CINCO_A_ME_DEZ = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CINCO_A_ME_DEZ = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_CINCO_A_ME_VINTE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CINCO_A_ME_VINTE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_CINCO_A_ME_TRINTA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CINCO_A_ME_TRINTA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_CINCO_A_ME_QUARENTA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CINCO_A_ME_QUARENTA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_CINCO_A_ME_CINQUENTA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CINCO_A_ME_CINQUENTA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_CINCO_PM = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CINCO_PM = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_CINCO_P_ME_DEZ = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CINCO_P_ME_DEZ = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_CINCO_P_ME_VINTE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CINCO_P_ME_VINTE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_CINCO_P_ME_TRINTA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CINCO_P_ME_TRINTA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_CINCO_P_ME_QUARENTA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CINCO_P_ME_QUARENTA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_CINCO_P_ME_CINQUENTA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CINCO_P_ME_CINQUENTA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_SEIS_AM = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_SEIS_AM = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_SEIS_A_ME_DEZ = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_SEIS_A_ME_DEZ = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_SEIS_A_ME_VINTE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_SEIS_A_ME_VINTE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_SEIS_A_ME_TRINTA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_SEIS_A_ME_TRINTA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_SEIS_A_ME_QUARENTA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_SEIS_A_ME_QUARENTA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_SEIS_A_ME_CINQUENTA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_SEIS_A_ME_CINQUENTA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_SEIS_PM = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_SEIS_PM = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_SEIS_P_ME_DEZ = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_SEIS_P_ME_DEZ = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_SEIS_P_ME_VINTE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_SEIS_P_ME_VINTE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_SEIS_P_ME_TRINTA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_SEIS_P_ME_TRINTA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_SEIS_P_ME_QUARENTA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_SEIS_P_ME_QUARENTA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_SEIS_P_ME_CINQUENTA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_SEIS_P_ME_CINQUENTA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_SETE_AM = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_SETE_AM = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_SETE_A_ME_DEZ = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_SETE_A_ME_DEZ = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_SETE_A_ME_VINTE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_SETE_A_ME_VINTE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_SETE_A_ME_TRINTA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_SETE_A_ME_TRINTA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_SETE_A_ME_QUARENTA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_SETE_A_ME_QUARENTA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_SETE_A_ME_CINQUENTA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_SETE_A_ME_CINQUENTA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_SETE_PM = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_SETE_PM = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_SETE_P_ME_DEZ = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_SETE_P_ME_DEZ = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_SETE_P_ME_VINTE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_SETE_P_ME_VINTE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_SETE_P_ME_TRINTA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_SETE_P_ME_TRINTA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_SETE_P_ME_QUARENTA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_SETE_P_ME_QUARENTA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_SETE_P_ME_CINQUENTA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_SETE_P_ME_CINQUENTA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_OITO_AM = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_OITO_AM = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_OITO_A_ME_DEZ = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_OITO_A_ME_DEZ = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_OITO_A_ME_VINTE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_OITO_A_ME_VINTE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_OITO_A_ME_TRINTA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_OITO_A_ME_TRINTA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_OITO_A_ME_QUARENTA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_OITO_A_ME_QUARENTA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_OITO_A_ME_CINQUENTA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_OITO_A_ME_CINQUENTA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_OITO_PM = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_OITO_PM = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_OITO_P_ME_DEZ = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_OITO_P_ME_DEZ = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_OITO_P_ME_VINTE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_OITO_P_ME_VINTE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_OITO_P_ME_TRINTA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_OITO_P_ME_TRINTA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_OITO_P_ME_QUARENTA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_OITO_P_ME_QUARENTA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_OITO_P_ME_CINQUENTA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_OITO_P_ME_CINQUENTA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_NOVE_AM = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_NOVE_AM = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_NOVE_A_ME_DEZ = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_NOVE_A_ME_DEZ = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_NOVE_A_ME_VINTE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_NOVE_A_ME_VINTE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_NOVE_A_ME_TRINTA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_NOVE_A_ME_TRINTA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_NOVE_A_ME_QUARENTA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_NOVE_A_ME_QUARENTA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_NOVE_A_ME_CINQUENTA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_NOVE_A_ME_CINQUENTA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_NOVE_PM = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_NOVE_PM = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_NOVE_P_ME_DEZ = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_NOVE_P_ME_DEZ = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_NOVE_P_ME_VINTE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_NOVE_P_ME_VINTE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_NOVE_P_ME_TRINTA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_NOVE_P_ME_TRINTA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_NOVE_P_ME_QUARENTA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_NOVE_P_ME_QUARENTA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_NOVE_P_ME_CINQUENTA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_NOVE_P_ME_CINQUENTA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DEZ_AM = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DEZ_AM = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DEZ_A_ME_DEZ = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DEZ_A_ME_DEZ = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DEZ_A_ME_VINTE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DEZ_A_ME_VINTE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DEZ_A_ME_TRINTA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DEZ_A_ME_TRINTA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DEZ_A_ME_QUARENTA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DEZ_A_ME_QUARENTA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DEZ_A_ME_CINQUENTA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DEZ_A_ME_CINQUENTA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DEZ_PM = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DEZ_PM = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DEZ_P_ME_DEZ = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DEZ_P_ME_DEZ = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DEZ_P_ME_VINTE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DEZ_P_ME_VINTE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DEZ_P_ME_TRINTA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DEZ_P_ME_TRINTA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DEZ_P_ME_QUARENTA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DEZ_P_ME_QUARENTA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DEZ_P_ME_CINQUENTA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DEZ_P_ME_CINQUENTA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_ONZE_AM = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_ONZE_AM = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_ONZE_A_ME_DEZ = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_ONZE_A_ME_DEZ = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_ONZE_A_ME_VINTE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_ONZE_A_ME_VINTE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_ONZE_A_ME_TRINTA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_ONZE_A_ME_TRINTA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_ONZE_A_ME_QUARENTA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_ONZE_A_ME_QUARENTA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_ONZE_A_ME_CINQUENTA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_ONZE_A_ME_CINQUENTA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_ONZE_PM = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_ONZE_PM = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_ONZE_P_ME_DEZ = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_ONZE_P_ME_DEZ = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_ONZE_P_ME_VINTE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_ONZE_P_ME_VINTE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_ONZE_P_ME_TRINTA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_ONZE_P_ME_TRINTA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_ONZE_P_ME_QUARENTA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_ONZE_P_ME_QUARENTA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_ONZE_P_ME_CINQUENTA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_ONZE_P_ME_CINQUENTA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String ENTITY_API_URL = "/api/blocos";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private BlocosRepository blocosRepository;

    @Mock
    private BlocosRepository blocosRepositoryMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBlocosMockMvc;

    private Blocos blocos;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Blocos createEntity(EntityManager em) {
        Blocos blocos = new Blocos()
            .zeroAM(DEFAULT_ZERO_AM)
            .zeroAMeDez(DEFAULT_ZERO_A_ME_DEZ)
            .zeroAMeVinte(DEFAULT_ZERO_A_ME_VINTE)
            .zeroAMeTrinta(DEFAULT_ZERO_A_ME_TRINTA)
            .zeroAMeQuarenta(DEFAULT_ZERO_A_ME_QUARENTA)
            .zeroAMeCinquenta(DEFAULT_ZERO_A_ME_CINQUENTA)
            .zeroPM(DEFAULT_ZERO_PM)
            .zeroPMeDez(DEFAULT_ZERO_P_ME_DEZ)
            .zeroPMeVinte(DEFAULT_ZERO_P_ME_VINTE)
            .zeroPMeTrinta(DEFAULT_ZERO_P_ME_TRINTA)
            .zeroPMeQuarenta(DEFAULT_ZERO_P_ME_QUARENTA)
            .zeroPMeCinquenta(DEFAULT_ZERO_P_ME_CINQUENTA)
            .umAM(DEFAULT_UM_AM)
            .umAMeDez(DEFAULT_UM_A_ME_DEZ)
            .umAMeVinte(DEFAULT_UM_A_ME_VINTE)
            .umAMeTrinta(DEFAULT_UM_A_ME_TRINTA)
            .umAMeQuarenta(DEFAULT_UM_A_ME_QUARENTA)
            .umAMeCinquenta(DEFAULT_UM_A_ME_CINQUENTA)
            .umPM(DEFAULT_UM_PM)
            .umPMeDez(DEFAULT_UM_P_ME_DEZ)
            .umPMeVinte(DEFAULT_UM_P_ME_VINTE)
            .umPMeTrinta(DEFAULT_UM_P_ME_TRINTA)
            .umPMeQuarenta(DEFAULT_UM_P_ME_QUARENTA)
            .umPMeCinquenta(DEFAULT_UM_P_ME_CINQUENTA)
            .doisAM(DEFAULT_DOIS_AM)
            .doisAMeDez(DEFAULT_DOIS_A_ME_DEZ)
            .doisAMeVinte(DEFAULT_DOIS_A_ME_VINTE)
            .doisAMeTrinta(DEFAULT_DOIS_A_ME_TRINTA)
            .doisAMeQuarenta(DEFAULT_DOIS_A_ME_QUARENTA)
            .doisAMeCinquenta(DEFAULT_DOIS_A_ME_CINQUENTA)
            .doisPM(DEFAULT_DOIS_PM)
            .doisPMeDez(DEFAULT_DOIS_P_ME_DEZ)
            .doisPMeVinte(DEFAULT_DOIS_P_ME_VINTE)
            .doisPMeTrinta(DEFAULT_DOIS_P_ME_TRINTA)
            .doisPMeQuarenta(DEFAULT_DOIS_P_ME_QUARENTA)
            .doisPMeCinquenta(DEFAULT_DOIS_P_ME_CINQUENTA)
            .tresAM(DEFAULT_TRES_AM)
            .tresAMeDez(DEFAULT_TRES_A_ME_DEZ)
            .tresAMeVinte(DEFAULT_TRES_A_ME_VINTE)
            .tresAMeTrinta(DEFAULT_TRES_A_ME_TRINTA)
            .tresAMeQuarenta(DEFAULT_TRES_A_ME_QUARENTA)
            .tresAMeCinquenta(DEFAULT_TRES_A_ME_CINQUENTA)
            .tresPM(DEFAULT_TRES_PM)
            .tresPMeDez(DEFAULT_TRES_P_ME_DEZ)
            .tresPMeVinte(DEFAULT_TRES_P_ME_VINTE)
            .tresPMeTrinta(DEFAULT_TRES_P_ME_TRINTA)
            .tresPMeQuarenta(DEFAULT_TRES_P_ME_QUARENTA)
            .tresPMeCinquenta(DEFAULT_TRES_P_ME_CINQUENTA)
            .quatroAM(DEFAULT_QUATRO_AM)
            .quatroAMeDez(DEFAULT_QUATRO_A_ME_DEZ)
            .quatroAMeVinte(DEFAULT_QUATRO_A_ME_VINTE)
            .quatroAMeTrinta(DEFAULT_QUATRO_A_ME_TRINTA)
            .quatroAMeQuarenta(DEFAULT_QUATRO_A_ME_QUARENTA)
            .quatroAMeCinquenta(DEFAULT_QUATRO_A_ME_CINQUENTA)
            .quatroPM(DEFAULT_QUATRO_PM)
            .quatroPMeDez(DEFAULT_QUATRO_P_ME_DEZ)
            .quatroPMeVinte(DEFAULT_QUATRO_P_ME_VINTE)
            .quatroPMeTrinta(DEFAULT_QUATRO_P_ME_TRINTA)
            .quatroPMeQuarenta(DEFAULT_QUATRO_P_ME_QUARENTA)
            .quatroPMeCinquenta(DEFAULT_QUATRO_P_ME_CINQUENTA)
            .cincoAM(DEFAULT_CINCO_AM)
            .cincoAMeDez(DEFAULT_CINCO_A_ME_DEZ)
            .cincoAMeVinte(DEFAULT_CINCO_A_ME_VINTE)
            .cincoAMeTrinta(DEFAULT_CINCO_A_ME_TRINTA)
            .cincoAMeQuarenta(DEFAULT_CINCO_A_ME_QUARENTA)
            .cincoAMeCinquenta(DEFAULT_CINCO_A_ME_CINQUENTA)
            .cincoPM(DEFAULT_CINCO_PM)
            .cincoPMeDez(DEFAULT_CINCO_P_ME_DEZ)
            .cincoPMeVinte(DEFAULT_CINCO_P_ME_VINTE)
            .cincoPMeTrinta(DEFAULT_CINCO_P_ME_TRINTA)
            .cincoPMeQuarenta(DEFAULT_CINCO_P_ME_QUARENTA)
            .cincoPMeCinquenta(DEFAULT_CINCO_P_ME_CINQUENTA)
            .seisAM(DEFAULT_SEIS_AM)
            .seisAMeDez(DEFAULT_SEIS_A_ME_DEZ)
            .seisAMeVinte(DEFAULT_SEIS_A_ME_VINTE)
            .seisAMeTrinta(DEFAULT_SEIS_A_ME_TRINTA)
            .seisAMeQuarenta(DEFAULT_SEIS_A_ME_QUARENTA)
            .seisAMeCinquenta(DEFAULT_SEIS_A_ME_CINQUENTA)
            .seisPM(DEFAULT_SEIS_PM)
            .seisPMeDez(DEFAULT_SEIS_P_ME_DEZ)
            .seisPMeVinte(DEFAULT_SEIS_P_ME_VINTE)
            .seisPMeTrinta(DEFAULT_SEIS_P_ME_TRINTA)
            .seisPMeQuarenta(DEFAULT_SEIS_P_ME_QUARENTA)
            .seisPMeCinquenta(DEFAULT_SEIS_P_ME_CINQUENTA)
            .seteAM(DEFAULT_SETE_AM)
            .seteAMeDez(DEFAULT_SETE_A_ME_DEZ)
            .seteAMeVinte(DEFAULT_SETE_A_ME_VINTE)
            .seteAMeTrinta(DEFAULT_SETE_A_ME_TRINTA)
            .seteAMeQuarenta(DEFAULT_SETE_A_ME_QUARENTA)
            .seteAMeCinquenta(DEFAULT_SETE_A_ME_CINQUENTA)
            .setePM(DEFAULT_SETE_PM)
            .setePMeDez(DEFAULT_SETE_P_ME_DEZ)
            .setePMeVinte(DEFAULT_SETE_P_ME_VINTE)
            .setePMeTrinta(DEFAULT_SETE_P_ME_TRINTA)
            .setePMeQuarenta(DEFAULT_SETE_P_ME_QUARENTA)
            .setePMeCinquenta(DEFAULT_SETE_P_ME_CINQUENTA)
            .oitoAM(DEFAULT_OITO_AM)
            .oitoAMeDez(DEFAULT_OITO_A_ME_DEZ)
            .oitoAMeVinte(DEFAULT_OITO_A_ME_VINTE)
            .oitoAMeTrinta(DEFAULT_OITO_A_ME_TRINTA)
            .oitoAMeQuarenta(DEFAULT_OITO_A_ME_QUARENTA)
            .oitoAMeCinquenta(DEFAULT_OITO_A_ME_CINQUENTA)
            .oitoPM(DEFAULT_OITO_PM)
            .oitoPMeDez(DEFAULT_OITO_P_ME_DEZ)
            .oitoPMeVinte(DEFAULT_OITO_P_ME_VINTE)
            .oitoPMeTrinta(DEFAULT_OITO_P_ME_TRINTA)
            .oitoPMeQuarenta(DEFAULT_OITO_P_ME_QUARENTA)
            .oitoPMeCinquenta(DEFAULT_OITO_P_ME_CINQUENTA)
            .noveAM(DEFAULT_NOVE_AM)
            .noveAMeDez(DEFAULT_NOVE_A_ME_DEZ)
            .noveAMeVinte(DEFAULT_NOVE_A_ME_VINTE)
            .noveAMeTrinta(DEFAULT_NOVE_A_ME_TRINTA)
            .noveAMeQuarenta(DEFAULT_NOVE_A_ME_QUARENTA)
            .noveAMeCinquenta(DEFAULT_NOVE_A_ME_CINQUENTA)
            .novePM(DEFAULT_NOVE_PM)
            .novePMeDez(DEFAULT_NOVE_P_ME_DEZ)
            .novePMeVinte(DEFAULT_NOVE_P_ME_VINTE)
            .novePMeTrinta(DEFAULT_NOVE_P_ME_TRINTA)
            .novePMeQuarenta(DEFAULT_NOVE_P_ME_QUARENTA)
            .novePMeCinquenta(DEFAULT_NOVE_P_ME_CINQUENTA)
            .dezAM(DEFAULT_DEZ_AM)
            .dezAMeDez(DEFAULT_DEZ_A_ME_DEZ)
            .dezAMeVinte(DEFAULT_DEZ_A_ME_VINTE)
            .dezAMeTrinta(DEFAULT_DEZ_A_ME_TRINTA)
            .dezAMeQuarenta(DEFAULT_DEZ_A_ME_QUARENTA)
            .dezAMeCinquenta(DEFAULT_DEZ_A_ME_CINQUENTA)
            .dezPM(DEFAULT_DEZ_PM)
            .dezPMeDez(DEFAULT_DEZ_P_ME_DEZ)
            .dezPMeVinte(DEFAULT_DEZ_P_ME_VINTE)
            .dezPMeTrinta(DEFAULT_DEZ_P_ME_TRINTA)
            .dezPMeQuarenta(DEFAULT_DEZ_P_ME_QUARENTA)
            .dezPMeCinquenta(DEFAULT_DEZ_P_ME_CINQUENTA)
            .onzeAM(DEFAULT_ONZE_AM)
            .onzeAMeDez(DEFAULT_ONZE_A_ME_DEZ)
            .onzeAMeVinte(DEFAULT_ONZE_A_ME_VINTE)
            .onzeAMeTrinta(DEFAULT_ONZE_A_ME_TRINTA)
            .onzeAMeQuarenta(DEFAULT_ONZE_A_ME_QUARENTA)
            .onzeAMeCinquenta(DEFAULT_ONZE_A_ME_CINQUENTA)
            .onzePM(DEFAULT_ONZE_PM)
            .onzePMeDez(DEFAULT_ONZE_P_ME_DEZ)
            .onzePMeVinte(DEFAULT_ONZE_P_ME_VINTE)
            .onzePMeTrinta(DEFAULT_ONZE_P_ME_TRINTA)
            .onzePMeQuarenta(DEFAULT_ONZE_P_ME_QUARENTA)
            .onzePMeCinquenta(DEFAULT_ONZE_P_ME_CINQUENTA);
        return blocos;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Blocos createUpdatedEntity(EntityManager em) {
        Blocos blocos = new Blocos()
            .zeroAM(UPDATED_ZERO_AM)
            .zeroAMeDez(UPDATED_ZERO_A_ME_DEZ)
            .zeroAMeVinte(UPDATED_ZERO_A_ME_VINTE)
            .zeroAMeTrinta(UPDATED_ZERO_A_ME_TRINTA)
            .zeroAMeQuarenta(UPDATED_ZERO_A_ME_QUARENTA)
            .zeroAMeCinquenta(UPDATED_ZERO_A_ME_CINQUENTA)
            .zeroPM(UPDATED_ZERO_PM)
            .zeroPMeDez(UPDATED_ZERO_P_ME_DEZ)
            .zeroPMeVinte(UPDATED_ZERO_P_ME_VINTE)
            .zeroPMeTrinta(UPDATED_ZERO_P_ME_TRINTA)
            .zeroPMeQuarenta(UPDATED_ZERO_P_ME_QUARENTA)
            .zeroPMeCinquenta(UPDATED_ZERO_P_ME_CINQUENTA)
            .umAM(UPDATED_UM_AM)
            .umAMeDez(UPDATED_UM_A_ME_DEZ)
            .umAMeVinte(UPDATED_UM_A_ME_VINTE)
            .umAMeTrinta(UPDATED_UM_A_ME_TRINTA)
            .umAMeQuarenta(UPDATED_UM_A_ME_QUARENTA)
            .umAMeCinquenta(UPDATED_UM_A_ME_CINQUENTA)
            .umPM(UPDATED_UM_PM)
            .umPMeDez(UPDATED_UM_P_ME_DEZ)
            .umPMeVinte(UPDATED_UM_P_ME_VINTE)
            .umPMeTrinta(UPDATED_UM_P_ME_TRINTA)
            .umPMeQuarenta(UPDATED_UM_P_ME_QUARENTA)
            .umPMeCinquenta(UPDATED_UM_P_ME_CINQUENTA)
            .doisAM(UPDATED_DOIS_AM)
            .doisAMeDez(UPDATED_DOIS_A_ME_DEZ)
            .doisAMeVinte(UPDATED_DOIS_A_ME_VINTE)
            .doisAMeTrinta(UPDATED_DOIS_A_ME_TRINTA)
            .doisAMeQuarenta(UPDATED_DOIS_A_ME_QUARENTA)
            .doisAMeCinquenta(UPDATED_DOIS_A_ME_CINQUENTA)
            .doisPM(UPDATED_DOIS_PM)
            .doisPMeDez(UPDATED_DOIS_P_ME_DEZ)
            .doisPMeVinte(UPDATED_DOIS_P_ME_VINTE)
            .doisPMeTrinta(UPDATED_DOIS_P_ME_TRINTA)
            .doisPMeQuarenta(UPDATED_DOIS_P_ME_QUARENTA)
            .doisPMeCinquenta(UPDATED_DOIS_P_ME_CINQUENTA)
            .tresAM(UPDATED_TRES_AM)
            .tresAMeDez(UPDATED_TRES_A_ME_DEZ)
            .tresAMeVinte(UPDATED_TRES_A_ME_VINTE)
            .tresAMeTrinta(UPDATED_TRES_A_ME_TRINTA)
            .tresAMeQuarenta(UPDATED_TRES_A_ME_QUARENTA)
            .tresAMeCinquenta(UPDATED_TRES_A_ME_CINQUENTA)
            .tresPM(UPDATED_TRES_PM)
            .tresPMeDez(UPDATED_TRES_P_ME_DEZ)
            .tresPMeVinte(UPDATED_TRES_P_ME_VINTE)
            .tresPMeTrinta(UPDATED_TRES_P_ME_TRINTA)
            .tresPMeQuarenta(UPDATED_TRES_P_ME_QUARENTA)
            .tresPMeCinquenta(UPDATED_TRES_P_ME_CINQUENTA)
            .quatroAM(UPDATED_QUATRO_AM)
            .quatroAMeDez(UPDATED_QUATRO_A_ME_DEZ)
            .quatroAMeVinte(UPDATED_QUATRO_A_ME_VINTE)
            .quatroAMeTrinta(UPDATED_QUATRO_A_ME_TRINTA)
            .quatroAMeQuarenta(UPDATED_QUATRO_A_ME_QUARENTA)
            .quatroAMeCinquenta(UPDATED_QUATRO_A_ME_CINQUENTA)
            .quatroPM(UPDATED_QUATRO_PM)
            .quatroPMeDez(UPDATED_QUATRO_P_ME_DEZ)
            .quatroPMeVinte(UPDATED_QUATRO_P_ME_VINTE)
            .quatroPMeTrinta(UPDATED_QUATRO_P_ME_TRINTA)
            .quatroPMeQuarenta(UPDATED_QUATRO_P_ME_QUARENTA)
            .quatroPMeCinquenta(UPDATED_QUATRO_P_ME_CINQUENTA)
            .cincoAM(UPDATED_CINCO_AM)
            .cincoAMeDez(UPDATED_CINCO_A_ME_DEZ)
            .cincoAMeVinte(UPDATED_CINCO_A_ME_VINTE)
            .cincoAMeTrinta(UPDATED_CINCO_A_ME_TRINTA)
            .cincoAMeQuarenta(UPDATED_CINCO_A_ME_QUARENTA)
            .cincoAMeCinquenta(UPDATED_CINCO_A_ME_CINQUENTA)
            .cincoPM(UPDATED_CINCO_PM)
            .cincoPMeDez(UPDATED_CINCO_P_ME_DEZ)
            .cincoPMeVinte(UPDATED_CINCO_P_ME_VINTE)
            .cincoPMeTrinta(UPDATED_CINCO_P_ME_TRINTA)
            .cincoPMeQuarenta(UPDATED_CINCO_P_ME_QUARENTA)
            .cincoPMeCinquenta(UPDATED_CINCO_P_ME_CINQUENTA)
            .seisAM(UPDATED_SEIS_AM)
            .seisAMeDez(UPDATED_SEIS_A_ME_DEZ)
            .seisAMeVinte(UPDATED_SEIS_A_ME_VINTE)
            .seisAMeTrinta(UPDATED_SEIS_A_ME_TRINTA)
            .seisAMeQuarenta(UPDATED_SEIS_A_ME_QUARENTA)
            .seisAMeCinquenta(UPDATED_SEIS_A_ME_CINQUENTA)
            .seisPM(UPDATED_SEIS_PM)
            .seisPMeDez(UPDATED_SEIS_P_ME_DEZ)
            .seisPMeVinte(UPDATED_SEIS_P_ME_VINTE)
            .seisPMeTrinta(UPDATED_SEIS_P_ME_TRINTA)
            .seisPMeQuarenta(UPDATED_SEIS_P_ME_QUARENTA)
            .seisPMeCinquenta(UPDATED_SEIS_P_ME_CINQUENTA)
            .seteAM(UPDATED_SETE_AM)
            .seteAMeDez(UPDATED_SETE_A_ME_DEZ)
            .seteAMeVinte(UPDATED_SETE_A_ME_VINTE)
            .seteAMeTrinta(UPDATED_SETE_A_ME_TRINTA)
            .seteAMeQuarenta(UPDATED_SETE_A_ME_QUARENTA)
            .seteAMeCinquenta(UPDATED_SETE_A_ME_CINQUENTA)
            .setePM(UPDATED_SETE_PM)
            .setePMeDez(UPDATED_SETE_P_ME_DEZ)
            .setePMeVinte(UPDATED_SETE_P_ME_VINTE)
            .setePMeTrinta(UPDATED_SETE_P_ME_TRINTA)
            .setePMeQuarenta(UPDATED_SETE_P_ME_QUARENTA)
            .setePMeCinquenta(UPDATED_SETE_P_ME_CINQUENTA)
            .oitoAM(UPDATED_OITO_AM)
            .oitoAMeDez(UPDATED_OITO_A_ME_DEZ)
            .oitoAMeVinte(UPDATED_OITO_A_ME_VINTE)
            .oitoAMeTrinta(UPDATED_OITO_A_ME_TRINTA)
            .oitoAMeQuarenta(UPDATED_OITO_A_ME_QUARENTA)
            .oitoAMeCinquenta(UPDATED_OITO_A_ME_CINQUENTA)
            .oitoPM(UPDATED_OITO_PM)
            .oitoPMeDez(UPDATED_OITO_P_ME_DEZ)
            .oitoPMeVinte(UPDATED_OITO_P_ME_VINTE)
            .oitoPMeTrinta(UPDATED_OITO_P_ME_TRINTA)
            .oitoPMeQuarenta(UPDATED_OITO_P_ME_QUARENTA)
            .oitoPMeCinquenta(UPDATED_OITO_P_ME_CINQUENTA)
            .noveAM(UPDATED_NOVE_AM)
            .noveAMeDez(UPDATED_NOVE_A_ME_DEZ)
            .noveAMeVinte(UPDATED_NOVE_A_ME_VINTE)
            .noveAMeTrinta(UPDATED_NOVE_A_ME_TRINTA)
            .noveAMeQuarenta(UPDATED_NOVE_A_ME_QUARENTA)
            .noveAMeCinquenta(UPDATED_NOVE_A_ME_CINQUENTA)
            .novePM(UPDATED_NOVE_PM)
            .novePMeDez(UPDATED_NOVE_P_ME_DEZ)
            .novePMeVinte(UPDATED_NOVE_P_ME_VINTE)
            .novePMeTrinta(UPDATED_NOVE_P_ME_TRINTA)
            .novePMeQuarenta(UPDATED_NOVE_P_ME_QUARENTA)
            .novePMeCinquenta(UPDATED_NOVE_P_ME_CINQUENTA)
            .dezAM(UPDATED_DEZ_AM)
            .dezAMeDez(UPDATED_DEZ_A_ME_DEZ)
            .dezAMeVinte(UPDATED_DEZ_A_ME_VINTE)
            .dezAMeTrinta(UPDATED_DEZ_A_ME_TRINTA)
            .dezAMeQuarenta(UPDATED_DEZ_A_ME_QUARENTA)
            .dezAMeCinquenta(UPDATED_DEZ_A_ME_CINQUENTA)
            .dezPM(UPDATED_DEZ_PM)
            .dezPMeDez(UPDATED_DEZ_P_ME_DEZ)
            .dezPMeVinte(UPDATED_DEZ_P_ME_VINTE)
            .dezPMeTrinta(UPDATED_DEZ_P_ME_TRINTA)
            .dezPMeQuarenta(UPDATED_DEZ_P_ME_QUARENTA)
            .dezPMeCinquenta(UPDATED_DEZ_P_ME_CINQUENTA)
            .onzeAM(UPDATED_ONZE_AM)
            .onzeAMeDez(UPDATED_ONZE_A_ME_DEZ)
            .onzeAMeVinte(UPDATED_ONZE_A_ME_VINTE)
            .onzeAMeTrinta(UPDATED_ONZE_A_ME_TRINTA)
            .onzeAMeQuarenta(UPDATED_ONZE_A_ME_QUARENTA)
            .onzeAMeCinquenta(UPDATED_ONZE_A_ME_CINQUENTA)
            .onzePM(UPDATED_ONZE_PM)
            .onzePMeDez(UPDATED_ONZE_P_ME_DEZ)
            .onzePMeVinte(UPDATED_ONZE_P_ME_VINTE)
            .onzePMeTrinta(UPDATED_ONZE_P_ME_TRINTA)
            .onzePMeQuarenta(UPDATED_ONZE_P_ME_QUARENTA)
            .onzePMeCinquenta(UPDATED_ONZE_P_ME_CINQUENTA);
        return blocos;
    }

    @BeforeEach
    public void initTest() {
        blocos = createEntity(em);
    }

    @Test
    @Transactional
    void createBlocos() throws Exception {
        int databaseSizeBeforeCreate = blocosRepository.findAll().size();
        // Create the Blocos
        restBlocosMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(blocos)))
            .andExpect(status().isCreated());

        // Validate the Blocos in the database
        List<Blocos> blocosList = blocosRepository.findAll();
        assertThat(blocosList).hasSize(databaseSizeBeforeCreate + 1);
        Blocos testBlocos = blocosList.get(blocosList.size() - 1);
        assertThat(testBlocos.getZeroAM()).isEqualTo(DEFAULT_ZERO_AM);
        assertThat(testBlocos.getZeroAMeDez()).isEqualTo(DEFAULT_ZERO_A_ME_DEZ);
        assertThat(testBlocos.getZeroAMeVinte()).isEqualTo(DEFAULT_ZERO_A_ME_VINTE);
        assertThat(testBlocos.getZeroAMeTrinta()).isEqualTo(DEFAULT_ZERO_A_ME_TRINTA);
        assertThat(testBlocos.getZeroAMeQuarenta()).isEqualTo(DEFAULT_ZERO_A_ME_QUARENTA);
        assertThat(testBlocos.getZeroAMeCinquenta()).isEqualTo(DEFAULT_ZERO_A_ME_CINQUENTA);
        assertThat(testBlocos.getZeroPM()).isEqualTo(DEFAULT_ZERO_PM);
        assertThat(testBlocos.getZeroPMeDez()).isEqualTo(DEFAULT_ZERO_P_ME_DEZ);
        assertThat(testBlocos.getZeroPMeVinte()).isEqualTo(DEFAULT_ZERO_P_ME_VINTE);
        assertThat(testBlocos.getZeroPMeTrinta()).isEqualTo(DEFAULT_ZERO_P_ME_TRINTA);
        assertThat(testBlocos.getZeroPMeQuarenta()).isEqualTo(DEFAULT_ZERO_P_ME_QUARENTA);
        assertThat(testBlocos.getZeroPMeCinquenta()).isEqualTo(DEFAULT_ZERO_P_ME_CINQUENTA);
        assertThat(testBlocos.getUmAM()).isEqualTo(DEFAULT_UM_AM);
        assertThat(testBlocos.getUmAMeDez()).isEqualTo(DEFAULT_UM_A_ME_DEZ);
        assertThat(testBlocos.getUmAMeVinte()).isEqualTo(DEFAULT_UM_A_ME_VINTE);
        assertThat(testBlocos.getUmAMeTrinta()).isEqualTo(DEFAULT_UM_A_ME_TRINTA);
        assertThat(testBlocos.getUmAMeQuarenta()).isEqualTo(DEFAULT_UM_A_ME_QUARENTA);
        assertThat(testBlocos.getUmAMeCinquenta()).isEqualTo(DEFAULT_UM_A_ME_CINQUENTA);
        assertThat(testBlocos.getUmPM()).isEqualTo(DEFAULT_UM_PM);
        assertThat(testBlocos.getUmPMeDez()).isEqualTo(DEFAULT_UM_P_ME_DEZ);
        assertThat(testBlocos.getUmPMeVinte()).isEqualTo(DEFAULT_UM_P_ME_VINTE);
        assertThat(testBlocos.getUmPMeTrinta()).isEqualTo(DEFAULT_UM_P_ME_TRINTA);
        assertThat(testBlocos.getUmPMeQuarenta()).isEqualTo(DEFAULT_UM_P_ME_QUARENTA);
        assertThat(testBlocos.getUmPMeCinquenta()).isEqualTo(DEFAULT_UM_P_ME_CINQUENTA);
        assertThat(testBlocos.getDoisAM()).isEqualTo(DEFAULT_DOIS_AM);
        assertThat(testBlocos.getDoisAMeDez()).isEqualTo(DEFAULT_DOIS_A_ME_DEZ);
        assertThat(testBlocos.getDoisAMeVinte()).isEqualTo(DEFAULT_DOIS_A_ME_VINTE);
        assertThat(testBlocos.getDoisAMeTrinta()).isEqualTo(DEFAULT_DOIS_A_ME_TRINTA);
        assertThat(testBlocos.getDoisAMeQuarenta()).isEqualTo(DEFAULT_DOIS_A_ME_QUARENTA);
        assertThat(testBlocos.getDoisAMeCinquenta()).isEqualTo(DEFAULT_DOIS_A_ME_CINQUENTA);
        assertThat(testBlocos.getDoisPM()).isEqualTo(DEFAULT_DOIS_PM);
        assertThat(testBlocos.getDoisPMeDez()).isEqualTo(DEFAULT_DOIS_P_ME_DEZ);
        assertThat(testBlocos.getDoisPMeVinte()).isEqualTo(DEFAULT_DOIS_P_ME_VINTE);
        assertThat(testBlocos.getDoisPMeTrinta()).isEqualTo(DEFAULT_DOIS_P_ME_TRINTA);
        assertThat(testBlocos.getDoisPMeQuarenta()).isEqualTo(DEFAULT_DOIS_P_ME_QUARENTA);
        assertThat(testBlocos.getDoisPMeCinquenta()).isEqualTo(DEFAULT_DOIS_P_ME_CINQUENTA);
        assertThat(testBlocos.getTresAM()).isEqualTo(DEFAULT_TRES_AM);
        assertThat(testBlocos.getTresAMeDez()).isEqualTo(DEFAULT_TRES_A_ME_DEZ);
        assertThat(testBlocos.getTresAMeVinte()).isEqualTo(DEFAULT_TRES_A_ME_VINTE);
        assertThat(testBlocos.getTresAMeTrinta()).isEqualTo(DEFAULT_TRES_A_ME_TRINTA);
        assertThat(testBlocos.getTresAMeQuarenta()).isEqualTo(DEFAULT_TRES_A_ME_QUARENTA);
        assertThat(testBlocos.getTresAMeCinquenta()).isEqualTo(DEFAULT_TRES_A_ME_CINQUENTA);
        assertThat(testBlocos.getTresPM()).isEqualTo(DEFAULT_TRES_PM);
        assertThat(testBlocos.getTresPMeDez()).isEqualTo(DEFAULT_TRES_P_ME_DEZ);
        assertThat(testBlocos.getTresPMeVinte()).isEqualTo(DEFAULT_TRES_P_ME_VINTE);
        assertThat(testBlocos.getTresPMeTrinta()).isEqualTo(DEFAULT_TRES_P_ME_TRINTA);
        assertThat(testBlocos.getTresPMeQuarenta()).isEqualTo(DEFAULT_TRES_P_ME_QUARENTA);
        assertThat(testBlocos.getTresPMeCinquenta()).isEqualTo(DEFAULT_TRES_P_ME_CINQUENTA);
        assertThat(testBlocos.getQuatroAM()).isEqualTo(DEFAULT_QUATRO_AM);
        assertThat(testBlocos.getQuatroAMeDez()).isEqualTo(DEFAULT_QUATRO_A_ME_DEZ);
        assertThat(testBlocos.getQuatroAMeVinte()).isEqualTo(DEFAULT_QUATRO_A_ME_VINTE);
        assertThat(testBlocos.getQuatroAMeTrinta()).isEqualTo(DEFAULT_QUATRO_A_ME_TRINTA);
        assertThat(testBlocos.getQuatroAMeQuarenta()).isEqualTo(DEFAULT_QUATRO_A_ME_QUARENTA);
        assertThat(testBlocos.getQuatroAMeCinquenta()).isEqualTo(DEFAULT_QUATRO_A_ME_CINQUENTA);
        assertThat(testBlocos.getQuatroPM()).isEqualTo(DEFAULT_QUATRO_PM);
        assertThat(testBlocos.getQuatroPMeDez()).isEqualTo(DEFAULT_QUATRO_P_ME_DEZ);
        assertThat(testBlocos.getQuatroPMeVinte()).isEqualTo(DEFAULT_QUATRO_P_ME_VINTE);
        assertThat(testBlocos.getQuatroPMeTrinta()).isEqualTo(DEFAULT_QUATRO_P_ME_TRINTA);
        assertThat(testBlocos.getQuatroPMeQuarenta()).isEqualTo(DEFAULT_QUATRO_P_ME_QUARENTA);
        assertThat(testBlocos.getQuatroPMeCinquenta()).isEqualTo(DEFAULT_QUATRO_P_ME_CINQUENTA);
        assertThat(testBlocos.getCincoAM()).isEqualTo(DEFAULT_CINCO_AM);
        assertThat(testBlocos.getCincoAMeDez()).isEqualTo(DEFAULT_CINCO_A_ME_DEZ);
        assertThat(testBlocos.getCincoAMeVinte()).isEqualTo(DEFAULT_CINCO_A_ME_VINTE);
        assertThat(testBlocos.getCincoAMeTrinta()).isEqualTo(DEFAULT_CINCO_A_ME_TRINTA);
        assertThat(testBlocos.getCincoAMeQuarenta()).isEqualTo(DEFAULT_CINCO_A_ME_QUARENTA);
        assertThat(testBlocos.getCincoAMeCinquenta()).isEqualTo(DEFAULT_CINCO_A_ME_CINQUENTA);
        assertThat(testBlocos.getCincoPM()).isEqualTo(DEFAULT_CINCO_PM);
        assertThat(testBlocos.getCincoPMeDez()).isEqualTo(DEFAULT_CINCO_P_ME_DEZ);
        assertThat(testBlocos.getCincoPMeVinte()).isEqualTo(DEFAULT_CINCO_P_ME_VINTE);
        assertThat(testBlocos.getCincoPMeTrinta()).isEqualTo(DEFAULT_CINCO_P_ME_TRINTA);
        assertThat(testBlocos.getCincoPMeQuarenta()).isEqualTo(DEFAULT_CINCO_P_ME_QUARENTA);
        assertThat(testBlocos.getCincoPMeCinquenta()).isEqualTo(DEFAULT_CINCO_P_ME_CINQUENTA);
        assertThat(testBlocos.getSeisAM()).isEqualTo(DEFAULT_SEIS_AM);
        assertThat(testBlocos.getSeisAMeDez()).isEqualTo(DEFAULT_SEIS_A_ME_DEZ);
        assertThat(testBlocos.getSeisAMeVinte()).isEqualTo(DEFAULT_SEIS_A_ME_VINTE);
        assertThat(testBlocos.getSeisAMeTrinta()).isEqualTo(DEFAULT_SEIS_A_ME_TRINTA);
        assertThat(testBlocos.getSeisAMeQuarenta()).isEqualTo(DEFAULT_SEIS_A_ME_QUARENTA);
        assertThat(testBlocos.getSeisAMeCinquenta()).isEqualTo(DEFAULT_SEIS_A_ME_CINQUENTA);
        assertThat(testBlocos.getSeisPM()).isEqualTo(DEFAULT_SEIS_PM);
        assertThat(testBlocos.getSeisPMeDez()).isEqualTo(DEFAULT_SEIS_P_ME_DEZ);
        assertThat(testBlocos.getSeisPMeVinte()).isEqualTo(DEFAULT_SEIS_P_ME_VINTE);
        assertThat(testBlocos.getSeisPMeTrinta()).isEqualTo(DEFAULT_SEIS_P_ME_TRINTA);
        assertThat(testBlocos.getSeisPMeQuarenta()).isEqualTo(DEFAULT_SEIS_P_ME_QUARENTA);
        assertThat(testBlocos.getSeisPMeCinquenta()).isEqualTo(DEFAULT_SEIS_P_ME_CINQUENTA);
        assertThat(testBlocos.getSeteAM()).isEqualTo(DEFAULT_SETE_AM);
        assertThat(testBlocos.getSeteAMeDez()).isEqualTo(DEFAULT_SETE_A_ME_DEZ);
        assertThat(testBlocos.getSeteAMeVinte()).isEqualTo(DEFAULT_SETE_A_ME_VINTE);
        assertThat(testBlocos.getSeteAMeTrinta()).isEqualTo(DEFAULT_SETE_A_ME_TRINTA);
        assertThat(testBlocos.getSeteAMeQuarenta()).isEqualTo(DEFAULT_SETE_A_ME_QUARENTA);
        assertThat(testBlocos.getSeteAMeCinquenta()).isEqualTo(DEFAULT_SETE_A_ME_CINQUENTA);
        assertThat(testBlocos.getSetePM()).isEqualTo(DEFAULT_SETE_PM);
        assertThat(testBlocos.getSetePMeDez()).isEqualTo(DEFAULT_SETE_P_ME_DEZ);
        assertThat(testBlocos.getSetePMeVinte()).isEqualTo(DEFAULT_SETE_P_ME_VINTE);
        assertThat(testBlocos.getSetePMeTrinta()).isEqualTo(DEFAULT_SETE_P_ME_TRINTA);
        assertThat(testBlocos.getSetePMeQuarenta()).isEqualTo(DEFAULT_SETE_P_ME_QUARENTA);
        assertThat(testBlocos.getSetePMeCinquenta()).isEqualTo(DEFAULT_SETE_P_ME_CINQUENTA);
        assertThat(testBlocos.getOitoAM()).isEqualTo(DEFAULT_OITO_AM);
        assertThat(testBlocos.getOitoAMeDez()).isEqualTo(DEFAULT_OITO_A_ME_DEZ);
        assertThat(testBlocos.getOitoAMeVinte()).isEqualTo(DEFAULT_OITO_A_ME_VINTE);
        assertThat(testBlocos.getOitoAMeTrinta()).isEqualTo(DEFAULT_OITO_A_ME_TRINTA);
        assertThat(testBlocos.getOitoAMeQuarenta()).isEqualTo(DEFAULT_OITO_A_ME_QUARENTA);
        assertThat(testBlocos.getOitoAMeCinquenta()).isEqualTo(DEFAULT_OITO_A_ME_CINQUENTA);
        assertThat(testBlocos.getOitoPM()).isEqualTo(DEFAULT_OITO_PM);
        assertThat(testBlocos.getOitoPMeDez()).isEqualTo(DEFAULT_OITO_P_ME_DEZ);
        assertThat(testBlocos.getOitoPMeVinte()).isEqualTo(DEFAULT_OITO_P_ME_VINTE);
        assertThat(testBlocos.getOitoPMeTrinta()).isEqualTo(DEFAULT_OITO_P_ME_TRINTA);
        assertThat(testBlocos.getOitoPMeQuarenta()).isEqualTo(DEFAULT_OITO_P_ME_QUARENTA);
        assertThat(testBlocos.getOitoPMeCinquenta()).isEqualTo(DEFAULT_OITO_P_ME_CINQUENTA);
        assertThat(testBlocos.getNoveAM()).isEqualTo(DEFAULT_NOVE_AM);
        assertThat(testBlocos.getNoveAMeDez()).isEqualTo(DEFAULT_NOVE_A_ME_DEZ);
        assertThat(testBlocos.getNoveAMeVinte()).isEqualTo(DEFAULT_NOVE_A_ME_VINTE);
        assertThat(testBlocos.getNoveAMeTrinta()).isEqualTo(DEFAULT_NOVE_A_ME_TRINTA);
        assertThat(testBlocos.getNoveAMeQuarenta()).isEqualTo(DEFAULT_NOVE_A_ME_QUARENTA);
        assertThat(testBlocos.getNoveAMeCinquenta()).isEqualTo(DEFAULT_NOVE_A_ME_CINQUENTA);
        assertThat(testBlocos.getNovePM()).isEqualTo(DEFAULT_NOVE_PM);
        assertThat(testBlocos.getNovePMeDez()).isEqualTo(DEFAULT_NOVE_P_ME_DEZ);
        assertThat(testBlocos.getNovePMeVinte()).isEqualTo(DEFAULT_NOVE_P_ME_VINTE);
        assertThat(testBlocos.getNovePMeTrinta()).isEqualTo(DEFAULT_NOVE_P_ME_TRINTA);
        assertThat(testBlocos.getNovePMeQuarenta()).isEqualTo(DEFAULT_NOVE_P_ME_QUARENTA);
        assertThat(testBlocos.getNovePMeCinquenta()).isEqualTo(DEFAULT_NOVE_P_ME_CINQUENTA);
        assertThat(testBlocos.getDezAM()).isEqualTo(DEFAULT_DEZ_AM);
        assertThat(testBlocos.getDezAMeDez()).isEqualTo(DEFAULT_DEZ_A_ME_DEZ);
        assertThat(testBlocos.getDezAMeVinte()).isEqualTo(DEFAULT_DEZ_A_ME_VINTE);
        assertThat(testBlocos.getDezAMeTrinta()).isEqualTo(DEFAULT_DEZ_A_ME_TRINTA);
        assertThat(testBlocos.getDezAMeQuarenta()).isEqualTo(DEFAULT_DEZ_A_ME_QUARENTA);
        assertThat(testBlocos.getDezAMeCinquenta()).isEqualTo(DEFAULT_DEZ_A_ME_CINQUENTA);
        assertThat(testBlocos.getDezPM()).isEqualTo(DEFAULT_DEZ_PM);
        assertThat(testBlocos.getDezPMeDez()).isEqualTo(DEFAULT_DEZ_P_ME_DEZ);
        assertThat(testBlocos.getDezPMeVinte()).isEqualTo(DEFAULT_DEZ_P_ME_VINTE);
        assertThat(testBlocos.getDezPMeTrinta()).isEqualTo(DEFAULT_DEZ_P_ME_TRINTA);
        assertThat(testBlocos.getDezPMeQuarenta()).isEqualTo(DEFAULT_DEZ_P_ME_QUARENTA);
        assertThat(testBlocos.getDezPMeCinquenta()).isEqualTo(DEFAULT_DEZ_P_ME_CINQUENTA);
        assertThat(testBlocos.getOnzeAM()).isEqualTo(DEFAULT_ONZE_AM);
        assertThat(testBlocos.getOnzeAMeDez()).isEqualTo(DEFAULT_ONZE_A_ME_DEZ);
        assertThat(testBlocos.getOnzeAMeVinte()).isEqualTo(DEFAULT_ONZE_A_ME_VINTE);
        assertThat(testBlocos.getOnzeAMeTrinta()).isEqualTo(DEFAULT_ONZE_A_ME_TRINTA);
        assertThat(testBlocos.getOnzeAMeQuarenta()).isEqualTo(DEFAULT_ONZE_A_ME_QUARENTA);
        assertThat(testBlocos.getOnzeAMeCinquenta()).isEqualTo(DEFAULT_ONZE_A_ME_CINQUENTA);
        assertThat(testBlocos.getOnzePM()).isEqualTo(DEFAULT_ONZE_PM);
        assertThat(testBlocos.getOnzePMeDez()).isEqualTo(DEFAULT_ONZE_P_ME_DEZ);
        assertThat(testBlocos.getOnzePMeVinte()).isEqualTo(DEFAULT_ONZE_P_ME_VINTE);
        assertThat(testBlocos.getOnzePMeTrinta()).isEqualTo(DEFAULT_ONZE_P_ME_TRINTA);
        assertThat(testBlocos.getOnzePMeQuarenta()).isEqualTo(DEFAULT_ONZE_P_ME_QUARENTA);
        assertThat(testBlocos.getOnzePMeCinquenta()).isEqualTo(DEFAULT_ONZE_P_ME_CINQUENTA);
    }

    @Test
    @Transactional
    void createBlocosWithExistingId() throws Exception {
        // Create the Blocos with an existing ID
        blocos.setId(1L);

        int databaseSizeBeforeCreate = blocosRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restBlocosMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(blocos)))
            .andExpect(status().isBadRequest());

        // Validate the Blocos in the database
        List<Blocos> blocosList = blocosRepository.findAll();
        assertThat(blocosList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllBlocos() throws Exception {
        // Initialize the database
        blocosRepository.saveAndFlush(blocos);

        // Get all the blocosList
        restBlocosMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(blocos.getId().intValue())))
            .andExpect(jsonPath("$.[*].zeroAM").value(hasItem(sameInstant(DEFAULT_ZERO_AM))))
            .andExpect(jsonPath("$.[*].zeroAMeDez").value(hasItem(sameInstant(DEFAULT_ZERO_A_ME_DEZ))))
            .andExpect(jsonPath("$.[*].zeroAMeVinte").value(hasItem(sameInstant(DEFAULT_ZERO_A_ME_VINTE))))
            .andExpect(jsonPath("$.[*].zeroAMeTrinta").value(hasItem(sameInstant(DEFAULT_ZERO_A_ME_TRINTA))))
            .andExpect(jsonPath("$.[*].zeroAMeQuarenta").value(hasItem(sameInstant(DEFAULT_ZERO_A_ME_QUARENTA))))
            .andExpect(jsonPath("$.[*].zeroAMeCinquenta").value(hasItem(sameInstant(DEFAULT_ZERO_A_ME_CINQUENTA))))
            .andExpect(jsonPath("$.[*].zeroPM").value(hasItem(sameInstant(DEFAULT_ZERO_PM))))
            .andExpect(jsonPath("$.[*].zeroPMeDez").value(hasItem(sameInstant(DEFAULT_ZERO_P_ME_DEZ))))
            .andExpect(jsonPath("$.[*].zeroPMeVinte").value(hasItem(sameInstant(DEFAULT_ZERO_P_ME_VINTE))))
            .andExpect(jsonPath("$.[*].zeroPMeTrinta").value(hasItem(sameInstant(DEFAULT_ZERO_P_ME_TRINTA))))
            .andExpect(jsonPath("$.[*].zeroPMeQuarenta").value(hasItem(sameInstant(DEFAULT_ZERO_P_ME_QUARENTA))))
            .andExpect(jsonPath("$.[*].zeroPMeCinquenta").value(hasItem(sameInstant(DEFAULT_ZERO_P_ME_CINQUENTA))))
            .andExpect(jsonPath("$.[*].umAM").value(hasItem(sameInstant(DEFAULT_UM_AM))))
            .andExpect(jsonPath("$.[*].umAMeDez").value(hasItem(sameInstant(DEFAULT_UM_A_ME_DEZ))))
            .andExpect(jsonPath("$.[*].umAMeVinte").value(hasItem(sameInstant(DEFAULT_UM_A_ME_VINTE))))
            .andExpect(jsonPath("$.[*].umAMeTrinta").value(hasItem(sameInstant(DEFAULT_UM_A_ME_TRINTA))))
            .andExpect(jsonPath("$.[*].umAMeQuarenta").value(hasItem(sameInstant(DEFAULT_UM_A_ME_QUARENTA))))
            .andExpect(jsonPath("$.[*].umAMeCinquenta").value(hasItem(sameInstant(DEFAULT_UM_A_ME_CINQUENTA))))
            .andExpect(jsonPath("$.[*].umPM").value(hasItem(sameInstant(DEFAULT_UM_PM))))
            .andExpect(jsonPath("$.[*].umPMeDez").value(hasItem(sameInstant(DEFAULT_UM_P_ME_DEZ))))
            .andExpect(jsonPath("$.[*].umPMeVinte").value(hasItem(sameInstant(DEFAULT_UM_P_ME_VINTE))))
            .andExpect(jsonPath("$.[*].umPMeTrinta").value(hasItem(sameInstant(DEFAULT_UM_P_ME_TRINTA))))
            .andExpect(jsonPath("$.[*].umPMeQuarenta").value(hasItem(sameInstant(DEFAULT_UM_P_ME_QUARENTA))))
            .andExpect(jsonPath("$.[*].umPMeCinquenta").value(hasItem(sameInstant(DEFAULT_UM_P_ME_CINQUENTA))))
            .andExpect(jsonPath("$.[*].doisAM").value(hasItem(sameInstant(DEFAULT_DOIS_AM))))
            .andExpect(jsonPath("$.[*].doisAMeDez").value(hasItem(sameInstant(DEFAULT_DOIS_A_ME_DEZ))))
            .andExpect(jsonPath("$.[*].doisAMeVinte").value(hasItem(sameInstant(DEFAULT_DOIS_A_ME_VINTE))))
            .andExpect(jsonPath("$.[*].doisAMeTrinta").value(hasItem(sameInstant(DEFAULT_DOIS_A_ME_TRINTA))))
            .andExpect(jsonPath("$.[*].doisAMeQuarenta").value(hasItem(sameInstant(DEFAULT_DOIS_A_ME_QUARENTA))))
            .andExpect(jsonPath("$.[*].doisAMeCinquenta").value(hasItem(sameInstant(DEFAULT_DOIS_A_ME_CINQUENTA))))
            .andExpect(jsonPath("$.[*].doisPM").value(hasItem(sameInstant(DEFAULT_DOIS_PM))))
            .andExpect(jsonPath("$.[*].doisPMeDez").value(hasItem(sameInstant(DEFAULT_DOIS_P_ME_DEZ))))
            .andExpect(jsonPath("$.[*].doisPMeVinte").value(hasItem(sameInstant(DEFAULT_DOIS_P_ME_VINTE))))
            .andExpect(jsonPath("$.[*].doisPMeTrinta").value(hasItem(sameInstant(DEFAULT_DOIS_P_ME_TRINTA))))
            .andExpect(jsonPath("$.[*].doisPMeQuarenta").value(hasItem(sameInstant(DEFAULT_DOIS_P_ME_QUARENTA))))
            .andExpect(jsonPath("$.[*].doisPMeCinquenta").value(hasItem(sameInstant(DEFAULT_DOIS_P_ME_CINQUENTA))))
            .andExpect(jsonPath("$.[*].tresAM").value(hasItem(sameInstant(DEFAULT_TRES_AM))))
            .andExpect(jsonPath("$.[*].tresAMeDez").value(hasItem(sameInstant(DEFAULT_TRES_A_ME_DEZ))))
            .andExpect(jsonPath("$.[*].tresAMeVinte").value(hasItem(sameInstant(DEFAULT_TRES_A_ME_VINTE))))
            .andExpect(jsonPath("$.[*].tresAMeTrinta").value(hasItem(sameInstant(DEFAULT_TRES_A_ME_TRINTA))))
            .andExpect(jsonPath("$.[*].tresAMeQuarenta").value(hasItem(sameInstant(DEFAULT_TRES_A_ME_QUARENTA))))
            .andExpect(jsonPath("$.[*].tresAMeCinquenta").value(hasItem(sameInstant(DEFAULT_TRES_A_ME_CINQUENTA))))
            .andExpect(jsonPath("$.[*].tresPM").value(hasItem(sameInstant(DEFAULT_TRES_PM))))
            .andExpect(jsonPath("$.[*].tresPMeDez").value(hasItem(sameInstant(DEFAULT_TRES_P_ME_DEZ))))
            .andExpect(jsonPath("$.[*].tresPMeVinte").value(hasItem(sameInstant(DEFAULT_TRES_P_ME_VINTE))))
            .andExpect(jsonPath("$.[*].tresPMeTrinta").value(hasItem(sameInstant(DEFAULT_TRES_P_ME_TRINTA))))
            .andExpect(jsonPath("$.[*].tresPMeQuarenta").value(hasItem(sameInstant(DEFAULT_TRES_P_ME_QUARENTA))))
            .andExpect(jsonPath("$.[*].tresPMeCinquenta").value(hasItem(sameInstant(DEFAULT_TRES_P_ME_CINQUENTA))))
            .andExpect(jsonPath("$.[*].quatroAM").value(hasItem(sameInstant(DEFAULT_QUATRO_AM))))
            .andExpect(jsonPath("$.[*].quatroAMeDez").value(hasItem(sameInstant(DEFAULT_QUATRO_A_ME_DEZ))))
            .andExpect(jsonPath("$.[*].quatroAMeVinte").value(hasItem(sameInstant(DEFAULT_QUATRO_A_ME_VINTE))))
            .andExpect(jsonPath("$.[*].quatroAMeTrinta").value(hasItem(sameInstant(DEFAULT_QUATRO_A_ME_TRINTA))))
            .andExpect(jsonPath("$.[*].quatroAMeQuarenta").value(hasItem(sameInstant(DEFAULT_QUATRO_A_ME_QUARENTA))))
            .andExpect(jsonPath("$.[*].quatroAMeCinquenta").value(hasItem(sameInstant(DEFAULT_QUATRO_A_ME_CINQUENTA))))
            .andExpect(jsonPath("$.[*].quatroPM").value(hasItem(sameInstant(DEFAULT_QUATRO_PM))))
            .andExpect(jsonPath("$.[*].quatroPMeDez").value(hasItem(sameInstant(DEFAULT_QUATRO_P_ME_DEZ))))
            .andExpect(jsonPath("$.[*].quatroPMeVinte").value(hasItem(sameInstant(DEFAULT_QUATRO_P_ME_VINTE))))
            .andExpect(jsonPath("$.[*].quatroPMeTrinta").value(hasItem(sameInstant(DEFAULT_QUATRO_P_ME_TRINTA))))
            .andExpect(jsonPath("$.[*].quatroPMeQuarenta").value(hasItem(sameInstant(DEFAULT_QUATRO_P_ME_QUARENTA))))
            .andExpect(jsonPath("$.[*].quatroPMeCinquenta").value(hasItem(sameInstant(DEFAULT_QUATRO_P_ME_CINQUENTA))))
            .andExpect(jsonPath("$.[*].cincoAM").value(hasItem(sameInstant(DEFAULT_CINCO_AM))))
            .andExpect(jsonPath("$.[*].cincoAMeDez").value(hasItem(sameInstant(DEFAULT_CINCO_A_ME_DEZ))))
            .andExpect(jsonPath("$.[*].cincoAMeVinte").value(hasItem(sameInstant(DEFAULT_CINCO_A_ME_VINTE))))
            .andExpect(jsonPath("$.[*].cincoAMeTrinta").value(hasItem(sameInstant(DEFAULT_CINCO_A_ME_TRINTA))))
            .andExpect(jsonPath("$.[*].cincoAMeQuarenta").value(hasItem(sameInstant(DEFAULT_CINCO_A_ME_QUARENTA))))
            .andExpect(jsonPath("$.[*].cincoAMeCinquenta").value(hasItem(sameInstant(DEFAULT_CINCO_A_ME_CINQUENTA))))
            .andExpect(jsonPath("$.[*].cincoPM").value(hasItem(sameInstant(DEFAULT_CINCO_PM))))
            .andExpect(jsonPath("$.[*].cincoPMeDez").value(hasItem(sameInstant(DEFAULT_CINCO_P_ME_DEZ))))
            .andExpect(jsonPath("$.[*].cincoPMeVinte").value(hasItem(sameInstant(DEFAULT_CINCO_P_ME_VINTE))))
            .andExpect(jsonPath("$.[*].cincoPMeTrinta").value(hasItem(sameInstant(DEFAULT_CINCO_P_ME_TRINTA))))
            .andExpect(jsonPath("$.[*].cincoPMeQuarenta").value(hasItem(sameInstant(DEFAULT_CINCO_P_ME_QUARENTA))))
            .andExpect(jsonPath("$.[*].cincoPMeCinquenta").value(hasItem(sameInstant(DEFAULT_CINCO_P_ME_CINQUENTA))))
            .andExpect(jsonPath("$.[*].seisAM").value(hasItem(sameInstant(DEFAULT_SEIS_AM))))
            .andExpect(jsonPath("$.[*].seisAMeDez").value(hasItem(sameInstant(DEFAULT_SEIS_A_ME_DEZ))))
            .andExpect(jsonPath("$.[*].seisAMeVinte").value(hasItem(sameInstant(DEFAULT_SEIS_A_ME_VINTE))))
            .andExpect(jsonPath("$.[*].seisAMeTrinta").value(hasItem(sameInstant(DEFAULT_SEIS_A_ME_TRINTA))))
            .andExpect(jsonPath("$.[*].seisAMeQuarenta").value(hasItem(sameInstant(DEFAULT_SEIS_A_ME_QUARENTA))))
            .andExpect(jsonPath("$.[*].seisAMeCinquenta").value(hasItem(sameInstant(DEFAULT_SEIS_A_ME_CINQUENTA))))
            .andExpect(jsonPath("$.[*].seisPM").value(hasItem(sameInstant(DEFAULT_SEIS_PM))))
            .andExpect(jsonPath("$.[*].seisPMeDez").value(hasItem(sameInstant(DEFAULT_SEIS_P_ME_DEZ))))
            .andExpect(jsonPath("$.[*].seisPMeVinte").value(hasItem(sameInstant(DEFAULT_SEIS_P_ME_VINTE))))
            .andExpect(jsonPath("$.[*].seisPMeTrinta").value(hasItem(sameInstant(DEFAULT_SEIS_P_ME_TRINTA))))
            .andExpect(jsonPath("$.[*].seisPMeQuarenta").value(hasItem(sameInstant(DEFAULT_SEIS_P_ME_QUARENTA))))
            .andExpect(jsonPath("$.[*].seisPMeCinquenta").value(hasItem(sameInstant(DEFAULT_SEIS_P_ME_CINQUENTA))))
            .andExpect(jsonPath("$.[*].seteAM").value(hasItem(sameInstant(DEFAULT_SETE_AM))))
            .andExpect(jsonPath("$.[*].seteAMeDez").value(hasItem(sameInstant(DEFAULT_SETE_A_ME_DEZ))))
            .andExpect(jsonPath("$.[*].seteAMeVinte").value(hasItem(sameInstant(DEFAULT_SETE_A_ME_VINTE))))
            .andExpect(jsonPath("$.[*].seteAMeTrinta").value(hasItem(sameInstant(DEFAULT_SETE_A_ME_TRINTA))))
            .andExpect(jsonPath("$.[*].seteAMeQuarenta").value(hasItem(sameInstant(DEFAULT_SETE_A_ME_QUARENTA))))
            .andExpect(jsonPath("$.[*].seteAMeCinquenta").value(hasItem(sameInstant(DEFAULT_SETE_A_ME_CINQUENTA))))
            .andExpect(jsonPath("$.[*].setePM").value(hasItem(sameInstant(DEFAULT_SETE_PM))))
            .andExpect(jsonPath("$.[*].setePMeDez").value(hasItem(sameInstant(DEFAULT_SETE_P_ME_DEZ))))
            .andExpect(jsonPath("$.[*].setePMeVinte").value(hasItem(sameInstant(DEFAULT_SETE_P_ME_VINTE))))
            .andExpect(jsonPath("$.[*].setePMeTrinta").value(hasItem(sameInstant(DEFAULT_SETE_P_ME_TRINTA))))
            .andExpect(jsonPath("$.[*].setePMeQuarenta").value(hasItem(sameInstant(DEFAULT_SETE_P_ME_QUARENTA))))
            .andExpect(jsonPath("$.[*].setePMeCinquenta").value(hasItem(sameInstant(DEFAULT_SETE_P_ME_CINQUENTA))))
            .andExpect(jsonPath("$.[*].oitoAM").value(hasItem(sameInstant(DEFAULT_OITO_AM))))
            .andExpect(jsonPath("$.[*].oitoAMeDez").value(hasItem(sameInstant(DEFAULT_OITO_A_ME_DEZ))))
            .andExpect(jsonPath("$.[*].oitoAMeVinte").value(hasItem(sameInstant(DEFAULT_OITO_A_ME_VINTE))))
            .andExpect(jsonPath("$.[*].oitoAMeTrinta").value(hasItem(sameInstant(DEFAULT_OITO_A_ME_TRINTA))))
            .andExpect(jsonPath("$.[*].oitoAMeQuarenta").value(hasItem(sameInstant(DEFAULT_OITO_A_ME_QUARENTA))))
            .andExpect(jsonPath("$.[*].oitoAMeCinquenta").value(hasItem(sameInstant(DEFAULT_OITO_A_ME_CINQUENTA))))
            .andExpect(jsonPath("$.[*].oitoPM").value(hasItem(sameInstant(DEFAULT_OITO_PM))))
            .andExpect(jsonPath("$.[*].oitoPMeDez").value(hasItem(sameInstant(DEFAULT_OITO_P_ME_DEZ))))
            .andExpect(jsonPath("$.[*].oitoPMeVinte").value(hasItem(sameInstant(DEFAULT_OITO_P_ME_VINTE))))
            .andExpect(jsonPath("$.[*].oitoPMeTrinta").value(hasItem(sameInstant(DEFAULT_OITO_P_ME_TRINTA))))
            .andExpect(jsonPath("$.[*].oitoPMeQuarenta").value(hasItem(sameInstant(DEFAULT_OITO_P_ME_QUARENTA))))
            .andExpect(jsonPath("$.[*].oitoPMeCinquenta").value(hasItem(sameInstant(DEFAULT_OITO_P_ME_CINQUENTA))))
            .andExpect(jsonPath("$.[*].noveAM").value(hasItem(sameInstant(DEFAULT_NOVE_AM))))
            .andExpect(jsonPath("$.[*].noveAMeDez").value(hasItem(sameInstant(DEFAULT_NOVE_A_ME_DEZ))))
            .andExpect(jsonPath("$.[*].noveAMeVinte").value(hasItem(sameInstant(DEFAULT_NOVE_A_ME_VINTE))))
            .andExpect(jsonPath("$.[*].noveAMeTrinta").value(hasItem(sameInstant(DEFAULT_NOVE_A_ME_TRINTA))))
            .andExpect(jsonPath("$.[*].noveAMeQuarenta").value(hasItem(sameInstant(DEFAULT_NOVE_A_ME_QUARENTA))))
            .andExpect(jsonPath("$.[*].noveAMeCinquenta").value(hasItem(sameInstant(DEFAULT_NOVE_A_ME_CINQUENTA))))
            .andExpect(jsonPath("$.[*].novePM").value(hasItem(sameInstant(DEFAULT_NOVE_PM))))
            .andExpect(jsonPath("$.[*].novePMeDez").value(hasItem(sameInstant(DEFAULT_NOVE_P_ME_DEZ))))
            .andExpect(jsonPath("$.[*].novePMeVinte").value(hasItem(sameInstant(DEFAULT_NOVE_P_ME_VINTE))))
            .andExpect(jsonPath("$.[*].novePMeTrinta").value(hasItem(sameInstant(DEFAULT_NOVE_P_ME_TRINTA))))
            .andExpect(jsonPath("$.[*].novePMeQuarenta").value(hasItem(sameInstant(DEFAULT_NOVE_P_ME_QUARENTA))))
            .andExpect(jsonPath("$.[*].novePMeCinquenta").value(hasItem(sameInstant(DEFAULT_NOVE_P_ME_CINQUENTA))))
            .andExpect(jsonPath("$.[*].dezAM").value(hasItem(sameInstant(DEFAULT_DEZ_AM))))
            .andExpect(jsonPath("$.[*].dezAMeDez").value(hasItem(sameInstant(DEFAULT_DEZ_A_ME_DEZ))))
            .andExpect(jsonPath("$.[*].dezAMeVinte").value(hasItem(sameInstant(DEFAULT_DEZ_A_ME_VINTE))))
            .andExpect(jsonPath("$.[*].dezAMeTrinta").value(hasItem(sameInstant(DEFAULT_DEZ_A_ME_TRINTA))))
            .andExpect(jsonPath("$.[*].dezAMeQuarenta").value(hasItem(sameInstant(DEFAULT_DEZ_A_ME_QUARENTA))))
            .andExpect(jsonPath("$.[*].dezAMeCinquenta").value(hasItem(sameInstant(DEFAULT_DEZ_A_ME_CINQUENTA))))
            .andExpect(jsonPath("$.[*].dezPM").value(hasItem(sameInstant(DEFAULT_DEZ_PM))))
            .andExpect(jsonPath("$.[*].dezPMeDez").value(hasItem(sameInstant(DEFAULT_DEZ_P_ME_DEZ))))
            .andExpect(jsonPath("$.[*].dezPMeVinte").value(hasItem(sameInstant(DEFAULT_DEZ_P_ME_VINTE))))
            .andExpect(jsonPath("$.[*].dezPMeTrinta").value(hasItem(sameInstant(DEFAULT_DEZ_P_ME_TRINTA))))
            .andExpect(jsonPath("$.[*].dezPMeQuarenta").value(hasItem(sameInstant(DEFAULT_DEZ_P_ME_QUARENTA))))
            .andExpect(jsonPath("$.[*].dezPMeCinquenta").value(hasItem(sameInstant(DEFAULT_DEZ_P_ME_CINQUENTA))))
            .andExpect(jsonPath("$.[*].onzeAM").value(hasItem(sameInstant(DEFAULT_ONZE_AM))))
            .andExpect(jsonPath("$.[*].onzeAMeDez").value(hasItem(sameInstant(DEFAULT_ONZE_A_ME_DEZ))))
            .andExpect(jsonPath("$.[*].onzeAMeVinte").value(hasItem(sameInstant(DEFAULT_ONZE_A_ME_VINTE))))
            .andExpect(jsonPath("$.[*].onzeAMeTrinta").value(hasItem(sameInstant(DEFAULT_ONZE_A_ME_TRINTA))))
            .andExpect(jsonPath("$.[*].onzeAMeQuarenta").value(hasItem(sameInstant(DEFAULT_ONZE_A_ME_QUARENTA))))
            .andExpect(jsonPath("$.[*].onzeAMeCinquenta").value(hasItem(sameInstant(DEFAULT_ONZE_A_ME_CINQUENTA))))
            .andExpect(jsonPath("$.[*].onzePM").value(hasItem(sameInstant(DEFAULT_ONZE_PM))))
            .andExpect(jsonPath("$.[*].onzePMeDez").value(hasItem(sameInstant(DEFAULT_ONZE_P_ME_DEZ))))
            .andExpect(jsonPath("$.[*].onzePMeVinte").value(hasItem(sameInstant(DEFAULT_ONZE_P_ME_VINTE))))
            .andExpect(jsonPath("$.[*].onzePMeTrinta").value(hasItem(sameInstant(DEFAULT_ONZE_P_ME_TRINTA))))
            .andExpect(jsonPath("$.[*].onzePMeQuarenta").value(hasItem(sameInstant(DEFAULT_ONZE_P_ME_QUARENTA))))
            .andExpect(jsonPath("$.[*].onzePMeCinquenta").value(hasItem(sameInstant(DEFAULT_ONZE_P_ME_CINQUENTA))));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllBlocosWithEagerRelationshipsIsEnabled() throws Exception {
        when(blocosRepositoryMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restBlocosMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(blocosRepositoryMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllBlocosWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(blocosRepositoryMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restBlocosMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(blocosRepositoryMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    void getBlocos() throws Exception {
        // Initialize the database
        blocosRepository.saveAndFlush(blocos);

        // Get the blocos
        restBlocosMockMvc
            .perform(get(ENTITY_API_URL_ID, blocos.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(blocos.getId().intValue()))
            .andExpect(jsonPath("$.zeroAM").value(sameInstant(DEFAULT_ZERO_AM)))
            .andExpect(jsonPath("$.zeroAMeDez").value(sameInstant(DEFAULT_ZERO_A_ME_DEZ)))
            .andExpect(jsonPath("$.zeroAMeVinte").value(sameInstant(DEFAULT_ZERO_A_ME_VINTE)))
            .andExpect(jsonPath("$.zeroAMeTrinta").value(sameInstant(DEFAULT_ZERO_A_ME_TRINTA)))
            .andExpect(jsonPath("$.zeroAMeQuarenta").value(sameInstant(DEFAULT_ZERO_A_ME_QUARENTA)))
            .andExpect(jsonPath("$.zeroAMeCinquenta").value(sameInstant(DEFAULT_ZERO_A_ME_CINQUENTA)))
            .andExpect(jsonPath("$.zeroPM").value(sameInstant(DEFAULT_ZERO_PM)))
            .andExpect(jsonPath("$.zeroPMeDez").value(sameInstant(DEFAULT_ZERO_P_ME_DEZ)))
            .andExpect(jsonPath("$.zeroPMeVinte").value(sameInstant(DEFAULT_ZERO_P_ME_VINTE)))
            .andExpect(jsonPath("$.zeroPMeTrinta").value(sameInstant(DEFAULT_ZERO_P_ME_TRINTA)))
            .andExpect(jsonPath("$.zeroPMeQuarenta").value(sameInstant(DEFAULT_ZERO_P_ME_QUARENTA)))
            .andExpect(jsonPath("$.zeroPMeCinquenta").value(sameInstant(DEFAULT_ZERO_P_ME_CINQUENTA)))
            .andExpect(jsonPath("$.umAM").value(sameInstant(DEFAULT_UM_AM)))
            .andExpect(jsonPath("$.umAMeDez").value(sameInstant(DEFAULT_UM_A_ME_DEZ)))
            .andExpect(jsonPath("$.umAMeVinte").value(sameInstant(DEFAULT_UM_A_ME_VINTE)))
            .andExpect(jsonPath("$.umAMeTrinta").value(sameInstant(DEFAULT_UM_A_ME_TRINTA)))
            .andExpect(jsonPath("$.umAMeQuarenta").value(sameInstant(DEFAULT_UM_A_ME_QUARENTA)))
            .andExpect(jsonPath("$.umAMeCinquenta").value(sameInstant(DEFAULT_UM_A_ME_CINQUENTA)))
            .andExpect(jsonPath("$.umPM").value(sameInstant(DEFAULT_UM_PM)))
            .andExpect(jsonPath("$.umPMeDez").value(sameInstant(DEFAULT_UM_P_ME_DEZ)))
            .andExpect(jsonPath("$.umPMeVinte").value(sameInstant(DEFAULT_UM_P_ME_VINTE)))
            .andExpect(jsonPath("$.umPMeTrinta").value(sameInstant(DEFAULT_UM_P_ME_TRINTA)))
            .andExpect(jsonPath("$.umPMeQuarenta").value(sameInstant(DEFAULT_UM_P_ME_QUARENTA)))
            .andExpect(jsonPath("$.umPMeCinquenta").value(sameInstant(DEFAULT_UM_P_ME_CINQUENTA)))
            .andExpect(jsonPath("$.doisAM").value(sameInstant(DEFAULT_DOIS_AM)))
            .andExpect(jsonPath("$.doisAMeDez").value(sameInstant(DEFAULT_DOIS_A_ME_DEZ)))
            .andExpect(jsonPath("$.doisAMeVinte").value(sameInstant(DEFAULT_DOIS_A_ME_VINTE)))
            .andExpect(jsonPath("$.doisAMeTrinta").value(sameInstant(DEFAULT_DOIS_A_ME_TRINTA)))
            .andExpect(jsonPath("$.doisAMeQuarenta").value(sameInstant(DEFAULT_DOIS_A_ME_QUARENTA)))
            .andExpect(jsonPath("$.doisAMeCinquenta").value(sameInstant(DEFAULT_DOIS_A_ME_CINQUENTA)))
            .andExpect(jsonPath("$.doisPM").value(sameInstant(DEFAULT_DOIS_PM)))
            .andExpect(jsonPath("$.doisPMeDez").value(sameInstant(DEFAULT_DOIS_P_ME_DEZ)))
            .andExpect(jsonPath("$.doisPMeVinte").value(sameInstant(DEFAULT_DOIS_P_ME_VINTE)))
            .andExpect(jsonPath("$.doisPMeTrinta").value(sameInstant(DEFAULT_DOIS_P_ME_TRINTA)))
            .andExpect(jsonPath("$.doisPMeQuarenta").value(sameInstant(DEFAULT_DOIS_P_ME_QUARENTA)))
            .andExpect(jsonPath("$.doisPMeCinquenta").value(sameInstant(DEFAULT_DOIS_P_ME_CINQUENTA)))
            .andExpect(jsonPath("$.tresAM").value(sameInstant(DEFAULT_TRES_AM)))
            .andExpect(jsonPath("$.tresAMeDez").value(sameInstant(DEFAULT_TRES_A_ME_DEZ)))
            .andExpect(jsonPath("$.tresAMeVinte").value(sameInstant(DEFAULT_TRES_A_ME_VINTE)))
            .andExpect(jsonPath("$.tresAMeTrinta").value(sameInstant(DEFAULT_TRES_A_ME_TRINTA)))
            .andExpect(jsonPath("$.tresAMeQuarenta").value(sameInstant(DEFAULT_TRES_A_ME_QUARENTA)))
            .andExpect(jsonPath("$.tresAMeCinquenta").value(sameInstant(DEFAULT_TRES_A_ME_CINQUENTA)))
            .andExpect(jsonPath("$.tresPM").value(sameInstant(DEFAULT_TRES_PM)))
            .andExpect(jsonPath("$.tresPMeDez").value(sameInstant(DEFAULT_TRES_P_ME_DEZ)))
            .andExpect(jsonPath("$.tresPMeVinte").value(sameInstant(DEFAULT_TRES_P_ME_VINTE)))
            .andExpect(jsonPath("$.tresPMeTrinta").value(sameInstant(DEFAULT_TRES_P_ME_TRINTA)))
            .andExpect(jsonPath("$.tresPMeQuarenta").value(sameInstant(DEFAULT_TRES_P_ME_QUARENTA)))
            .andExpect(jsonPath("$.tresPMeCinquenta").value(sameInstant(DEFAULT_TRES_P_ME_CINQUENTA)))
            .andExpect(jsonPath("$.quatroAM").value(sameInstant(DEFAULT_QUATRO_AM)))
            .andExpect(jsonPath("$.quatroAMeDez").value(sameInstant(DEFAULT_QUATRO_A_ME_DEZ)))
            .andExpect(jsonPath("$.quatroAMeVinte").value(sameInstant(DEFAULT_QUATRO_A_ME_VINTE)))
            .andExpect(jsonPath("$.quatroAMeTrinta").value(sameInstant(DEFAULT_QUATRO_A_ME_TRINTA)))
            .andExpect(jsonPath("$.quatroAMeQuarenta").value(sameInstant(DEFAULT_QUATRO_A_ME_QUARENTA)))
            .andExpect(jsonPath("$.quatroAMeCinquenta").value(sameInstant(DEFAULT_QUATRO_A_ME_CINQUENTA)))
            .andExpect(jsonPath("$.quatroPM").value(sameInstant(DEFAULT_QUATRO_PM)))
            .andExpect(jsonPath("$.quatroPMeDez").value(sameInstant(DEFAULT_QUATRO_P_ME_DEZ)))
            .andExpect(jsonPath("$.quatroPMeVinte").value(sameInstant(DEFAULT_QUATRO_P_ME_VINTE)))
            .andExpect(jsonPath("$.quatroPMeTrinta").value(sameInstant(DEFAULT_QUATRO_P_ME_TRINTA)))
            .andExpect(jsonPath("$.quatroPMeQuarenta").value(sameInstant(DEFAULT_QUATRO_P_ME_QUARENTA)))
            .andExpect(jsonPath("$.quatroPMeCinquenta").value(sameInstant(DEFAULT_QUATRO_P_ME_CINQUENTA)))
            .andExpect(jsonPath("$.cincoAM").value(sameInstant(DEFAULT_CINCO_AM)))
            .andExpect(jsonPath("$.cincoAMeDez").value(sameInstant(DEFAULT_CINCO_A_ME_DEZ)))
            .andExpect(jsonPath("$.cincoAMeVinte").value(sameInstant(DEFAULT_CINCO_A_ME_VINTE)))
            .andExpect(jsonPath("$.cincoAMeTrinta").value(sameInstant(DEFAULT_CINCO_A_ME_TRINTA)))
            .andExpect(jsonPath("$.cincoAMeQuarenta").value(sameInstant(DEFAULT_CINCO_A_ME_QUARENTA)))
            .andExpect(jsonPath("$.cincoAMeCinquenta").value(sameInstant(DEFAULT_CINCO_A_ME_CINQUENTA)))
            .andExpect(jsonPath("$.cincoPM").value(sameInstant(DEFAULT_CINCO_PM)))
            .andExpect(jsonPath("$.cincoPMeDez").value(sameInstant(DEFAULT_CINCO_P_ME_DEZ)))
            .andExpect(jsonPath("$.cincoPMeVinte").value(sameInstant(DEFAULT_CINCO_P_ME_VINTE)))
            .andExpect(jsonPath("$.cincoPMeTrinta").value(sameInstant(DEFAULT_CINCO_P_ME_TRINTA)))
            .andExpect(jsonPath("$.cincoPMeQuarenta").value(sameInstant(DEFAULT_CINCO_P_ME_QUARENTA)))
            .andExpect(jsonPath("$.cincoPMeCinquenta").value(sameInstant(DEFAULT_CINCO_P_ME_CINQUENTA)))
            .andExpect(jsonPath("$.seisAM").value(sameInstant(DEFAULT_SEIS_AM)))
            .andExpect(jsonPath("$.seisAMeDez").value(sameInstant(DEFAULT_SEIS_A_ME_DEZ)))
            .andExpect(jsonPath("$.seisAMeVinte").value(sameInstant(DEFAULT_SEIS_A_ME_VINTE)))
            .andExpect(jsonPath("$.seisAMeTrinta").value(sameInstant(DEFAULT_SEIS_A_ME_TRINTA)))
            .andExpect(jsonPath("$.seisAMeQuarenta").value(sameInstant(DEFAULT_SEIS_A_ME_QUARENTA)))
            .andExpect(jsonPath("$.seisAMeCinquenta").value(sameInstant(DEFAULT_SEIS_A_ME_CINQUENTA)))
            .andExpect(jsonPath("$.seisPM").value(sameInstant(DEFAULT_SEIS_PM)))
            .andExpect(jsonPath("$.seisPMeDez").value(sameInstant(DEFAULT_SEIS_P_ME_DEZ)))
            .andExpect(jsonPath("$.seisPMeVinte").value(sameInstant(DEFAULT_SEIS_P_ME_VINTE)))
            .andExpect(jsonPath("$.seisPMeTrinta").value(sameInstant(DEFAULT_SEIS_P_ME_TRINTA)))
            .andExpect(jsonPath("$.seisPMeQuarenta").value(sameInstant(DEFAULT_SEIS_P_ME_QUARENTA)))
            .andExpect(jsonPath("$.seisPMeCinquenta").value(sameInstant(DEFAULT_SEIS_P_ME_CINQUENTA)))
            .andExpect(jsonPath("$.seteAM").value(sameInstant(DEFAULT_SETE_AM)))
            .andExpect(jsonPath("$.seteAMeDez").value(sameInstant(DEFAULT_SETE_A_ME_DEZ)))
            .andExpect(jsonPath("$.seteAMeVinte").value(sameInstant(DEFAULT_SETE_A_ME_VINTE)))
            .andExpect(jsonPath("$.seteAMeTrinta").value(sameInstant(DEFAULT_SETE_A_ME_TRINTA)))
            .andExpect(jsonPath("$.seteAMeQuarenta").value(sameInstant(DEFAULT_SETE_A_ME_QUARENTA)))
            .andExpect(jsonPath("$.seteAMeCinquenta").value(sameInstant(DEFAULT_SETE_A_ME_CINQUENTA)))
            .andExpect(jsonPath("$.setePM").value(sameInstant(DEFAULT_SETE_PM)))
            .andExpect(jsonPath("$.setePMeDez").value(sameInstant(DEFAULT_SETE_P_ME_DEZ)))
            .andExpect(jsonPath("$.setePMeVinte").value(sameInstant(DEFAULT_SETE_P_ME_VINTE)))
            .andExpect(jsonPath("$.setePMeTrinta").value(sameInstant(DEFAULT_SETE_P_ME_TRINTA)))
            .andExpect(jsonPath("$.setePMeQuarenta").value(sameInstant(DEFAULT_SETE_P_ME_QUARENTA)))
            .andExpect(jsonPath("$.setePMeCinquenta").value(sameInstant(DEFAULT_SETE_P_ME_CINQUENTA)))
            .andExpect(jsonPath("$.oitoAM").value(sameInstant(DEFAULT_OITO_AM)))
            .andExpect(jsonPath("$.oitoAMeDez").value(sameInstant(DEFAULT_OITO_A_ME_DEZ)))
            .andExpect(jsonPath("$.oitoAMeVinte").value(sameInstant(DEFAULT_OITO_A_ME_VINTE)))
            .andExpect(jsonPath("$.oitoAMeTrinta").value(sameInstant(DEFAULT_OITO_A_ME_TRINTA)))
            .andExpect(jsonPath("$.oitoAMeQuarenta").value(sameInstant(DEFAULT_OITO_A_ME_QUARENTA)))
            .andExpect(jsonPath("$.oitoAMeCinquenta").value(sameInstant(DEFAULT_OITO_A_ME_CINQUENTA)))
            .andExpect(jsonPath("$.oitoPM").value(sameInstant(DEFAULT_OITO_PM)))
            .andExpect(jsonPath("$.oitoPMeDez").value(sameInstant(DEFAULT_OITO_P_ME_DEZ)))
            .andExpect(jsonPath("$.oitoPMeVinte").value(sameInstant(DEFAULT_OITO_P_ME_VINTE)))
            .andExpect(jsonPath("$.oitoPMeTrinta").value(sameInstant(DEFAULT_OITO_P_ME_TRINTA)))
            .andExpect(jsonPath("$.oitoPMeQuarenta").value(sameInstant(DEFAULT_OITO_P_ME_QUARENTA)))
            .andExpect(jsonPath("$.oitoPMeCinquenta").value(sameInstant(DEFAULT_OITO_P_ME_CINQUENTA)))
            .andExpect(jsonPath("$.noveAM").value(sameInstant(DEFAULT_NOVE_AM)))
            .andExpect(jsonPath("$.noveAMeDez").value(sameInstant(DEFAULT_NOVE_A_ME_DEZ)))
            .andExpect(jsonPath("$.noveAMeVinte").value(sameInstant(DEFAULT_NOVE_A_ME_VINTE)))
            .andExpect(jsonPath("$.noveAMeTrinta").value(sameInstant(DEFAULT_NOVE_A_ME_TRINTA)))
            .andExpect(jsonPath("$.noveAMeQuarenta").value(sameInstant(DEFAULT_NOVE_A_ME_QUARENTA)))
            .andExpect(jsonPath("$.noveAMeCinquenta").value(sameInstant(DEFAULT_NOVE_A_ME_CINQUENTA)))
            .andExpect(jsonPath("$.novePM").value(sameInstant(DEFAULT_NOVE_PM)))
            .andExpect(jsonPath("$.novePMeDez").value(sameInstant(DEFAULT_NOVE_P_ME_DEZ)))
            .andExpect(jsonPath("$.novePMeVinte").value(sameInstant(DEFAULT_NOVE_P_ME_VINTE)))
            .andExpect(jsonPath("$.novePMeTrinta").value(sameInstant(DEFAULT_NOVE_P_ME_TRINTA)))
            .andExpect(jsonPath("$.novePMeQuarenta").value(sameInstant(DEFAULT_NOVE_P_ME_QUARENTA)))
            .andExpect(jsonPath("$.novePMeCinquenta").value(sameInstant(DEFAULT_NOVE_P_ME_CINQUENTA)))
            .andExpect(jsonPath("$.dezAM").value(sameInstant(DEFAULT_DEZ_AM)))
            .andExpect(jsonPath("$.dezAMeDez").value(sameInstant(DEFAULT_DEZ_A_ME_DEZ)))
            .andExpect(jsonPath("$.dezAMeVinte").value(sameInstant(DEFAULT_DEZ_A_ME_VINTE)))
            .andExpect(jsonPath("$.dezAMeTrinta").value(sameInstant(DEFAULT_DEZ_A_ME_TRINTA)))
            .andExpect(jsonPath("$.dezAMeQuarenta").value(sameInstant(DEFAULT_DEZ_A_ME_QUARENTA)))
            .andExpect(jsonPath("$.dezAMeCinquenta").value(sameInstant(DEFAULT_DEZ_A_ME_CINQUENTA)))
            .andExpect(jsonPath("$.dezPM").value(sameInstant(DEFAULT_DEZ_PM)))
            .andExpect(jsonPath("$.dezPMeDez").value(sameInstant(DEFAULT_DEZ_P_ME_DEZ)))
            .andExpect(jsonPath("$.dezPMeVinte").value(sameInstant(DEFAULT_DEZ_P_ME_VINTE)))
            .andExpect(jsonPath("$.dezPMeTrinta").value(sameInstant(DEFAULT_DEZ_P_ME_TRINTA)))
            .andExpect(jsonPath("$.dezPMeQuarenta").value(sameInstant(DEFAULT_DEZ_P_ME_QUARENTA)))
            .andExpect(jsonPath("$.dezPMeCinquenta").value(sameInstant(DEFAULT_DEZ_P_ME_CINQUENTA)))
            .andExpect(jsonPath("$.onzeAM").value(sameInstant(DEFAULT_ONZE_AM)))
            .andExpect(jsonPath("$.onzeAMeDez").value(sameInstant(DEFAULT_ONZE_A_ME_DEZ)))
            .andExpect(jsonPath("$.onzeAMeVinte").value(sameInstant(DEFAULT_ONZE_A_ME_VINTE)))
            .andExpect(jsonPath("$.onzeAMeTrinta").value(sameInstant(DEFAULT_ONZE_A_ME_TRINTA)))
            .andExpect(jsonPath("$.onzeAMeQuarenta").value(sameInstant(DEFAULT_ONZE_A_ME_QUARENTA)))
            .andExpect(jsonPath("$.onzeAMeCinquenta").value(sameInstant(DEFAULT_ONZE_A_ME_CINQUENTA)))
            .andExpect(jsonPath("$.onzePM").value(sameInstant(DEFAULT_ONZE_PM)))
            .andExpect(jsonPath("$.onzePMeDez").value(sameInstant(DEFAULT_ONZE_P_ME_DEZ)))
            .andExpect(jsonPath("$.onzePMeVinte").value(sameInstant(DEFAULT_ONZE_P_ME_VINTE)))
            .andExpect(jsonPath("$.onzePMeTrinta").value(sameInstant(DEFAULT_ONZE_P_ME_TRINTA)))
            .andExpect(jsonPath("$.onzePMeQuarenta").value(sameInstant(DEFAULT_ONZE_P_ME_QUARENTA)))
            .andExpect(jsonPath("$.onzePMeCinquenta").value(sameInstant(DEFAULT_ONZE_P_ME_CINQUENTA)));
    }

    @Test
    @Transactional
    void getNonExistingBlocos() throws Exception {
        // Get the blocos
        restBlocosMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewBlocos() throws Exception {
        // Initialize the database
        blocosRepository.saveAndFlush(blocos);

        int databaseSizeBeforeUpdate = blocosRepository.findAll().size();

        // Update the blocos
        Blocos updatedBlocos = blocosRepository.findById(blocos.getId()).get();
        // Disconnect from session so that the updates on updatedBlocos are not directly saved in db
        em.detach(updatedBlocos);
        updatedBlocos
            .zeroAM(UPDATED_ZERO_AM)
            .zeroAMeDez(UPDATED_ZERO_A_ME_DEZ)
            .zeroAMeVinte(UPDATED_ZERO_A_ME_VINTE)
            .zeroAMeTrinta(UPDATED_ZERO_A_ME_TRINTA)
            .zeroAMeQuarenta(UPDATED_ZERO_A_ME_QUARENTA)
            .zeroAMeCinquenta(UPDATED_ZERO_A_ME_CINQUENTA)
            .zeroPM(UPDATED_ZERO_PM)
            .zeroPMeDez(UPDATED_ZERO_P_ME_DEZ)
            .zeroPMeVinte(UPDATED_ZERO_P_ME_VINTE)
            .zeroPMeTrinta(UPDATED_ZERO_P_ME_TRINTA)
            .zeroPMeQuarenta(UPDATED_ZERO_P_ME_QUARENTA)
            .zeroPMeCinquenta(UPDATED_ZERO_P_ME_CINQUENTA)
            .umAM(UPDATED_UM_AM)
            .umAMeDez(UPDATED_UM_A_ME_DEZ)
            .umAMeVinte(UPDATED_UM_A_ME_VINTE)
            .umAMeTrinta(UPDATED_UM_A_ME_TRINTA)
            .umAMeQuarenta(UPDATED_UM_A_ME_QUARENTA)
            .umAMeCinquenta(UPDATED_UM_A_ME_CINQUENTA)
            .umPM(UPDATED_UM_PM)
            .umPMeDez(UPDATED_UM_P_ME_DEZ)
            .umPMeVinte(UPDATED_UM_P_ME_VINTE)
            .umPMeTrinta(UPDATED_UM_P_ME_TRINTA)
            .umPMeQuarenta(UPDATED_UM_P_ME_QUARENTA)
            .umPMeCinquenta(UPDATED_UM_P_ME_CINQUENTA)
            .doisAM(UPDATED_DOIS_AM)
            .doisAMeDez(UPDATED_DOIS_A_ME_DEZ)
            .doisAMeVinte(UPDATED_DOIS_A_ME_VINTE)
            .doisAMeTrinta(UPDATED_DOIS_A_ME_TRINTA)
            .doisAMeQuarenta(UPDATED_DOIS_A_ME_QUARENTA)
            .doisAMeCinquenta(UPDATED_DOIS_A_ME_CINQUENTA)
            .doisPM(UPDATED_DOIS_PM)
            .doisPMeDez(UPDATED_DOIS_P_ME_DEZ)
            .doisPMeVinte(UPDATED_DOIS_P_ME_VINTE)
            .doisPMeTrinta(UPDATED_DOIS_P_ME_TRINTA)
            .doisPMeQuarenta(UPDATED_DOIS_P_ME_QUARENTA)
            .doisPMeCinquenta(UPDATED_DOIS_P_ME_CINQUENTA)
            .tresAM(UPDATED_TRES_AM)
            .tresAMeDez(UPDATED_TRES_A_ME_DEZ)
            .tresAMeVinte(UPDATED_TRES_A_ME_VINTE)
            .tresAMeTrinta(UPDATED_TRES_A_ME_TRINTA)
            .tresAMeQuarenta(UPDATED_TRES_A_ME_QUARENTA)
            .tresAMeCinquenta(UPDATED_TRES_A_ME_CINQUENTA)
            .tresPM(UPDATED_TRES_PM)
            .tresPMeDez(UPDATED_TRES_P_ME_DEZ)
            .tresPMeVinte(UPDATED_TRES_P_ME_VINTE)
            .tresPMeTrinta(UPDATED_TRES_P_ME_TRINTA)
            .tresPMeQuarenta(UPDATED_TRES_P_ME_QUARENTA)
            .tresPMeCinquenta(UPDATED_TRES_P_ME_CINQUENTA)
            .quatroAM(UPDATED_QUATRO_AM)
            .quatroAMeDez(UPDATED_QUATRO_A_ME_DEZ)
            .quatroAMeVinte(UPDATED_QUATRO_A_ME_VINTE)
            .quatroAMeTrinta(UPDATED_QUATRO_A_ME_TRINTA)
            .quatroAMeQuarenta(UPDATED_QUATRO_A_ME_QUARENTA)
            .quatroAMeCinquenta(UPDATED_QUATRO_A_ME_CINQUENTA)
            .quatroPM(UPDATED_QUATRO_PM)
            .quatroPMeDez(UPDATED_QUATRO_P_ME_DEZ)
            .quatroPMeVinte(UPDATED_QUATRO_P_ME_VINTE)
            .quatroPMeTrinta(UPDATED_QUATRO_P_ME_TRINTA)
            .quatroPMeQuarenta(UPDATED_QUATRO_P_ME_QUARENTA)
            .quatroPMeCinquenta(UPDATED_QUATRO_P_ME_CINQUENTA)
            .cincoAM(UPDATED_CINCO_AM)
            .cincoAMeDez(UPDATED_CINCO_A_ME_DEZ)
            .cincoAMeVinte(UPDATED_CINCO_A_ME_VINTE)
            .cincoAMeTrinta(UPDATED_CINCO_A_ME_TRINTA)
            .cincoAMeQuarenta(UPDATED_CINCO_A_ME_QUARENTA)
            .cincoAMeCinquenta(UPDATED_CINCO_A_ME_CINQUENTA)
            .cincoPM(UPDATED_CINCO_PM)
            .cincoPMeDez(UPDATED_CINCO_P_ME_DEZ)
            .cincoPMeVinte(UPDATED_CINCO_P_ME_VINTE)
            .cincoPMeTrinta(UPDATED_CINCO_P_ME_TRINTA)
            .cincoPMeQuarenta(UPDATED_CINCO_P_ME_QUARENTA)
            .cincoPMeCinquenta(UPDATED_CINCO_P_ME_CINQUENTA)
            .seisAM(UPDATED_SEIS_AM)
            .seisAMeDez(UPDATED_SEIS_A_ME_DEZ)
            .seisAMeVinte(UPDATED_SEIS_A_ME_VINTE)
            .seisAMeTrinta(UPDATED_SEIS_A_ME_TRINTA)
            .seisAMeQuarenta(UPDATED_SEIS_A_ME_QUARENTA)
            .seisAMeCinquenta(UPDATED_SEIS_A_ME_CINQUENTA)
            .seisPM(UPDATED_SEIS_PM)
            .seisPMeDez(UPDATED_SEIS_P_ME_DEZ)
            .seisPMeVinte(UPDATED_SEIS_P_ME_VINTE)
            .seisPMeTrinta(UPDATED_SEIS_P_ME_TRINTA)
            .seisPMeQuarenta(UPDATED_SEIS_P_ME_QUARENTA)
            .seisPMeCinquenta(UPDATED_SEIS_P_ME_CINQUENTA)
            .seteAM(UPDATED_SETE_AM)
            .seteAMeDez(UPDATED_SETE_A_ME_DEZ)
            .seteAMeVinte(UPDATED_SETE_A_ME_VINTE)
            .seteAMeTrinta(UPDATED_SETE_A_ME_TRINTA)
            .seteAMeQuarenta(UPDATED_SETE_A_ME_QUARENTA)
            .seteAMeCinquenta(UPDATED_SETE_A_ME_CINQUENTA)
            .setePM(UPDATED_SETE_PM)
            .setePMeDez(UPDATED_SETE_P_ME_DEZ)
            .setePMeVinte(UPDATED_SETE_P_ME_VINTE)
            .setePMeTrinta(UPDATED_SETE_P_ME_TRINTA)
            .setePMeQuarenta(UPDATED_SETE_P_ME_QUARENTA)
            .setePMeCinquenta(UPDATED_SETE_P_ME_CINQUENTA)
            .oitoAM(UPDATED_OITO_AM)
            .oitoAMeDez(UPDATED_OITO_A_ME_DEZ)
            .oitoAMeVinte(UPDATED_OITO_A_ME_VINTE)
            .oitoAMeTrinta(UPDATED_OITO_A_ME_TRINTA)
            .oitoAMeQuarenta(UPDATED_OITO_A_ME_QUARENTA)
            .oitoAMeCinquenta(UPDATED_OITO_A_ME_CINQUENTA)
            .oitoPM(UPDATED_OITO_PM)
            .oitoPMeDez(UPDATED_OITO_P_ME_DEZ)
            .oitoPMeVinte(UPDATED_OITO_P_ME_VINTE)
            .oitoPMeTrinta(UPDATED_OITO_P_ME_TRINTA)
            .oitoPMeQuarenta(UPDATED_OITO_P_ME_QUARENTA)
            .oitoPMeCinquenta(UPDATED_OITO_P_ME_CINQUENTA)
            .noveAM(UPDATED_NOVE_AM)
            .noveAMeDez(UPDATED_NOVE_A_ME_DEZ)
            .noveAMeVinte(UPDATED_NOVE_A_ME_VINTE)
            .noveAMeTrinta(UPDATED_NOVE_A_ME_TRINTA)
            .noveAMeQuarenta(UPDATED_NOVE_A_ME_QUARENTA)
            .noveAMeCinquenta(UPDATED_NOVE_A_ME_CINQUENTA)
            .novePM(UPDATED_NOVE_PM)
            .novePMeDez(UPDATED_NOVE_P_ME_DEZ)
            .novePMeVinte(UPDATED_NOVE_P_ME_VINTE)
            .novePMeTrinta(UPDATED_NOVE_P_ME_TRINTA)
            .novePMeQuarenta(UPDATED_NOVE_P_ME_QUARENTA)
            .novePMeCinquenta(UPDATED_NOVE_P_ME_CINQUENTA)
            .dezAM(UPDATED_DEZ_AM)
            .dezAMeDez(UPDATED_DEZ_A_ME_DEZ)
            .dezAMeVinte(UPDATED_DEZ_A_ME_VINTE)
            .dezAMeTrinta(UPDATED_DEZ_A_ME_TRINTA)
            .dezAMeQuarenta(UPDATED_DEZ_A_ME_QUARENTA)
            .dezAMeCinquenta(UPDATED_DEZ_A_ME_CINQUENTA)
            .dezPM(UPDATED_DEZ_PM)
            .dezPMeDez(UPDATED_DEZ_P_ME_DEZ)
            .dezPMeVinte(UPDATED_DEZ_P_ME_VINTE)
            .dezPMeTrinta(UPDATED_DEZ_P_ME_TRINTA)
            .dezPMeQuarenta(UPDATED_DEZ_P_ME_QUARENTA)
            .dezPMeCinquenta(UPDATED_DEZ_P_ME_CINQUENTA)
            .onzeAM(UPDATED_ONZE_AM)
            .onzeAMeDez(UPDATED_ONZE_A_ME_DEZ)
            .onzeAMeVinte(UPDATED_ONZE_A_ME_VINTE)
            .onzeAMeTrinta(UPDATED_ONZE_A_ME_TRINTA)
            .onzeAMeQuarenta(UPDATED_ONZE_A_ME_QUARENTA)
            .onzeAMeCinquenta(UPDATED_ONZE_A_ME_CINQUENTA)
            .onzePM(UPDATED_ONZE_PM)
            .onzePMeDez(UPDATED_ONZE_P_ME_DEZ)
            .onzePMeVinte(UPDATED_ONZE_P_ME_VINTE)
            .onzePMeTrinta(UPDATED_ONZE_P_ME_TRINTA)
            .onzePMeQuarenta(UPDATED_ONZE_P_ME_QUARENTA)
            .onzePMeCinquenta(UPDATED_ONZE_P_ME_CINQUENTA);

        restBlocosMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedBlocos.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedBlocos))
            )
            .andExpect(status().isOk());

        // Validate the Blocos in the database
        List<Blocos> blocosList = blocosRepository.findAll();
        assertThat(blocosList).hasSize(databaseSizeBeforeUpdate);
        Blocos testBlocos = blocosList.get(blocosList.size() - 1);
        assertThat(testBlocos.getZeroAM()).isEqualTo(UPDATED_ZERO_AM);
        assertThat(testBlocos.getZeroAMeDez()).isEqualTo(UPDATED_ZERO_A_ME_DEZ);
        assertThat(testBlocos.getZeroAMeVinte()).isEqualTo(UPDATED_ZERO_A_ME_VINTE);
        assertThat(testBlocos.getZeroAMeTrinta()).isEqualTo(UPDATED_ZERO_A_ME_TRINTA);
        assertThat(testBlocos.getZeroAMeQuarenta()).isEqualTo(UPDATED_ZERO_A_ME_QUARENTA);
        assertThat(testBlocos.getZeroAMeCinquenta()).isEqualTo(UPDATED_ZERO_A_ME_CINQUENTA);
        assertThat(testBlocos.getZeroPM()).isEqualTo(UPDATED_ZERO_PM);
        assertThat(testBlocos.getZeroPMeDez()).isEqualTo(UPDATED_ZERO_P_ME_DEZ);
        assertThat(testBlocos.getZeroPMeVinte()).isEqualTo(UPDATED_ZERO_P_ME_VINTE);
        assertThat(testBlocos.getZeroPMeTrinta()).isEqualTo(UPDATED_ZERO_P_ME_TRINTA);
        assertThat(testBlocos.getZeroPMeQuarenta()).isEqualTo(UPDATED_ZERO_P_ME_QUARENTA);
        assertThat(testBlocos.getZeroPMeCinquenta()).isEqualTo(UPDATED_ZERO_P_ME_CINQUENTA);
        assertThat(testBlocos.getUmAM()).isEqualTo(UPDATED_UM_AM);
        assertThat(testBlocos.getUmAMeDez()).isEqualTo(UPDATED_UM_A_ME_DEZ);
        assertThat(testBlocos.getUmAMeVinte()).isEqualTo(UPDATED_UM_A_ME_VINTE);
        assertThat(testBlocos.getUmAMeTrinta()).isEqualTo(UPDATED_UM_A_ME_TRINTA);
        assertThat(testBlocos.getUmAMeQuarenta()).isEqualTo(UPDATED_UM_A_ME_QUARENTA);
        assertThat(testBlocos.getUmAMeCinquenta()).isEqualTo(UPDATED_UM_A_ME_CINQUENTA);
        assertThat(testBlocos.getUmPM()).isEqualTo(UPDATED_UM_PM);
        assertThat(testBlocos.getUmPMeDez()).isEqualTo(UPDATED_UM_P_ME_DEZ);
        assertThat(testBlocos.getUmPMeVinte()).isEqualTo(UPDATED_UM_P_ME_VINTE);
        assertThat(testBlocos.getUmPMeTrinta()).isEqualTo(UPDATED_UM_P_ME_TRINTA);
        assertThat(testBlocos.getUmPMeQuarenta()).isEqualTo(UPDATED_UM_P_ME_QUARENTA);
        assertThat(testBlocos.getUmPMeCinquenta()).isEqualTo(UPDATED_UM_P_ME_CINQUENTA);
        assertThat(testBlocos.getDoisAM()).isEqualTo(UPDATED_DOIS_AM);
        assertThat(testBlocos.getDoisAMeDez()).isEqualTo(UPDATED_DOIS_A_ME_DEZ);
        assertThat(testBlocos.getDoisAMeVinte()).isEqualTo(UPDATED_DOIS_A_ME_VINTE);
        assertThat(testBlocos.getDoisAMeTrinta()).isEqualTo(UPDATED_DOIS_A_ME_TRINTA);
        assertThat(testBlocos.getDoisAMeQuarenta()).isEqualTo(UPDATED_DOIS_A_ME_QUARENTA);
        assertThat(testBlocos.getDoisAMeCinquenta()).isEqualTo(UPDATED_DOIS_A_ME_CINQUENTA);
        assertThat(testBlocos.getDoisPM()).isEqualTo(UPDATED_DOIS_PM);
        assertThat(testBlocos.getDoisPMeDez()).isEqualTo(UPDATED_DOIS_P_ME_DEZ);
        assertThat(testBlocos.getDoisPMeVinte()).isEqualTo(UPDATED_DOIS_P_ME_VINTE);
        assertThat(testBlocos.getDoisPMeTrinta()).isEqualTo(UPDATED_DOIS_P_ME_TRINTA);
        assertThat(testBlocos.getDoisPMeQuarenta()).isEqualTo(UPDATED_DOIS_P_ME_QUARENTA);
        assertThat(testBlocos.getDoisPMeCinquenta()).isEqualTo(UPDATED_DOIS_P_ME_CINQUENTA);
        assertThat(testBlocos.getTresAM()).isEqualTo(UPDATED_TRES_AM);
        assertThat(testBlocos.getTresAMeDez()).isEqualTo(UPDATED_TRES_A_ME_DEZ);
        assertThat(testBlocos.getTresAMeVinte()).isEqualTo(UPDATED_TRES_A_ME_VINTE);
        assertThat(testBlocos.getTresAMeTrinta()).isEqualTo(UPDATED_TRES_A_ME_TRINTA);
        assertThat(testBlocos.getTresAMeQuarenta()).isEqualTo(UPDATED_TRES_A_ME_QUARENTA);
        assertThat(testBlocos.getTresAMeCinquenta()).isEqualTo(UPDATED_TRES_A_ME_CINQUENTA);
        assertThat(testBlocos.getTresPM()).isEqualTo(UPDATED_TRES_PM);
        assertThat(testBlocos.getTresPMeDez()).isEqualTo(UPDATED_TRES_P_ME_DEZ);
        assertThat(testBlocos.getTresPMeVinte()).isEqualTo(UPDATED_TRES_P_ME_VINTE);
        assertThat(testBlocos.getTresPMeTrinta()).isEqualTo(UPDATED_TRES_P_ME_TRINTA);
        assertThat(testBlocos.getTresPMeQuarenta()).isEqualTo(UPDATED_TRES_P_ME_QUARENTA);
        assertThat(testBlocos.getTresPMeCinquenta()).isEqualTo(UPDATED_TRES_P_ME_CINQUENTA);
        assertThat(testBlocos.getQuatroAM()).isEqualTo(UPDATED_QUATRO_AM);
        assertThat(testBlocos.getQuatroAMeDez()).isEqualTo(UPDATED_QUATRO_A_ME_DEZ);
        assertThat(testBlocos.getQuatroAMeVinte()).isEqualTo(UPDATED_QUATRO_A_ME_VINTE);
        assertThat(testBlocos.getQuatroAMeTrinta()).isEqualTo(UPDATED_QUATRO_A_ME_TRINTA);
        assertThat(testBlocos.getQuatroAMeQuarenta()).isEqualTo(UPDATED_QUATRO_A_ME_QUARENTA);
        assertThat(testBlocos.getQuatroAMeCinquenta()).isEqualTo(UPDATED_QUATRO_A_ME_CINQUENTA);
        assertThat(testBlocos.getQuatroPM()).isEqualTo(UPDATED_QUATRO_PM);
        assertThat(testBlocos.getQuatroPMeDez()).isEqualTo(UPDATED_QUATRO_P_ME_DEZ);
        assertThat(testBlocos.getQuatroPMeVinte()).isEqualTo(UPDATED_QUATRO_P_ME_VINTE);
        assertThat(testBlocos.getQuatroPMeTrinta()).isEqualTo(UPDATED_QUATRO_P_ME_TRINTA);
        assertThat(testBlocos.getQuatroPMeQuarenta()).isEqualTo(UPDATED_QUATRO_P_ME_QUARENTA);
        assertThat(testBlocos.getQuatroPMeCinquenta()).isEqualTo(UPDATED_QUATRO_P_ME_CINQUENTA);
        assertThat(testBlocos.getCincoAM()).isEqualTo(UPDATED_CINCO_AM);
        assertThat(testBlocos.getCincoAMeDez()).isEqualTo(UPDATED_CINCO_A_ME_DEZ);
        assertThat(testBlocos.getCincoAMeVinte()).isEqualTo(UPDATED_CINCO_A_ME_VINTE);
        assertThat(testBlocos.getCincoAMeTrinta()).isEqualTo(UPDATED_CINCO_A_ME_TRINTA);
        assertThat(testBlocos.getCincoAMeQuarenta()).isEqualTo(UPDATED_CINCO_A_ME_QUARENTA);
        assertThat(testBlocos.getCincoAMeCinquenta()).isEqualTo(UPDATED_CINCO_A_ME_CINQUENTA);
        assertThat(testBlocos.getCincoPM()).isEqualTo(UPDATED_CINCO_PM);
        assertThat(testBlocos.getCincoPMeDez()).isEqualTo(UPDATED_CINCO_P_ME_DEZ);
        assertThat(testBlocos.getCincoPMeVinte()).isEqualTo(UPDATED_CINCO_P_ME_VINTE);
        assertThat(testBlocos.getCincoPMeTrinta()).isEqualTo(UPDATED_CINCO_P_ME_TRINTA);
        assertThat(testBlocos.getCincoPMeQuarenta()).isEqualTo(UPDATED_CINCO_P_ME_QUARENTA);
        assertThat(testBlocos.getCincoPMeCinquenta()).isEqualTo(UPDATED_CINCO_P_ME_CINQUENTA);
        assertThat(testBlocos.getSeisAM()).isEqualTo(UPDATED_SEIS_AM);
        assertThat(testBlocos.getSeisAMeDez()).isEqualTo(UPDATED_SEIS_A_ME_DEZ);
        assertThat(testBlocos.getSeisAMeVinte()).isEqualTo(UPDATED_SEIS_A_ME_VINTE);
        assertThat(testBlocos.getSeisAMeTrinta()).isEqualTo(UPDATED_SEIS_A_ME_TRINTA);
        assertThat(testBlocos.getSeisAMeQuarenta()).isEqualTo(UPDATED_SEIS_A_ME_QUARENTA);
        assertThat(testBlocos.getSeisAMeCinquenta()).isEqualTo(UPDATED_SEIS_A_ME_CINQUENTA);
        assertThat(testBlocos.getSeisPM()).isEqualTo(UPDATED_SEIS_PM);
        assertThat(testBlocos.getSeisPMeDez()).isEqualTo(UPDATED_SEIS_P_ME_DEZ);
        assertThat(testBlocos.getSeisPMeVinte()).isEqualTo(UPDATED_SEIS_P_ME_VINTE);
        assertThat(testBlocos.getSeisPMeTrinta()).isEqualTo(UPDATED_SEIS_P_ME_TRINTA);
        assertThat(testBlocos.getSeisPMeQuarenta()).isEqualTo(UPDATED_SEIS_P_ME_QUARENTA);
        assertThat(testBlocos.getSeisPMeCinquenta()).isEqualTo(UPDATED_SEIS_P_ME_CINQUENTA);
        assertThat(testBlocos.getSeteAM()).isEqualTo(UPDATED_SETE_AM);
        assertThat(testBlocos.getSeteAMeDez()).isEqualTo(UPDATED_SETE_A_ME_DEZ);
        assertThat(testBlocos.getSeteAMeVinte()).isEqualTo(UPDATED_SETE_A_ME_VINTE);
        assertThat(testBlocos.getSeteAMeTrinta()).isEqualTo(UPDATED_SETE_A_ME_TRINTA);
        assertThat(testBlocos.getSeteAMeQuarenta()).isEqualTo(UPDATED_SETE_A_ME_QUARENTA);
        assertThat(testBlocos.getSeteAMeCinquenta()).isEqualTo(UPDATED_SETE_A_ME_CINQUENTA);
        assertThat(testBlocos.getSetePM()).isEqualTo(UPDATED_SETE_PM);
        assertThat(testBlocos.getSetePMeDez()).isEqualTo(UPDATED_SETE_P_ME_DEZ);
        assertThat(testBlocos.getSetePMeVinte()).isEqualTo(UPDATED_SETE_P_ME_VINTE);
        assertThat(testBlocos.getSetePMeTrinta()).isEqualTo(UPDATED_SETE_P_ME_TRINTA);
        assertThat(testBlocos.getSetePMeQuarenta()).isEqualTo(UPDATED_SETE_P_ME_QUARENTA);
        assertThat(testBlocos.getSetePMeCinquenta()).isEqualTo(UPDATED_SETE_P_ME_CINQUENTA);
        assertThat(testBlocos.getOitoAM()).isEqualTo(UPDATED_OITO_AM);
        assertThat(testBlocos.getOitoAMeDez()).isEqualTo(UPDATED_OITO_A_ME_DEZ);
        assertThat(testBlocos.getOitoAMeVinte()).isEqualTo(UPDATED_OITO_A_ME_VINTE);
        assertThat(testBlocos.getOitoAMeTrinta()).isEqualTo(UPDATED_OITO_A_ME_TRINTA);
        assertThat(testBlocos.getOitoAMeQuarenta()).isEqualTo(UPDATED_OITO_A_ME_QUARENTA);
        assertThat(testBlocos.getOitoAMeCinquenta()).isEqualTo(UPDATED_OITO_A_ME_CINQUENTA);
        assertThat(testBlocos.getOitoPM()).isEqualTo(UPDATED_OITO_PM);
        assertThat(testBlocos.getOitoPMeDez()).isEqualTo(UPDATED_OITO_P_ME_DEZ);
        assertThat(testBlocos.getOitoPMeVinte()).isEqualTo(UPDATED_OITO_P_ME_VINTE);
        assertThat(testBlocos.getOitoPMeTrinta()).isEqualTo(UPDATED_OITO_P_ME_TRINTA);
        assertThat(testBlocos.getOitoPMeQuarenta()).isEqualTo(UPDATED_OITO_P_ME_QUARENTA);
        assertThat(testBlocos.getOitoPMeCinquenta()).isEqualTo(UPDATED_OITO_P_ME_CINQUENTA);
        assertThat(testBlocos.getNoveAM()).isEqualTo(UPDATED_NOVE_AM);
        assertThat(testBlocos.getNoveAMeDez()).isEqualTo(UPDATED_NOVE_A_ME_DEZ);
        assertThat(testBlocos.getNoveAMeVinte()).isEqualTo(UPDATED_NOVE_A_ME_VINTE);
        assertThat(testBlocos.getNoveAMeTrinta()).isEqualTo(UPDATED_NOVE_A_ME_TRINTA);
        assertThat(testBlocos.getNoveAMeQuarenta()).isEqualTo(UPDATED_NOVE_A_ME_QUARENTA);
        assertThat(testBlocos.getNoveAMeCinquenta()).isEqualTo(UPDATED_NOVE_A_ME_CINQUENTA);
        assertThat(testBlocos.getNovePM()).isEqualTo(UPDATED_NOVE_PM);
        assertThat(testBlocos.getNovePMeDez()).isEqualTo(UPDATED_NOVE_P_ME_DEZ);
        assertThat(testBlocos.getNovePMeVinte()).isEqualTo(UPDATED_NOVE_P_ME_VINTE);
        assertThat(testBlocos.getNovePMeTrinta()).isEqualTo(UPDATED_NOVE_P_ME_TRINTA);
        assertThat(testBlocos.getNovePMeQuarenta()).isEqualTo(UPDATED_NOVE_P_ME_QUARENTA);
        assertThat(testBlocos.getNovePMeCinquenta()).isEqualTo(UPDATED_NOVE_P_ME_CINQUENTA);
        assertThat(testBlocos.getDezAM()).isEqualTo(UPDATED_DEZ_AM);
        assertThat(testBlocos.getDezAMeDez()).isEqualTo(UPDATED_DEZ_A_ME_DEZ);
        assertThat(testBlocos.getDezAMeVinte()).isEqualTo(UPDATED_DEZ_A_ME_VINTE);
        assertThat(testBlocos.getDezAMeTrinta()).isEqualTo(UPDATED_DEZ_A_ME_TRINTA);
        assertThat(testBlocos.getDezAMeQuarenta()).isEqualTo(UPDATED_DEZ_A_ME_QUARENTA);
        assertThat(testBlocos.getDezAMeCinquenta()).isEqualTo(UPDATED_DEZ_A_ME_CINQUENTA);
        assertThat(testBlocos.getDezPM()).isEqualTo(UPDATED_DEZ_PM);
        assertThat(testBlocos.getDezPMeDez()).isEqualTo(UPDATED_DEZ_P_ME_DEZ);
        assertThat(testBlocos.getDezPMeVinte()).isEqualTo(UPDATED_DEZ_P_ME_VINTE);
        assertThat(testBlocos.getDezPMeTrinta()).isEqualTo(UPDATED_DEZ_P_ME_TRINTA);
        assertThat(testBlocos.getDezPMeQuarenta()).isEqualTo(UPDATED_DEZ_P_ME_QUARENTA);
        assertThat(testBlocos.getDezPMeCinquenta()).isEqualTo(UPDATED_DEZ_P_ME_CINQUENTA);
        assertThat(testBlocos.getOnzeAM()).isEqualTo(UPDATED_ONZE_AM);
        assertThat(testBlocos.getOnzeAMeDez()).isEqualTo(UPDATED_ONZE_A_ME_DEZ);
        assertThat(testBlocos.getOnzeAMeVinte()).isEqualTo(UPDATED_ONZE_A_ME_VINTE);
        assertThat(testBlocos.getOnzeAMeTrinta()).isEqualTo(UPDATED_ONZE_A_ME_TRINTA);
        assertThat(testBlocos.getOnzeAMeQuarenta()).isEqualTo(UPDATED_ONZE_A_ME_QUARENTA);
        assertThat(testBlocos.getOnzeAMeCinquenta()).isEqualTo(UPDATED_ONZE_A_ME_CINQUENTA);
        assertThat(testBlocos.getOnzePM()).isEqualTo(UPDATED_ONZE_PM);
        assertThat(testBlocos.getOnzePMeDez()).isEqualTo(UPDATED_ONZE_P_ME_DEZ);
        assertThat(testBlocos.getOnzePMeVinte()).isEqualTo(UPDATED_ONZE_P_ME_VINTE);
        assertThat(testBlocos.getOnzePMeTrinta()).isEqualTo(UPDATED_ONZE_P_ME_TRINTA);
        assertThat(testBlocos.getOnzePMeQuarenta()).isEqualTo(UPDATED_ONZE_P_ME_QUARENTA);
        assertThat(testBlocos.getOnzePMeCinquenta()).isEqualTo(UPDATED_ONZE_P_ME_CINQUENTA);
    }

    @Test
    @Transactional
    void putNonExistingBlocos() throws Exception {
        int databaseSizeBeforeUpdate = blocosRepository.findAll().size();
        blocos.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBlocosMockMvc
            .perform(
                put(ENTITY_API_URL_ID, blocos.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(blocos))
            )
            .andExpect(status().isBadRequest());

        // Validate the Blocos in the database
        List<Blocos> blocosList = blocosRepository.findAll();
        assertThat(blocosList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchBlocos() throws Exception {
        int databaseSizeBeforeUpdate = blocosRepository.findAll().size();
        blocos.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBlocosMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(blocos))
            )
            .andExpect(status().isBadRequest());

        // Validate the Blocos in the database
        List<Blocos> blocosList = blocosRepository.findAll();
        assertThat(blocosList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamBlocos() throws Exception {
        int databaseSizeBeforeUpdate = blocosRepository.findAll().size();
        blocos.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBlocosMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(blocos)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Blocos in the database
        List<Blocos> blocosList = blocosRepository.findAll();
        assertThat(blocosList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateBlocosWithPatch() throws Exception {
        // Initialize the database
        blocosRepository.saveAndFlush(blocos);

        int databaseSizeBeforeUpdate = blocosRepository.findAll().size();

        // Update the blocos using partial update
        Blocos partialUpdatedBlocos = new Blocos();
        partialUpdatedBlocos.setId(blocos.getId());

        partialUpdatedBlocos
            .zeroAM(UPDATED_ZERO_AM)
            .zeroAMeDez(UPDATED_ZERO_A_ME_DEZ)
            .zeroAMeVinte(UPDATED_ZERO_A_ME_VINTE)
            .zeroAMeQuarenta(UPDATED_ZERO_A_ME_QUARENTA)
            .zeroAMeCinquenta(UPDATED_ZERO_A_ME_CINQUENTA)
            .zeroPM(UPDATED_ZERO_PM)
            .zeroPMeDez(UPDATED_ZERO_P_ME_DEZ)
            .zeroPMeTrinta(UPDATED_ZERO_P_ME_TRINTA)
            .zeroPMeQuarenta(UPDATED_ZERO_P_ME_QUARENTA)
            .zeroPMeCinquenta(UPDATED_ZERO_P_ME_CINQUENTA)
            .umAMeDez(UPDATED_UM_A_ME_DEZ)
            .umAMeTrinta(UPDATED_UM_A_ME_TRINTA)
            .umAMeQuarenta(UPDATED_UM_A_ME_QUARENTA)
            .umAMeCinquenta(UPDATED_UM_A_ME_CINQUENTA)
            .umPMeVinte(UPDATED_UM_P_ME_VINTE)
            .umPMeTrinta(UPDATED_UM_P_ME_TRINTA)
            .umPMeQuarenta(UPDATED_UM_P_ME_QUARENTA)
            .doisAMeDez(UPDATED_DOIS_A_ME_DEZ)
            .doisAMeTrinta(UPDATED_DOIS_A_ME_TRINTA)
            .doisPMeTrinta(UPDATED_DOIS_P_ME_TRINTA)
            .doisPMeCinquenta(UPDATED_DOIS_P_ME_CINQUENTA)
            .tresAMeVinte(UPDATED_TRES_A_ME_VINTE)
            .tresAMeTrinta(UPDATED_TRES_A_ME_TRINTA)
            .tresAMeQuarenta(UPDATED_TRES_A_ME_QUARENTA)
            .tresAMeCinquenta(UPDATED_TRES_A_ME_CINQUENTA)
            .tresPMeVinte(UPDATED_TRES_P_ME_VINTE)
            .tresPMeTrinta(UPDATED_TRES_P_ME_TRINTA)
            .tresPMeCinquenta(UPDATED_TRES_P_ME_CINQUENTA)
            .quatroAM(UPDATED_QUATRO_AM)
            .quatroAMeDez(UPDATED_QUATRO_A_ME_DEZ)
            .quatroAMeTrinta(UPDATED_QUATRO_A_ME_TRINTA)
            .quatroAMeQuarenta(UPDATED_QUATRO_A_ME_QUARENTA)
            .quatroAMeCinquenta(UPDATED_QUATRO_A_ME_CINQUENTA)
            .quatroPMeDez(UPDATED_QUATRO_P_ME_DEZ)
            .quatroPMeVinte(UPDATED_QUATRO_P_ME_VINTE)
            .quatroPMeQuarenta(UPDATED_QUATRO_P_ME_QUARENTA)
            .quatroPMeCinquenta(UPDATED_QUATRO_P_ME_CINQUENTA)
            .cincoAM(UPDATED_CINCO_AM)
            .cincoAMeQuarenta(UPDATED_CINCO_A_ME_QUARENTA)
            .cincoAMeCinquenta(UPDATED_CINCO_A_ME_CINQUENTA)
            .cincoPM(UPDATED_CINCO_PM)
            .cincoPMeDez(UPDATED_CINCO_P_ME_DEZ)
            .cincoPMeQuarenta(UPDATED_CINCO_P_ME_QUARENTA)
            .seisAM(UPDATED_SEIS_AM)
            .seisAMeDez(UPDATED_SEIS_A_ME_DEZ)
            .seisAMeVinte(UPDATED_SEIS_A_ME_VINTE)
            .seisAMeTrinta(UPDATED_SEIS_A_ME_TRINTA)
            .seisAMeQuarenta(UPDATED_SEIS_A_ME_QUARENTA)
            .seisPM(UPDATED_SEIS_PM)
            .seisPMeDez(UPDATED_SEIS_P_ME_DEZ)
            .seteAM(UPDATED_SETE_AM)
            .seteAMeDez(UPDATED_SETE_A_ME_DEZ)
            .seteAMeVinte(UPDATED_SETE_A_ME_VINTE)
            .seteAMeTrinta(UPDATED_SETE_A_ME_TRINTA)
            .seteAMeQuarenta(UPDATED_SETE_A_ME_QUARENTA)
            .seteAMeCinquenta(UPDATED_SETE_A_ME_CINQUENTA)
            .setePMeDez(UPDATED_SETE_P_ME_DEZ)
            .setePMeQuarenta(UPDATED_SETE_P_ME_QUARENTA)
            .oitoAM(UPDATED_OITO_AM)
            .oitoAMeDez(UPDATED_OITO_A_ME_DEZ)
            .oitoAMeQuarenta(UPDATED_OITO_A_ME_QUARENTA)
            .oitoPM(UPDATED_OITO_PM)
            .oitoPMeDez(UPDATED_OITO_P_ME_DEZ)
            .oitoPMeCinquenta(UPDATED_OITO_P_ME_CINQUENTA)
            .noveAMeTrinta(UPDATED_NOVE_A_ME_TRINTA)
            .noveAMeQuarenta(UPDATED_NOVE_A_ME_QUARENTA)
            .novePMeDez(UPDATED_NOVE_P_ME_DEZ)
            .novePMeCinquenta(UPDATED_NOVE_P_ME_CINQUENTA)
            .dezAM(UPDATED_DEZ_AM)
            .dezAMeDez(UPDATED_DEZ_A_ME_DEZ)
            .dezAMeVinte(UPDATED_DEZ_A_ME_VINTE)
            .dezAMeQuarenta(UPDATED_DEZ_A_ME_QUARENTA)
            .dezAMeCinquenta(UPDATED_DEZ_A_ME_CINQUENTA)
            .dezPMeDez(UPDATED_DEZ_P_ME_DEZ)
            .dezPMeVinte(UPDATED_DEZ_P_ME_VINTE)
            .dezPMeTrinta(UPDATED_DEZ_P_ME_TRINTA)
            .dezPMeCinquenta(UPDATED_DEZ_P_ME_CINQUENTA)
            .onzeAMeVinte(UPDATED_ONZE_A_ME_VINTE)
            .onzeAMeCinquenta(UPDATED_ONZE_A_ME_CINQUENTA)
            .onzePMeVinte(UPDATED_ONZE_P_ME_VINTE)
            .onzePMeCinquenta(UPDATED_ONZE_P_ME_CINQUENTA);

        restBlocosMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBlocos.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBlocos))
            )
            .andExpect(status().isOk());

        // Validate the Blocos in the database
        List<Blocos> blocosList = blocosRepository.findAll();
        assertThat(blocosList).hasSize(databaseSizeBeforeUpdate);
        Blocos testBlocos = blocosList.get(blocosList.size() - 1);
        assertThat(testBlocos.getZeroAM()).isEqualTo(UPDATED_ZERO_AM);
        assertThat(testBlocos.getZeroAMeDez()).isEqualTo(UPDATED_ZERO_A_ME_DEZ);
        assertThat(testBlocos.getZeroAMeVinte()).isEqualTo(UPDATED_ZERO_A_ME_VINTE);
        assertThat(testBlocos.getZeroAMeTrinta()).isEqualTo(DEFAULT_ZERO_A_ME_TRINTA);
        assertThat(testBlocos.getZeroAMeQuarenta()).isEqualTo(UPDATED_ZERO_A_ME_QUARENTA);
        assertThat(testBlocos.getZeroAMeCinquenta()).isEqualTo(UPDATED_ZERO_A_ME_CINQUENTA);
        assertThat(testBlocos.getZeroPM()).isEqualTo(UPDATED_ZERO_PM);
        assertThat(testBlocos.getZeroPMeDez()).isEqualTo(UPDATED_ZERO_P_ME_DEZ);
        assertThat(testBlocos.getZeroPMeVinte()).isEqualTo(DEFAULT_ZERO_P_ME_VINTE);
        assertThat(testBlocos.getZeroPMeTrinta()).isEqualTo(UPDATED_ZERO_P_ME_TRINTA);
        assertThat(testBlocos.getZeroPMeQuarenta()).isEqualTo(UPDATED_ZERO_P_ME_QUARENTA);
        assertThat(testBlocos.getZeroPMeCinquenta()).isEqualTo(UPDATED_ZERO_P_ME_CINQUENTA);
        assertThat(testBlocos.getUmAM()).isEqualTo(DEFAULT_UM_AM);
        assertThat(testBlocos.getUmAMeDez()).isEqualTo(UPDATED_UM_A_ME_DEZ);
        assertThat(testBlocos.getUmAMeVinte()).isEqualTo(DEFAULT_UM_A_ME_VINTE);
        assertThat(testBlocos.getUmAMeTrinta()).isEqualTo(UPDATED_UM_A_ME_TRINTA);
        assertThat(testBlocos.getUmAMeQuarenta()).isEqualTo(UPDATED_UM_A_ME_QUARENTA);
        assertThat(testBlocos.getUmAMeCinquenta()).isEqualTo(UPDATED_UM_A_ME_CINQUENTA);
        assertThat(testBlocos.getUmPM()).isEqualTo(DEFAULT_UM_PM);
        assertThat(testBlocos.getUmPMeDez()).isEqualTo(DEFAULT_UM_P_ME_DEZ);
        assertThat(testBlocos.getUmPMeVinte()).isEqualTo(UPDATED_UM_P_ME_VINTE);
        assertThat(testBlocos.getUmPMeTrinta()).isEqualTo(UPDATED_UM_P_ME_TRINTA);
        assertThat(testBlocos.getUmPMeQuarenta()).isEqualTo(UPDATED_UM_P_ME_QUARENTA);
        assertThat(testBlocos.getUmPMeCinquenta()).isEqualTo(DEFAULT_UM_P_ME_CINQUENTA);
        assertThat(testBlocos.getDoisAM()).isEqualTo(DEFAULT_DOIS_AM);
        assertThat(testBlocos.getDoisAMeDez()).isEqualTo(UPDATED_DOIS_A_ME_DEZ);
        assertThat(testBlocos.getDoisAMeVinte()).isEqualTo(DEFAULT_DOIS_A_ME_VINTE);
        assertThat(testBlocos.getDoisAMeTrinta()).isEqualTo(UPDATED_DOIS_A_ME_TRINTA);
        assertThat(testBlocos.getDoisAMeQuarenta()).isEqualTo(DEFAULT_DOIS_A_ME_QUARENTA);
        assertThat(testBlocos.getDoisAMeCinquenta()).isEqualTo(DEFAULT_DOIS_A_ME_CINQUENTA);
        assertThat(testBlocos.getDoisPM()).isEqualTo(DEFAULT_DOIS_PM);
        assertThat(testBlocos.getDoisPMeDez()).isEqualTo(DEFAULT_DOIS_P_ME_DEZ);
        assertThat(testBlocos.getDoisPMeVinte()).isEqualTo(DEFAULT_DOIS_P_ME_VINTE);
        assertThat(testBlocos.getDoisPMeTrinta()).isEqualTo(UPDATED_DOIS_P_ME_TRINTA);
        assertThat(testBlocos.getDoisPMeQuarenta()).isEqualTo(DEFAULT_DOIS_P_ME_QUARENTA);
        assertThat(testBlocos.getDoisPMeCinquenta()).isEqualTo(UPDATED_DOIS_P_ME_CINQUENTA);
        assertThat(testBlocos.getTresAM()).isEqualTo(DEFAULT_TRES_AM);
        assertThat(testBlocos.getTresAMeDez()).isEqualTo(DEFAULT_TRES_A_ME_DEZ);
        assertThat(testBlocos.getTresAMeVinte()).isEqualTo(UPDATED_TRES_A_ME_VINTE);
        assertThat(testBlocos.getTresAMeTrinta()).isEqualTo(UPDATED_TRES_A_ME_TRINTA);
        assertThat(testBlocos.getTresAMeQuarenta()).isEqualTo(UPDATED_TRES_A_ME_QUARENTA);
        assertThat(testBlocos.getTresAMeCinquenta()).isEqualTo(UPDATED_TRES_A_ME_CINQUENTA);
        assertThat(testBlocos.getTresPM()).isEqualTo(DEFAULT_TRES_PM);
        assertThat(testBlocos.getTresPMeDez()).isEqualTo(DEFAULT_TRES_P_ME_DEZ);
        assertThat(testBlocos.getTresPMeVinte()).isEqualTo(UPDATED_TRES_P_ME_VINTE);
        assertThat(testBlocos.getTresPMeTrinta()).isEqualTo(UPDATED_TRES_P_ME_TRINTA);
        assertThat(testBlocos.getTresPMeQuarenta()).isEqualTo(DEFAULT_TRES_P_ME_QUARENTA);
        assertThat(testBlocos.getTresPMeCinquenta()).isEqualTo(UPDATED_TRES_P_ME_CINQUENTA);
        assertThat(testBlocos.getQuatroAM()).isEqualTo(UPDATED_QUATRO_AM);
        assertThat(testBlocos.getQuatroAMeDez()).isEqualTo(UPDATED_QUATRO_A_ME_DEZ);
        assertThat(testBlocos.getQuatroAMeVinte()).isEqualTo(DEFAULT_QUATRO_A_ME_VINTE);
        assertThat(testBlocos.getQuatroAMeTrinta()).isEqualTo(UPDATED_QUATRO_A_ME_TRINTA);
        assertThat(testBlocos.getQuatroAMeQuarenta()).isEqualTo(UPDATED_QUATRO_A_ME_QUARENTA);
        assertThat(testBlocos.getQuatroAMeCinquenta()).isEqualTo(UPDATED_QUATRO_A_ME_CINQUENTA);
        assertThat(testBlocos.getQuatroPM()).isEqualTo(DEFAULT_QUATRO_PM);
        assertThat(testBlocos.getQuatroPMeDez()).isEqualTo(UPDATED_QUATRO_P_ME_DEZ);
        assertThat(testBlocos.getQuatroPMeVinte()).isEqualTo(UPDATED_QUATRO_P_ME_VINTE);
        assertThat(testBlocos.getQuatroPMeTrinta()).isEqualTo(DEFAULT_QUATRO_P_ME_TRINTA);
        assertThat(testBlocos.getQuatroPMeQuarenta()).isEqualTo(UPDATED_QUATRO_P_ME_QUARENTA);
        assertThat(testBlocos.getQuatroPMeCinquenta()).isEqualTo(UPDATED_QUATRO_P_ME_CINQUENTA);
        assertThat(testBlocos.getCincoAM()).isEqualTo(UPDATED_CINCO_AM);
        assertThat(testBlocos.getCincoAMeDez()).isEqualTo(DEFAULT_CINCO_A_ME_DEZ);
        assertThat(testBlocos.getCincoAMeVinte()).isEqualTo(DEFAULT_CINCO_A_ME_VINTE);
        assertThat(testBlocos.getCincoAMeTrinta()).isEqualTo(DEFAULT_CINCO_A_ME_TRINTA);
        assertThat(testBlocos.getCincoAMeQuarenta()).isEqualTo(UPDATED_CINCO_A_ME_QUARENTA);
        assertThat(testBlocos.getCincoAMeCinquenta()).isEqualTo(UPDATED_CINCO_A_ME_CINQUENTA);
        assertThat(testBlocos.getCincoPM()).isEqualTo(UPDATED_CINCO_PM);
        assertThat(testBlocos.getCincoPMeDez()).isEqualTo(UPDATED_CINCO_P_ME_DEZ);
        assertThat(testBlocos.getCincoPMeVinte()).isEqualTo(DEFAULT_CINCO_P_ME_VINTE);
        assertThat(testBlocos.getCincoPMeTrinta()).isEqualTo(DEFAULT_CINCO_P_ME_TRINTA);
        assertThat(testBlocos.getCincoPMeQuarenta()).isEqualTo(UPDATED_CINCO_P_ME_QUARENTA);
        assertThat(testBlocos.getCincoPMeCinquenta()).isEqualTo(DEFAULT_CINCO_P_ME_CINQUENTA);
        assertThat(testBlocos.getSeisAM()).isEqualTo(UPDATED_SEIS_AM);
        assertThat(testBlocos.getSeisAMeDez()).isEqualTo(UPDATED_SEIS_A_ME_DEZ);
        assertThat(testBlocos.getSeisAMeVinte()).isEqualTo(UPDATED_SEIS_A_ME_VINTE);
        assertThat(testBlocos.getSeisAMeTrinta()).isEqualTo(UPDATED_SEIS_A_ME_TRINTA);
        assertThat(testBlocos.getSeisAMeQuarenta()).isEqualTo(UPDATED_SEIS_A_ME_QUARENTA);
        assertThat(testBlocos.getSeisAMeCinquenta()).isEqualTo(DEFAULT_SEIS_A_ME_CINQUENTA);
        assertThat(testBlocos.getSeisPM()).isEqualTo(UPDATED_SEIS_PM);
        assertThat(testBlocos.getSeisPMeDez()).isEqualTo(UPDATED_SEIS_P_ME_DEZ);
        assertThat(testBlocos.getSeisPMeVinte()).isEqualTo(DEFAULT_SEIS_P_ME_VINTE);
        assertThat(testBlocos.getSeisPMeTrinta()).isEqualTo(DEFAULT_SEIS_P_ME_TRINTA);
        assertThat(testBlocos.getSeisPMeQuarenta()).isEqualTo(DEFAULT_SEIS_P_ME_QUARENTA);
        assertThat(testBlocos.getSeisPMeCinquenta()).isEqualTo(DEFAULT_SEIS_P_ME_CINQUENTA);
        assertThat(testBlocos.getSeteAM()).isEqualTo(UPDATED_SETE_AM);
        assertThat(testBlocos.getSeteAMeDez()).isEqualTo(UPDATED_SETE_A_ME_DEZ);
        assertThat(testBlocos.getSeteAMeVinte()).isEqualTo(UPDATED_SETE_A_ME_VINTE);
        assertThat(testBlocos.getSeteAMeTrinta()).isEqualTo(UPDATED_SETE_A_ME_TRINTA);
        assertThat(testBlocos.getSeteAMeQuarenta()).isEqualTo(UPDATED_SETE_A_ME_QUARENTA);
        assertThat(testBlocos.getSeteAMeCinquenta()).isEqualTo(UPDATED_SETE_A_ME_CINQUENTA);
        assertThat(testBlocos.getSetePM()).isEqualTo(DEFAULT_SETE_PM);
        assertThat(testBlocos.getSetePMeDez()).isEqualTo(UPDATED_SETE_P_ME_DEZ);
        assertThat(testBlocos.getSetePMeVinte()).isEqualTo(DEFAULT_SETE_P_ME_VINTE);
        assertThat(testBlocos.getSetePMeTrinta()).isEqualTo(DEFAULT_SETE_P_ME_TRINTA);
        assertThat(testBlocos.getSetePMeQuarenta()).isEqualTo(UPDATED_SETE_P_ME_QUARENTA);
        assertThat(testBlocos.getSetePMeCinquenta()).isEqualTo(DEFAULT_SETE_P_ME_CINQUENTA);
        assertThat(testBlocos.getOitoAM()).isEqualTo(UPDATED_OITO_AM);
        assertThat(testBlocos.getOitoAMeDez()).isEqualTo(UPDATED_OITO_A_ME_DEZ);
        assertThat(testBlocos.getOitoAMeVinte()).isEqualTo(DEFAULT_OITO_A_ME_VINTE);
        assertThat(testBlocos.getOitoAMeTrinta()).isEqualTo(DEFAULT_OITO_A_ME_TRINTA);
        assertThat(testBlocos.getOitoAMeQuarenta()).isEqualTo(UPDATED_OITO_A_ME_QUARENTA);
        assertThat(testBlocos.getOitoAMeCinquenta()).isEqualTo(DEFAULT_OITO_A_ME_CINQUENTA);
        assertThat(testBlocos.getOitoPM()).isEqualTo(UPDATED_OITO_PM);
        assertThat(testBlocos.getOitoPMeDez()).isEqualTo(UPDATED_OITO_P_ME_DEZ);
        assertThat(testBlocos.getOitoPMeVinte()).isEqualTo(DEFAULT_OITO_P_ME_VINTE);
        assertThat(testBlocos.getOitoPMeTrinta()).isEqualTo(DEFAULT_OITO_P_ME_TRINTA);
        assertThat(testBlocos.getOitoPMeQuarenta()).isEqualTo(DEFAULT_OITO_P_ME_QUARENTA);
        assertThat(testBlocos.getOitoPMeCinquenta()).isEqualTo(UPDATED_OITO_P_ME_CINQUENTA);
        assertThat(testBlocos.getNoveAM()).isEqualTo(DEFAULT_NOVE_AM);
        assertThat(testBlocos.getNoveAMeDez()).isEqualTo(DEFAULT_NOVE_A_ME_DEZ);
        assertThat(testBlocos.getNoveAMeVinte()).isEqualTo(DEFAULT_NOVE_A_ME_VINTE);
        assertThat(testBlocos.getNoveAMeTrinta()).isEqualTo(UPDATED_NOVE_A_ME_TRINTA);
        assertThat(testBlocos.getNoveAMeQuarenta()).isEqualTo(UPDATED_NOVE_A_ME_QUARENTA);
        assertThat(testBlocos.getNoveAMeCinquenta()).isEqualTo(DEFAULT_NOVE_A_ME_CINQUENTA);
        assertThat(testBlocos.getNovePM()).isEqualTo(DEFAULT_NOVE_PM);
        assertThat(testBlocos.getNovePMeDez()).isEqualTo(UPDATED_NOVE_P_ME_DEZ);
        assertThat(testBlocos.getNovePMeVinte()).isEqualTo(DEFAULT_NOVE_P_ME_VINTE);
        assertThat(testBlocos.getNovePMeTrinta()).isEqualTo(DEFAULT_NOVE_P_ME_TRINTA);
        assertThat(testBlocos.getNovePMeQuarenta()).isEqualTo(DEFAULT_NOVE_P_ME_QUARENTA);
        assertThat(testBlocos.getNovePMeCinquenta()).isEqualTo(UPDATED_NOVE_P_ME_CINQUENTA);
        assertThat(testBlocos.getDezAM()).isEqualTo(UPDATED_DEZ_AM);
        assertThat(testBlocos.getDezAMeDez()).isEqualTo(UPDATED_DEZ_A_ME_DEZ);
        assertThat(testBlocos.getDezAMeVinte()).isEqualTo(UPDATED_DEZ_A_ME_VINTE);
        assertThat(testBlocos.getDezAMeTrinta()).isEqualTo(DEFAULT_DEZ_A_ME_TRINTA);
        assertThat(testBlocos.getDezAMeQuarenta()).isEqualTo(UPDATED_DEZ_A_ME_QUARENTA);
        assertThat(testBlocos.getDezAMeCinquenta()).isEqualTo(UPDATED_DEZ_A_ME_CINQUENTA);
        assertThat(testBlocos.getDezPM()).isEqualTo(DEFAULT_DEZ_PM);
        assertThat(testBlocos.getDezPMeDez()).isEqualTo(UPDATED_DEZ_P_ME_DEZ);
        assertThat(testBlocos.getDezPMeVinte()).isEqualTo(UPDATED_DEZ_P_ME_VINTE);
        assertThat(testBlocos.getDezPMeTrinta()).isEqualTo(UPDATED_DEZ_P_ME_TRINTA);
        assertThat(testBlocos.getDezPMeQuarenta()).isEqualTo(DEFAULT_DEZ_P_ME_QUARENTA);
        assertThat(testBlocos.getDezPMeCinquenta()).isEqualTo(UPDATED_DEZ_P_ME_CINQUENTA);
        assertThat(testBlocos.getOnzeAM()).isEqualTo(DEFAULT_ONZE_AM);
        assertThat(testBlocos.getOnzeAMeDez()).isEqualTo(DEFAULT_ONZE_A_ME_DEZ);
        assertThat(testBlocos.getOnzeAMeVinte()).isEqualTo(UPDATED_ONZE_A_ME_VINTE);
        assertThat(testBlocos.getOnzeAMeTrinta()).isEqualTo(DEFAULT_ONZE_A_ME_TRINTA);
        assertThat(testBlocos.getOnzeAMeQuarenta()).isEqualTo(DEFAULT_ONZE_A_ME_QUARENTA);
        assertThat(testBlocos.getOnzeAMeCinquenta()).isEqualTo(UPDATED_ONZE_A_ME_CINQUENTA);
        assertThat(testBlocos.getOnzePM()).isEqualTo(DEFAULT_ONZE_PM);
        assertThat(testBlocos.getOnzePMeDez()).isEqualTo(DEFAULT_ONZE_P_ME_DEZ);
        assertThat(testBlocos.getOnzePMeVinte()).isEqualTo(UPDATED_ONZE_P_ME_VINTE);
        assertThat(testBlocos.getOnzePMeTrinta()).isEqualTo(DEFAULT_ONZE_P_ME_TRINTA);
        assertThat(testBlocos.getOnzePMeQuarenta()).isEqualTo(DEFAULT_ONZE_P_ME_QUARENTA);
        assertThat(testBlocos.getOnzePMeCinquenta()).isEqualTo(UPDATED_ONZE_P_ME_CINQUENTA);
    }

    @Test
    @Transactional
    void fullUpdateBlocosWithPatch() throws Exception {
        // Initialize the database
        blocosRepository.saveAndFlush(blocos);

        int databaseSizeBeforeUpdate = blocosRepository.findAll().size();

        // Update the blocos using partial update
        Blocos partialUpdatedBlocos = new Blocos();
        partialUpdatedBlocos.setId(blocos.getId());

        partialUpdatedBlocos
            .zeroAM(UPDATED_ZERO_AM)
            .zeroAMeDez(UPDATED_ZERO_A_ME_DEZ)
            .zeroAMeVinte(UPDATED_ZERO_A_ME_VINTE)
            .zeroAMeTrinta(UPDATED_ZERO_A_ME_TRINTA)
            .zeroAMeQuarenta(UPDATED_ZERO_A_ME_QUARENTA)
            .zeroAMeCinquenta(UPDATED_ZERO_A_ME_CINQUENTA)
            .zeroPM(UPDATED_ZERO_PM)
            .zeroPMeDez(UPDATED_ZERO_P_ME_DEZ)
            .zeroPMeVinte(UPDATED_ZERO_P_ME_VINTE)
            .zeroPMeTrinta(UPDATED_ZERO_P_ME_TRINTA)
            .zeroPMeQuarenta(UPDATED_ZERO_P_ME_QUARENTA)
            .zeroPMeCinquenta(UPDATED_ZERO_P_ME_CINQUENTA)
            .umAM(UPDATED_UM_AM)
            .umAMeDez(UPDATED_UM_A_ME_DEZ)
            .umAMeVinte(UPDATED_UM_A_ME_VINTE)
            .umAMeTrinta(UPDATED_UM_A_ME_TRINTA)
            .umAMeQuarenta(UPDATED_UM_A_ME_QUARENTA)
            .umAMeCinquenta(UPDATED_UM_A_ME_CINQUENTA)
            .umPM(UPDATED_UM_PM)
            .umPMeDez(UPDATED_UM_P_ME_DEZ)
            .umPMeVinte(UPDATED_UM_P_ME_VINTE)
            .umPMeTrinta(UPDATED_UM_P_ME_TRINTA)
            .umPMeQuarenta(UPDATED_UM_P_ME_QUARENTA)
            .umPMeCinquenta(UPDATED_UM_P_ME_CINQUENTA)
            .doisAM(UPDATED_DOIS_AM)
            .doisAMeDez(UPDATED_DOIS_A_ME_DEZ)
            .doisAMeVinte(UPDATED_DOIS_A_ME_VINTE)
            .doisAMeTrinta(UPDATED_DOIS_A_ME_TRINTA)
            .doisAMeQuarenta(UPDATED_DOIS_A_ME_QUARENTA)
            .doisAMeCinquenta(UPDATED_DOIS_A_ME_CINQUENTA)
            .doisPM(UPDATED_DOIS_PM)
            .doisPMeDez(UPDATED_DOIS_P_ME_DEZ)
            .doisPMeVinte(UPDATED_DOIS_P_ME_VINTE)
            .doisPMeTrinta(UPDATED_DOIS_P_ME_TRINTA)
            .doisPMeQuarenta(UPDATED_DOIS_P_ME_QUARENTA)
            .doisPMeCinquenta(UPDATED_DOIS_P_ME_CINQUENTA)
            .tresAM(UPDATED_TRES_AM)
            .tresAMeDez(UPDATED_TRES_A_ME_DEZ)
            .tresAMeVinte(UPDATED_TRES_A_ME_VINTE)
            .tresAMeTrinta(UPDATED_TRES_A_ME_TRINTA)
            .tresAMeQuarenta(UPDATED_TRES_A_ME_QUARENTA)
            .tresAMeCinquenta(UPDATED_TRES_A_ME_CINQUENTA)
            .tresPM(UPDATED_TRES_PM)
            .tresPMeDez(UPDATED_TRES_P_ME_DEZ)
            .tresPMeVinte(UPDATED_TRES_P_ME_VINTE)
            .tresPMeTrinta(UPDATED_TRES_P_ME_TRINTA)
            .tresPMeQuarenta(UPDATED_TRES_P_ME_QUARENTA)
            .tresPMeCinquenta(UPDATED_TRES_P_ME_CINQUENTA)
            .quatroAM(UPDATED_QUATRO_AM)
            .quatroAMeDez(UPDATED_QUATRO_A_ME_DEZ)
            .quatroAMeVinte(UPDATED_QUATRO_A_ME_VINTE)
            .quatroAMeTrinta(UPDATED_QUATRO_A_ME_TRINTA)
            .quatroAMeQuarenta(UPDATED_QUATRO_A_ME_QUARENTA)
            .quatroAMeCinquenta(UPDATED_QUATRO_A_ME_CINQUENTA)
            .quatroPM(UPDATED_QUATRO_PM)
            .quatroPMeDez(UPDATED_QUATRO_P_ME_DEZ)
            .quatroPMeVinte(UPDATED_QUATRO_P_ME_VINTE)
            .quatroPMeTrinta(UPDATED_QUATRO_P_ME_TRINTA)
            .quatroPMeQuarenta(UPDATED_QUATRO_P_ME_QUARENTA)
            .quatroPMeCinquenta(UPDATED_QUATRO_P_ME_CINQUENTA)
            .cincoAM(UPDATED_CINCO_AM)
            .cincoAMeDez(UPDATED_CINCO_A_ME_DEZ)
            .cincoAMeVinte(UPDATED_CINCO_A_ME_VINTE)
            .cincoAMeTrinta(UPDATED_CINCO_A_ME_TRINTA)
            .cincoAMeQuarenta(UPDATED_CINCO_A_ME_QUARENTA)
            .cincoAMeCinquenta(UPDATED_CINCO_A_ME_CINQUENTA)
            .cincoPM(UPDATED_CINCO_PM)
            .cincoPMeDez(UPDATED_CINCO_P_ME_DEZ)
            .cincoPMeVinte(UPDATED_CINCO_P_ME_VINTE)
            .cincoPMeTrinta(UPDATED_CINCO_P_ME_TRINTA)
            .cincoPMeQuarenta(UPDATED_CINCO_P_ME_QUARENTA)
            .cincoPMeCinquenta(UPDATED_CINCO_P_ME_CINQUENTA)
            .seisAM(UPDATED_SEIS_AM)
            .seisAMeDez(UPDATED_SEIS_A_ME_DEZ)
            .seisAMeVinte(UPDATED_SEIS_A_ME_VINTE)
            .seisAMeTrinta(UPDATED_SEIS_A_ME_TRINTA)
            .seisAMeQuarenta(UPDATED_SEIS_A_ME_QUARENTA)
            .seisAMeCinquenta(UPDATED_SEIS_A_ME_CINQUENTA)
            .seisPM(UPDATED_SEIS_PM)
            .seisPMeDez(UPDATED_SEIS_P_ME_DEZ)
            .seisPMeVinte(UPDATED_SEIS_P_ME_VINTE)
            .seisPMeTrinta(UPDATED_SEIS_P_ME_TRINTA)
            .seisPMeQuarenta(UPDATED_SEIS_P_ME_QUARENTA)
            .seisPMeCinquenta(UPDATED_SEIS_P_ME_CINQUENTA)
            .seteAM(UPDATED_SETE_AM)
            .seteAMeDez(UPDATED_SETE_A_ME_DEZ)
            .seteAMeVinte(UPDATED_SETE_A_ME_VINTE)
            .seteAMeTrinta(UPDATED_SETE_A_ME_TRINTA)
            .seteAMeQuarenta(UPDATED_SETE_A_ME_QUARENTA)
            .seteAMeCinquenta(UPDATED_SETE_A_ME_CINQUENTA)
            .setePM(UPDATED_SETE_PM)
            .setePMeDez(UPDATED_SETE_P_ME_DEZ)
            .setePMeVinte(UPDATED_SETE_P_ME_VINTE)
            .setePMeTrinta(UPDATED_SETE_P_ME_TRINTA)
            .setePMeQuarenta(UPDATED_SETE_P_ME_QUARENTA)
            .setePMeCinquenta(UPDATED_SETE_P_ME_CINQUENTA)
            .oitoAM(UPDATED_OITO_AM)
            .oitoAMeDez(UPDATED_OITO_A_ME_DEZ)
            .oitoAMeVinte(UPDATED_OITO_A_ME_VINTE)
            .oitoAMeTrinta(UPDATED_OITO_A_ME_TRINTA)
            .oitoAMeQuarenta(UPDATED_OITO_A_ME_QUARENTA)
            .oitoAMeCinquenta(UPDATED_OITO_A_ME_CINQUENTA)
            .oitoPM(UPDATED_OITO_PM)
            .oitoPMeDez(UPDATED_OITO_P_ME_DEZ)
            .oitoPMeVinte(UPDATED_OITO_P_ME_VINTE)
            .oitoPMeTrinta(UPDATED_OITO_P_ME_TRINTA)
            .oitoPMeQuarenta(UPDATED_OITO_P_ME_QUARENTA)
            .oitoPMeCinquenta(UPDATED_OITO_P_ME_CINQUENTA)
            .noveAM(UPDATED_NOVE_AM)
            .noveAMeDez(UPDATED_NOVE_A_ME_DEZ)
            .noveAMeVinte(UPDATED_NOVE_A_ME_VINTE)
            .noveAMeTrinta(UPDATED_NOVE_A_ME_TRINTA)
            .noveAMeQuarenta(UPDATED_NOVE_A_ME_QUARENTA)
            .noveAMeCinquenta(UPDATED_NOVE_A_ME_CINQUENTA)
            .novePM(UPDATED_NOVE_PM)
            .novePMeDez(UPDATED_NOVE_P_ME_DEZ)
            .novePMeVinte(UPDATED_NOVE_P_ME_VINTE)
            .novePMeTrinta(UPDATED_NOVE_P_ME_TRINTA)
            .novePMeQuarenta(UPDATED_NOVE_P_ME_QUARENTA)
            .novePMeCinquenta(UPDATED_NOVE_P_ME_CINQUENTA)
            .dezAM(UPDATED_DEZ_AM)
            .dezAMeDez(UPDATED_DEZ_A_ME_DEZ)
            .dezAMeVinte(UPDATED_DEZ_A_ME_VINTE)
            .dezAMeTrinta(UPDATED_DEZ_A_ME_TRINTA)
            .dezAMeQuarenta(UPDATED_DEZ_A_ME_QUARENTA)
            .dezAMeCinquenta(UPDATED_DEZ_A_ME_CINQUENTA)
            .dezPM(UPDATED_DEZ_PM)
            .dezPMeDez(UPDATED_DEZ_P_ME_DEZ)
            .dezPMeVinte(UPDATED_DEZ_P_ME_VINTE)
            .dezPMeTrinta(UPDATED_DEZ_P_ME_TRINTA)
            .dezPMeQuarenta(UPDATED_DEZ_P_ME_QUARENTA)
            .dezPMeCinquenta(UPDATED_DEZ_P_ME_CINQUENTA)
            .onzeAM(UPDATED_ONZE_AM)
            .onzeAMeDez(UPDATED_ONZE_A_ME_DEZ)
            .onzeAMeVinte(UPDATED_ONZE_A_ME_VINTE)
            .onzeAMeTrinta(UPDATED_ONZE_A_ME_TRINTA)
            .onzeAMeQuarenta(UPDATED_ONZE_A_ME_QUARENTA)
            .onzeAMeCinquenta(UPDATED_ONZE_A_ME_CINQUENTA)
            .onzePM(UPDATED_ONZE_PM)
            .onzePMeDez(UPDATED_ONZE_P_ME_DEZ)
            .onzePMeVinte(UPDATED_ONZE_P_ME_VINTE)
            .onzePMeTrinta(UPDATED_ONZE_P_ME_TRINTA)
            .onzePMeQuarenta(UPDATED_ONZE_P_ME_QUARENTA)
            .onzePMeCinquenta(UPDATED_ONZE_P_ME_CINQUENTA);

        restBlocosMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBlocos.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBlocos))
            )
            .andExpect(status().isOk());

        // Validate the Blocos in the database
        List<Blocos> blocosList = blocosRepository.findAll();
        assertThat(blocosList).hasSize(databaseSizeBeforeUpdate);
        Blocos testBlocos = blocosList.get(blocosList.size() - 1);
        assertThat(testBlocos.getZeroAM()).isEqualTo(UPDATED_ZERO_AM);
        assertThat(testBlocos.getZeroAMeDez()).isEqualTo(UPDATED_ZERO_A_ME_DEZ);
        assertThat(testBlocos.getZeroAMeVinte()).isEqualTo(UPDATED_ZERO_A_ME_VINTE);
        assertThat(testBlocos.getZeroAMeTrinta()).isEqualTo(UPDATED_ZERO_A_ME_TRINTA);
        assertThat(testBlocos.getZeroAMeQuarenta()).isEqualTo(UPDATED_ZERO_A_ME_QUARENTA);
        assertThat(testBlocos.getZeroAMeCinquenta()).isEqualTo(UPDATED_ZERO_A_ME_CINQUENTA);
        assertThat(testBlocos.getZeroPM()).isEqualTo(UPDATED_ZERO_PM);
        assertThat(testBlocos.getZeroPMeDez()).isEqualTo(UPDATED_ZERO_P_ME_DEZ);
        assertThat(testBlocos.getZeroPMeVinte()).isEqualTo(UPDATED_ZERO_P_ME_VINTE);
        assertThat(testBlocos.getZeroPMeTrinta()).isEqualTo(UPDATED_ZERO_P_ME_TRINTA);
        assertThat(testBlocos.getZeroPMeQuarenta()).isEqualTo(UPDATED_ZERO_P_ME_QUARENTA);
        assertThat(testBlocos.getZeroPMeCinquenta()).isEqualTo(UPDATED_ZERO_P_ME_CINQUENTA);
        assertThat(testBlocos.getUmAM()).isEqualTo(UPDATED_UM_AM);
        assertThat(testBlocos.getUmAMeDez()).isEqualTo(UPDATED_UM_A_ME_DEZ);
        assertThat(testBlocos.getUmAMeVinte()).isEqualTo(UPDATED_UM_A_ME_VINTE);
        assertThat(testBlocos.getUmAMeTrinta()).isEqualTo(UPDATED_UM_A_ME_TRINTA);
        assertThat(testBlocos.getUmAMeQuarenta()).isEqualTo(UPDATED_UM_A_ME_QUARENTA);
        assertThat(testBlocos.getUmAMeCinquenta()).isEqualTo(UPDATED_UM_A_ME_CINQUENTA);
        assertThat(testBlocos.getUmPM()).isEqualTo(UPDATED_UM_PM);
        assertThat(testBlocos.getUmPMeDez()).isEqualTo(UPDATED_UM_P_ME_DEZ);
        assertThat(testBlocos.getUmPMeVinte()).isEqualTo(UPDATED_UM_P_ME_VINTE);
        assertThat(testBlocos.getUmPMeTrinta()).isEqualTo(UPDATED_UM_P_ME_TRINTA);
        assertThat(testBlocos.getUmPMeQuarenta()).isEqualTo(UPDATED_UM_P_ME_QUARENTA);
        assertThat(testBlocos.getUmPMeCinquenta()).isEqualTo(UPDATED_UM_P_ME_CINQUENTA);
        assertThat(testBlocos.getDoisAM()).isEqualTo(UPDATED_DOIS_AM);
        assertThat(testBlocos.getDoisAMeDez()).isEqualTo(UPDATED_DOIS_A_ME_DEZ);
        assertThat(testBlocos.getDoisAMeVinte()).isEqualTo(UPDATED_DOIS_A_ME_VINTE);
        assertThat(testBlocos.getDoisAMeTrinta()).isEqualTo(UPDATED_DOIS_A_ME_TRINTA);
        assertThat(testBlocos.getDoisAMeQuarenta()).isEqualTo(UPDATED_DOIS_A_ME_QUARENTA);
        assertThat(testBlocos.getDoisAMeCinquenta()).isEqualTo(UPDATED_DOIS_A_ME_CINQUENTA);
        assertThat(testBlocos.getDoisPM()).isEqualTo(UPDATED_DOIS_PM);
        assertThat(testBlocos.getDoisPMeDez()).isEqualTo(UPDATED_DOIS_P_ME_DEZ);
        assertThat(testBlocos.getDoisPMeVinte()).isEqualTo(UPDATED_DOIS_P_ME_VINTE);
        assertThat(testBlocos.getDoisPMeTrinta()).isEqualTo(UPDATED_DOIS_P_ME_TRINTA);
        assertThat(testBlocos.getDoisPMeQuarenta()).isEqualTo(UPDATED_DOIS_P_ME_QUARENTA);
        assertThat(testBlocos.getDoisPMeCinquenta()).isEqualTo(UPDATED_DOIS_P_ME_CINQUENTA);
        assertThat(testBlocos.getTresAM()).isEqualTo(UPDATED_TRES_AM);
        assertThat(testBlocos.getTresAMeDez()).isEqualTo(UPDATED_TRES_A_ME_DEZ);
        assertThat(testBlocos.getTresAMeVinte()).isEqualTo(UPDATED_TRES_A_ME_VINTE);
        assertThat(testBlocos.getTresAMeTrinta()).isEqualTo(UPDATED_TRES_A_ME_TRINTA);
        assertThat(testBlocos.getTresAMeQuarenta()).isEqualTo(UPDATED_TRES_A_ME_QUARENTA);
        assertThat(testBlocos.getTresAMeCinquenta()).isEqualTo(UPDATED_TRES_A_ME_CINQUENTA);
        assertThat(testBlocos.getTresPM()).isEqualTo(UPDATED_TRES_PM);
        assertThat(testBlocos.getTresPMeDez()).isEqualTo(UPDATED_TRES_P_ME_DEZ);
        assertThat(testBlocos.getTresPMeVinte()).isEqualTo(UPDATED_TRES_P_ME_VINTE);
        assertThat(testBlocos.getTresPMeTrinta()).isEqualTo(UPDATED_TRES_P_ME_TRINTA);
        assertThat(testBlocos.getTresPMeQuarenta()).isEqualTo(UPDATED_TRES_P_ME_QUARENTA);
        assertThat(testBlocos.getTresPMeCinquenta()).isEqualTo(UPDATED_TRES_P_ME_CINQUENTA);
        assertThat(testBlocos.getQuatroAM()).isEqualTo(UPDATED_QUATRO_AM);
        assertThat(testBlocos.getQuatroAMeDez()).isEqualTo(UPDATED_QUATRO_A_ME_DEZ);
        assertThat(testBlocos.getQuatroAMeVinte()).isEqualTo(UPDATED_QUATRO_A_ME_VINTE);
        assertThat(testBlocos.getQuatroAMeTrinta()).isEqualTo(UPDATED_QUATRO_A_ME_TRINTA);
        assertThat(testBlocos.getQuatroAMeQuarenta()).isEqualTo(UPDATED_QUATRO_A_ME_QUARENTA);
        assertThat(testBlocos.getQuatroAMeCinquenta()).isEqualTo(UPDATED_QUATRO_A_ME_CINQUENTA);
        assertThat(testBlocos.getQuatroPM()).isEqualTo(UPDATED_QUATRO_PM);
        assertThat(testBlocos.getQuatroPMeDez()).isEqualTo(UPDATED_QUATRO_P_ME_DEZ);
        assertThat(testBlocos.getQuatroPMeVinte()).isEqualTo(UPDATED_QUATRO_P_ME_VINTE);
        assertThat(testBlocos.getQuatroPMeTrinta()).isEqualTo(UPDATED_QUATRO_P_ME_TRINTA);
        assertThat(testBlocos.getQuatroPMeQuarenta()).isEqualTo(UPDATED_QUATRO_P_ME_QUARENTA);
        assertThat(testBlocos.getQuatroPMeCinquenta()).isEqualTo(UPDATED_QUATRO_P_ME_CINQUENTA);
        assertThat(testBlocos.getCincoAM()).isEqualTo(UPDATED_CINCO_AM);
        assertThat(testBlocos.getCincoAMeDez()).isEqualTo(UPDATED_CINCO_A_ME_DEZ);
        assertThat(testBlocos.getCincoAMeVinte()).isEqualTo(UPDATED_CINCO_A_ME_VINTE);
        assertThat(testBlocos.getCincoAMeTrinta()).isEqualTo(UPDATED_CINCO_A_ME_TRINTA);
        assertThat(testBlocos.getCincoAMeQuarenta()).isEqualTo(UPDATED_CINCO_A_ME_QUARENTA);
        assertThat(testBlocos.getCincoAMeCinquenta()).isEqualTo(UPDATED_CINCO_A_ME_CINQUENTA);
        assertThat(testBlocos.getCincoPM()).isEqualTo(UPDATED_CINCO_PM);
        assertThat(testBlocos.getCincoPMeDez()).isEqualTo(UPDATED_CINCO_P_ME_DEZ);
        assertThat(testBlocos.getCincoPMeVinte()).isEqualTo(UPDATED_CINCO_P_ME_VINTE);
        assertThat(testBlocos.getCincoPMeTrinta()).isEqualTo(UPDATED_CINCO_P_ME_TRINTA);
        assertThat(testBlocos.getCincoPMeQuarenta()).isEqualTo(UPDATED_CINCO_P_ME_QUARENTA);
        assertThat(testBlocos.getCincoPMeCinquenta()).isEqualTo(UPDATED_CINCO_P_ME_CINQUENTA);
        assertThat(testBlocos.getSeisAM()).isEqualTo(UPDATED_SEIS_AM);
        assertThat(testBlocos.getSeisAMeDez()).isEqualTo(UPDATED_SEIS_A_ME_DEZ);
        assertThat(testBlocos.getSeisAMeVinte()).isEqualTo(UPDATED_SEIS_A_ME_VINTE);
        assertThat(testBlocos.getSeisAMeTrinta()).isEqualTo(UPDATED_SEIS_A_ME_TRINTA);
        assertThat(testBlocos.getSeisAMeQuarenta()).isEqualTo(UPDATED_SEIS_A_ME_QUARENTA);
        assertThat(testBlocos.getSeisAMeCinquenta()).isEqualTo(UPDATED_SEIS_A_ME_CINQUENTA);
        assertThat(testBlocos.getSeisPM()).isEqualTo(UPDATED_SEIS_PM);
        assertThat(testBlocos.getSeisPMeDez()).isEqualTo(UPDATED_SEIS_P_ME_DEZ);
        assertThat(testBlocos.getSeisPMeVinte()).isEqualTo(UPDATED_SEIS_P_ME_VINTE);
        assertThat(testBlocos.getSeisPMeTrinta()).isEqualTo(UPDATED_SEIS_P_ME_TRINTA);
        assertThat(testBlocos.getSeisPMeQuarenta()).isEqualTo(UPDATED_SEIS_P_ME_QUARENTA);
        assertThat(testBlocos.getSeisPMeCinquenta()).isEqualTo(UPDATED_SEIS_P_ME_CINQUENTA);
        assertThat(testBlocos.getSeteAM()).isEqualTo(UPDATED_SETE_AM);
        assertThat(testBlocos.getSeteAMeDez()).isEqualTo(UPDATED_SETE_A_ME_DEZ);
        assertThat(testBlocos.getSeteAMeVinte()).isEqualTo(UPDATED_SETE_A_ME_VINTE);
        assertThat(testBlocos.getSeteAMeTrinta()).isEqualTo(UPDATED_SETE_A_ME_TRINTA);
        assertThat(testBlocos.getSeteAMeQuarenta()).isEqualTo(UPDATED_SETE_A_ME_QUARENTA);
        assertThat(testBlocos.getSeteAMeCinquenta()).isEqualTo(UPDATED_SETE_A_ME_CINQUENTA);
        assertThat(testBlocos.getSetePM()).isEqualTo(UPDATED_SETE_PM);
        assertThat(testBlocos.getSetePMeDez()).isEqualTo(UPDATED_SETE_P_ME_DEZ);
        assertThat(testBlocos.getSetePMeVinte()).isEqualTo(UPDATED_SETE_P_ME_VINTE);
        assertThat(testBlocos.getSetePMeTrinta()).isEqualTo(UPDATED_SETE_P_ME_TRINTA);
        assertThat(testBlocos.getSetePMeQuarenta()).isEqualTo(UPDATED_SETE_P_ME_QUARENTA);
        assertThat(testBlocos.getSetePMeCinquenta()).isEqualTo(UPDATED_SETE_P_ME_CINQUENTA);
        assertThat(testBlocos.getOitoAM()).isEqualTo(UPDATED_OITO_AM);
        assertThat(testBlocos.getOitoAMeDez()).isEqualTo(UPDATED_OITO_A_ME_DEZ);
        assertThat(testBlocos.getOitoAMeVinte()).isEqualTo(UPDATED_OITO_A_ME_VINTE);
        assertThat(testBlocos.getOitoAMeTrinta()).isEqualTo(UPDATED_OITO_A_ME_TRINTA);
        assertThat(testBlocos.getOitoAMeQuarenta()).isEqualTo(UPDATED_OITO_A_ME_QUARENTA);
        assertThat(testBlocos.getOitoAMeCinquenta()).isEqualTo(UPDATED_OITO_A_ME_CINQUENTA);
        assertThat(testBlocos.getOitoPM()).isEqualTo(UPDATED_OITO_PM);
        assertThat(testBlocos.getOitoPMeDez()).isEqualTo(UPDATED_OITO_P_ME_DEZ);
        assertThat(testBlocos.getOitoPMeVinte()).isEqualTo(UPDATED_OITO_P_ME_VINTE);
        assertThat(testBlocos.getOitoPMeTrinta()).isEqualTo(UPDATED_OITO_P_ME_TRINTA);
        assertThat(testBlocos.getOitoPMeQuarenta()).isEqualTo(UPDATED_OITO_P_ME_QUARENTA);
        assertThat(testBlocos.getOitoPMeCinquenta()).isEqualTo(UPDATED_OITO_P_ME_CINQUENTA);
        assertThat(testBlocos.getNoveAM()).isEqualTo(UPDATED_NOVE_AM);
        assertThat(testBlocos.getNoveAMeDez()).isEqualTo(UPDATED_NOVE_A_ME_DEZ);
        assertThat(testBlocos.getNoveAMeVinte()).isEqualTo(UPDATED_NOVE_A_ME_VINTE);
        assertThat(testBlocos.getNoveAMeTrinta()).isEqualTo(UPDATED_NOVE_A_ME_TRINTA);
        assertThat(testBlocos.getNoveAMeQuarenta()).isEqualTo(UPDATED_NOVE_A_ME_QUARENTA);
        assertThat(testBlocos.getNoveAMeCinquenta()).isEqualTo(UPDATED_NOVE_A_ME_CINQUENTA);
        assertThat(testBlocos.getNovePM()).isEqualTo(UPDATED_NOVE_PM);
        assertThat(testBlocos.getNovePMeDez()).isEqualTo(UPDATED_NOVE_P_ME_DEZ);
        assertThat(testBlocos.getNovePMeVinte()).isEqualTo(UPDATED_NOVE_P_ME_VINTE);
        assertThat(testBlocos.getNovePMeTrinta()).isEqualTo(UPDATED_NOVE_P_ME_TRINTA);
        assertThat(testBlocos.getNovePMeQuarenta()).isEqualTo(UPDATED_NOVE_P_ME_QUARENTA);
        assertThat(testBlocos.getNovePMeCinquenta()).isEqualTo(UPDATED_NOVE_P_ME_CINQUENTA);
        assertThat(testBlocos.getDezAM()).isEqualTo(UPDATED_DEZ_AM);
        assertThat(testBlocos.getDezAMeDez()).isEqualTo(UPDATED_DEZ_A_ME_DEZ);
        assertThat(testBlocos.getDezAMeVinte()).isEqualTo(UPDATED_DEZ_A_ME_VINTE);
        assertThat(testBlocos.getDezAMeTrinta()).isEqualTo(UPDATED_DEZ_A_ME_TRINTA);
        assertThat(testBlocos.getDezAMeQuarenta()).isEqualTo(UPDATED_DEZ_A_ME_QUARENTA);
        assertThat(testBlocos.getDezAMeCinquenta()).isEqualTo(UPDATED_DEZ_A_ME_CINQUENTA);
        assertThat(testBlocos.getDezPM()).isEqualTo(UPDATED_DEZ_PM);
        assertThat(testBlocos.getDezPMeDez()).isEqualTo(UPDATED_DEZ_P_ME_DEZ);
        assertThat(testBlocos.getDezPMeVinte()).isEqualTo(UPDATED_DEZ_P_ME_VINTE);
        assertThat(testBlocos.getDezPMeTrinta()).isEqualTo(UPDATED_DEZ_P_ME_TRINTA);
        assertThat(testBlocos.getDezPMeQuarenta()).isEqualTo(UPDATED_DEZ_P_ME_QUARENTA);
        assertThat(testBlocos.getDezPMeCinquenta()).isEqualTo(UPDATED_DEZ_P_ME_CINQUENTA);
        assertThat(testBlocos.getOnzeAM()).isEqualTo(UPDATED_ONZE_AM);
        assertThat(testBlocos.getOnzeAMeDez()).isEqualTo(UPDATED_ONZE_A_ME_DEZ);
        assertThat(testBlocos.getOnzeAMeVinte()).isEqualTo(UPDATED_ONZE_A_ME_VINTE);
        assertThat(testBlocos.getOnzeAMeTrinta()).isEqualTo(UPDATED_ONZE_A_ME_TRINTA);
        assertThat(testBlocos.getOnzeAMeQuarenta()).isEqualTo(UPDATED_ONZE_A_ME_QUARENTA);
        assertThat(testBlocos.getOnzeAMeCinquenta()).isEqualTo(UPDATED_ONZE_A_ME_CINQUENTA);
        assertThat(testBlocos.getOnzePM()).isEqualTo(UPDATED_ONZE_PM);
        assertThat(testBlocos.getOnzePMeDez()).isEqualTo(UPDATED_ONZE_P_ME_DEZ);
        assertThat(testBlocos.getOnzePMeVinte()).isEqualTo(UPDATED_ONZE_P_ME_VINTE);
        assertThat(testBlocos.getOnzePMeTrinta()).isEqualTo(UPDATED_ONZE_P_ME_TRINTA);
        assertThat(testBlocos.getOnzePMeQuarenta()).isEqualTo(UPDATED_ONZE_P_ME_QUARENTA);
        assertThat(testBlocos.getOnzePMeCinquenta()).isEqualTo(UPDATED_ONZE_P_ME_CINQUENTA);
    }

    @Test
    @Transactional
    void patchNonExistingBlocos() throws Exception {
        int databaseSizeBeforeUpdate = blocosRepository.findAll().size();
        blocos.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBlocosMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, blocos.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(blocos))
            )
            .andExpect(status().isBadRequest());

        // Validate the Blocos in the database
        List<Blocos> blocosList = blocosRepository.findAll();
        assertThat(blocosList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchBlocos() throws Exception {
        int databaseSizeBeforeUpdate = blocosRepository.findAll().size();
        blocos.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBlocosMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(blocos))
            )
            .andExpect(status().isBadRequest());

        // Validate the Blocos in the database
        List<Blocos> blocosList = blocosRepository.findAll();
        assertThat(blocosList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamBlocos() throws Exception {
        int databaseSizeBeforeUpdate = blocosRepository.findAll().size();
        blocos.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBlocosMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(blocos)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Blocos in the database
        List<Blocos> blocosList = blocosRepository.findAll();
        assertThat(blocosList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteBlocos() throws Exception {
        // Initialize the database
        blocosRepository.saveAndFlush(blocos);

        int databaseSizeBeforeDelete = blocosRepository.findAll().size();

        // Delete the blocos
        restBlocosMockMvc
            .perform(delete(ENTITY_API_URL_ID, blocos.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Blocos> blocosList = blocosRepository.findAll();
        assertThat(blocosList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
