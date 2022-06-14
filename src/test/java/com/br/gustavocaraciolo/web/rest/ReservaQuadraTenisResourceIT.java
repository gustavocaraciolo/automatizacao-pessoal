package com.br.gustavocaraciolo.web.rest;

import static com.br.gustavocaraciolo.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.br.gustavocaraciolo.IntegrationTest;
import com.br.gustavocaraciolo.domain.ReservaQuadraTenis;
import com.br.gustavocaraciolo.repository.ReservaQuadraTenisRepository;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;

/**
 * Integration tests for the {@link ReservaQuadraTenisResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ReservaQuadraTenisResourceIT {

    private static final String DEFAULT_EMAIL_DESTINO = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL_DESTINO = "BBBBBBBBBB";

    private static final String DEFAULT_TEMPLATE_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_TEMPLATE_EMAIL = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_SEMANA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_SEMANA = LocalDate.now(ZoneId.systemDefault());

    private static final ZonedDateTime DEFAULT_SEGUNDAFEIRA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_SEGUNDAFEIRA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_TERCAFEIRA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_TERCAFEIRA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_QUARTAFEIRA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_QUARTAFEIRA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_QUINTAFEIRA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_QUINTAFEIRA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_SEXTAFEIRA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_SEXTAFEIRA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_SABADO = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_SABADO = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DOMINGO = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DOMINGO = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Boolean DEFAULT_SEGUNDA_FEIRA_EMAIL_ENVIADO = false;
    private static final Boolean UPDATED_SEGUNDA_FEIRA_EMAIL_ENVIADO = true;

    private static final Boolean DEFAULT_TERCA_FEIRA_EMAIL_ENVIADO = false;
    private static final Boolean UPDATED_TERCA_FEIRA_EMAIL_ENVIADO = true;

    private static final Boolean DEFAULT_QUARTA_FEIRA_EMAIL_ENVIADO = false;
    private static final Boolean UPDATED_QUARTA_FEIRA_EMAIL_ENVIADO = true;

    private static final Boolean DEFAULT_QUINTA_FEIRA_EMAIL_ENVIADO = false;
    private static final Boolean UPDATED_QUINTA_FEIRA_EMAIL_ENVIADO = true;

    private static final Boolean DEFAULT_SEXTA_FEIRA_EMAIL_ENVIADO = false;
    private static final Boolean UPDATED_SEXTA_FEIRA_EMAIL_ENVIADO = true;

    private static final Boolean DEFAULT_SABADO_EMAIL_ENVIADO = false;
    private static final Boolean UPDATED_SABADO_EMAIL_ENVIADO = true;

    private static final Boolean DEFAULT_DOMINGO_EMAIL_ENVIADO = false;
    private static final Boolean UPDATED_DOMINGO_EMAIL_ENVIADO = true;

    private static final String ENTITY_API_URL = "/api/reserva-quadra-tenis";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ReservaQuadraTenisRepository reservaQuadraTenisRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restReservaQuadraTenisMockMvc;

    private ReservaQuadraTenis reservaQuadraTenis;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ReservaQuadraTenis createEntity(EntityManager em) {
        ReservaQuadraTenis reservaQuadraTenis = new ReservaQuadraTenis()
            .emailDestino(DEFAULT_EMAIL_DESTINO)
            .templateEmail(DEFAULT_TEMPLATE_EMAIL)
            .semana(DEFAULT_SEMANA)
            .segundafeira(DEFAULT_SEGUNDAFEIRA)
            .tercafeira(DEFAULT_TERCAFEIRA)
            .quartafeira(DEFAULT_QUARTAFEIRA)
            .quintafeira(DEFAULT_QUINTAFEIRA)
            .sextafeira(DEFAULT_SEXTAFEIRA)
            .sabado(DEFAULT_SABADO)
            .domingo(DEFAULT_DOMINGO)
            .segundaFeiraEmailEnviado(DEFAULT_SEGUNDA_FEIRA_EMAIL_ENVIADO)
            .tercaFeiraEmailEnviado(DEFAULT_TERCA_FEIRA_EMAIL_ENVIADO)
            .quartaFeiraEmailEnviado(DEFAULT_QUARTA_FEIRA_EMAIL_ENVIADO)
            .quintaFeiraEmailEnviado(DEFAULT_QUINTA_FEIRA_EMAIL_ENVIADO)
            .sextaFeiraEmailEnviado(DEFAULT_SEXTA_FEIRA_EMAIL_ENVIADO)
            .sabadoEmailEnviado(DEFAULT_SABADO_EMAIL_ENVIADO)
            .domingoEmailEnviado(DEFAULT_DOMINGO_EMAIL_ENVIADO);
        return reservaQuadraTenis;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ReservaQuadraTenis createUpdatedEntity(EntityManager em) {
        ReservaQuadraTenis reservaQuadraTenis = new ReservaQuadraTenis()
            .emailDestino(UPDATED_EMAIL_DESTINO)
            .templateEmail(UPDATED_TEMPLATE_EMAIL)
            .semana(UPDATED_SEMANA)
            .segundafeira(UPDATED_SEGUNDAFEIRA)
            .tercafeira(UPDATED_TERCAFEIRA)
            .quartafeira(UPDATED_QUARTAFEIRA)
            .quintafeira(UPDATED_QUINTAFEIRA)
            .sextafeira(UPDATED_SEXTAFEIRA)
            .sabado(UPDATED_SABADO)
            .domingo(UPDATED_DOMINGO)
            .segundaFeiraEmailEnviado(UPDATED_SEGUNDA_FEIRA_EMAIL_ENVIADO)
            .tercaFeiraEmailEnviado(UPDATED_TERCA_FEIRA_EMAIL_ENVIADO)
            .quartaFeiraEmailEnviado(UPDATED_QUARTA_FEIRA_EMAIL_ENVIADO)
            .quintaFeiraEmailEnviado(UPDATED_QUINTA_FEIRA_EMAIL_ENVIADO)
            .sextaFeiraEmailEnviado(UPDATED_SEXTA_FEIRA_EMAIL_ENVIADO)
            .sabadoEmailEnviado(UPDATED_SABADO_EMAIL_ENVIADO)
            .domingoEmailEnviado(UPDATED_DOMINGO_EMAIL_ENVIADO);
        return reservaQuadraTenis;
    }

    @BeforeEach
    public void initTest() {
        reservaQuadraTenis = createEntity(em);
    }

    @Test
    @Transactional
    void createReservaQuadraTenis() throws Exception {
        int databaseSizeBeforeCreate = reservaQuadraTenisRepository.findAll().size();
        // Create the ReservaQuadraTenis
        restReservaQuadraTenisMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(reservaQuadraTenis))
            )
            .andExpect(status().isCreated());

        // Validate the ReservaQuadraTenis in the database
        List<ReservaQuadraTenis> reservaQuadraTenisList = reservaQuadraTenisRepository.findAll();
        assertThat(reservaQuadraTenisList).hasSize(databaseSizeBeforeCreate + 1);
        ReservaQuadraTenis testReservaQuadraTenis = reservaQuadraTenisList.get(reservaQuadraTenisList.size() - 1);
        assertThat(testReservaQuadraTenis.getEmailDestino()).isEqualTo(DEFAULT_EMAIL_DESTINO);
        assertThat(testReservaQuadraTenis.getTemplateEmail()).isEqualTo(DEFAULT_TEMPLATE_EMAIL);
        assertThat(testReservaQuadraTenis.getSemana()).isEqualTo(DEFAULT_SEMANA);
        assertThat(testReservaQuadraTenis.getSegundafeira()).isEqualTo(DEFAULT_SEGUNDAFEIRA);
        assertThat(testReservaQuadraTenis.getTercafeira()).isEqualTo(DEFAULT_TERCAFEIRA);
        assertThat(testReservaQuadraTenis.getQuartafeira()).isEqualTo(DEFAULT_QUARTAFEIRA);
        assertThat(testReservaQuadraTenis.getQuintafeira()).isEqualTo(DEFAULT_QUINTAFEIRA);
        assertThat(testReservaQuadraTenis.getSextafeira()).isEqualTo(DEFAULT_SEXTAFEIRA);
        assertThat(testReservaQuadraTenis.getSabado()).isEqualTo(DEFAULT_SABADO);
        assertThat(testReservaQuadraTenis.getDomingo()).isEqualTo(DEFAULT_DOMINGO);
        assertThat(testReservaQuadraTenis.getSegundaFeiraEmailEnviado()).isEqualTo(DEFAULT_SEGUNDA_FEIRA_EMAIL_ENVIADO);
        assertThat(testReservaQuadraTenis.getTercaFeiraEmailEnviado()).isEqualTo(DEFAULT_TERCA_FEIRA_EMAIL_ENVIADO);
        assertThat(testReservaQuadraTenis.getQuartaFeiraEmailEnviado()).isEqualTo(DEFAULT_QUARTA_FEIRA_EMAIL_ENVIADO);
        assertThat(testReservaQuadraTenis.getQuintaFeiraEmailEnviado()).isEqualTo(DEFAULT_QUINTA_FEIRA_EMAIL_ENVIADO);
        assertThat(testReservaQuadraTenis.getSextaFeiraEmailEnviado()).isEqualTo(DEFAULT_SEXTA_FEIRA_EMAIL_ENVIADO);
        assertThat(testReservaQuadraTenis.getSabadoEmailEnviado()).isEqualTo(DEFAULT_SABADO_EMAIL_ENVIADO);
        assertThat(testReservaQuadraTenis.getDomingoEmailEnviado()).isEqualTo(DEFAULT_DOMINGO_EMAIL_ENVIADO);
    }

    @Test
    @Transactional
    void createReservaQuadraTenisWithExistingId() throws Exception {
        // Create the ReservaQuadraTenis with an existing ID
        reservaQuadraTenis.setId(1L);

        int databaseSizeBeforeCreate = reservaQuadraTenisRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restReservaQuadraTenisMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(reservaQuadraTenis))
            )
            .andExpect(status().isBadRequest());

        // Validate the ReservaQuadraTenis in the database
        List<ReservaQuadraTenis> reservaQuadraTenisList = reservaQuadraTenisRepository.findAll();
        assertThat(reservaQuadraTenisList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkEmailDestinoIsRequired() throws Exception {
        int databaseSizeBeforeTest = reservaQuadraTenisRepository.findAll().size();
        // set the field null
        reservaQuadraTenis.setEmailDestino(null);

        // Create the ReservaQuadraTenis, which fails.

        restReservaQuadraTenisMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(reservaQuadraTenis))
            )
            .andExpect(status().isBadRequest());

        List<ReservaQuadraTenis> reservaQuadraTenisList = reservaQuadraTenisRepository.findAll();
        assertThat(reservaQuadraTenisList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllReservaQuadraTenis() throws Exception {
        // Initialize the database
        reservaQuadraTenisRepository.saveAndFlush(reservaQuadraTenis);

        // Get all the reservaQuadraTenisList
        restReservaQuadraTenisMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(reservaQuadraTenis.getId().intValue())))
            .andExpect(jsonPath("$.[*].emailDestino").value(hasItem(DEFAULT_EMAIL_DESTINO)))
            .andExpect(jsonPath("$.[*].templateEmail").value(hasItem(DEFAULT_TEMPLATE_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].semana").value(hasItem(DEFAULT_SEMANA.toString())))
            .andExpect(jsonPath("$.[*].segundafeira").value(hasItem(sameInstant(DEFAULT_SEGUNDAFEIRA))))
            .andExpect(jsonPath("$.[*].tercafeira").value(hasItem(sameInstant(DEFAULT_TERCAFEIRA))))
            .andExpect(jsonPath("$.[*].quartafeira").value(hasItem(sameInstant(DEFAULT_QUARTAFEIRA))))
            .andExpect(jsonPath("$.[*].quintafeira").value(hasItem(sameInstant(DEFAULT_QUINTAFEIRA))))
            .andExpect(jsonPath("$.[*].sextafeira").value(hasItem(sameInstant(DEFAULT_SEXTAFEIRA))))
            .andExpect(jsonPath("$.[*].sabado").value(hasItem(sameInstant(DEFAULT_SABADO))))
            .andExpect(jsonPath("$.[*].domingo").value(hasItem(sameInstant(DEFAULT_DOMINGO))))
            .andExpect(jsonPath("$.[*].segundaFeiraEmailEnviado").value(hasItem(DEFAULT_SEGUNDA_FEIRA_EMAIL_ENVIADO.booleanValue())))
            .andExpect(jsonPath("$.[*].tercaFeiraEmailEnviado").value(hasItem(DEFAULT_TERCA_FEIRA_EMAIL_ENVIADO.booleanValue())))
            .andExpect(jsonPath("$.[*].quartaFeiraEmailEnviado").value(hasItem(DEFAULT_QUARTA_FEIRA_EMAIL_ENVIADO.booleanValue())))
            .andExpect(jsonPath("$.[*].quintaFeiraEmailEnviado").value(hasItem(DEFAULT_QUINTA_FEIRA_EMAIL_ENVIADO.booleanValue())))
            .andExpect(jsonPath("$.[*].sextaFeiraEmailEnviado").value(hasItem(DEFAULT_SEXTA_FEIRA_EMAIL_ENVIADO.booleanValue())))
            .andExpect(jsonPath("$.[*].sabadoEmailEnviado").value(hasItem(DEFAULT_SABADO_EMAIL_ENVIADO.booleanValue())))
            .andExpect(jsonPath("$.[*].domingoEmailEnviado").value(hasItem(DEFAULT_DOMINGO_EMAIL_ENVIADO.booleanValue())));
    }

    @Test
    @Transactional
    void getReservaQuadraTenis() throws Exception {
        // Initialize the database
        reservaQuadraTenisRepository.saveAndFlush(reservaQuadraTenis);

        // Get the reservaQuadraTenis
        restReservaQuadraTenisMockMvc
            .perform(get(ENTITY_API_URL_ID, reservaQuadraTenis.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(reservaQuadraTenis.getId().intValue()))
            .andExpect(jsonPath("$.emailDestino").value(DEFAULT_EMAIL_DESTINO))
            .andExpect(jsonPath("$.templateEmail").value(DEFAULT_TEMPLATE_EMAIL.toString()))
            .andExpect(jsonPath("$.semana").value(DEFAULT_SEMANA.toString()))
            .andExpect(jsonPath("$.segundafeira").value(sameInstant(DEFAULT_SEGUNDAFEIRA)))
            .andExpect(jsonPath("$.tercafeira").value(sameInstant(DEFAULT_TERCAFEIRA)))
            .andExpect(jsonPath("$.quartafeira").value(sameInstant(DEFAULT_QUARTAFEIRA)))
            .andExpect(jsonPath("$.quintafeira").value(sameInstant(DEFAULT_QUINTAFEIRA)))
            .andExpect(jsonPath("$.sextafeira").value(sameInstant(DEFAULT_SEXTAFEIRA)))
            .andExpect(jsonPath("$.sabado").value(sameInstant(DEFAULT_SABADO)))
            .andExpect(jsonPath("$.domingo").value(sameInstant(DEFAULT_DOMINGO)))
            .andExpect(jsonPath("$.segundaFeiraEmailEnviado").value(DEFAULT_SEGUNDA_FEIRA_EMAIL_ENVIADO.booleanValue()))
            .andExpect(jsonPath("$.tercaFeiraEmailEnviado").value(DEFAULT_TERCA_FEIRA_EMAIL_ENVIADO.booleanValue()))
            .andExpect(jsonPath("$.quartaFeiraEmailEnviado").value(DEFAULT_QUARTA_FEIRA_EMAIL_ENVIADO.booleanValue()))
            .andExpect(jsonPath("$.quintaFeiraEmailEnviado").value(DEFAULT_QUINTA_FEIRA_EMAIL_ENVIADO.booleanValue()))
            .andExpect(jsonPath("$.sextaFeiraEmailEnviado").value(DEFAULT_SEXTA_FEIRA_EMAIL_ENVIADO.booleanValue()))
            .andExpect(jsonPath("$.sabadoEmailEnviado").value(DEFAULT_SABADO_EMAIL_ENVIADO.booleanValue()))
            .andExpect(jsonPath("$.domingoEmailEnviado").value(DEFAULT_DOMINGO_EMAIL_ENVIADO.booleanValue()));
    }

    @Test
    @Transactional
    void getNonExistingReservaQuadraTenis() throws Exception {
        // Get the reservaQuadraTenis
        restReservaQuadraTenisMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewReservaQuadraTenis() throws Exception {
        // Initialize the database
        reservaQuadraTenisRepository.saveAndFlush(reservaQuadraTenis);

        int databaseSizeBeforeUpdate = reservaQuadraTenisRepository.findAll().size();

        // Update the reservaQuadraTenis
        ReservaQuadraTenis updatedReservaQuadraTenis = reservaQuadraTenisRepository.findById(reservaQuadraTenis.getId()).get();
        // Disconnect from session so that the updates on updatedReservaQuadraTenis are not directly saved in db
        em.detach(updatedReservaQuadraTenis);
        updatedReservaQuadraTenis
            .emailDestino(UPDATED_EMAIL_DESTINO)
            .templateEmail(UPDATED_TEMPLATE_EMAIL)
            .semana(UPDATED_SEMANA)
            .segundafeira(UPDATED_SEGUNDAFEIRA)
            .tercafeira(UPDATED_TERCAFEIRA)
            .quartafeira(UPDATED_QUARTAFEIRA)
            .quintafeira(UPDATED_QUINTAFEIRA)
            .sextafeira(UPDATED_SEXTAFEIRA)
            .sabado(UPDATED_SABADO)
            .domingo(UPDATED_DOMINGO)
            .segundaFeiraEmailEnviado(UPDATED_SEGUNDA_FEIRA_EMAIL_ENVIADO)
            .tercaFeiraEmailEnviado(UPDATED_TERCA_FEIRA_EMAIL_ENVIADO)
            .quartaFeiraEmailEnviado(UPDATED_QUARTA_FEIRA_EMAIL_ENVIADO)
            .quintaFeiraEmailEnviado(UPDATED_QUINTA_FEIRA_EMAIL_ENVIADO)
            .sextaFeiraEmailEnviado(UPDATED_SEXTA_FEIRA_EMAIL_ENVIADO)
            .sabadoEmailEnviado(UPDATED_SABADO_EMAIL_ENVIADO)
            .domingoEmailEnviado(UPDATED_DOMINGO_EMAIL_ENVIADO);

        restReservaQuadraTenisMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedReservaQuadraTenis.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedReservaQuadraTenis))
            )
            .andExpect(status().isOk());

        // Validate the ReservaQuadraTenis in the database
        List<ReservaQuadraTenis> reservaQuadraTenisList = reservaQuadraTenisRepository.findAll();
        assertThat(reservaQuadraTenisList).hasSize(databaseSizeBeforeUpdate);
        ReservaQuadraTenis testReservaQuadraTenis = reservaQuadraTenisList.get(reservaQuadraTenisList.size() - 1);
        assertThat(testReservaQuadraTenis.getEmailDestino()).isEqualTo(UPDATED_EMAIL_DESTINO);
        assertThat(testReservaQuadraTenis.getTemplateEmail()).isEqualTo(UPDATED_TEMPLATE_EMAIL);
        assertThat(testReservaQuadraTenis.getSemana()).isEqualTo(UPDATED_SEMANA);
        assertThat(testReservaQuadraTenis.getSegundafeira()).isEqualTo(UPDATED_SEGUNDAFEIRA);
        assertThat(testReservaQuadraTenis.getTercafeira()).isEqualTo(UPDATED_TERCAFEIRA);
        assertThat(testReservaQuadraTenis.getQuartafeira()).isEqualTo(UPDATED_QUARTAFEIRA);
        assertThat(testReservaQuadraTenis.getQuintafeira()).isEqualTo(UPDATED_QUINTAFEIRA);
        assertThat(testReservaQuadraTenis.getSextafeira()).isEqualTo(UPDATED_SEXTAFEIRA);
        assertThat(testReservaQuadraTenis.getSabado()).isEqualTo(UPDATED_SABADO);
        assertThat(testReservaQuadraTenis.getDomingo()).isEqualTo(UPDATED_DOMINGO);
        assertThat(testReservaQuadraTenis.getSegundaFeiraEmailEnviado()).isEqualTo(UPDATED_SEGUNDA_FEIRA_EMAIL_ENVIADO);
        assertThat(testReservaQuadraTenis.getTercaFeiraEmailEnviado()).isEqualTo(UPDATED_TERCA_FEIRA_EMAIL_ENVIADO);
        assertThat(testReservaQuadraTenis.getQuartaFeiraEmailEnviado()).isEqualTo(UPDATED_QUARTA_FEIRA_EMAIL_ENVIADO);
        assertThat(testReservaQuadraTenis.getQuintaFeiraEmailEnviado()).isEqualTo(UPDATED_QUINTA_FEIRA_EMAIL_ENVIADO);
        assertThat(testReservaQuadraTenis.getSextaFeiraEmailEnviado()).isEqualTo(UPDATED_SEXTA_FEIRA_EMAIL_ENVIADO);
        assertThat(testReservaQuadraTenis.getSabadoEmailEnviado()).isEqualTo(UPDATED_SABADO_EMAIL_ENVIADO);
        assertThat(testReservaQuadraTenis.getDomingoEmailEnviado()).isEqualTo(UPDATED_DOMINGO_EMAIL_ENVIADO);
    }

    @Test
    @Transactional
    void putNonExistingReservaQuadraTenis() throws Exception {
        int databaseSizeBeforeUpdate = reservaQuadraTenisRepository.findAll().size();
        reservaQuadraTenis.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restReservaQuadraTenisMockMvc
            .perform(
                put(ENTITY_API_URL_ID, reservaQuadraTenis.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(reservaQuadraTenis))
            )
            .andExpect(status().isBadRequest());

        // Validate the ReservaQuadraTenis in the database
        List<ReservaQuadraTenis> reservaQuadraTenisList = reservaQuadraTenisRepository.findAll();
        assertThat(reservaQuadraTenisList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchReservaQuadraTenis() throws Exception {
        int databaseSizeBeforeUpdate = reservaQuadraTenisRepository.findAll().size();
        reservaQuadraTenis.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restReservaQuadraTenisMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(reservaQuadraTenis))
            )
            .andExpect(status().isBadRequest());

        // Validate the ReservaQuadraTenis in the database
        List<ReservaQuadraTenis> reservaQuadraTenisList = reservaQuadraTenisRepository.findAll();
        assertThat(reservaQuadraTenisList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamReservaQuadraTenis() throws Exception {
        int databaseSizeBeforeUpdate = reservaQuadraTenisRepository.findAll().size();
        reservaQuadraTenis.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restReservaQuadraTenisMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(reservaQuadraTenis))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ReservaQuadraTenis in the database
        List<ReservaQuadraTenis> reservaQuadraTenisList = reservaQuadraTenisRepository.findAll();
        assertThat(reservaQuadraTenisList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateReservaQuadraTenisWithPatch() throws Exception {
        // Initialize the database
        reservaQuadraTenisRepository.saveAndFlush(reservaQuadraTenis);

        int databaseSizeBeforeUpdate = reservaQuadraTenisRepository.findAll().size();

        // Update the reservaQuadraTenis using partial update
        ReservaQuadraTenis partialUpdatedReservaQuadraTenis = new ReservaQuadraTenis();
        partialUpdatedReservaQuadraTenis.setId(reservaQuadraTenis.getId());

        partialUpdatedReservaQuadraTenis
            .semana(UPDATED_SEMANA)
            .tercaFeiraEmailEnviado(UPDATED_TERCA_FEIRA_EMAIL_ENVIADO)
            .quartaFeiraEmailEnviado(UPDATED_QUARTA_FEIRA_EMAIL_ENVIADO)
            .quintaFeiraEmailEnviado(UPDATED_QUINTA_FEIRA_EMAIL_ENVIADO)
            .sabadoEmailEnviado(UPDATED_SABADO_EMAIL_ENVIADO)
            .domingoEmailEnviado(UPDATED_DOMINGO_EMAIL_ENVIADO);

        restReservaQuadraTenisMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedReservaQuadraTenis.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedReservaQuadraTenis))
            )
            .andExpect(status().isOk());

        // Validate the ReservaQuadraTenis in the database
        List<ReservaQuadraTenis> reservaQuadraTenisList = reservaQuadraTenisRepository.findAll();
        assertThat(reservaQuadraTenisList).hasSize(databaseSizeBeforeUpdate);
        ReservaQuadraTenis testReservaQuadraTenis = reservaQuadraTenisList.get(reservaQuadraTenisList.size() - 1);
        assertThat(testReservaQuadraTenis.getEmailDestino()).isEqualTo(DEFAULT_EMAIL_DESTINO);
        assertThat(testReservaQuadraTenis.getTemplateEmail()).isEqualTo(DEFAULT_TEMPLATE_EMAIL);
        assertThat(testReservaQuadraTenis.getSemana()).isEqualTo(UPDATED_SEMANA);
        assertThat(testReservaQuadraTenis.getSegundafeira()).isEqualTo(DEFAULT_SEGUNDAFEIRA);
        assertThat(testReservaQuadraTenis.getTercafeira()).isEqualTo(DEFAULT_TERCAFEIRA);
        assertThat(testReservaQuadraTenis.getQuartafeira()).isEqualTo(DEFAULT_QUARTAFEIRA);
        assertThat(testReservaQuadraTenis.getQuintafeira()).isEqualTo(DEFAULT_QUINTAFEIRA);
        assertThat(testReservaQuadraTenis.getSextafeira()).isEqualTo(DEFAULT_SEXTAFEIRA);
        assertThat(testReservaQuadraTenis.getSabado()).isEqualTo(DEFAULT_SABADO);
        assertThat(testReservaQuadraTenis.getDomingo()).isEqualTo(DEFAULT_DOMINGO);
        assertThat(testReservaQuadraTenis.getSegundaFeiraEmailEnviado()).isEqualTo(DEFAULT_SEGUNDA_FEIRA_EMAIL_ENVIADO);
        assertThat(testReservaQuadraTenis.getTercaFeiraEmailEnviado()).isEqualTo(UPDATED_TERCA_FEIRA_EMAIL_ENVIADO);
        assertThat(testReservaQuadraTenis.getQuartaFeiraEmailEnviado()).isEqualTo(UPDATED_QUARTA_FEIRA_EMAIL_ENVIADO);
        assertThat(testReservaQuadraTenis.getQuintaFeiraEmailEnviado()).isEqualTo(UPDATED_QUINTA_FEIRA_EMAIL_ENVIADO);
        assertThat(testReservaQuadraTenis.getSextaFeiraEmailEnviado()).isEqualTo(DEFAULT_SEXTA_FEIRA_EMAIL_ENVIADO);
        assertThat(testReservaQuadraTenis.getSabadoEmailEnviado()).isEqualTo(UPDATED_SABADO_EMAIL_ENVIADO);
        assertThat(testReservaQuadraTenis.getDomingoEmailEnviado()).isEqualTo(UPDATED_DOMINGO_EMAIL_ENVIADO);
    }

    @Test
    @Transactional
    void fullUpdateReservaQuadraTenisWithPatch() throws Exception {
        // Initialize the database
        reservaQuadraTenisRepository.saveAndFlush(reservaQuadraTenis);

        int databaseSizeBeforeUpdate = reservaQuadraTenisRepository.findAll().size();

        // Update the reservaQuadraTenis using partial update
        ReservaQuadraTenis partialUpdatedReservaQuadraTenis = new ReservaQuadraTenis();
        partialUpdatedReservaQuadraTenis.setId(reservaQuadraTenis.getId());

        partialUpdatedReservaQuadraTenis
            .emailDestino(UPDATED_EMAIL_DESTINO)
            .templateEmail(UPDATED_TEMPLATE_EMAIL)
            .semana(UPDATED_SEMANA)
            .segundafeira(UPDATED_SEGUNDAFEIRA)
            .tercafeira(UPDATED_TERCAFEIRA)
            .quartafeira(UPDATED_QUARTAFEIRA)
            .quintafeira(UPDATED_QUINTAFEIRA)
            .sextafeira(UPDATED_SEXTAFEIRA)
            .sabado(UPDATED_SABADO)
            .domingo(UPDATED_DOMINGO)
            .segundaFeiraEmailEnviado(UPDATED_SEGUNDA_FEIRA_EMAIL_ENVIADO)
            .tercaFeiraEmailEnviado(UPDATED_TERCA_FEIRA_EMAIL_ENVIADO)
            .quartaFeiraEmailEnviado(UPDATED_QUARTA_FEIRA_EMAIL_ENVIADO)
            .quintaFeiraEmailEnviado(UPDATED_QUINTA_FEIRA_EMAIL_ENVIADO)
            .sextaFeiraEmailEnviado(UPDATED_SEXTA_FEIRA_EMAIL_ENVIADO)
            .sabadoEmailEnviado(UPDATED_SABADO_EMAIL_ENVIADO)
            .domingoEmailEnviado(UPDATED_DOMINGO_EMAIL_ENVIADO);

        restReservaQuadraTenisMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedReservaQuadraTenis.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedReservaQuadraTenis))
            )
            .andExpect(status().isOk());

        // Validate the ReservaQuadraTenis in the database
        List<ReservaQuadraTenis> reservaQuadraTenisList = reservaQuadraTenisRepository.findAll();
        assertThat(reservaQuadraTenisList).hasSize(databaseSizeBeforeUpdate);
        ReservaQuadraTenis testReservaQuadraTenis = reservaQuadraTenisList.get(reservaQuadraTenisList.size() - 1);
        assertThat(testReservaQuadraTenis.getEmailDestino()).isEqualTo(UPDATED_EMAIL_DESTINO);
        assertThat(testReservaQuadraTenis.getTemplateEmail()).isEqualTo(UPDATED_TEMPLATE_EMAIL);
        assertThat(testReservaQuadraTenis.getSemana()).isEqualTo(UPDATED_SEMANA);
        assertThat(testReservaQuadraTenis.getSegundafeira()).isEqualTo(UPDATED_SEGUNDAFEIRA);
        assertThat(testReservaQuadraTenis.getTercafeira()).isEqualTo(UPDATED_TERCAFEIRA);
        assertThat(testReservaQuadraTenis.getQuartafeira()).isEqualTo(UPDATED_QUARTAFEIRA);
        assertThat(testReservaQuadraTenis.getQuintafeira()).isEqualTo(UPDATED_QUINTAFEIRA);
        assertThat(testReservaQuadraTenis.getSextafeira()).isEqualTo(UPDATED_SEXTAFEIRA);
        assertThat(testReservaQuadraTenis.getSabado()).isEqualTo(UPDATED_SABADO);
        assertThat(testReservaQuadraTenis.getDomingo()).isEqualTo(UPDATED_DOMINGO);
        assertThat(testReservaQuadraTenis.getSegundaFeiraEmailEnviado()).isEqualTo(UPDATED_SEGUNDA_FEIRA_EMAIL_ENVIADO);
        assertThat(testReservaQuadraTenis.getTercaFeiraEmailEnviado()).isEqualTo(UPDATED_TERCA_FEIRA_EMAIL_ENVIADO);
        assertThat(testReservaQuadraTenis.getQuartaFeiraEmailEnviado()).isEqualTo(UPDATED_QUARTA_FEIRA_EMAIL_ENVIADO);
        assertThat(testReservaQuadraTenis.getQuintaFeiraEmailEnviado()).isEqualTo(UPDATED_QUINTA_FEIRA_EMAIL_ENVIADO);
        assertThat(testReservaQuadraTenis.getSextaFeiraEmailEnviado()).isEqualTo(UPDATED_SEXTA_FEIRA_EMAIL_ENVIADO);
        assertThat(testReservaQuadraTenis.getSabadoEmailEnviado()).isEqualTo(UPDATED_SABADO_EMAIL_ENVIADO);
        assertThat(testReservaQuadraTenis.getDomingoEmailEnviado()).isEqualTo(UPDATED_DOMINGO_EMAIL_ENVIADO);
    }

    @Test
    @Transactional
    void patchNonExistingReservaQuadraTenis() throws Exception {
        int databaseSizeBeforeUpdate = reservaQuadraTenisRepository.findAll().size();
        reservaQuadraTenis.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restReservaQuadraTenisMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, reservaQuadraTenis.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(reservaQuadraTenis))
            )
            .andExpect(status().isBadRequest());

        // Validate the ReservaQuadraTenis in the database
        List<ReservaQuadraTenis> reservaQuadraTenisList = reservaQuadraTenisRepository.findAll();
        assertThat(reservaQuadraTenisList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchReservaQuadraTenis() throws Exception {
        int databaseSizeBeforeUpdate = reservaQuadraTenisRepository.findAll().size();
        reservaQuadraTenis.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restReservaQuadraTenisMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(reservaQuadraTenis))
            )
            .andExpect(status().isBadRequest());

        // Validate the ReservaQuadraTenis in the database
        List<ReservaQuadraTenis> reservaQuadraTenisList = reservaQuadraTenisRepository.findAll();
        assertThat(reservaQuadraTenisList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamReservaQuadraTenis() throws Exception {
        int databaseSizeBeforeUpdate = reservaQuadraTenisRepository.findAll().size();
        reservaQuadraTenis.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restReservaQuadraTenisMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(reservaQuadraTenis))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ReservaQuadraTenis in the database
        List<ReservaQuadraTenis> reservaQuadraTenisList = reservaQuadraTenisRepository.findAll();
        assertThat(reservaQuadraTenisList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteReservaQuadraTenis() throws Exception {
        // Initialize the database
        reservaQuadraTenisRepository.saveAndFlush(reservaQuadraTenis);

        int databaseSizeBeforeDelete = reservaQuadraTenisRepository.findAll().size();

        // Delete the reservaQuadraTenis
        restReservaQuadraTenisMockMvc
            .perform(delete(ENTITY_API_URL_ID, reservaQuadraTenis.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ReservaQuadraTenis> reservaQuadraTenisList = reservaQuadraTenisRepository.findAll();
        assertThat(reservaQuadraTenisList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
