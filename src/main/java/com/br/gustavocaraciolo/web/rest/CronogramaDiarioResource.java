package com.br.gustavocaraciolo.web.rest;

import com.br.gustavocaraciolo.domain.CronogramaDiario;
import com.br.gustavocaraciolo.repository.CronogramaDiarioRepository;
import com.br.gustavocaraciolo.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.br.gustavocaraciolo.domain.CronogramaDiario}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class CronogramaDiarioResource {

    private final Logger log = LoggerFactory.getLogger(CronogramaDiarioResource.class);

    private static final String ENTITY_NAME = "cronogramaDiario";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CronogramaDiarioRepository cronogramaDiarioRepository;

    public CronogramaDiarioResource(CronogramaDiarioRepository cronogramaDiarioRepository) {
        this.cronogramaDiarioRepository = cronogramaDiarioRepository;
    }

    /**
     * {@code POST  /cronograma-diarios} : Create a new cronogramaDiario.
     *
     * @param cronogramaDiario the cronogramaDiario to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new cronogramaDiario, or with status {@code 400 (Bad Request)} if the cronogramaDiario has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/cronograma-diarios")
    public ResponseEntity<CronogramaDiario> createCronogramaDiario(@RequestBody CronogramaDiario cronogramaDiario)
        throws URISyntaxException {
        log.debug("REST request to save CronogramaDiario : {}", cronogramaDiario);
        if (cronogramaDiario.getId() != null) {
            throw new BadRequestAlertException("A new cronogramaDiario cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CronogramaDiario result = cronogramaDiarioRepository.save(cronogramaDiario);
        return ResponseEntity
            .created(new URI("/api/cronograma-diarios/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /cronograma-diarios/:id} : Updates an existing cronogramaDiario.
     *
     * @param id the id of the cronogramaDiario to save.
     * @param cronogramaDiario the cronogramaDiario to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cronogramaDiario,
     * or with status {@code 400 (Bad Request)} if the cronogramaDiario is not valid,
     * or with status {@code 500 (Internal Server Error)} if the cronogramaDiario couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/cronograma-diarios/{id}")
    public ResponseEntity<CronogramaDiario> updateCronogramaDiario(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CronogramaDiario cronogramaDiario
    ) throws URISyntaxException {
        log.debug("REST request to update CronogramaDiario : {}, {}", id, cronogramaDiario);
        if (cronogramaDiario.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, cronogramaDiario.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!cronogramaDiarioRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        CronogramaDiario result = cronogramaDiarioRepository.save(cronogramaDiario);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, cronogramaDiario.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /cronograma-diarios/:id} : Partial updates given fields of an existing cronogramaDiario, field will ignore if it is null
     *
     * @param id the id of the cronogramaDiario to save.
     * @param cronogramaDiario the cronogramaDiario to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cronogramaDiario,
     * or with status {@code 400 (Bad Request)} if the cronogramaDiario is not valid,
     * or with status {@code 404 (Not Found)} if the cronogramaDiario is not found,
     * or with status {@code 500 (Internal Server Error)} if the cronogramaDiario couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/cronograma-diarios/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<CronogramaDiario> partialUpdateCronogramaDiario(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CronogramaDiario cronogramaDiario
    ) throws URISyntaxException {
        log.debug("REST request to partial update CronogramaDiario partially : {}, {}", id, cronogramaDiario);
        if (cronogramaDiario.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, cronogramaDiario.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!cronogramaDiarioRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CronogramaDiario> result = cronogramaDiarioRepository
            .findById(cronogramaDiario.getId())
            .map(existingCronogramaDiario -> {
                if (cronogramaDiario.getDia() != null) {
                    existingCronogramaDiario.setDia(cronogramaDiario.getDia());
                }

                return existingCronogramaDiario;
            })
            .map(cronogramaDiarioRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, cronogramaDiario.getId().toString())
        );
    }

    /**
     * {@code GET  /cronograma-diarios} : get all the cronogramaDiarios.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cronogramaDiarios in body.
     */
    @GetMapping("/cronograma-diarios")
    public List<CronogramaDiario> getAllCronogramaDiarios() {
        log.debug("REST request to get all CronogramaDiarios");
        return cronogramaDiarioRepository.findAll();
    }

    /**
     * {@code GET  /cronograma-diarios/:id} : get the "id" cronogramaDiario.
     *
     * @param id the id of the cronogramaDiario to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cronogramaDiario, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/cronograma-diarios/{id}")
    public ResponseEntity<CronogramaDiario> getCronogramaDiario(@PathVariable Long id) {
        log.debug("REST request to get CronogramaDiario : {}", id);
        Optional<CronogramaDiario> cronogramaDiario = cronogramaDiarioRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(cronogramaDiario);
    }

    /**
     * {@code DELETE  /cronograma-diarios/:id} : delete the "id" cronogramaDiario.
     *
     * @param id the id of the cronogramaDiario to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/cronograma-diarios/{id}")
    public ResponseEntity<Void> deleteCronogramaDiario(@PathVariable Long id) {
        log.debug("REST request to delete CronogramaDiario : {}", id);
        cronogramaDiarioRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    @GetMapping("/cronograma-diarios/by-date/{date}")
    public ResponseEntity<CronogramaDiario> getCronogramaDiarioByDate(@PathVariable LocalDate date) {
        log.debug("REST request to get CronogramaDiario : {}", date);
        Optional<CronogramaDiario> cronogramaDiario = cronogramaDiarioRepository.findByDiaEquals(date);
        return ResponseUtil.wrapOrNotFound(cronogramaDiario);
    }
}
