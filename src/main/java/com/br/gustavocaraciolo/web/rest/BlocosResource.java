package com.br.gustavocaraciolo.web.rest;

import com.br.gustavocaraciolo.domain.Blocos;
import com.br.gustavocaraciolo.domain.CronogramaDiario;
import com.br.gustavocaraciolo.repository.BlocosRepository;
import com.br.gustavocaraciolo.repository.CronogramaDiarioRepository;
import com.br.gustavocaraciolo.web.rest.errors.BadRequestAlertException;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.br.gustavocaraciolo.domain.Blocos}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class BlocosResource {

    private final Logger log = LoggerFactory.getLogger(BlocosResource.class);

    private static final String ENTITY_NAME = "blocos";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BlocosRepository blocosRepository;

    private final CronogramaDiarioRepository cronogramaDiarioRepository;

    public BlocosResource(BlocosRepository blocosRepository, CronogramaDiarioRepository cronogramaDiarioRepository) {
        this.blocosRepository = blocosRepository;
        this.cronogramaDiarioRepository = cronogramaDiarioRepository;
    }

    /**
     * {@code POST  /blocos} : Create a new blocos.
     *
     * @param blocos the blocos to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new blocos, or with status {@code 400 (Bad Request)} if the blocos has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/blocos")
    public ResponseEntity<Blocos> createBlocos(@RequestBody Blocos blocos) throws URISyntaxException {
        log.debug("REST request to save Blocos : {}", blocos);
        if (blocos.getId() != null) {
            throw new BadRequestAlertException("A new blocos cannot already have an ID", ENTITY_NAME, "idexists");
        }
        if (blocos.getCronogramaDiario() != null && blocos.getCronogramaDiario().getDia() != null) {
            Optional<CronogramaDiario> cronogramaDiario = cronogramaDiarioRepository.findByDiaEquals(blocos.getCronogramaDiario().getDia());
            if(cronogramaDiario.isPresent()) {
                blocos.setCronogramaDiario(cronogramaDiario.get());
            }else {
                CronogramaDiario save = new CronogramaDiario();
                save.setDia(blocos.getCronogramaDiario().getDia());
                save = cronogramaDiarioRepository.save(save);
                blocos.setCronogramaDiario(save);
            }
        }

        Blocos result = blocosRepository.save(blocos);
        return ResponseEntity
            .created(new URI("/api/blocos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /blocos/:id} : Updates an existing blocos.
     *
     * @param id the id of the blocos to save.
     * @param blocos the blocos to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated blocos,
     * or with status {@code 400 (Bad Request)} if the blocos is not valid,
     * or with status {@code 500 (Internal Server Error)} if the blocos couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/blocos/{id}")
    public ResponseEntity<Blocos> updateBlocos(@PathVariable(value = "id", required = false) final Long id, @RequestBody Blocos blocos)
        throws URISyntaxException {
        log.debug("REST request to update Blocos : {}, {}", id, blocos);
        if (blocos.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, blocos.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!blocosRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Blocos result = blocosRepository.save(blocos);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, blocos.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /blocos/:id} : Partial updates given fields of an existing blocos, field will ignore if it is null
     *
     * @param id the id of the blocos to save.
     * @param blocos the blocos to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated blocos,
     * or with status {@code 400 (Bad Request)} if the blocos is not valid,
     * or with status {@code 404 (Not Found)} if the blocos is not found,
     * or with status {@code 500 (Internal Server Error)} if the blocos couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/blocos/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Blocos> partialUpdateBlocos(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Blocos blocos
    ) throws URISyntaxException {
        log.debug("REST request to partial update Blocos partially : {}, {}", id, blocos);
        if (blocos.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, blocos.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!blocosRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Blocos> result = blocosRepository
            .findById(blocos.getId())
            .map(existingBlocos -> {
                if (blocos.getZeroAM() != null) {
                    existingBlocos.setZeroAM(blocos.getZeroAM());
                }
                if (blocos.getZeroAMeDez() != null) {
                    existingBlocos.setZeroAMeDez(blocos.getZeroAMeDez());
                }
                if (blocos.getZeroAMeVinte() != null) {
                    existingBlocos.setZeroAMeVinte(blocos.getZeroAMeVinte());
                }
                if (blocos.getZeroAMeTrinta() != null) {
                    existingBlocos.setZeroAMeTrinta(blocos.getZeroAMeTrinta());
                }
                if (blocos.getZeroAMeQuarenta() != null) {
                    existingBlocos.setZeroAMeQuarenta(blocos.getZeroAMeQuarenta());
                }
                if (blocos.getZeroAMeCinquenta() != null) {
                    existingBlocos.setZeroAMeCinquenta(blocos.getZeroAMeCinquenta());
                }
                if (blocos.getZeroPM() != null) {
                    existingBlocos.setZeroPM(blocos.getZeroPM());
                }
                if (blocos.getZeroPMeDez() != null) {
                    existingBlocos.setZeroPMeDez(blocos.getZeroPMeDez());
                }
                if (blocos.getZeroPMeVinte() != null) {
                    existingBlocos.setZeroPMeVinte(blocos.getZeroPMeVinte());
                }
                if (blocos.getZeroPMeTrinta() != null) {
                    existingBlocos.setZeroPMeTrinta(blocos.getZeroPMeTrinta());
                }
                if (blocos.getZeroPMeQuarenta() != null) {
                    existingBlocos.setZeroPMeQuarenta(blocos.getZeroPMeQuarenta());
                }
                if (blocos.getZeroPMeCinquenta() != null) {
                    existingBlocos.setZeroPMeCinquenta(blocos.getZeroPMeCinquenta());
                }
                if (blocos.getUmAM() != null) {
                    existingBlocos.setUmAM(blocos.getUmAM());
                }
                if (blocos.getUmAMeDez() != null) {
                    existingBlocos.setUmAMeDez(blocos.getUmAMeDez());
                }
                if (blocos.getUmAMeVinte() != null) {
                    existingBlocos.setUmAMeVinte(blocos.getUmAMeVinte());
                }
                if (blocos.getUmAMeTrinta() != null) {
                    existingBlocos.setUmAMeTrinta(blocos.getUmAMeTrinta());
                }
                if (blocos.getUmAMeQuarenta() != null) {
                    existingBlocos.setUmAMeQuarenta(blocos.getUmAMeQuarenta());
                }
                if (blocos.getUmAMeCinquenta() != null) {
                    existingBlocos.setUmAMeCinquenta(blocos.getUmAMeCinquenta());
                }
                if (blocos.getUmPM() != null) {
                    existingBlocos.setUmPM(blocos.getUmPM());
                }
                if (blocos.getUmPMeDez() != null) {
                    existingBlocos.setUmPMeDez(blocos.getUmPMeDez());
                }
                if (blocos.getUmPMeVinte() != null) {
                    existingBlocos.setUmPMeVinte(blocos.getUmPMeVinte());
                }
                if (blocos.getUmPMeTrinta() != null) {
                    existingBlocos.setUmPMeTrinta(blocos.getUmPMeTrinta());
                }
                if (blocos.getUmPMeQuarenta() != null) {
                    existingBlocos.setUmPMeQuarenta(blocos.getUmPMeQuarenta());
                }
                if (blocos.getUmPMeCinquenta() != null) {
                    existingBlocos.setUmPMeCinquenta(blocos.getUmPMeCinquenta());
                }
                if (blocos.getDoisAM() != null) {
                    existingBlocos.setDoisAM(blocos.getDoisAM());
                }
                if (blocos.getDoisAMeDez() != null) {
                    existingBlocos.setDoisAMeDez(blocos.getDoisAMeDez());
                }
                if (blocos.getDoisAMeVinte() != null) {
                    existingBlocos.setDoisAMeVinte(blocos.getDoisAMeVinte());
                }
                if (blocos.getDoisAMeTrinta() != null) {
                    existingBlocos.setDoisAMeTrinta(blocos.getDoisAMeTrinta());
                }
                if (blocos.getDoisAMeQuarenta() != null) {
                    existingBlocos.setDoisAMeQuarenta(blocos.getDoisAMeQuarenta());
                }
                if (blocos.getDoisAMeCinquenta() != null) {
                    existingBlocos.setDoisAMeCinquenta(blocos.getDoisAMeCinquenta());
                }
                if (blocos.getDoisPM() != null) {
                    existingBlocos.setDoisPM(blocos.getDoisPM());
                }
                if (blocos.getDoisPMeDez() != null) {
                    existingBlocos.setDoisPMeDez(blocos.getDoisPMeDez());
                }
                if (blocos.getDoisPMeVinte() != null) {
                    existingBlocos.setDoisPMeVinte(blocos.getDoisPMeVinte());
                }
                if (blocos.getDoisPMeTrinta() != null) {
                    existingBlocos.setDoisPMeTrinta(blocos.getDoisPMeTrinta());
                }
                if (blocos.getDoisPMeQuarenta() != null) {
                    existingBlocos.setDoisPMeQuarenta(blocos.getDoisPMeQuarenta());
                }
                if (blocos.getDoisPMeCinquenta() != null) {
                    existingBlocos.setDoisPMeCinquenta(blocos.getDoisPMeCinquenta());
                }
                if (blocos.getTresAM() != null) {
                    existingBlocos.setTresAM(blocos.getTresAM());
                }
                if (blocos.getTresAMeDez() != null) {
                    existingBlocos.setTresAMeDez(blocos.getTresAMeDez());
                }
                if (blocos.getTresAMeVinte() != null) {
                    existingBlocos.setTresAMeVinte(blocos.getTresAMeVinte());
                }
                if (blocos.getTresAMeTrinta() != null) {
                    existingBlocos.setTresAMeTrinta(blocos.getTresAMeTrinta());
                }
                if (blocos.getTresAMeQuarenta() != null) {
                    existingBlocos.setTresAMeQuarenta(blocos.getTresAMeQuarenta());
                }
                if (blocos.getTresAMeCinquenta() != null) {
                    existingBlocos.setTresAMeCinquenta(blocos.getTresAMeCinquenta());
                }
                if (blocos.getTresPM() != null) {
                    existingBlocos.setTresPM(blocos.getTresPM());
                }
                if (blocos.getTresPMeDez() != null) {
                    existingBlocos.setTresPMeDez(blocos.getTresPMeDez());
                }
                if (blocos.getTresPMeVinte() != null) {
                    existingBlocos.setTresPMeVinte(blocos.getTresPMeVinte());
                }
                if (blocos.getTresPMeTrinta() != null) {
                    existingBlocos.setTresPMeTrinta(blocos.getTresPMeTrinta());
                }
                if (blocos.getTresPMeQuarenta() != null) {
                    existingBlocos.setTresPMeQuarenta(blocos.getTresPMeQuarenta());
                }
                if (blocos.getTresPMeCinquenta() != null) {
                    existingBlocos.setTresPMeCinquenta(blocos.getTresPMeCinquenta());
                }
                if (blocos.getQuatroAM() != null) {
                    existingBlocos.setQuatroAM(blocos.getQuatroAM());
                }
                if (blocos.getQuatroAMeDez() != null) {
                    existingBlocos.setQuatroAMeDez(blocos.getQuatroAMeDez());
                }
                if (blocos.getQuatroAMeVinte() != null) {
                    existingBlocos.setQuatroAMeVinte(blocos.getQuatroAMeVinte());
                }
                if (blocos.getQuatroAMeTrinta() != null) {
                    existingBlocos.setQuatroAMeTrinta(blocos.getQuatroAMeTrinta());
                }
                if (blocos.getQuatroAMeQuarenta() != null) {
                    existingBlocos.setQuatroAMeQuarenta(blocos.getQuatroAMeQuarenta());
                }
                if (blocos.getQuatroAMeCinquenta() != null) {
                    existingBlocos.setQuatroAMeCinquenta(blocos.getQuatroAMeCinquenta());
                }
                if (blocos.getQuatroPM() != null) {
                    existingBlocos.setQuatroPM(blocos.getQuatroPM());
                }
                if (blocos.getQuatroPMeDez() != null) {
                    existingBlocos.setQuatroPMeDez(blocos.getQuatroPMeDez());
                }
                if (blocos.getQuatroPMeVinte() != null) {
                    existingBlocos.setQuatroPMeVinte(blocos.getQuatroPMeVinte());
                }
                if (blocos.getQuatroPMeTrinta() != null) {
                    existingBlocos.setQuatroPMeTrinta(blocos.getQuatroPMeTrinta());
                }
                if (blocos.getQuatroPMeQuarenta() != null) {
                    existingBlocos.setQuatroPMeQuarenta(blocos.getQuatroPMeQuarenta());
                }
                if (blocos.getQuatroPMeCinquenta() != null) {
                    existingBlocos.setQuatroPMeCinquenta(blocos.getQuatroPMeCinquenta());
                }
                if (blocos.getCincoAM() != null) {
                    existingBlocos.setCincoAM(blocos.getCincoAM());
                }
                if (blocos.getCincoAMeDez() != null) {
                    existingBlocos.setCincoAMeDez(blocos.getCincoAMeDez());
                }
                if (blocos.getCincoAMeVinte() != null) {
                    existingBlocos.setCincoAMeVinte(blocos.getCincoAMeVinte());
                }
                if (blocos.getCincoAMeTrinta() != null) {
                    existingBlocos.setCincoAMeTrinta(blocos.getCincoAMeTrinta());
                }
                if (blocos.getCincoAMeQuarenta() != null) {
                    existingBlocos.setCincoAMeQuarenta(blocos.getCincoAMeQuarenta());
                }
                if (blocos.getCincoAMeCinquenta() != null) {
                    existingBlocos.setCincoAMeCinquenta(blocos.getCincoAMeCinquenta());
                }
                if (blocos.getCincoPM() != null) {
                    existingBlocos.setCincoPM(blocos.getCincoPM());
                }
                if (blocos.getCincoPMeDez() != null) {
                    existingBlocos.setCincoPMeDez(blocos.getCincoPMeDez());
                }
                if (blocos.getCincoPMeVinte() != null) {
                    existingBlocos.setCincoPMeVinte(blocos.getCincoPMeVinte());
                }
                if (blocos.getCincoPMeTrinta() != null) {
                    existingBlocos.setCincoPMeTrinta(blocos.getCincoPMeTrinta());
                }
                if (blocos.getCincoPMeQuarenta() != null) {
                    existingBlocos.setCincoPMeQuarenta(blocos.getCincoPMeQuarenta());
                }
                if (blocos.getCincoPMeCinquenta() != null) {
                    existingBlocos.setCincoPMeCinquenta(blocos.getCincoPMeCinquenta());
                }
                if (blocos.getSeisAM() != null) {
                    existingBlocos.setSeisAM(blocos.getSeisAM());
                }
                if (blocos.getSeisAMeDez() != null) {
                    existingBlocos.setSeisAMeDez(blocos.getSeisAMeDez());
                }
                if (blocos.getSeisAMeVinte() != null) {
                    existingBlocos.setSeisAMeVinte(blocos.getSeisAMeVinte());
                }
                if (blocos.getSeisAMeTrinta() != null) {
                    existingBlocos.setSeisAMeTrinta(blocos.getSeisAMeTrinta());
                }
                if (blocos.getSeisAMeQuarenta() != null) {
                    existingBlocos.setSeisAMeQuarenta(blocos.getSeisAMeQuarenta());
                }
                if (blocos.getSeisAMeCinquenta() != null) {
                    existingBlocos.setSeisAMeCinquenta(blocos.getSeisAMeCinquenta());
                }
                if (blocos.getSeisPM() != null) {
                    existingBlocos.setSeisPM(blocos.getSeisPM());
                }
                if (blocos.getSeisPMeDez() != null) {
                    existingBlocos.setSeisPMeDez(blocos.getSeisPMeDez());
                }
                if (blocos.getSeisPMeVinte() != null) {
                    existingBlocos.setSeisPMeVinte(blocos.getSeisPMeVinte());
                }
                if (blocos.getSeisPMeTrinta() != null) {
                    existingBlocos.setSeisPMeTrinta(blocos.getSeisPMeTrinta());
                }
                if (blocos.getSeisPMeQuarenta() != null) {
                    existingBlocos.setSeisPMeQuarenta(blocos.getSeisPMeQuarenta());
                }
                if (blocos.getSeisPMeCinquenta() != null) {
                    existingBlocos.setSeisPMeCinquenta(blocos.getSeisPMeCinquenta());
                }
                if (blocos.getSeteAM() != null) {
                    existingBlocos.setSeteAM(blocos.getSeteAM());
                }
                if (blocos.getSeteAMeDez() != null) {
                    existingBlocos.setSeteAMeDez(blocos.getSeteAMeDez());
                }
                if (blocos.getSeteAMeVinte() != null) {
                    existingBlocos.setSeteAMeVinte(blocos.getSeteAMeVinte());
                }
                if (blocos.getSeteAMeTrinta() != null) {
                    existingBlocos.setSeteAMeTrinta(blocos.getSeteAMeTrinta());
                }
                if (blocos.getSeteAMeQuarenta() != null) {
                    existingBlocos.setSeteAMeQuarenta(blocos.getSeteAMeQuarenta());
                }
                if (blocos.getSeteAMeCinquenta() != null) {
                    existingBlocos.setSeteAMeCinquenta(blocos.getSeteAMeCinquenta());
                }
                if (blocos.getSetePM() != null) {
                    existingBlocos.setSetePM(blocos.getSetePM());
                }
                if (blocos.getSetePMeDez() != null) {
                    existingBlocos.setSetePMeDez(blocos.getSetePMeDez());
                }
                if (blocos.getSetePMeVinte() != null) {
                    existingBlocos.setSetePMeVinte(blocos.getSetePMeVinte());
                }
                if (blocos.getSetePMeTrinta() != null) {
                    existingBlocos.setSetePMeTrinta(blocos.getSetePMeTrinta());
                }
                if (blocos.getSetePMeQuarenta() != null) {
                    existingBlocos.setSetePMeQuarenta(blocos.getSetePMeQuarenta());
                }
                if (blocos.getSetePMeCinquenta() != null) {
                    existingBlocos.setSetePMeCinquenta(blocos.getSetePMeCinquenta());
                }
                if (blocos.getOitoAM() != null) {
                    existingBlocos.setOitoAM(blocos.getOitoAM());
                }
                if (blocos.getOitoAMeDez() != null) {
                    existingBlocos.setOitoAMeDez(blocos.getOitoAMeDez());
                }
                if (blocos.getOitoAMeVinte() != null) {
                    existingBlocos.setOitoAMeVinte(blocos.getOitoAMeVinte());
                }
                if (blocos.getOitoAMeTrinta() != null) {
                    existingBlocos.setOitoAMeTrinta(blocos.getOitoAMeTrinta());
                }
                if (blocos.getOitoAMeQuarenta() != null) {
                    existingBlocos.setOitoAMeQuarenta(blocos.getOitoAMeQuarenta());
                }
                if (blocos.getOitoAMeCinquenta() != null) {
                    existingBlocos.setOitoAMeCinquenta(blocos.getOitoAMeCinquenta());
                }
                if (blocos.getOitoPM() != null) {
                    existingBlocos.setOitoPM(blocos.getOitoPM());
                }
                if (blocos.getOitoPMeDez() != null) {
                    existingBlocos.setOitoPMeDez(blocos.getOitoPMeDez());
                }
                if (blocos.getOitoPMeVinte() != null) {
                    existingBlocos.setOitoPMeVinte(blocos.getOitoPMeVinte());
                }
                if (blocos.getOitoPMeTrinta() != null) {
                    existingBlocos.setOitoPMeTrinta(blocos.getOitoPMeTrinta());
                }
                if (blocos.getOitoPMeQuarenta() != null) {
                    existingBlocos.setOitoPMeQuarenta(blocos.getOitoPMeQuarenta());
                }
                if (blocos.getOitoPMeCinquenta() != null) {
                    existingBlocos.setOitoPMeCinquenta(blocos.getOitoPMeCinquenta());
                }
                if (blocos.getNoveAM() != null) {
                    existingBlocos.setNoveAM(blocos.getNoveAM());
                }
                if (blocos.getNoveAMeDez() != null) {
                    existingBlocos.setNoveAMeDez(blocos.getNoveAMeDez());
                }
                if (blocos.getNoveAMeVinte() != null) {
                    existingBlocos.setNoveAMeVinte(blocos.getNoveAMeVinte());
                }
                if (blocos.getNoveAMeTrinta() != null) {
                    existingBlocos.setNoveAMeTrinta(blocos.getNoveAMeTrinta());
                }
                if (blocos.getNoveAMeQuarenta() != null) {
                    existingBlocos.setNoveAMeQuarenta(blocos.getNoveAMeQuarenta());
                }
                if (blocos.getNoveAMeCinquenta() != null) {
                    existingBlocos.setNoveAMeCinquenta(blocos.getNoveAMeCinquenta());
                }
                if (blocos.getNovePM() != null) {
                    existingBlocos.setNovePM(blocos.getNovePM());
                }
                if (blocos.getNovePMeDez() != null) {
                    existingBlocos.setNovePMeDez(blocos.getNovePMeDez());
                }
                if (blocos.getNovePMeVinte() != null) {
                    existingBlocos.setNovePMeVinte(blocos.getNovePMeVinte());
                }
                if (blocos.getNovePMeTrinta() != null) {
                    existingBlocos.setNovePMeTrinta(blocos.getNovePMeTrinta());
                }
                if (blocos.getNovePMeQuarenta() != null) {
                    existingBlocos.setNovePMeQuarenta(blocos.getNovePMeQuarenta());
                }
                if (blocos.getNovePMeCinquenta() != null) {
                    existingBlocos.setNovePMeCinquenta(blocos.getNovePMeCinquenta());
                }
                if (blocos.getDezAM() != null) {
                    existingBlocos.setDezAM(blocos.getDezAM());
                }
                if (blocos.getDezAMeDez() != null) {
                    existingBlocos.setDezAMeDez(blocos.getDezAMeDez());
                }
                if (blocos.getDezAMeVinte() != null) {
                    existingBlocos.setDezAMeVinte(blocos.getDezAMeVinte());
                }
                if (blocos.getDezAMeTrinta() != null) {
                    existingBlocos.setDezAMeTrinta(blocos.getDezAMeTrinta());
                }
                if (blocos.getDezAMeQuarenta() != null) {
                    existingBlocos.setDezAMeQuarenta(blocos.getDezAMeQuarenta());
                }
                if (blocos.getDezAMeCinquenta() != null) {
                    existingBlocos.setDezAMeCinquenta(blocos.getDezAMeCinquenta());
                }
                if (blocos.getDezPM() != null) {
                    existingBlocos.setDezPM(blocos.getDezPM());
                }
                if (blocos.getDezPMeDez() != null) {
                    existingBlocos.setDezPMeDez(blocos.getDezPMeDez());
                }
                if (blocos.getDezPMeVinte() != null) {
                    existingBlocos.setDezPMeVinte(blocos.getDezPMeVinte());
                }
                if (blocos.getDezPMeTrinta() != null) {
                    existingBlocos.setDezPMeTrinta(blocos.getDezPMeTrinta());
                }
                if (blocos.getDezPMeQuarenta() != null) {
                    existingBlocos.setDezPMeQuarenta(blocos.getDezPMeQuarenta());
                }
                if (blocos.getDezPMeCinquenta() != null) {
                    existingBlocos.setDezPMeCinquenta(blocos.getDezPMeCinquenta());
                }
                if (blocos.getOnzeAM() != null) {
                    existingBlocos.setOnzeAM(blocos.getOnzeAM());
                }
                if (blocos.getOnzeAMeDez() != null) {
                    existingBlocos.setOnzeAMeDez(blocos.getOnzeAMeDez());
                }
                if (blocos.getOnzeAMeVinte() != null) {
                    existingBlocos.setOnzeAMeVinte(blocos.getOnzeAMeVinte());
                }
                if (blocos.getOnzeAMeTrinta() != null) {
                    existingBlocos.setOnzeAMeTrinta(blocos.getOnzeAMeTrinta());
                }
                if (blocos.getOnzeAMeQuarenta() != null) {
                    existingBlocos.setOnzeAMeQuarenta(blocos.getOnzeAMeQuarenta());
                }
                if (blocos.getOnzeAMeCinquenta() != null) {
                    existingBlocos.setOnzeAMeCinquenta(blocos.getOnzeAMeCinquenta());
                }
                if (blocos.getOnzePM() != null) {
                    existingBlocos.setOnzePM(blocos.getOnzePM());
                }
                if (blocos.getOnzePMeDez() != null) {
                    existingBlocos.setOnzePMeDez(blocos.getOnzePMeDez());
                }
                if (blocos.getOnzePMeVinte() != null) {
                    existingBlocos.setOnzePMeVinte(blocos.getOnzePMeVinte());
                }
                if (blocos.getOnzePMeTrinta() != null) {
                    existingBlocos.setOnzePMeTrinta(blocos.getOnzePMeTrinta());
                }
                if (blocos.getOnzePMeQuarenta() != null) {
                    existingBlocos.setOnzePMeQuarenta(blocos.getOnzePMeQuarenta());
                }
                if (blocos.getOnzePMeCinquenta() != null) {
                    existingBlocos.setOnzePMeCinquenta(blocos.getOnzePMeCinquenta());
                }

                return existingBlocos;
            })
            .map(blocosRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, blocos.getId().toString())
        );
    }

    /**
     * {@code GET  /blocos} : get all the blocos.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of blocos in body.
     */
    @GetMapping("/blocos")
    public List<Blocos> getAllBlocos(@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get all Blocos");
        return blocosRepository.findAllWithEagerRelationships();
    }

    /**
     * {@code GET  /blocos/:id} : get the "id" blocos.
     *
     * @param id the id of the blocos to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the blocos, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/blocos/{id}")
    public ResponseEntity<Blocos> getBlocos(@PathVariable Long id) {
        log.debug("REST request to get Blocos : {}", id);
        Optional<Blocos> blocos = blocosRepository.findOneWithEagerRelationships(id);
        return ResponseUtil.wrapOrNotFound(blocos);
    }

    /**
     * {@code DELETE  /blocos/:id} : delete the "id" blocos.
     *
     * @param id the id of the blocos to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/blocos/{id}")
    public ResponseEntity<Void> deleteBlocos(@PathVariable Long id) {
        log.debug("REST request to delete Blocos : {}", id);
        blocosRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    @GetMapping("/blocos/by-date/{date}")
    public ResponseEntity<Blocos> getCronogramaDiarioByDate(@PathVariable LocalDate date) {
        log.debug("REST request to get CronogramaDiario : {}", date);
        Optional<Blocos> blocos = blocosRepository.findByCronogramaDiario_DiaEquals(date);
        return ResponseUtil.wrapOrNotFound(blocos);
    }
}
