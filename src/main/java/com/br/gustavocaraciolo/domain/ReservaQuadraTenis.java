package com.br.gustavocaraciolo.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ReservaQuadraTenis.
 */
@Entity
@Table(name = "reserva_quadra_tenis")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ReservaQuadraTenis implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "email_origem", nullable = false)
    private String emailOrigem;

    @NotNull
    @Column(name = "email_destino", nullable = false)
    private String emailDestino;

    @NotNull
    @Column(name = "template_email", nullable = false)
    private String templateEmail;

    @Column(name = "semana")
    private LocalDate semana;

    @Column(name = "segundafeira")
    private ZonedDateTime segundafeira;

    @Column(name = "tercafeira")
    private ZonedDateTime tercafeira;

    @Column(name = "quartafeira")
    private ZonedDateTime quartafeira;

    @Column(name = "quintafeira")
    private ZonedDateTime quintafeira;

    @Column(name = "sextafeira")
    private ZonedDateTime sextafeira;

    @Column(name = "sabado")
    private ZonedDateTime sabado;

    @Column(name = "domingo")
    private ZonedDateTime domingo;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ReservaQuadraTenis id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmailOrigem() {
        return this.emailOrigem;
    }

    public ReservaQuadraTenis emailOrigem(String emailOrigem) {
        this.setEmailOrigem(emailOrigem);
        return this;
    }

    public void setEmailOrigem(String emailOrigem) {
        this.emailOrigem = emailOrigem;
    }

    public String getEmailDestino() {
        return this.emailDestino;
    }

    public ReservaQuadraTenis emailDestino(String emailDestino) {
        this.setEmailDestino(emailDestino);
        return this;
    }

    public void setEmailDestino(String emailDestino) {
        this.emailDestino = emailDestino;
    }

    public String getTemplateEmail() {
        return this.templateEmail;
    }

    public ReservaQuadraTenis templateEmail(String templateEmail) {
        this.setTemplateEmail(templateEmail);
        return this;
    }

    public void setTemplateEmail(String templateEmail) {
        this.templateEmail = templateEmail;
    }

    public LocalDate getSemana() {
        return this.semana;
    }

    public ReservaQuadraTenis semana(LocalDate semana) {
        this.setSemana(semana);
        return this;
    }

    public void setSemana(LocalDate semana) {
        this.semana = semana;
    }

    public ZonedDateTime getSegundafeira() {
        return this.segundafeira;
    }

    public ReservaQuadraTenis segundafeira(ZonedDateTime segundafeira) {
        this.setSegundafeira(segundafeira);
        return this;
    }

    public void setSegundafeira(ZonedDateTime segundafeira) {
        this.segundafeira = segundafeira;
    }

    public ZonedDateTime getTercafeira() {
        return this.tercafeira;
    }

    public ReservaQuadraTenis tercafeira(ZonedDateTime tercafeira) {
        this.setTercafeira(tercafeira);
        return this;
    }

    public void setTercafeira(ZonedDateTime tercafeira) {
        this.tercafeira = tercafeira;
    }

    public ZonedDateTime getQuartafeira() {
        return this.quartafeira;
    }

    public ReservaQuadraTenis quartafeira(ZonedDateTime quartafeira) {
        this.setQuartafeira(quartafeira);
        return this;
    }

    public void setQuartafeira(ZonedDateTime quartafeira) {
        this.quartafeira = quartafeira;
    }

    public ZonedDateTime getQuintafeira() {
        return this.quintafeira;
    }

    public ReservaQuadraTenis quintafeira(ZonedDateTime quintafeira) {
        this.setQuintafeira(quintafeira);
        return this;
    }

    public void setQuintafeira(ZonedDateTime quintafeira) {
        this.quintafeira = quintafeira;
    }

    public ZonedDateTime getSextafeira() {
        return this.sextafeira;
    }

    public ReservaQuadraTenis sextafeira(ZonedDateTime sextafeira) {
        this.setSextafeira(sextafeira);
        return this;
    }

    public void setSextafeira(ZonedDateTime sextafeira) {
        this.sextafeira = sextafeira;
    }

    public ZonedDateTime getSabado() {
        return this.sabado;
    }

    public ReservaQuadraTenis sabado(ZonedDateTime sabado) {
        this.setSabado(sabado);
        return this;
    }

    public void setSabado(ZonedDateTime sabado) {
        this.sabado = sabado;
    }

    public ZonedDateTime getDomingo() {
        return this.domingo;
    }

    public ReservaQuadraTenis domingo(ZonedDateTime domingo) {
        this.setDomingo(domingo);
        return this;
    }

    public void setDomingo(ZonedDateTime domingo) {
        this.domingo = domingo;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ReservaQuadraTenis)) {
            return false;
        }
        return id != null && id.equals(((ReservaQuadraTenis) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ReservaQuadraTenis{" +
            "id=" + getId() +
            ", emailOrigem='" + getEmailOrigem() + "'" +
            ", emailDestino='" + getEmailDestino() + "'" +
            ", templateEmail='" + getTemplateEmail() + "'" +
            ", semana='" + getSemana() + "'" +
            ", segundafeira='" + getSegundafeira() + "'" +
            ", tercafeira='" + getTercafeira() + "'" +
            ", quartafeira='" + getQuartafeira() + "'" +
            ", quintafeira='" + getQuintafeira() + "'" +
            ", sextafeira='" + getSextafeira() + "'" +
            ", sabado='" + getSabado() + "'" +
            ", domingo='" + getDomingo() + "'" +
            "}";
    }
}
