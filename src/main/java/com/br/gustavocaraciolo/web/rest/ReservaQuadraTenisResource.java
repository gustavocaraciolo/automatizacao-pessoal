package com.br.gustavocaraciolo.web.rest;

import com.br.gustavocaraciolo.domain.ReservaQuadraTenis;
import com.br.gustavocaraciolo.repository.ReservaQuadraTenisRepository;
import com.br.gustavocaraciolo.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.br.gustavocaraciolo.domain.ReservaQuadraTenis}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ReservaQuadraTenisResource {

    private final Logger log = LoggerFactory.getLogger(ReservaQuadraTenisResource.class);

    private static final String ENTITY_NAME = "reservaQuadraTenis";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ReservaQuadraTenisRepository reservaQuadraTenisRepository;

    public ReservaQuadraTenisResource(ReservaQuadraTenisRepository reservaQuadraTenisRepository) {
        this.reservaQuadraTenisRepository = reservaQuadraTenisRepository;
    }

    /**
     * {@code POST  /reserva-quadra-tenis} : Create a new reservaQuadraTenis.
     *
     * @param reservaQuadraTenis the reservaQuadraTenis to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new reservaQuadraTenis, or with status {@code 400 (Bad Request)} if the reservaQuadraTenis has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/reserva-quadra-tenis")
    public ResponseEntity<ReservaQuadraTenis> createReservaQuadraTenis(@Valid @RequestBody ReservaQuadraTenis reservaQuadraTenis)
        throws URISyntaxException {
        log.debug("REST request to save ReservaQuadraTenis : {}", reservaQuadraTenis);
        if (reservaQuadraTenis.getId() != null) {
            throw new BadRequestAlertException("A new reservaQuadraTenis cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ReservaQuadraTenis result = reservaQuadraTenisRepository.save(reservaQuadraTenis);
        return ResponseEntity
            .created(new URI("/api/reserva-quadra-tenis/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /reserva-quadra-tenis/:id} : Updates an existing reservaQuadraTenis.
     *
     * @param id the id of the reservaQuadraTenis to save.
     * @param reservaQuadraTenis the reservaQuadraTenis to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated reservaQuadraTenis,
     * or with status {@code 400 (Bad Request)} if the reservaQuadraTenis is not valid,
     * or with status {@code 500 (Internal Server Error)} if the reservaQuadraTenis couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/reserva-quadra-tenis/{id}")
    public ResponseEntity<ReservaQuadraTenis> updateReservaQuadraTenis(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody ReservaQuadraTenis reservaQuadraTenis
    ) throws URISyntaxException {
        log.debug("REST request to update ReservaQuadraTenis : {}, {}", id, reservaQuadraTenis);
        if (reservaQuadraTenis.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, reservaQuadraTenis.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!reservaQuadraTenisRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ReservaQuadraTenis result = reservaQuadraTenisRepository.save(reservaQuadraTenis);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, reservaQuadraTenis.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /reserva-quadra-tenis/:id} : Partial updates given fields of an existing reservaQuadraTenis, field will ignore if it is null
     *
     * @param id the id of the reservaQuadraTenis to save.
     * @param reservaQuadraTenis the reservaQuadraTenis to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated reservaQuadraTenis,
     * or with status {@code 400 (Bad Request)} if the reservaQuadraTenis is not valid,
     * or with status {@code 404 (Not Found)} if the reservaQuadraTenis is not found,
     * or with status {@code 500 (Internal Server Error)} if the reservaQuadraTenis couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/reserva-quadra-tenis/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ReservaQuadraTenis> partialUpdateReservaQuadraTenis(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody ReservaQuadraTenis reservaQuadraTenis
    ) throws URISyntaxException {
        log.debug("REST request to partial update ReservaQuadraTenis partially : {}, {}", id, reservaQuadraTenis);
        if (reservaQuadraTenis.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, reservaQuadraTenis.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!reservaQuadraTenisRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ReservaQuadraTenis> result = reservaQuadraTenisRepository
            .findById(reservaQuadraTenis.getId())
            .map(existingReservaQuadraTenis -> {
                if (reservaQuadraTenis.getEmailDestino() != null) {
                    existingReservaQuadraTenis.setEmailDestino(reservaQuadraTenis.getEmailDestino());
                }
                if (reservaQuadraTenis.getTemplateEmail() != null) {
                    existingReservaQuadraTenis.setTemplateEmail(reservaQuadraTenis.getTemplateEmail());
                }
                if (reservaQuadraTenis.getSemana() != null) {
                    existingReservaQuadraTenis.setSemana(reservaQuadraTenis.getSemana());
                }
                if (reservaQuadraTenis.getSegundafeira() != null) {
                    existingReservaQuadraTenis.setSegundafeira(reservaQuadraTenis.getSegundafeira());
                }
                if (reservaQuadraTenis.getTercafeira() != null) {
                    existingReservaQuadraTenis.setTercafeira(reservaQuadraTenis.getTercafeira());
                }
                if (reservaQuadraTenis.getQuartafeira() != null) {
                    existingReservaQuadraTenis.setQuartafeira(reservaQuadraTenis.getQuartafeira());
                }
                if (reservaQuadraTenis.getQuintafeira() != null) {
                    existingReservaQuadraTenis.setQuintafeira(reservaQuadraTenis.getQuintafeira());
                }
                if (reservaQuadraTenis.getSextafeira() != null) {
                    existingReservaQuadraTenis.setSextafeira(reservaQuadraTenis.getSextafeira());
                }
                if (reservaQuadraTenis.getSabado() != null) {
                    existingReservaQuadraTenis.setSabado(reservaQuadraTenis.getSabado());
                }
                if (reservaQuadraTenis.getDomingo() != null) {
                    existingReservaQuadraTenis.setDomingo(reservaQuadraTenis.getDomingo());
                }
                if (reservaQuadraTenis.getSegundaFeiraEmailEnviado() != null) {
                    existingReservaQuadraTenis.setSegundaFeiraEmailEnviado(reservaQuadraTenis.getSegundaFeiraEmailEnviado());
                }
                if (reservaQuadraTenis.getTercaFeiraEmailEnviado() != null) {
                    existingReservaQuadraTenis.setTercaFeiraEmailEnviado(reservaQuadraTenis.getTercaFeiraEmailEnviado());
                }
                if (reservaQuadraTenis.getQuartaFeiraEmailEnviado() != null) {
                    existingReservaQuadraTenis.setQuartaFeiraEmailEnviado(reservaQuadraTenis.getQuartaFeiraEmailEnviado());
                }
                if (reservaQuadraTenis.getQuintaFeiraEmailEnviado() != null) {
                    existingReservaQuadraTenis.setQuintaFeiraEmailEnviado(reservaQuadraTenis.getQuintaFeiraEmailEnviado());
                }
                if (reservaQuadraTenis.getSextaFeiraEmailEnviado() != null) {
                    existingReservaQuadraTenis.setSextaFeiraEmailEnviado(reservaQuadraTenis.getSextaFeiraEmailEnviado());
                }
                if (reservaQuadraTenis.getSabadoEmailEnviado() != null) {
                    existingReservaQuadraTenis.setSabadoEmailEnviado(reservaQuadraTenis.getSabadoEmailEnviado());
                }
                if (reservaQuadraTenis.getDomingoEmailEnviado() != null) {
                    existingReservaQuadraTenis.setDomingoEmailEnviado(reservaQuadraTenis.getDomingoEmailEnviado());
                }

                return existingReservaQuadraTenis;
            })
            .map(reservaQuadraTenisRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, reservaQuadraTenis.getId().toString())
        );
    }

    /**
     * {@code GET  /reserva-quadra-tenis} : get all the reservaQuadraTenis.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of reservaQuadraTenis in body.
     */
    @GetMapping("/reserva-quadra-tenis")
    public ResponseEntity<List<ReservaQuadraTenis>> getAllReservaQuadraTenis(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of ReservaQuadraTenis");
        Page<ReservaQuadraTenis> page = reservaQuadraTenisRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /reserva-quadra-tenis/:id} : get the "id" reservaQuadraTenis.
     *
     * @param id the id of the reservaQuadraTenis to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the reservaQuadraTenis, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/reserva-quadra-tenis/{id}")
    public ResponseEntity<ReservaQuadraTenis> getReservaQuadraTenis(@PathVariable Long id) {
        log.debug("REST request to get ReservaQuadraTenis : {}", id);
        Optional<ReservaQuadraTenis> reservaQuadraTenis = reservaQuadraTenisRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(reservaQuadraTenis);
    }

    /**
     * {@code DELETE  /reserva-quadra-tenis/:id} : delete the "id" reservaQuadraTenis.
     *
     * @param id the id of the reservaQuadraTenis to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/reserva-quadra-tenis/{id}")
    public ResponseEntity<Void> deleteReservaQuadraTenis(@PathVariable Long id) {
        log.debug("REST request to delete ReservaQuadraTenis : {}", id);
        reservaQuadraTenisRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
