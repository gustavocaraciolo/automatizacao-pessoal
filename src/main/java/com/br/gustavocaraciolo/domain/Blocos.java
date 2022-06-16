package com.br.gustavocaraciolo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Blocos.
 */
@Entity
@Table(name = "blocos")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Blocos implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "zero_am")
    private ZonedDateTime zeroAM;

    @Column(name = "zero_a_me_dez")
    private ZonedDateTime zeroAMeDez;

    @Column(name = "zero_a_me_vinte")
    private ZonedDateTime zeroAMeVinte;

    @Column(name = "zero_a_me_trinta")
    private ZonedDateTime zeroAMeTrinta;

    @Column(name = "zero_a_me_quarenta")
    private ZonedDateTime zeroAMeQuarenta;

    @Column(name = "zero_a_me_cinquenta")
    private ZonedDateTime zeroAMeCinquenta;

    @Column(name = "zero_pm")
    private ZonedDateTime zeroPM;

    @Column(name = "zero_p_me_dez")
    private ZonedDateTime zeroPMeDez;

    @Column(name = "zero_p_me_vinte")
    private ZonedDateTime zeroPMeVinte;

    @Column(name = "zero_p_me_trinta")
    private ZonedDateTime zeroPMeTrinta;

    @Column(name = "zero_p_me_quarenta")
    private ZonedDateTime zeroPMeQuarenta;

    @Column(name = "zero_p_me_cinquenta")
    private ZonedDateTime zeroPMeCinquenta;

    @Column(name = "um_am")
    private ZonedDateTime umAM;

    @Column(name = "um_a_me_dez")
    private ZonedDateTime umAMeDez;

    @Column(name = "um_a_me_vinte")
    private ZonedDateTime umAMeVinte;

    @Column(name = "um_a_me_trinta")
    private ZonedDateTime umAMeTrinta;

    @Column(name = "um_a_me_quarenta")
    private ZonedDateTime umAMeQuarenta;

    @Column(name = "um_a_me_cinquenta")
    private ZonedDateTime umAMeCinquenta;

    @Column(name = "um_pm")
    private ZonedDateTime umPM;

    @Column(name = "um_p_me_dez")
    private ZonedDateTime umPMeDez;

    @Column(name = "um_p_me_vinte")
    private ZonedDateTime umPMeVinte;

    @Column(name = "um_p_me_trinta")
    private ZonedDateTime umPMeTrinta;

    @Column(name = "um_p_me_quarenta")
    private ZonedDateTime umPMeQuarenta;

    @Column(name = "um_p_me_cinquenta")
    private ZonedDateTime umPMeCinquenta;

    @Column(name = "dois_am")
    private ZonedDateTime doisAM;

    @Column(name = "dois_a_me_dez")
    private ZonedDateTime doisAMeDez;

    @Column(name = "dois_a_me_vinte")
    private ZonedDateTime doisAMeVinte;

    @Column(name = "dois_a_me_trinta")
    private ZonedDateTime doisAMeTrinta;

    @Column(name = "dois_a_me_quarenta")
    private ZonedDateTime doisAMeQuarenta;

    @Column(name = "dois_a_me_cinquenta")
    private ZonedDateTime doisAMeCinquenta;

    @Column(name = "dois_pm")
    private ZonedDateTime doisPM;

    @Column(name = "dois_p_me_dez")
    private ZonedDateTime doisPMeDez;

    @Column(name = "dois_p_me_vinte")
    private ZonedDateTime doisPMeVinte;

    @Column(name = "dois_p_me_trinta")
    private ZonedDateTime doisPMeTrinta;

    @Column(name = "dois_p_me_quarenta")
    private ZonedDateTime doisPMeQuarenta;

    @Column(name = "dois_p_me_cinquenta")
    private ZonedDateTime doisPMeCinquenta;

    @Column(name = "tres_am")
    private ZonedDateTime tresAM;

    @Column(name = "tres_a_me_dez")
    private ZonedDateTime tresAMeDez;

    @Column(name = "tres_a_me_vinte")
    private ZonedDateTime tresAMeVinte;

    @Column(name = "tres_a_me_trinta")
    private ZonedDateTime tresAMeTrinta;

    @Column(name = "tres_a_me_quarenta")
    private ZonedDateTime tresAMeQuarenta;

    @Column(name = "tres_a_me_cinquenta")
    private ZonedDateTime tresAMeCinquenta;

    @Column(name = "tres_pm")
    private ZonedDateTime tresPM;

    @Column(name = "tres_p_me_dez")
    private ZonedDateTime tresPMeDez;

    @Column(name = "tres_p_me_vinte")
    private ZonedDateTime tresPMeVinte;

    @Column(name = "tres_p_me_trinta")
    private ZonedDateTime tresPMeTrinta;

    @Column(name = "tres_p_me_quarenta")
    private ZonedDateTime tresPMeQuarenta;

    @Column(name = "tres_p_me_cinquenta")
    private ZonedDateTime tresPMeCinquenta;

    @Column(name = "quatro_am")
    private ZonedDateTime quatroAM;

    @Column(name = "quatro_a_me_dez")
    private ZonedDateTime quatroAMeDez;

    @Column(name = "quatro_a_me_vinte")
    private ZonedDateTime quatroAMeVinte;

    @Column(name = "quatro_a_me_trinta")
    private ZonedDateTime quatroAMeTrinta;

    @Column(name = "quatro_a_me_quarenta")
    private ZonedDateTime quatroAMeQuarenta;

    @Column(name = "quatro_a_me_cinquenta")
    private ZonedDateTime quatroAMeCinquenta;

    @Column(name = "quatro_pm")
    private ZonedDateTime quatroPM;

    @Column(name = "quatro_p_me_dez")
    private ZonedDateTime quatroPMeDez;

    @Column(name = "quatro_p_me_vinte")
    private ZonedDateTime quatroPMeVinte;

    @Column(name = "quatro_p_me_trinta")
    private ZonedDateTime quatroPMeTrinta;

    @Column(name = "quatro_p_me_quarenta")
    private ZonedDateTime quatroPMeQuarenta;

    @Column(name = "quatro_p_me_cinquenta")
    private ZonedDateTime quatroPMeCinquenta;

    @Column(name = "cinco_am")
    private ZonedDateTime cincoAM;

    @Column(name = "cinco_a_me_dez")
    private ZonedDateTime cincoAMeDez;

    @Column(name = "cinco_a_me_vinte")
    private ZonedDateTime cincoAMeVinte;

    @Column(name = "cinco_a_me_trinta")
    private ZonedDateTime cincoAMeTrinta;

    @Column(name = "cinco_a_me_quarenta")
    private ZonedDateTime cincoAMeQuarenta;

    @Column(name = "cinco_a_me_cinquenta")
    private ZonedDateTime cincoAMeCinquenta;

    @Column(name = "cinco_pm")
    private ZonedDateTime cincoPM;

    @Column(name = "cinco_p_me_dez")
    private ZonedDateTime cincoPMeDez;

    @Column(name = "cinco_p_me_vinte")
    private ZonedDateTime cincoPMeVinte;

    @Column(name = "cinco_p_me_trinta")
    private ZonedDateTime cincoPMeTrinta;

    @Column(name = "cinco_p_me_quarenta")
    private ZonedDateTime cincoPMeQuarenta;

    @Column(name = "cinco_p_me_cinquenta")
    private ZonedDateTime cincoPMeCinquenta;

    @Column(name = "seis_am")
    private ZonedDateTime seisAM;

    @Column(name = "seis_a_me_dez")
    private ZonedDateTime seisAMeDez;

    @Column(name = "seis_a_me_vinte")
    private ZonedDateTime seisAMeVinte;

    @Column(name = "seis_a_me_trinta")
    private ZonedDateTime seisAMeTrinta;

    @Column(name = "seis_a_me_quarenta")
    private ZonedDateTime seisAMeQuarenta;

    @Column(name = "seis_a_me_cinquenta")
    private ZonedDateTime seisAMeCinquenta;

    @Column(name = "seis_pm")
    private ZonedDateTime seisPM;

    @Column(name = "seis_p_me_dez")
    private ZonedDateTime seisPMeDez;

    @Column(name = "seis_p_me_vinte")
    private ZonedDateTime seisPMeVinte;

    @Column(name = "seis_p_me_trinta")
    private ZonedDateTime seisPMeTrinta;

    @Column(name = "seis_p_me_quarenta")
    private ZonedDateTime seisPMeQuarenta;

    @Column(name = "seis_p_me_cinquenta")
    private ZonedDateTime seisPMeCinquenta;

    @Column(name = "sete_am")
    private ZonedDateTime seteAM;

    @Column(name = "sete_a_me_dez")
    private ZonedDateTime seteAMeDez;

    @Column(name = "sete_a_me_vinte")
    private ZonedDateTime seteAMeVinte;

    @Column(name = "sete_a_me_trinta")
    private ZonedDateTime seteAMeTrinta;

    @Column(name = "sete_a_me_quarenta")
    private ZonedDateTime seteAMeQuarenta;

    @Column(name = "sete_a_me_cinquenta")
    private ZonedDateTime seteAMeCinquenta;

    @Column(name = "sete_pm")
    private ZonedDateTime setePM;

    @Column(name = "sete_p_me_dez")
    private ZonedDateTime setePMeDez;

    @Column(name = "sete_p_me_vinte")
    private ZonedDateTime setePMeVinte;

    @Column(name = "sete_p_me_trinta")
    private ZonedDateTime setePMeTrinta;

    @Column(name = "sete_p_me_quarenta")
    private ZonedDateTime setePMeQuarenta;

    @Column(name = "sete_p_me_cinquenta")
    private ZonedDateTime setePMeCinquenta;

    @Column(name = "oito_am")
    private ZonedDateTime oitoAM;

    @Column(name = "oito_a_me_dez")
    private ZonedDateTime oitoAMeDez;

    @Column(name = "oito_a_me_vinte")
    private ZonedDateTime oitoAMeVinte;

    @Column(name = "oito_a_me_trinta")
    private ZonedDateTime oitoAMeTrinta;

    @Column(name = "oito_a_me_quarenta")
    private ZonedDateTime oitoAMeQuarenta;

    @Column(name = "oito_a_me_cinquenta")
    private ZonedDateTime oitoAMeCinquenta;

    @Column(name = "oito_pm")
    private ZonedDateTime oitoPM;

    @Column(name = "oito_p_me_dez")
    private ZonedDateTime oitoPMeDez;

    @Column(name = "oito_p_me_vinte")
    private ZonedDateTime oitoPMeVinte;

    @Column(name = "oito_p_me_trinta")
    private ZonedDateTime oitoPMeTrinta;

    @Column(name = "oito_p_me_quarenta")
    private ZonedDateTime oitoPMeQuarenta;

    @Column(name = "oito_p_me_cinquenta")
    private ZonedDateTime oitoPMeCinquenta;

    @Column(name = "nove_am")
    private ZonedDateTime noveAM;

    @Column(name = "nove_a_me_dez")
    private ZonedDateTime noveAMeDez;

    @Column(name = "nove_a_me_vinte")
    private ZonedDateTime noveAMeVinte;

    @Column(name = "nove_a_me_trinta")
    private ZonedDateTime noveAMeTrinta;

    @Column(name = "nove_a_me_quarenta")
    private ZonedDateTime noveAMeQuarenta;

    @Column(name = "nove_a_me_cinquenta")
    private ZonedDateTime noveAMeCinquenta;

    @Column(name = "nove_pm")
    private ZonedDateTime novePM;

    @Column(name = "nove_p_me_dez")
    private ZonedDateTime novePMeDez;

    @Column(name = "nove_p_me_vinte")
    private ZonedDateTime novePMeVinte;

    @Column(name = "nove_p_me_trinta")
    private ZonedDateTime novePMeTrinta;

    @Column(name = "nove_p_me_quarenta")
    private ZonedDateTime novePMeQuarenta;

    @Column(name = "nove_p_me_cinquenta")
    private ZonedDateTime novePMeCinquenta;

    @Column(name = "dez_am")
    private ZonedDateTime dezAM;

    @Column(name = "dez_a_me_dez")
    private ZonedDateTime dezAMeDez;

    @Column(name = "dez_a_me_vinte")
    private ZonedDateTime dezAMeVinte;

    @Column(name = "dez_a_me_trinta")
    private ZonedDateTime dezAMeTrinta;

    @Column(name = "dez_a_me_quarenta")
    private ZonedDateTime dezAMeQuarenta;

    @Column(name = "dez_a_me_cinquenta")
    private ZonedDateTime dezAMeCinquenta;

    @Column(name = "dez_pm")
    private ZonedDateTime dezPM;

    @Column(name = "dez_p_me_dez")
    private ZonedDateTime dezPMeDez;

    @Column(name = "dez_p_me_vinte")
    private ZonedDateTime dezPMeVinte;

    @Column(name = "dez_p_me_trinta")
    private ZonedDateTime dezPMeTrinta;

    @Column(name = "dez_p_me_quarenta")
    private ZonedDateTime dezPMeQuarenta;

    @Column(name = "dez_p_me_cinquenta")
    private ZonedDateTime dezPMeCinquenta;

    @Column(name = "onze_am")
    private ZonedDateTime onzeAM;

    @Column(name = "onze_a_me_dez")
    private ZonedDateTime onzeAMeDez;

    @Column(name = "onze_a_me_vinte")
    private ZonedDateTime onzeAMeVinte;

    @Column(name = "onze_a_me_trinta")
    private ZonedDateTime onzeAMeTrinta;

    @Column(name = "onze_a_me_quarenta")
    private ZonedDateTime onzeAMeQuarenta;

    @Column(name = "onze_a_me_cinquenta")
    private ZonedDateTime onzeAMeCinquenta;

    @Column(name = "onze_pm")
    private ZonedDateTime onzePM;

    @Column(name = "onze_p_me_dez")
    private ZonedDateTime onzePMeDez;

    @Column(name = "onze_p_me_vinte")
    private ZonedDateTime onzePMeVinte;

    @Column(name = "onze_p_me_trinta")
    private ZonedDateTime onzePMeTrinta;

    @Column(name = "onze_p_me_quarenta")
    private ZonedDateTime onzePMeQuarenta;

    @Column(name = "onze_p_me_cinquenta")
    private ZonedDateTime onzePMeCinquenta;

    @ManyToOne
    @JsonIgnoreProperties(value = { "blocos" }, allowSetters = true)
    private CronogramaDiario cronogramaDiario;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Blocos id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getZeroAM() {
        return this.zeroAM;
    }

    public Blocos zeroAM(ZonedDateTime zeroAM) {
        this.setZeroAM(zeroAM);
        return this;
    }

    public void setZeroAM(ZonedDateTime zeroAM) {
        this.zeroAM = zeroAM;
    }

    public ZonedDateTime getZeroAMeDez() {
        return this.zeroAMeDez;
    }

    public Blocos zeroAMeDez(ZonedDateTime zeroAMeDez) {
        this.setZeroAMeDez(zeroAMeDez);
        return this;
    }

    public void setZeroAMeDez(ZonedDateTime zeroAMeDez) {
        this.zeroAMeDez = zeroAMeDez;
    }

    public ZonedDateTime getZeroAMeVinte() {
        return this.zeroAMeVinte;
    }

    public Blocos zeroAMeVinte(ZonedDateTime zeroAMeVinte) {
        this.setZeroAMeVinte(zeroAMeVinte);
        return this;
    }

    public void setZeroAMeVinte(ZonedDateTime zeroAMeVinte) {
        this.zeroAMeVinte = zeroAMeVinte;
    }

    public ZonedDateTime getZeroAMeTrinta() {
        return this.zeroAMeTrinta;
    }

    public Blocos zeroAMeTrinta(ZonedDateTime zeroAMeTrinta) {
        this.setZeroAMeTrinta(zeroAMeTrinta);
        return this;
    }

    public void setZeroAMeTrinta(ZonedDateTime zeroAMeTrinta) {
        this.zeroAMeTrinta = zeroAMeTrinta;
    }

    public ZonedDateTime getZeroAMeQuarenta() {
        return this.zeroAMeQuarenta;
    }

    public Blocos zeroAMeQuarenta(ZonedDateTime zeroAMeQuarenta) {
        this.setZeroAMeQuarenta(zeroAMeQuarenta);
        return this;
    }

    public void setZeroAMeQuarenta(ZonedDateTime zeroAMeQuarenta) {
        this.zeroAMeQuarenta = zeroAMeQuarenta;
    }

    public ZonedDateTime getZeroAMeCinquenta() {
        return this.zeroAMeCinquenta;
    }

    public Blocos zeroAMeCinquenta(ZonedDateTime zeroAMeCinquenta) {
        this.setZeroAMeCinquenta(zeroAMeCinquenta);
        return this;
    }

    public void setZeroAMeCinquenta(ZonedDateTime zeroAMeCinquenta) {
        this.zeroAMeCinquenta = zeroAMeCinquenta;
    }

    public ZonedDateTime getZeroPM() {
        return this.zeroPM;
    }

    public Blocos zeroPM(ZonedDateTime zeroPM) {
        this.setZeroPM(zeroPM);
        return this;
    }

    public void setZeroPM(ZonedDateTime zeroPM) {
        this.zeroPM = zeroPM;
    }

    public ZonedDateTime getZeroPMeDez() {
        return this.zeroPMeDez;
    }

    public Blocos zeroPMeDez(ZonedDateTime zeroPMeDez) {
        this.setZeroPMeDez(zeroPMeDez);
        return this;
    }

    public void setZeroPMeDez(ZonedDateTime zeroPMeDez) {
        this.zeroPMeDez = zeroPMeDez;
    }

    public ZonedDateTime getZeroPMeVinte() {
        return this.zeroPMeVinte;
    }

    public Blocos zeroPMeVinte(ZonedDateTime zeroPMeVinte) {
        this.setZeroPMeVinte(zeroPMeVinte);
        return this;
    }

    public void setZeroPMeVinte(ZonedDateTime zeroPMeVinte) {
        this.zeroPMeVinte = zeroPMeVinte;
    }

    public ZonedDateTime getZeroPMeTrinta() {
        return this.zeroPMeTrinta;
    }

    public Blocos zeroPMeTrinta(ZonedDateTime zeroPMeTrinta) {
        this.setZeroPMeTrinta(zeroPMeTrinta);
        return this;
    }

    public void setZeroPMeTrinta(ZonedDateTime zeroPMeTrinta) {
        this.zeroPMeTrinta = zeroPMeTrinta;
    }

    public ZonedDateTime getZeroPMeQuarenta() {
        return this.zeroPMeQuarenta;
    }

    public Blocos zeroPMeQuarenta(ZonedDateTime zeroPMeQuarenta) {
        this.setZeroPMeQuarenta(zeroPMeQuarenta);
        return this;
    }

    public void setZeroPMeQuarenta(ZonedDateTime zeroPMeQuarenta) {
        this.zeroPMeQuarenta = zeroPMeQuarenta;
    }

    public ZonedDateTime getZeroPMeCinquenta() {
        return this.zeroPMeCinquenta;
    }

    public Blocos zeroPMeCinquenta(ZonedDateTime zeroPMeCinquenta) {
        this.setZeroPMeCinquenta(zeroPMeCinquenta);
        return this;
    }

    public void setZeroPMeCinquenta(ZonedDateTime zeroPMeCinquenta) {
        this.zeroPMeCinquenta = zeroPMeCinquenta;
    }

    public ZonedDateTime getUmAM() {
        return this.umAM;
    }

    public Blocos umAM(ZonedDateTime umAM) {
        this.setUmAM(umAM);
        return this;
    }

    public void setUmAM(ZonedDateTime umAM) {
        this.umAM = umAM;
    }

    public ZonedDateTime getUmAMeDez() {
        return this.umAMeDez;
    }

    public Blocos umAMeDez(ZonedDateTime umAMeDez) {
        this.setUmAMeDez(umAMeDez);
        return this;
    }

    public void setUmAMeDez(ZonedDateTime umAMeDez) {
        this.umAMeDez = umAMeDez;
    }

    public ZonedDateTime getUmAMeVinte() {
        return this.umAMeVinte;
    }

    public Blocos umAMeVinte(ZonedDateTime umAMeVinte) {
        this.setUmAMeVinte(umAMeVinte);
        return this;
    }

    public void setUmAMeVinte(ZonedDateTime umAMeVinte) {
        this.umAMeVinte = umAMeVinte;
    }

    public ZonedDateTime getUmAMeTrinta() {
        return this.umAMeTrinta;
    }

    public Blocos umAMeTrinta(ZonedDateTime umAMeTrinta) {
        this.setUmAMeTrinta(umAMeTrinta);
        return this;
    }

    public void setUmAMeTrinta(ZonedDateTime umAMeTrinta) {
        this.umAMeTrinta = umAMeTrinta;
    }

    public ZonedDateTime getUmAMeQuarenta() {
        return this.umAMeQuarenta;
    }

    public Blocos umAMeQuarenta(ZonedDateTime umAMeQuarenta) {
        this.setUmAMeQuarenta(umAMeQuarenta);
        return this;
    }

    public void setUmAMeQuarenta(ZonedDateTime umAMeQuarenta) {
        this.umAMeQuarenta = umAMeQuarenta;
    }

    public ZonedDateTime getUmAMeCinquenta() {
        return this.umAMeCinquenta;
    }

    public Blocos umAMeCinquenta(ZonedDateTime umAMeCinquenta) {
        this.setUmAMeCinquenta(umAMeCinquenta);
        return this;
    }

    public void setUmAMeCinquenta(ZonedDateTime umAMeCinquenta) {
        this.umAMeCinquenta = umAMeCinquenta;
    }

    public ZonedDateTime getUmPM() {
        return this.umPM;
    }

    public Blocos umPM(ZonedDateTime umPM) {
        this.setUmPM(umPM);
        return this;
    }

    public void setUmPM(ZonedDateTime umPM) {
        this.umPM = umPM;
    }

    public ZonedDateTime getUmPMeDez() {
        return this.umPMeDez;
    }

    public Blocos umPMeDez(ZonedDateTime umPMeDez) {
        this.setUmPMeDez(umPMeDez);
        return this;
    }

    public void setUmPMeDez(ZonedDateTime umPMeDez) {
        this.umPMeDez = umPMeDez;
    }

    public ZonedDateTime getUmPMeVinte() {
        return this.umPMeVinte;
    }

    public Blocos umPMeVinte(ZonedDateTime umPMeVinte) {
        this.setUmPMeVinte(umPMeVinte);
        return this;
    }

    public void setUmPMeVinte(ZonedDateTime umPMeVinte) {
        this.umPMeVinte = umPMeVinte;
    }

    public ZonedDateTime getUmPMeTrinta() {
        return this.umPMeTrinta;
    }

    public Blocos umPMeTrinta(ZonedDateTime umPMeTrinta) {
        this.setUmPMeTrinta(umPMeTrinta);
        return this;
    }

    public void setUmPMeTrinta(ZonedDateTime umPMeTrinta) {
        this.umPMeTrinta = umPMeTrinta;
    }

    public ZonedDateTime getUmPMeQuarenta() {
        return this.umPMeQuarenta;
    }

    public Blocos umPMeQuarenta(ZonedDateTime umPMeQuarenta) {
        this.setUmPMeQuarenta(umPMeQuarenta);
        return this;
    }

    public void setUmPMeQuarenta(ZonedDateTime umPMeQuarenta) {
        this.umPMeQuarenta = umPMeQuarenta;
    }

    public ZonedDateTime getUmPMeCinquenta() {
        return this.umPMeCinquenta;
    }

    public Blocos umPMeCinquenta(ZonedDateTime umPMeCinquenta) {
        this.setUmPMeCinquenta(umPMeCinquenta);
        return this;
    }

    public void setUmPMeCinquenta(ZonedDateTime umPMeCinquenta) {
        this.umPMeCinquenta = umPMeCinquenta;
    }

    public ZonedDateTime getDoisAM() {
        return this.doisAM;
    }

    public Blocos doisAM(ZonedDateTime doisAM) {
        this.setDoisAM(doisAM);
        return this;
    }

    public void setDoisAM(ZonedDateTime doisAM) {
        this.doisAM = doisAM;
    }

    public ZonedDateTime getDoisAMeDez() {
        return this.doisAMeDez;
    }

    public Blocos doisAMeDez(ZonedDateTime doisAMeDez) {
        this.setDoisAMeDez(doisAMeDez);
        return this;
    }

    public void setDoisAMeDez(ZonedDateTime doisAMeDez) {
        this.doisAMeDez = doisAMeDez;
    }

    public ZonedDateTime getDoisAMeVinte() {
        return this.doisAMeVinte;
    }

    public Blocos doisAMeVinte(ZonedDateTime doisAMeVinte) {
        this.setDoisAMeVinte(doisAMeVinte);
        return this;
    }

    public void setDoisAMeVinte(ZonedDateTime doisAMeVinte) {
        this.doisAMeVinte = doisAMeVinte;
    }

    public ZonedDateTime getDoisAMeTrinta() {
        return this.doisAMeTrinta;
    }

    public Blocos doisAMeTrinta(ZonedDateTime doisAMeTrinta) {
        this.setDoisAMeTrinta(doisAMeTrinta);
        return this;
    }

    public void setDoisAMeTrinta(ZonedDateTime doisAMeTrinta) {
        this.doisAMeTrinta = doisAMeTrinta;
    }

    public ZonedDateTime getDoisAMeQuarenta() {
        return this.doisAMeQuarenta;
    }

    public Blocos doisAMeQuarenta(ZonedDateTime doisAMeQuarenta) {
        this.setDoisAMeQuarenta(doisAMeQuarenta);
        return this;
    }

    public void setDoisAMeQuarenta(ZonedDateTime doisAMeQuarenta) {
        this.doisAMeQuarenta = doisAMeQuarenta;
    }

    public ZonedDateTime getDoisAMeCinquenta() {
        return this.doisAMeCinquenta;
    }

    public Blocos doisAMeCinquenta(ZonedDateTime doisAMeCinquenta) {
        this.setDoisAMeCinquenta(doisAMeCinquenta);
        return this;
    }

    public void setDoisAMeCinquenta(ZonedDateTime doisAMeCinquenta) {
        this.doisAMeCinquenta = doisAMeCinquenta;
    }

    public ZonedDateTime getDoisPM() {
        return this.doisPM;
    }

    public Blocos doisPM(ZonedDateTime doisPM) {
        this.setDoisPM(doisPM);
        return this;
    }

    public void setDoisPM(ZonedDateTime doisPM) {
        this.doisPM = doisPM;
    }

    public ZonedDateTime getDoisPMeDez() {
        return this.doisPMeDez;
    }

    public Blocos doisPMeDez(ZonedDateTime doisPMeDez) {
        this.setDoisPMeDez(doisPMeDez);
        return this;
    }

    public void setDoisPMeDez(ZonedDateTime doisPMeDez) {
        this.doisPMeDez = doisPMeDez;
    }

    public ZonedDateTime getDoisPMeVinte() {
        return this.doisPMeVinte;
    }

    public Blocos doisPMeVinte(ZonedDateTime doisPMeVinte) {
        this.setDoisPMeVinte(doisPMeVinte);
        return this;
    }

    public void setDoisPMeVinte(ZonedDateTime doisPMeVinte) {
        this.doisPMeVinte = doisPMeVinte;
    }

    public ZonedDateTime getDoisPMeTrinta() {
        return this.doisPMeTrinta;
    }

    public Blocos doisPMeTrinta(ZonedDateTime doisPMeTrinta) {
        this.setDoisPMeTrinta(doisPMeTrinta);
        return this;
    }

    public void setDoisPMeTrinta(ZonedDateTime doisPMeTrinta) {
        this.doisPMeTrinta = doisPMeTrinta;
    }

    public ZonedDateTime getDoisPMeQuarenta() {
        return this.doisPMeQuarenta;
    }

    public Blocos doisPMeQuarenta(ZonedDateTime doisPMeQuarenta) {
        this.setDoisPMeQuarenta(doisPMeQuarenta);
        return this;
    }

    public void setDoisPMeQuarenta(ZonedDateTime doisPMeQuarenta) {
        this.doisPMeQuarenta = doisPMeQuarenta;
    }

    public ZonedDateTime getDoisPMeCinquenta() {
        return this.doisPMeCinquenta;
    }

    public Blocos doisPMeCinquenta(ZonedDateTime doisPMeCinquenta) {
        this.setDoisPMeCinquenta(doisPMeCinquenta);
        return this;
    }

    public void setDoisPMeCinquenta(ZonedDateTime doisPMeCinquenta) {
        this.doisPMeCinquenta = doisPMeCinquenta;
    }

    public ZonedDateTime getTresAM() {
        return this.tresAM;
    }

    public Blocos tresAM(ZonedDateTime tresAM) {
        this.setTresAM(tresAM);
        return this;
    }

    public void setTresAM(ZonedDateTime tresAM) {
        this.tresAM = tresAM;
    }

    public ZonedDateTime getTresAMeDez() {
        return this.tresAMeDez;
    }

    public Blocos tresAMeDez(ZonedDateTime tresAMeDez) {
        this.setTresAMeDez(tresAMeDez);
        return this;
    }

    public void setTresAMeDez(ZonedDateTime tresAMeDez) {
        this.tresAMeDez = tresAMeDez;
    }

    public ZonedDateTime getTresAMeVinte() {
        return this.tresAMeVinte;
    }

    public Blocos tresAMeVinte(ZonedDateTime tresAMeVinte) {
        this.setTresAMeVinte(tresAMeVinte);
        return this;
    }

    public void setTresAMeVinte(ZonedDateTime tresAMeVinte) {
        this.tresAMeVinte = tresAMeVinte;
    }

    public ZonedDateTime getTresAMeTrinta() {
        return this.tresAMeTrinta;
    }

    public Blocos tresAMeTrinta(ZonedDateTime tresAMeTrinta) {
        this.setTresAMeTrinta(tresAMeTrinta);
        return this;
    }

    public void setTresAMeTrinta(ZonedDateTime tresAMeTrinta) {
        this.tresAMeTrinta = tresAMeTrinta;
    }

    public ZonedDateTime getTresAMeQuarenta() {
        return this.tresAMeQuarenta;
    }

    public Blocos tresAMeQuarenta(ZonedDateTime tresAMeQuarenta) {
        this.setTresAMeQuarenta(tresAMeQuarenta);
        return this;
    }

    public void setTresAMeQuarenta(ZonedDateTime tresAMeQuarenta) {
        this.tresAMeQuarenta = tresAMeQuarenta;
    }

    public ZonedDateTime getTresAMeCinquenta() {
        return this.tresAMeCinquenta;
    }

    public Blocos tresAMeCinquenta(ZonedDateTime tresAMeCinquenta) {
        this.setTresAMeCinquenta(tresAMeCinquenta);
        return this;
    }

    public void setTresAMeCinquenta(ZonedDateTime tresAMeCinquenta) {
        this.tresAMeCinquenta = tresAMeCinquenta;
    }

    public ZonedDateTime getTresPM() {
        return this.tresPM;
    }

    public Blocos tresPM(ZonedDateTime tresPM) {
        this.setTresPM(tresPM);
        return this;
    }

    public void setTresPM(ZonedDateTime tresPM) {
        this.tresPM = tresPM;
    }

    public ZonedDateTime getTresPMeDez() {
        return this.tresPMeDez;
    }

    public Blocos tresPMeDez(ZonedDateTime tresPMeDez) {
        this.setTresPMeDez(tresPMeDez);
        return this;
    }

    public void setTresPMeDez(ZonedDateTime tresPMeDez) {
        this.tresPMeDez = tresPMeDez;
    }

    public ZonedDateTime getTresPMeVinte() {
        return this.tresPMeVinte;
    }

    public Blocos tresPMeVinte(ZonedDateTime tresPMeVinte) {
        this.setTresPMeVinte(tresPMeVinte);
        return this;
    }

    public void setTresPMeVinte(ZonedDateTime tresPMeVinte) {
        this.tresPMeVinte = tresPMeVinte;
    }

    public ZonedDateTime getTresPMeTrinta() {
        return this.tresPMeTrinta;
    }

    public Blocos tresPMeTrinta(ZonedDateTime tresPMeTrinta) {
        this.setTresPMeTrinta(tresPMeTrinta);
        return this;
    }

    public void setTresPMeTrinta(ZonedDateTime tresPMeTrinta) {
        this.tresPMeTrinta = tresPMeTrinta;
    }

    public ZonedDateTime getTresPMeQuarenta() {
        return this.tresPMeQuarenta;
    }

    public Blocos tresPMeQuarenta(ZonedDateTime tresPMeQuarenta) {
        this.setTresPMeQuarenta(tresPMeQuarenta);
        return this;
    }

    public void setTresPMeQuarenta(ZonedDateTime tresPMeQuarenta) {
        this.tresPMeQuarenta = tresPMeQuarenta;
    }

    public ZonedDateTime getTresPMeCinquenta() {
        return this.tresPMeCinquenta;
    }

    public Blocos tresPMeCinquenta(ZonedDateTime tresPMeCinquenta) {
        this.setTresPMeCinquenta(tresPMeCinquenta);
        return this;
    }

    public void setTresPMeCinquenta(ZonedDateTime tresPMeCinquenta) {
        this.tresPMeCinquenta = tresPMeCinquenta;
    }

    public ZonedDateTime getQuatroAM() {
        return this.quatroAM;
    }

    public Blocos quatroAM(ZonedDateTime quatroAM) {
        this.setQuatroAM(quatroAM);
        return this;
    }

    public void setQuatroAM(ZonedDateTime quatroAM) {
        this.quatroAM = quatroAM;
    }

    public ZonedDateTime getQuatroAMeDez() {
        return this.quatroAMeDez;
    }

    public Blocos quatroAMeDez(ZonedDateTime quatroAMeDez) {
        this.setQuatroAMeDez(quatroAMeDez);
        return this;
    }

    public void setQuatroAMeDez(ZonedDateTime quatroAMeDez) {
        this.quatroAMeDez = quatroAMeDez;
    }

    public ZonedDateTime getQuatroAMeVinte() {
        return this.quatroAMeVinte;
    }

    public Blocos quatroAMeVinte(ZonedDateTime quatroAMeVinte) {
        this.setQuatroAMeVinte(quatroAMeVinte);
        return this;
    }

    public void setQuatroAMeVinte(ZonedDateTime quatroAMeVinte) {
        this.quatroAMeVinte = quatroAMeVinte;
    }

    public ZonedDateTime getQuatroAMeTrinta() {
        return this.quatroAMeTrinta;
    }

    public Blocos quatroAMeTrinta(ZonedDateTime quatroAMeTrinta) {
        this.setQuatroAMeTrinta(quatroAMeTrinta);
        return this;
    }

    public void setQuatroAMeTrinta(ZonedDateTime quatroAMeTrinta) {
        this.quatroAMeTrinta = quatroAMeTrinta;
    }

    public ZonedDateTime getQuatroAMeQuarenta() {
        return this.quatroAMeQuarenta;
    }

    public Blocos quatroAMeQuarenta(ZonedDateTime quatroAMeQuarenta) {
        this.setQuatroAMeQuarenta(quatroAMeQuarenta);
        return this;
    }

    public void setQuatroAMeQuarenta(ZonedDateTime quatroAMeQuarenta) {
        this.quatroAMeQuarenta = quatroAMeQuarenta;
    }

    public ZonedDateTime getQuatroAMeCinquenta() {
        return this.quatroAMeCinquenta;
    }

    public Blocos quatroAMeCinquenta(ZonedDateTime quatroAMeCinquenta) {
        this.setQuatroAMeCinquenta(quatroAMeCinquenta);
        return this;
    }

    public void setQuatroAMeCinquenta(ZonedDateTime quatroAMeCinquenta) {
        this.quatroAMeCinquenta = quatroAMeCinquenta;
    }

    public ZonedDateTime getQuatroPM() {
        return this.quatroPM;
    }

    public Blocos quatroPM(ZonedDateTime quatroPM) {
        this.setQuatroPM(quatroPM);
        return this;
    }

    public void setQuatroPM(ZonedDateTime quatroPM) {
        this.quatroPM = quatroPM;
    }

    public ZonedDateTime getQuatroPMeDez() {
        return this.quatroPMeDez;
    }

    public Blocos quatroPMeDez(ZonedDateTime quatroPMeDez) {
        this.setQuatroPMeDez(quatroPMeDez);
        return this;
    }

    public void setQuatroPMeDez(ZonedDateTime quatroPMeDez) {
        this.quatroPMeDez = quatroPMeDez;
    }

    public ZonedDateTime getQuatroPMeVinte() {
        return this.quatroPMeVinte;
    }

    public Blocos quatroPMeVinte(ZonedDateTime quatroPMeVinte) {
        this.setQuatroPMeVinte(quatroPMeVinte);
        return this;
    }

    public void setQuatroPMeVinte(ZonedDateTime quatroPMeVinte) {
        this.quatroPMeVinte = quatroPMeVinte;
    }

    public ZonedDateTime getQuatroPMeTrinta() {
        return this.quatroPMeTrinta;
    }

    public Blocos quatroPMeTrinta(ZonedDateTime quatroPMeTrinta) {
        this.setQuatroPMeTrinta(quatroPMeTrinta);
        return this;
    }

    public void setQuatroPMeTrinta(ZonedDateTime quatroPMeTrinta) {
        this.quatroPMeTrinta = quatroPMeTrinta;
    }

    public ZonedDateTime getQuatroPMeQuarenta() {
        return this.quatroPMeQuarenta;
    }

    public Blocos quatroPMeQuarenta(ZonedDateTime quatroPMeQuarenta) {
        this.setQuatroPMeQuarenta(quatroPMeQuarenta);
        return this;
    }

    public void setQuatroPMeQuarenta(ZonedDateTime quatroPMeQuarenta) {
        this.quatroPMeQuarenta = quatroPMeQuarenta;
    }

    public ZonedDateTime getQuatroPMeCinquenta() {
        return this.quatroPMeCinquenta;
    }

    public Blocos quatroPMeCinquenta(ZonedDateTime quatroPMeCinquenta) {
        this.setQuatroPMeCinquenta(quatroPMeCinquenta);
        return this;
    }

    public void setQuatroPMeCinquenta(ZonedDateTime quatroPMeCinquenta) {
        this.quatroPMeCinquenta = quatroPMeCinquenta;
    }

    public ZonedDateTime getCincoAM() {
        return this.cincoAM;
    }

    public Blocos cincoAM(ZonedDateTime cincoAM) {
        this.setCincoAM(cincoAM);
        return this;
    }

    public void setCincoAM(ZonedDateTime cincoAM) {
        this.cincoAM = cincoAM;
    }

    public ZonedDateTime getCincoAMeDez() {
        return this.cincoAMeDez;
    }

    public Blocos cincoAMeDez(ZonedDateTime cincoAMeDez) {
        this.setCincoAMeDez(cincoAMeDez);
        return this;
    }

    public void setCincoAMeDez(ZonedDateTime cincoAMeDez) {
        this.cincoAMeDez = cincoAMeDez;
    }

    public ZonedDateTime getCincoAMeVinte() {
        return this.cincoAMeVinte;
    }

    public Blocos cincoAMeVinte(ZonedDateTime cincoAMeVinte) {
        this.setCincoAMeVinte(cincoAMeVinte);
        return this;
    }

    public void setCincoAMeVinte(ZonedDateTime cincoAMeVinte) {
        this.cincoAMeVinte = cincoAMeVinte;
    }

    public ZonedDateTime getCincoAMeTrinta() {
        return this.cincoAMeTrinta;
    }

    public Blocos cincoAMeTrinta(ZonedDateTime cincoAMeTrinta) {
        this.setCincoAMeTrinta(cincoAMeTrinta);
        return this;
    }

    public void setCincoAMeTrinta(ZonedDateTime cincoAMeTrinta) {
        this.cincoAMeTrinta = cincoAMeTrinta;
    }

    public ZonedDateTime getCincoAMeQuarenta() {
        return this.cincoAMeQuarenta;
    }

    public Blocos cincoAMeQuarenta(ZonedDateTime cincoAMeQuarenta) {
        this.setCincoAMeQuarenta(cincoAMeQuarenta);
        return this;
    }

    public void setCincoAMeQuarenta(ZonedDateTime cincoAMeQuarenta) {
        this.cincoAMeQuarenta = cincoAMeQuarenta;
    }

    public ZonedDateTime getCincoAMeCinquenta() {
        return this.cincoAMeCinquenta;
    }

    public Blocos cincoAMeCinquenta(ZonedDateTime cincoAMeCinquenta) {
        this.setCincoAMeCinquenta(cincoAMeCinquenta);
        return this;
    }

    public void setCincoAMeCinquenta(ZonedDateTime cincoAMeCinquenta) {
        this.cincoAMeCinquenta = cincoAMeCinquenta;
    }

    public ZonedDateTime getCincoPM() {
        return this.cincoPM;
    }

    public Blocos cincoPM(ZonedDateTime cincoPM) {
        this.setCincoPM(cincoPM);
        return this;
    }

    public void setCincoPM(ZonedDateTime cincoPM) {
        this.cincoPM = cincoPM;
    }

    public ZonedDateTime getCincoPMeDez() {
        return this.cincoPMeDez;
    }

    public Blocos cincoPMeDez(ZonedDateTime cincoPMeDez) {
        this.setCincoPMeDez(cincoPMeDez);
        return this;
    }

    public void setCincoPMeDez(ZonedDateTime cincoPMeDez) {
        this.cincoPMeDez = cincoPMeDez;
    }

    public ZonedDateTime getCincoPMeVinte() {
        return this.cincoPMeVinte;
    }

    public Blocos cincoPMeVinte(ZonedDateTime cincoPMeVinte) {
        this.setCincoPMeVinte(cincoPMeVinte);
        return this;
    }

    public void setCincoPMeVinte(ZonedDateTime cincoPMeVinte) {
        this.cincoPMeVinte = cincoPMeVinte;
    }

    public ZonedDateTime getCincoPMeTrinta() {
        return this.cincoPMeTrinta;
    }

    public Blocos cincoPMeTrinta(ZonedDateTime cincoPMeTrinta) {
        this.setCincoPMeTrinta(cincoPMeTrinta);
        return this;
    }

    public void setCincoPMeTrinta(ZonedDateTime cincoPMeTrinta) {
        this.cincoPMeTrinta = cincoPMeTrinta;
    }

    public ZonedDateTime getCincoPMeQuarenta() {
        return this.cincoPMeQuarenta;
    }

    public Blocos cincoPMeQuarenta(ZonedDateTime cincoPMeQuarenta) {
        this.setCincoPMeQuarenta(cincoPMeQuarenta);
        return this;
    }

    public void setCincoPMeQuarenta(ZonedDateTime cincoPMeQuarenta) {
        this.cincoPMeQuarenta = cincoPMeQuarenta;
    }

    public ZonedDateTime getCincoPMeCinquenta() {
        return this.cincoPMeCinquenta;
    }

    public Blocos cincoPMeCinquenta(ZonedDateTime cincoPMeCinquenta) {
        this.setCincoPMeCinquenta(cincoPMeCinquenta);
        return this;
    }

    public void setCincoPMeCinquenta(ZonedDateTime cincoPMeCinquenta) {
        this.cincoPMeCinquenta = cincoPMeCinquenta;
    }

    public ZonedDateTime getSeisAM() {
        return this.seisAM;
    }

    public Blocos seisAM(ZonedDateTime seisAM) {
        this.setSeisAM(seisAM);
        return this;
    }

    public void setSeisAM(ZonedDateTime seisAM) {
        this.seisAM = seisAM;
    }

    public ZonedDateTime getSeisAMeDez() {
        return this.seisAMeDez;
    }

    public Blocos seisAMeDez(ZonedDateTime seisAMeDez) {
        this.setSeisAMeDez(seisAMeDez);
        return this;
    }

    public void setSeisAMeDez(ZonedDateTime seisAMeDez) {
        this.seisAMeDez = seisAMeDez;
    }

    public ZonedDateTime getSeisAMeVinte() {
        return this.seisAMeVinte;
    }

    public Blocos seisAMeVinte(ZonedDateTime seisAMeVinte) {
        this.setSeisAMeVinte(seisAMeVinte);
        return this;
    }

    public void setSeisAMeVinte(ZonedDateTime seisAMeVinte) {
        this.seisAMeVinte = seisAMeVinte;
    }

    public ZonedDateTime getSeisAMeTrinta() {
        return this.seisAMeTrinta;
    }

    public Blocos seisAMeTrinta(ZonedDateTime seisAMeTrinta) {
        this.setSeisAMeTrinta(seisAMeTrinta);
        return this;
    }

    public void setSeisAMeTrinta(ZonedDateTime seisAMeTrinta) {
        this.seisAMeTrinta = seisAMeTrinta;
    }

    public ZonedDateTime getSeisAMeQuarenta() {
        return this.seisAMeQuarenta;
    }

    public Blocos seisAMeQuarenta(ZonedDateTime seisAMeQuarenta) {
        this.setSeisAMeQuarenta(seisAMeQuarenta);
        return this;
    }

    public void setSeisAMeQuarenta(ZonedDateTime seisAMeQuarenta) {
        this.seisAMeQuarenta = seisAMeQuarenta;
    }

    public ZonedDateTime getSeisAMeCinquenta() {
        return this.seisAMeCinquenta;
    }

    public Blocos seisAMeCinquenta(ZonedDateTime seisAMeCinquenta) {
        this.setSeisAMeCinquenta(seisAMeCinquenta);
        return this;
    }

    public void setSeisAMeCinquenta(ZonedDateTime seisAMeCinquenta) {
        this.seisAMeCinquenta = seisAMeCinquenta;
    }

    public ZonedDateTime getSeisPM() {
        return this.seisPM;
    }

    public Blocos seisPM(ZonedDateTime seisPM) {
        this.setSeisPM(seisPM);
        return this;
    }

    public void setSeisPM(ZonedDateTime seisPM) {
        this.seisPM = seisPM;
    }

    public ZonedDateTime getSeisPMeDez() {
        return this.seisPMeDez;
    }

    public Blocos seisPMeDez(ZonedDateTime seisPMeDez) {
        this.setSeisPMeDez(seisPMeDez);
        return this;
    }

    public void setSeisPMeDez(ZonedDateTime seisPMeDez) {
        this.seisPMeDez = seisPMeDez;
    }

    public ZonedDateTime getSeisPMeVinte() {
        return this.seisPMeVinte;
    }

    public Blocos seisPMeVinte(ZonedDateTime seisPMeVinte) {
        this.setSeisPMeVinte(seisPMeVinte);
        return this;
    }

    public void setSeisPMeVinte(ZonedDateTime seisPMeVinte) {
        this.seisPMeVinte = seisPMeVinte;
    }

    public ZonedDateTime getSeisPMeTrinta() {
        return this.seisPMeTrinta;
    }

    public Blocos seisPMeTrinta(ZonedDateTime seisPMeTrinta) {
        this.setSeisPMeTrinta(seisPMeTrinta);
        return this;
    }

    public void setSeisPMeTrinta(ZonedDateTime seisPMeTrinta) {
        this.seisPMeTrinta = seisPMeTrinta;
    }

    public ZonedDateTime getSeisPMeQuarenta() {
        return this.seisPMeQuarenta;
    }

    public Blocos seisPMeQuarenta(ZonedDateTime seisPMeQuarenta) {
        this.setSeisPMeQuarenta(seisPMeQuarenta);
        return this;
    }

    public void setSeisPMeQuarenta(ZonedDateTime seisPMeQuarenta) {
        this.seisPMeQuarenta = seisPMeQuarenta;
    }

    public ZonedDateTime getSeisPMeCinquenta() {
        return this.seisPMeCinquenta;
    }

    public Blocos seisPMeCinquenta(ZonedDateTime seisPMeCinquenta) {
        this.setSeisPMeCinquenta(seisPMeCinquenta);
        return this;
    }

    public void setSeisPMeCinquenta(ZonedDateTime seisPMeCinquenta) {
        this.seisPMeCinquenta = seisPMeCinquenta;
    }

    public ZonedDateTime getSeteAM() {
        return this.seteAM;
    }

    public Blocos seteAM(ZonedDateTime seteAM) {
        this.setSeteAM(seteAM);
        return this;
    }

    public void setSeteAM(ZonedDateTime seteAM) {
        this.seteAM = seteAM;
    }

    public ZonedDateTime getSeteAMeDez() {
        return this.seteAMeDez;
    }

    public Blocos seteAMeDez(ZonedDateTime seteAMeDez) {
        this.setSeteAMeDez(seteAMeDez);
        return this;
    }

    public void setSeteAMeDez(ZonedDateTime seteAMeDez) {
        this.seteAMeDez = seteAMeDez;
    }

    public ZonedDateTime getSeteAMeVinte() {
        return this.seteAMeVinte;
    }

    public Blocos seteAMeVinte(ZonedDateTime seteAMeVinte) {
        this.setSeteAMeVinte(seteAMeVinte);
        return this;
    }

    public void setSeteAMeVinte(ZonedDateTime seteAMeVinte) {
        this.seteAMeVinte = seteAMeVinte;
    }

    public ZonedDateTime getSeteAMeTrinta() {
        return this.seteAMeTrinta;
    }

    public Blocos seteAMeTrinta(ZonedDateTime seteAMeTrinta) {
        this.setSeteAMeTrinta(seteAMeTrinta);
        return this;
    }

    public void setSeteAMeTrinta(ZonedDateTime seteAMeTrinta) {
        this.seteAMeTrinta = seteAMeTrinta;
    }

    public ZonedDateTime getSeteAMeQuarenta() {
        return this.seteAMeQuarenta;
    }

    public Blocos seteAMeQuarenta(ZonedDateTime seteAMeQuarenta) {
        this.setSeteAMeQuarenta(seteAMeQuarenta);
        return this;
    }

    public void setSeteAMeQuarenta(ZonedDateTime seteAMeQuarenta) {
        this.seteAMeQuarenta = seteAMeQuarenta;
    }

    public ZonedDateTime getSeteAMeCinquenta() {
        return this.seteAMeCinquenta;
    }

    public Blocos seteAMeCinquenta(ZonedDateTime seteAMeCinquenta) {
        this.setSeteAMeCinquenta(seteAMeCinquenta);
        return this;
    }

    public void setSeteAMeCinquenta(ZonedDateTime seteAMeCinquenta) {
        this.seteAMeCinquenta = seteAMeCinquenta;
    }

    public ZonedDateTime getSetePM() {
        return this.setePM;
    }

    public Blocos setePM(ZonedDateTime setePM) {
        this.setSetePM(setePM);
        return this;
    }

    public void setSetePM(ZonedDateTime setePM) {
        this.setePM = setePM;
    }

    public ZonedDateTime getSetePMeDez() {
        return this.setePMeDez;
    }

    public Blocos setePMeDez(ZonedDateTime setePMeDez) {
        this.setSetePMeDez(setePMeDez);
        return this;
    }

    public void setSetePMeDez(ZonedDateTime setePMeDez) {
        this.setePMeDez = setePMeDez;
    }

    public ZonedDateTime getSetePMeVinte() {
        return this.setePMeVinte;
    }

    public Blocos setePMeVinte(ZonedDateTime setePMeVinte) {
        this.setSetePMeVinte(setePMeVinte);
        return this;
    }

    public void setSetePMeVinte(ZonedDateTime setePMeVinte) {
        this.setePMeVinte = setePMeVinte;
    }

    public ZonedDateTime getSetePMeTrinta() {
        return this.setePMeTrinta;
    }

    public Blocos setePMeTrinta(ZonedDateTime setePMeTrinta) {
        this.setSetePMeTrinta(setePMeTrinta);
        return this;
    }

    public void setSetePMeTrinta(ZonedDateTime setePMeTrinta) {
        this.setePMeTrinta = setePMeTrinta;
    }

    public ZonedDateTime getSetePMeQuarenta() {
        return this.setePMeQuarenta;
    }

    public Blocos setePMeQuarenta(ZonedDateTime setePMeQuarenta) {
        this.setSetePMeQuarenta(setePMeQuarenta);
        return this;
    }

    public void setSetePMeQuarenta(ZonedDateTime setePMeQuarenta) {
        this.setePMeQuarenta = setePMeQuarenta;
    }

    public ZonedDateTime getSetePMeCinquenta() {
        return this.setePMeCinquenta;
    }

    public Blocos setePMeCinquenta(ZonedDateTime setePMeCinquenta) {
        this.setSetePMeCinquenta(setePMeCinquenta);
        return this;
    }

    public void setSetePMeCinquenta(ZonedDateTime setePMeCinquenta) {
        this.setePMeCinquenta = setePMeCinquenta;
    }

    public ZonedDateTime getOitoAM() {
        return this.oitoAM;
    }

    public Blocos oitoAM(ZonedDateTime oitoAM) {
        this.setOitoAM(oitoAM);
        return this;
    }

    public void setOitoAM(ZonedDateTime oitoAM) {
        this.oitoAM = oitoAM;
    }

    public ZonedDateTime getOitoAMeDez() {
        return this.oitoAMeDez;
    }

    public Blocos oitoAMeDez(ZonedDateTime oitoAMeDez) {
        this.setOitoAMeDez(oitoAMeDez);
        return this;
    }

    public void setOitoAMeDez(ZonedDateTime oitoAMeDez) {
        this.oitoAMeDez = oitoAMeDez;
    }

    public ZonedDateTime getOitoAMeVinte() {
        return this.oitoAMeVinte;
    }

    public Blocos oitoAMeVinte(ZonedDateTime oitoAMeVinte) {
        this.setOitoAMeVinte(oitoAMeVinte);
        return this;
    }

    public void setOitoAMeVinte(ZonedDateTime oitoAMeVinte) {
        this.oitoAMeVinte = oitoAMeVinte;
    }

    public ZonedDateTime getOitoAMeTrinta() {
        return this.oitoAMeTrinta;
    }

    public Blocos oitoAMeTrinta(ZonedDateTime oitoAMeTrinta) {
        this.setOitoAMeTrinta(oitoAMeTrinta);
        return this;
    }

    public void setOitoAMeTrinta(ZonedDateTime oitoAMeTrinta) {
        this.oitoAMeTrinta = oitoAMeTrinta;
    }

    public ZonedDateTime getOitoAMeQuarenta() {
        return this.oitoAMeQuarenta;
    }

    public Blocos oitoAMeQuarenta(ZonedDateTime oitoAMeQuarenta) {
        this.setOitoAMeQuarenta(oitoAMeQuarenta);
        return this;
    }

    public void setOitoAMeQuarenta(ZonedDateTime oitoAMeQuarenta) {
        this.oitoAMeQuarenta = oitoAMeQuarenta;
    }

    public ZonedDateTime getOitoAMeCinquenta() {
        return this.oitoAMeCinquenta;
    }

    public Blocos oitoAMeCinquenta(ZonedDateTime oitoAMeCinquenta) {
        this.setOitoAMeCinquenta(oitoAMeCinquenta);
        return this;
    }

    public void setOitoAMeCinquenta(ZonedDateTime oitoAMeCinquenta) {
        this.oitoAMeCinquenta = oitoAMeCinquenta;
    }

    public ZonedDateTime getOitoPM() {
        return this.oitoPM;
    }

    public Blocos oitoPM(ZonedDateTime oitoPM) {
        this.setOitoPM(oitoPM);
        return this;
    }

    public void setOitoPM(ZonedDateTime oitoPM) {
        this.oitoPM = oitoPM;
    }

    public ZonedDateTime getOitoPMeDez() {
        return this.oitoPMeDez;
    }

    public Blocos oitoPMeDez(ZonedDateTime oitoPMeDez) {
        this.setOitoPMeDez(oitoPMeDez);
        return this;
    }

    public void setOitoPMeDez(ZonedDateTime oitoPMeDez) {
        this.oitoPMeDez = oitoPMeDez;
    }

    public ZonedDateTime getOitoPMeVinte() {
        return this.oitoPMeVinte;
    }

    public Blocos oitoPMeVinte(ZonedDateTime oitoPMeVinte) {
        this.setOitoPMeVinte(oitoPMeVinte);
        return this;
    }

    public void setOitoPMeVinte(ZonedDateTime oitoPMeVinte) {
        this.oitoPMeVinte = oitoPMeVinte;
    }

    public ZonedDateTime getOitoPMeTrinta() {
        return this.oitoPMeTrinta;
    }

    public Blocos oitoPMeTrinta(ZonedDateTime oitoPMeTrinta) {
        this.setOitoPMeTrinta(oitoPMeTrinta);
        return this;
    }

    public void setOitoPMeTrinta(ZonedDateTime oitoPMeTrinta) {
        this.oitoPMeTrinta = oitoPMeTrinta;
    }

    public ZonedDateTime getOitoPMeQuarenta() {
        return this.oitoPMeQuarenta;
    }

    public Blocos oitoPMeQuarenta(ZonedDateTime oitoPMeQuarenta) {
        this.setOitoPMeQuarenta(oitoPMeQuarenta);
        return this;
    }

    public void setOitoPMeQuarenta(ZonedDateTime oitoPMeQuarenta) {
        this.oitoPMeQuarenta = oitoPMeQuarenta;
    }

    public ZonedDateTime getOitoPMeCinquenta() {
        return this.oitoPMeCinquenta;
    }

    public Blocos oitoPMeCinquenta(ZonedDateTime oitoPMeCinquenta) {
        this.setOitoPMeCinquenta(oitoPMeCinquenta);
        return this;
    }

    public void setOitoPMeCinquenta(ZonedDateTime oitoPMeCinquenta) {
        this.oitoPMeCinquenta = oitoPMeCinquenta;
    }

    public ZonedDateTime getNoveAM() {
        return this.noveAM;
    }

    public Blocos noveAM(ZonedDateTime noveAM) {
        this.setNoveAM(noveAM);
        return this;
    }

    public void setNoveAM(ZonedDateTime noveAM) {
        this.noveAM = noveAM;
    }

    public ZonedDateTime getNoveAMeDez() {
        return this.noveAMeDez;
    }

    public Blocos noveAMeDez(ZonedDateTime noveAMeDez) {
        this.setNoveAMeDez(noveAMeDez);
        return this;
    }

    public void setNoveAMeDez(ZonedDateTime noveAMeDez) {
        this.noveAMeDez = noveAMeDez;
    }

    public ZonedDateTime getNoveAMeVinte() {
        return this.noveAMeVinte;
    }

    public Blocos noveAMeVinte(ZonedDateTime noveAMeVinte) {
        this.setNoveAMeVinte(noveAMeVinte);
        return this;
    }

    public void setNoveAMeVinte(ZonedDateTime noveAMeVinte) {
        this.noveAMeVinte = noveAMeVinte;
    }

    public ZonedDateTime getNoveAMeTrinta() {
        return this.noveAMeTrinta;
    }

    public Blocos noveAMeTrinta(ZonedDateTime noveAMeTrinta) {
        this.setNoveAMeTrinta(noveAMeTrinta);
        return this;
    }

    public void setNoveAMeTrinta(ZonedDateTime noveAMeTrinta) {
        this.noveAMeTrinta = noveAMeTrinta;
    }

    public ZonedDateTime getNoveAMeQuarenta() {
        return this.noveAMeQuarenta;
    }

    public Blocos noveAMeQuarenta(ZonedDateTime noveAMeQuarenta) {
        this.setNoveAMeQuarenta(noveAMeQuarenta);
        return this;
    }

    public void setNoveAMeQuarenta(ZonedDateTime noveAMeQuarenta) {
        this.noveAMeQuarenta = noveAMeQuarenta;
    }

    public ZonedDateTime getNoveAMeCinquenta() {
        return this.noveAMeCinquenta;
    }

    public Blocos noveAMeCinquenta(ZonedDateTime noveAMeCinquenta) {
        this.setNoveAMeCinquenta(noveAMeCinquenta);
        return this;
    }

    public void setNoveAMeCinquenta(ZonedDateTime noveAMeCinquenta) {
        this.noveAMeCinquenta = noveAMeCinquenta;
    }

    public ZonedDateTime getNovePM() {
        return this.novePM;
    }

    public Blocos novePM(ZonedDateTime novePM) {
        this.setNovePM(novePM);
        return this;
    }

    public void setNovePM(ZonedDateTime novePM) {
        this.novePM = novePM;
    }

    public ZonedDateTime getNovePMeDez() {
        return this.novePMeDez;
    }

    public Blocos novePMeDez(ZonedDateTime novePMeDez) {
        this.setNovePMeDez(novePMeDez);
        return this;
    }

    public void setNovePMeDez(ZonedDateTime novePMeDez) {
        this.novePMeDez = novePMeDez;
    }

    public ZonedDateTime getNovePMeVinte() {
        return this.novePMeVinte;
    }

    public Blocos novePMeVinte(ZonedDateTime novePMeVinte) {
        this.setNovePMeVinte(novePMeVinte);
        return this;
    }

    public void setNovePMeVinte(ZonedDateTime novePMeVinte) {
        this.novePMeVinte = novePMeVinte;
    }

    public ZonedDateTime getNovePMeTrinta() {
        return this.novePMeTrinta;
    }

    public Blocos novePMeTrinta(ZonedDateTime novePMeTrinta) {
        this.setNovePMeTrinta(novePMeTrinta);
        return this;
    }

    public void setNovePMeTrinta(ZonedDateTime novePMeTrinta) {
        this.novePMeTrinta = novePMeTrinta;
    }

    public ZonedDateTime getNovePMeQuarenta() {
        return this.novePMeQuarenta;
    }

    public Blocos novePMeQuarenta(ZonedDateTime novePMeQuarenta) {
        this.setNovePMeQuarenta(novePMeQuarenta);
        return this;
    }

    public void setNovePMeQuarenta(ZonedDateTime novePMeQuarenta) {
        this.novePMeQuarenta = novePMeQuarenta;
    }

    public ZonedDateTime getNovePMeCinquenta() {
        return this.novePMeCinquenta;
    }

    public Blocos novePMeCinquenta(ZonedDateTime novePMeCinquenta) {
        this.setNovePMeCinquenta(novePMeCinquenta);
        return this;
    }

    public void setNovePMeCinquenta(ZonedDateTime novePMeCinquenta) {
        this.novePMeCinquenta = novePMeCinquenta;
    }

    public ZonedDateTime getDezAM() {
        return this.dezAM;
    }

    public Blocos dezAM(ZonedDateTime dezAM) {
        this.setDezAM(dezAM);
        return this;
    }

    public void setDezAM(ZonedDateTime dezAM) {
        this.dezAM = dezAM;
    }

    public ZonedDateTime getDezAMeDez() {
        return this.dezAMeDez;
    }

    public Blocos dezAMeDez(ZonedDateTime dezAMeDez) {
        this.setDezAMeDez(dezAMeDez);
        return this;
    }

    public void setDezAMeDez(ZonedDateTime dezAMeDez) {
        this.dezAMeDez = dezAMeDez;
    }

    public ZonedDateTime getDezAMeVinte() {
        return this.dezAMeVinte;
    }

    public Blocos dezAMeVinte(ZonedDateTime dezAMeVinte) {
        this.setDezAMeVinte(dezAMeVinte);
        return this;
    }

    public void setDezAMeVinte(ZonedDateTime dezAMeVinte) {
        this.dezAMeVinte = dezAMeVinte;
    }

    public ZonedDateTime getDezAMeTrinta() {
        return this.dezAMeTrinta;
    }

    public Blocos dezAMeTrinta(ZonedDateTime dezAMeTrinta) {
        this.setDezAMeTrinta(dezAMeTrinta);
        return this;
    }

    public void setDezAMeTrinta(ZonedDateTime dezAMeTrinta) {
        this.dezAMeTrinta = dezAMeTrinta;
    }

    public ZonedDateTime getDezAMeQuarenta() {
        return this.dezAMeQuarenta;
    }

    public Blocos dezAMeQuarenta(ZonedDateTime dezAMeQuarenta) {
        this.setDezAMeQuarenta(dezAMeQuarenta);
        return this;
    }

    public void setDezAMeQuarenta(ZonedDateTime dezAMeQuarenta) {
        this.dezAMeQuarenta = dezAMeQuarenta;
    }

    public ZonedDateTime getDezAMeCinquenta() {
        return this.dezAMeCinquenta;
    }

    public Blocos dezAMeCinquenta(ZonedDateTime dezAMeCinquenta) {
        this.setDezAMeCinquenta(dezAMeCinquenta);
        return this;
    }

    public void setDezAMeCinquenta(ZonedDateTime dezAMeCinquenta) {
        this.dezAMeCinquenta = dezAMeCinquenta;
    }

    public ZonedDateTime getDezPM() {
        return this.dezPM;
    }

    public Blocos dezPM(ZonedDateTime dezPM) {
        this.setDezPM(dezPM);
        return this;
    }

    public void setDezPM(ZonedDateTime dezPM) {
        this.dezPM = dezPM;
    }

    public ZonedDateTime getDezPMeDez() {
        return this.dezPMeDez;
    }

    public Blocos dezPMeDez(ZonedDateTime dezPMeDez) {
        this.setDezPMeDez(dezPMeDez);
        return this;
    }

    public void setDezPMeDez(ZonedDateTime dezPMeDez) {
        this.dezPMeDez = dezPMeDez;
    }

    public ZonedDateTime getDezPMeVinte() {
        return this.dezPMeVinte;
    }

    public Blocos dezPMeVinte(ZonedDateTime dezPMeVinte) {
        this.setDezPMeVinte(dezPMeVinte);
        return this;
    }

    public void setDezPMeVinte(ZonedDateTime dezPMeVinte) {
        this.dezPMeVinte = dezPMeVinte;
    }

    public ZonedDateTime getDezPMeTrinta() {
        return this.dezPMeTrinta;
    }

    public Blocos dezPMeTrinta(ZonedDateTime dezPMeTrinta) {
        this.setDezPMeTrinta(dezPMeTrinta);
        return this;
    }

    public void setDezPMeTrinta(ZonedDateTime dezPMeTrinta) {
        this.dezPMeTrinta = dezPMeTrinta;
    }

    public ZonedDateTime getDezPMeQuarenta() {
        return this.dezPMeQuarenta;
    }

    public Blocos dezPMeQuarenta(ZonedDateTime dezPMeQuarenta) {
        this.setDezPMeQuarenta(dezPMeQuarenta);
        return this;
    }

    public void setDezPMeQuarenta(ZonedDateTime dezPMeQuarenta) {
        this.dezPMeQuarenta = dezPMeQuarenta;
    }

    public ZonedDateTime getDezPMeCinquenta() {
        return this.dezPMeCinquenta;
    }

    public Blocos dezPMeCinquenta(ZonedDateTime dezPMeCinquenta) {
        this.setDezPMeCinquenta(dezPMeCinquenta);
        return this;
    }

    public void setDezPMeCinquenta(ZonedDateTime dezPMeCinquenta) {
        this.dezPMeCinquenta = dezPMeCinquenta;
    }

    public ZonedDateTime getOnzeAM() {
        return this.onzeAM;
    }

    public Blocos onzeAM(ZonedDateTime onzeAM) {
        this.setOnzeAM(onzeAM);
        return this;
    }

    public void setOnzeAM(ZonedDateTime onzeAM) {
        this.onzeAM = onzeAM;
    }

    public ZonedDateTime getOnzeAMeDez() {
        return this.onzeAMeDez;
    }

    public Blocos onzeAMeDez(ZonedDateTime onzeAMeDez) {
        this.setOnzeAMeDez(onzeAMeDez);
        return this;
    }

    public void setOnzeAMeDez(ZonedDateTime onzeAMeDez) {
        this.onzeAMeDez = onzeAMeDez;
    }

    public ZonedDateTime getOnzeAMeVinte() {
        return this.onzeAMeVinte;
    }

    public Blocos onzeAMeVinte(ZonedDateTime onzeAMeVinte) {
        this.setOnzeAMeVinte(onzeAMeVinte);
        return this;
    }

    public void setOnzeAMeVinte(ZonedDateTime onzeAMeVinte) {
        this.onzeAMeVinte = onzeAMeVinte;
    }

    public ZonedDateTime getOnzeAMeTrinta() {
        return this.onzeAMeTrinta;
    }

    public Blocos onzeAMeTrinta(ZonedDateTime onzeAMeTrinta) {
        this.setOnzeAMeTrinta(onzeAMeTrinta);
        return this;
    }

    public void setOnzeAMeTrinta(ZonedDateTime onzeAMeTrinta) {
        this.onzeAMeTrinta = onzeAMeTrinta;
    }

    public ZonedDateTime getOnzeAMeQuarenta() {
        return this.onzeAMeQuarenta;
    }

    public Blocos onzeAMeQuarenta(ZonedDateTime onzeAMeQuarenta) {
        this.setOnzeAMeQuarenta(onzeAMeQuarenta);
        return this;
    }

    public void setOnzeAMeQuarenta(ZonedDateTime onzeAMeQuarenta) {
        this.onzeAMeQuarenta = onzeAMeQuarenta;
    }

    public ZonedDateTime getOnzeAMeCinquenta() {
        return this.onzeAMeCinquenta;
    }

    public Blocos onzeAMeCinquenta(ZonedDateTime onzeAMeCinquenta) {
        this.setOnzeAMeCinquenta(onzeAMeCinquenta);
        return this;
    }

    public void setOnzeAMeCinquenta(ZonedDateTime onzeAMeCinquenta) {
        this.onzeAMeCinquenta = onzeAMeCinquenta;
    }

    public ZonedDateTime getOnzePM() {
        return this.onzePM;
    }

    public Blocos onzePM(ZonedDateTime onzePM) {
        this.setOnzePM(onzePM);
        return this;
    }

    public void setOnzePM(ZonedDateTime onzePM) {
        this.onzePM = onzePM;
    }

    public ZonedDateTime getOnzePMeDez() {
        return this.onzePMeDez;
    }

    public Blocos onzePMeDez(ZonedDateTime onzePMeDez) {
        this.setOnzePMeDez(onzePMeDez);
        return this;
    }

    public void setOnzePMeDez(ZonedDateTime onzePMeDez) {
        this.onzePMeDez = onzePMeDez;
    }

    public ZonedDateTime getOnzePMeVinte() {
        return this.onzePMeVinte;
    }

    public Blocos onzePMeVinte(ZonedDateTime onzePMeVinte) {
        this.setOnzePMeVinte(onzePMeVinte);
        return this;
    }

    public void setOnzePMeVinte(ZonedDateTime onzePMeVinte) {
        this.onzePMeVinte = onzePMeVinte;
    }

    public ZonedDateTime getOnzePMeTrinta() {
        return this.onzePMeTrinta;
    }

    public Blocos onzePMeTrinta(ZonedDateTime onzePMeTrinta) {
        this.setOnzePMeTrinta(onzePMeTrinta);
        return this;
    }

    public void setOnzePMeTrinta(ZonedDateTime onzePMeTrinta) {
        this.onzePMeTrinta = onzePMeTrinta;
    }

    public ZonedDateTime getOnzePMeQuarenta() {
        return this.onzePMeQuarenta;
    }

    public Blocos onzePMeQuarenta(ZonedDateTime onzePMeQuarenta) {
        this.setOnzePMeQuarenta(onzePMeQuarenta);
        return this;
    }

    public void setOnzePMeQuarenta(ZonedDateTime onzePMeQuarenta) {
        this.onzePMeQuarenta = onzePMeQuarenta;
    }

    public ZonedDateTime getOnzePMeCinquenta() {
        return this.onzePMeCinquenta;
    }

    public Blocos onzePMeCinquenta(ZonedDateTime onzePMeCinquenta) {
        this.setOnzePMeCinquenta(onzePMeCinquenta);
        return this;
    }

    public void setOnzePMeCinquenta(ZonedDateTime onzePMeCinquenta) {
        this.onzePMeCinquenta = onzePMeCinquenta;
    }

    public CronogramaDiario getCronogramaDiario() {
        return this.cronogramaDiario;
    }

    public void setCronogramaDiario(CronogramaDiario cronogramaDiario) {
        this.cronogramaDiario = cronogramaDiario;
    }

    public Blocos cronogramaDiario(CronogramaDiario cronogramaDiario) {
        this.setCronogramaDiario(cronogramaDiario);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Blocos)) {
            return false;
        }
        return id != null && id.equals(((Blocos) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Blocos{" +
            "id=" + getId() +
            ", zeroAM='" + getZeroAM() + "'" +
            ", zeroAMeDez='" + getZeroAMeDez() + "'" +
            ", zeroAMeVinte='" + getZeroAMeVinte() + "'" +
            ", zeroAMeTrinta='" + getZeroAMeTrinta() + "'" +
            ", zeroAMeQuarenta='" + getZeroAMeQuarenta() + "'" +
            ", zeroAMeCinquenta='" + getZeroAMeCinquenta() + "'" +
            ", zeroPM='" + getZeroPM() + "'" +
            ", zeroPMeDez='" + getZeroPMeDez() + "'" +
            ", zeroPMeVinte='" + getZeroPMeVinte() + "'" +
            ", zeroPMeTrinta='" + getZeroPMeTrinta() + "'" +
            ", zeroPMeQuarenta='" + getZeroPMeQuarenta() + "'" +
            ", zeroPMeCinquenta='" + getZeroPMeCinquenta() + "'" +
            ", umAM='" + getUmAM() + "'" +
            ", umAMeDez='" + getUmAMeDez() + "'" +
            ", umAMeVinte='" + getUmAMeVinte() + "'" +
            ", umAMeTrinta='" + getUmAMeTrinta() + "'" +
            ", umAMeQuarenta='" + getUmAMeQuarenta() + "'" +
            ", umAMeCinquenta='" + getUmAMeCinquenta() + "'" +
            ", umPM='" + getUmPM() + "'" +
            ", umPMeDez='" + getUmPMeDez() + "'" +
            ", umPMeVinte='" + getUmPMeVinte() + "'" +
            ", umPMeTrinta='" + getUmPMeTrinta() + "'" +
            ", umPMeQuarenta='" + getUmPMeQuarenta() + "'" +
            ", umPMeCinquenta='" + getUmPMeCinquenta() + "'" +
            ", doisAM='" + getDoisAM() + "'" +
            ", doisAMeDez='" + getDoisAMeDez() + "'" +
            ", doisAMeVinte='" + getDoisAMeVinte() + "'" +
            ", doisAMeTrinta='" + getDoisAMeTrinta() + "'" +
            ", doisAMeQuarenta='" + getDoisAMeQuarenta() + "'" +
            ", doisAMeCinquenta='" + getDoisAMeCinquenta() + "'" +
            ", doisPM='" + getDoisPM() + "'" +
            ", doisPMeDez='" + getDoisPMeDez() + "'" +
            ", doisPMeVinte='" + getDoisPMeVinte() + "'" +
            ", doisPMeTrinta='" + getDoisPMeTrinta() + "'" +
            ", doisPMeQuarenta='" + getDoisPMeQuarenta() + "'" +
            ", doisPMeCinquenta='" + getDoisPMeCinquenta() + "'" +
            ", tresAM='" + getTresAM() + "'" +
            ", tresAMeDez='" + getTresAMeDez() + "'" +
            ", tresAMeVinte='" + getTresAMeVinte() + "'" +
            ", tresAMeTrinta='" + getTresAMeTrinta() + "'" +
            ", tresAMeQuarenta='" + getTresAMeQuarenta() + "'" +
            ", tresAMeCinquenta='" + getTresAMeCinquenta() + "'" +
            ", tresPM='" + getTresPM() + "'" +
            ", tresPMeDez='" + getTresPMeDez() + "'" +
            ", tresPMeVinte='" + getTresPMeVinte() + "'" +
            ", tresPMeTrinta='" + getTresPMeTrinta() + "'" +
            ", tresPMeQuarenta='" + getTresPMeQuarenta() + "'" +
            ", tresPMeCinquenta='" + getTresPMeCinquenta() + "'" +
            ", quatroAM='" + getQuatroAM() + "'" +
            ", quatroAMeDez='" + getQuatroAMeDez() + "'" +
            ", quatroAMeVinte='" + getQuatroAMeVinte() + "'" +
            ", quatroAMeTrinta='" + getQuatroAMeTrinta() + "'" +
            ", quatroAMeQuarenta='" + getQuatroAMeQuarenta() + "'" +
            ", quatroAMeCinquenta='" + getQuatroAMeCinquenta() + "'" +
            ", quatroPM='" + getQuatroPM() + "'" +
            ", quatroPMeDez='" + getQuatroPMeDez() + "'" +
            ", quatroPMeVinte='" + getQuatroPMeVinte() + "'" +
            ", quatroPMeTrinta='" + getQuatroPMeTrinta() + "'" +
            ", quatroPMeQuarenta='" + getQuatroPMeQuarenta() + "'" +
            ", quatroPMeCinquenta='" + getQuatroPMeCinquenta() + "'" +
            ", cincoAM='" + getCincoAM() + "'" +
            ", cincoAMeDez='" + getCincoAMeDez() + "'" +
            ", cincoAMeVinte='" + getCincoAMeVinte() + "'" +
            ", cincoAMeTrinta='" + getCincoAMeTrinta() + "'" +
            ", cincoAMeQuarenta='" + getCincoAMeQuarenta() + "'" +
            ", cincoAMeCinquenta='" + getCincoAMeCinquenta() + "'" +
            ", cincoPM='" + getCincoPM() + "'" +
            ", cincoPMeDez='" + getCincoPMeDez() + "'" +
            ", cincoPMeVinte='" + getCincoPMeVinte() + "'" +
            ", cincoPMeTrinta='" + getCincoPMeTrinta() + "'" +
            ", cincoPMeQuarenta='" + getCincoPMeQuarenta() + "'" +
            ", cincoPMeCinquenta='" + getCincoPMeCinquenta() + "'" +
            ", seisAM='" + getSeisAM() + "'" +
            ", seisAMeDez='" + getSeisAMeDez() + "'" +
            ", seisAMeVinte='" + getSeisAMeVinte() + "'" +
            ", seisAMeTrinta='" + getSeisAMeTrinta() + "'" +
            ", seisAMeQuarenta='" + getSeisAMeQuarenta() + "'" +
            ", seisAMeCinquenta='" + getSeisAMeCinquenta() + "'" +
            ", seisPM='" + getSeisPM() + "'" +
            ", seisPMeDez='" + getSeisPMeDez() + "'" +
            ", seisPMeVinte='" + getSeisPMeVinte() + "'" +
            ", seisPMeTrinta='" + getSeisPMeTrinta() + "'" +
            ", seisPMeQuarenta='" + getSeisPMeQuarenta() + "'" +
            ", seisPMeCinquenta='" + getSeisPMeCinquenta() + "'" +
            ", seteAM='" + getSeteAM() + "'" +
            ", seteAMeDez='" + getSeteAMeDez() + "'" +
            ", seteAMeVinte='" + getSeteAMeVinte() + "'" +
            ", seteAMeTrinta='" + getSeteAMeTrinta() + "'" +
            ", seteAMeQuarenta='" + getSeteAMeQuarenta() + "'" +
            ", seteAMeCinquenta='" + getSeteAMeCinquenta() + "'" +
            ", setePM='" + getSetePM() + "'" +
            ", setePMeDez='" + getSetePMeDez() + "'" +
            ", setePMeVinte='" + getSetePMeVinte() + "'" +
            ", setePMeTrinta='" + getSetePMeTrinta() + "'" +
            ", setePMeQuarenta='" + getSetePMeQuarenta() + "'" +
            ", setePMeCinquenta='" + getSetePMeCinquenta() + "'" +
            ", oitoAM='" + getOitoAM() + "'" +
            ", oitoAMeDez='" + getOitoAMeDez() + "'" +
            ", oitoAMeVinte='" + getOitoAMeVinte() + "'" +
            ", oitoAMeTrinta='" + getOitoAMeTrinta() + "'" +
            ", oitoAMeQuarenta='" + getOitoAMeQuarenta() + "'" +
            ", oitoAMeCinquenta='" + getOitoAMeCinquenta() + "'" +
            ", oitoPM='" + getOitoPM() + "'" +
            ", oitoPMeDez='" + getOitoPMeDez() + "'" +
            ", oitoPMeVinte='" + getOitoPMeVinte() + "'" +
            ", oitoPMeTrinta='" + getOitoPMeTrinta() + "'" +
            ", oitoPMeQuarenta='" + getOitoPMeQuarenta() + "'" +
            ", oitoPMeCinquenta='" + getOitoPMeCinquenta() + "'" +
            ", noveAM='" + getNoveAM() + "'" +
            ", noveAMeDez='" + getNoveAMeDez() + "'" +
            ", noveAMeVinte='" + getNoveAMeVinte() + "'" +
            ", noveAMeTrinta='" + getNoveAMeTrinta() + "'" +
            ", noveAMeQuarenta='" + getNoveAMeQuarenta() + "'" +
            ", noveAMeCinquenta='" + getNoveAMeCinquenta() + "'" +
            ", novePM='" + getNovePM() + "'" +
            ", novePMeDez='" + getNovePMeDez() + "'" +
            ", novePMeVinte='" + getNovePMeVinte() + "'" +
            ", novePMeTrinta='" + getNovePMeTrinta() + "'" +
            ", novePMeQuarenta='" + getNovePMeQuarenta() + "'" +
            ", novePMeCinquenta='" + getNovePMeCinquenta() + "'" +
            ", dezAM='" + getDezAM() + "'" +
            ", dezAMeDez='" + getDezAMeDez() + "'" +
            ", dezAMeVinte='" + getDezAMeVinte() + "'" +
            ", dezAMeTrinta='" + getDezAMeTrinta() + "'" +
            ", dezAMeQuarenta='" + getDezAMeQuarenta() + "'" +
            ", dezAMeCinquenta='" + getDezAMeCinquenta() + "'" +
            ", dezPM='" + getDezPM() + "'" +
            ", dezPMeDez='" + getDezPMeDez() + "'" +
            ", dezPMeVinte='" + getDezPMeVinte() + "'" +
            ", dezPMeTrinta='" + getDezPMeTrinta() + "'" +
            ", dezPMeQuarenta='" + getDezPMeQuarenta() + "'" +
            ", dezPMeCinquenta='" + getDezPMeCinquenta() + "'" +
            ", onzeAM='" + getOnzeAM() + "'" +
            ", onzeAMeDez='" + getOnzeAMeDez() + "'" +
            ", onzeAMeVinte='" + getOnzeAMeVinte() + "'" +
            ", onzeAMeTrinta='" + getOnzeAMeTrinta() + "'" +
            ", onzeAMeQuarenta='" + getOnzeAMeQuarenta() + "'" +
            ", onzeAMeCinquenta='" + getOnzeAMeCinquenta() + "'" +
            ", onzePM='" + getOnzePM() + "'" +
            ", onzePMeDez='" + getOnzePMeDez() + "'" +
            ", onzePMeVinte='" + getOnzePMeVinte() + "'" +
            ", onzePMeTrinta='" + getOnzePMeTrinta() + "'" +
            ", onzePMeQuarenta='" + getOnzePMeQuarenta() + "'" +
            ", onzePMeCinquenta='" + getOnzePMeCinquenta() + "'" +
            "}";
    }
}
