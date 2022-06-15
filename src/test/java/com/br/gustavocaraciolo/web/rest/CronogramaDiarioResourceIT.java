package com.br.gustavocaraciolo.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.br.gustavocaraciolo.IntegrationTest;
import com.br.gustavocaraciolo.domain.CronogramaDiario;
import com.br.gustavocaraciolo.repository.CronogramaDiarioRepository;
import java.time.LocalDate;
import java.time.ZoneId;
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

/**
 * Integration tests for the {@link CronogramaDiarioResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CronogramaDiarioResourceIT {

    private static final LocalDate DEFAULT_DIA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DIA = LocalDate.now(ZoneId.systemDefault());

    private static final String ENTITY_API_URL = "/api/cronograma-diarios";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private CronogramaDiarioRepository cronogramaDiarioRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCronogramaDiarioMockMvc;

    private CronogramaDiario cronogramaDiario;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CronogramaDiario createEntity(EntityManager em) {
        CronogramaDiario cronogramaDiario = new CronogramaDiario().dia(DEFAULT_DIA);
        return cronogramaDiario;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CronogramaDiario createUpdatedEntity(EntityManager em) {
        CronogramaDiario cronogramaDiario = new CronogramaDiario().dia(UPDATED_DIA);
        return cronogramaDiario;
    }

    @BeforeEach
    public void initTest() {
        cronogramaDiario = createEntity(em);
    }

    @Test
    @Transactional
    void createCronogramaDiario() throws Exception {
        int databaseSizeBeforeCreate = cronogramaDiarioRepository.findAll().size();
        // Create the CronogramaDiario
        restCronogramaDiarioMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(cronogramaDiario))
            )
            .andExpect(status().isCreated());

        // Validate the CronogramaDiario in the database
        List<CronogramaDiario> cronogramaDiarioList = cronogramaDiarioRepository.findAll();
        assertThat(cronogramaDiarioList).hasSize(databaseSizeBeforeCreate + 1);
        CronogramaDiario testCronogramaDiario = cronogramaDiarioList.get(cronogramaDiarioList.size() - 1);
        assertThat(testCronogramaDiario.getDia()).isEqualTo(DEFAULT_DIA);
    }

    @Test
    @Transactional
    void createCronogramaDiarioWithExistingId() throws Exception {
        // Create the CronogramaDiario with an existing ID
        cronogramaDiario.setId(1L);

        int databaseSizeBeforeCreate = cronogramaDiarioRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCronogramaDiarioMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(cronogramaDiario))
            )
            .andExpect(status().isBadRequest());

        // Validate the CronogramaDiario in the database
        List<CronogramaDiario> cronogramaDiarioList = cronogramaDiarioRepository.findAll();
        assertThat(cronogramaDiarioList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllCronogramaDiarios() throws Exception {
        // Initialize the database
        cronogramaDiarioRepository.saveAndFlush(cronogramaDiario);

        // Get all the cronogramaDiarioList
        restCronogramaDiarioMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cronogramaDiario.getId().intValue())))
            .andExpect(jsonPath("$.[*].dia").value(hasItem(DEFAULT_DIA.toString())));
    }

    @Test
    @Transactional
    void getCronogramaDiario() throws Exception {
        // Initialize the database
        cronogramaDiarioRepository.saveAndFlush(cronogramaDiario);

        // Get the cronogramaDiario
        restCronogramaDiarioMockMvc
            .perform(get(ENTITY_API_URL_ID, cronogramaDiario.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(cronogramaDiario.getId().intValue()))
            .andExpect(jsonPath("$.dia").value(DEFAULT_DIA.toString()));
    }

    @Test
    @Transactional
    void getNonExistingCronogramaDiario() throws Exception {
        // Get the cronogramaDiario
        restCronogramaDiarioMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewCronogramaDiario() throws Exception {
        // Initialize the database
        cronogramaDiarioRepository.saveAndFlush(cronogramaDiario);

        int databaseSizeBeforeUpdate = cronogramaDiarioRepository.findAll().size();

        // Update the cronogramaDiario
        CronogramaDiario updatedCronogramaDiario = cronogramaDiarioRepository.findById(cronogramaDiario.getId()).get();
        // Disconnect from session so that the updates on updatedCronogramaDiario are not directly saved in db
        em.detach(updatedCronogramaDiario);
        updatedCronogramaDiario.dia(UPDATED_DIA);

        restCronogramaDiarioMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedCronogramaDiario.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedCronogramaDiario))
            )
            .andExpect(status().isOk());

        // Validate the CronogramaDiario in the database
        List<CronogramaDiario> cronogramaDiarioList = cronogramaDiarioRepository.findAll();
        assertThat(cronogramaDiarioList).hasSize(databaseSizeBeforeUpdate);
        CronogramaDiario testCronogramaDiario = cronogramaDiarioList.get(cronogramaDiarioList.size() - 1);
        assertThat(testCronogramaDiario.getDia()).isEqualTo(UPDATED_DIA);
    }

    @Test
    @Transactional
    void putNonExistingCronogramaDiario() throws Exception {
        int databaseSizeBeforeUpdate = cronogramaDiarioRepository.findAll().size();
        cronogramaDiario.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCronogramaDiarioMockMvc
            .perform(
                put(ENTITY_API_URL_ID, cronogramaDiario.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(cronogramaDiario))
            )
            .andExpect(status().isBadRequest());

        // Validate the CronogramaDiario in the database
        List<CronogramaDiario> cronogramaDiarioList = cronogramaDiarioRepository.findAll();
        assertThat(cronogramaDiarioList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCronogramaDiario() throws Exception {
        int databaseSizeBeforeUpdate = cronogramaDiarioRepository.findAll().size();
        cronogramaDiario.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCronogramaDiarioMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(cronogramaDiario))
            )
            .andExpect(status().isBadRequest());

        // Validate the CronogramaDiario in the database
        List<CronogramaDiario> cronogramaDiarioList = cronogramaDiarioRepository.findAll();
        assertThat(cronogramaDiarioList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCronogramaDiario() throws Exception {
        int databaseSizeBeforeUpdate = cronogramaDiarioRepository.findAll().size();
        cronogramaDiario.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCronogramaDiarioMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(cronogramaDiario))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the CronogramaDiario in the database
        List<CronogramaDiario> cronogramaDiarioList = cronogramaDiarioRepository.findAll();
        assertThat(cronogramaDiarioList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCronogramaDiarioWithPatch() throws Exception {
        // Initialize the database
        cronogramaDiarioRepository.saveAndFlush(cronogramaDiario);

        int databaseSizeBeforeUpdate = cronogramaDiarioRepository.findAll().size();

        // Update the cronogramaDiario using partial update
        CronogramaDiario partialUpdatedCronogramaDiario = new CronogramaDiario();
        partialUpdatedCronogramaDiario.setId(cronogramaDiario.getId());

        restCronogramaDiarioMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCronogramaDiario.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCronogramaDiario))
            )
            .andExpect(status().isOk());

        // Validate the CronogramaDiario in the database
        List<CronogramaDiario> cronogramaDiarioList = cronogramaDiarioRepository.findAll();
        assertThat(cronogramaDiarioList).hasSize(databaseSizeBeforeUpdate);
        CronogramaDiario testCronogramaDiario = cronogramaDiarioList.get(cronogramaDiarioList.size() - 1);
        assertThat(testCronogramaDiario.getDia()).isEqualTo(DEFAULT_DIA);
    }

    @Test
    @Transactional
    void fullUpdateCronogramaDiarioWithPatch() throws Exception {
        // Initialize the database
        cronogramaDiarioRepository.saveAndFlush(cronogramaDiario);

        int databaseSizeBeforeUpdate = cronogramaDiarioRepository.findAll().size();

        // Update the cronogramaDiario using partial update
        CronogramaDiario partialUpdatedCronogramaDiario = new CronogramaDiario();
        partialUpdatedCronogramaDiario.setId(cronogramaDiario.getId());

        partialUpdatedCronogramaDiario.dia(UPDATED_DIA);

        restCronogramaDiarioMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCronogramaDiario.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCronogramaDiario))
            )
            .andExpect(status().isOk());

        // Validate the CronogramaDiario in the database
        List<CronogramaDiario> cronogramaDiarioList = cronogramaDiarioRepository.findAll();
        assertThat(cronogramaDiarioList).hasSize(databaseSizeBeforeUpdate);
        CronogramaDiario testCronogramaDiario = cronogramaDiarioList.get(cronogramaDiarioList.size() - 1);
        assertThat(testCronogramaDiario.getDia()).isEqualTo(UPDATED_DIA);
    }

    @Test
    @Transactional
    void patchNonExistingCronogramaDiario() throws Exception {
        int databaseSizeBeforeUpdate = cronogramaDiarioRepository.findAll().size();
        cronogramaDiario.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCronogramaDiarioMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, cronogramaDiario.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(cronogramaDiario))
            )
            .andExpect(status().isBadRequest());

        // Validate the CronogramaDiario in the database
        List<CronogramaDiario> cronogramaDiarioList = cronogramaDiarioRepository.findAll();
        assertThat(cronogramaDiarioList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCronogramaDiario() throws Exception {
        int databaseSizeBeforeUpdate = cronogramaDiarioRepository.findAll().size();
        cronogramaDiario.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCronogramaDiarioMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(cronogramaDiario))
            )
            .andExpect(status().isBadRequest());

        // Validate the CronogramaDiario in the database
        List<CronogramaDiario> cronogramaDiarioList = cronogramaDiarioRepository.findAll();
        assertThat(cronogramaDiarioList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCronogramaDiario() throws Exception {
        int databaseSizeBeforeUpdate = cronogramaDiarioRepository.findAll().size();
        cronogramaDiario.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCronogramaDiarioMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(cronogramaDiario))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the CronogramaDiario in the database
        List<CronogramaDiario> cronogramaDiarioList = cronogramaDiarioRepository.findAll();
        assertThat(cronogramaDiarioList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCronogramaDiario() throws Exception {
        // Initialize the database
        cronogramaDiarioRepository.saveAndFlush(cronogramaDiario);

        int databaseSizeBeforeDelete = cronogramaDiarioRepository.findAll().size();

        // Delete the cronogramaDiario
        restCronogramaDiarioMockMvc
            .perform(delete(ENTITY_API_URL_ID, cronogramaDiario.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CronogramaDiario> cronogramaDiarioList = cronogramaDiarioRepository.findAll();
        assertThat(cronogramaDiarioList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
