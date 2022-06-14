package com.br.gustavocaraciolo.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

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
    @Column(name = "email_destino", nullable = false)
    private String emailDestino;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
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

    @Column(name = "segunda_feira_email_enviado")
    private Boolean segundaFeiraEmailEnviado;

    @Column(name = "terca_feira_email_enviado")
    private Boolean tercaFeiraEmailEnviado;

    @Column(name = "quarta_feira_email_enviado")
    private Boolean quartaFeiraEmailEnviado;

    @Column(name = "quinta_feira_email_enviado")
    private Boolean quintaFeiraEmailEnviado;

    @Column(name = "sexta_feira_email_enviado")
    private Boolean sextaFeiraEmailEnviado;

    @Column(name = "sabado_email_enviado")
    private Boolean sabadoEmailEnviado;

    @Column(name = "domingo_email_enviado")
    private Boolean domingoEmailEnviado;

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

    public Boolean getSegundaFeiraEmailEnviado() {
        return this.segundaFeiraEmailEnviado;
    }

    public ReservaQuadraTenis segundaFeiraEmailEnviado(Boolean segundaFeiraEmailEnviado) {
        this.setSegundaFeiraEmailEnviado(segundaFeiraEmailEnviado);
        return this;
    }

    public void setSegundaFeiraEmailEnviado(Boolean segundaFeiraEmailEnviado) {
        this.segundaFeiraEmailEnviado = segundaFeiraEmailEnviado;
    }

    public Boolean getTercaFeiraEmailEnviado() {
        return this.tercaFeiraEmailEnviado;
    }

    public ReservaQuadraTenis tercaFeiraEmailEnviado(Boolean tercaFeiraEmailEnviado) {
        this.setTercaFeiraEmailEnviado(tercaFeiraEmailEnviado);
        return this;
    }

    public void setTercaFeiraEmailEnviado(Boolean tercaFeiraEmailEnviado) {
        this.tercaFeiraEmailEnviado = tercaFeiraEmailEnviado;
    }

    public Boolean getQuartaFeiraEmailEnviado() {
        return this.quartaFeiraEmailEnviado;
    }

    public ReservaQuadraTenis quartaFeiraEmailEnviado(Boolean quartaFeiraEmailEnviado) {
        this.setQuartaFeiraEmailEnviado(quartaFeiraEmailEnviado);
        return this;
    }

    public void setQuartaFeiraEmailEnviado(Boolean quartaFeiraEmailEnviado) {
        this.quartaFeiraEmailEnviado = quartaFeiraEmailEnviado;
    }

    public Boolean getQuintaFeiraEmailEnviado() {
        return this.quintaFeiraEmailEnviado;
    }

    public ReservaQuadraTenis quintaFeiraEmailEnviado(Boolean quintaFeiraEmailEnviado) {
        this.setQuintaFeiraEmailEnviado(quintaFeiraEmailEnviado);
        return this;
    }

    public void setQuintaFeiraEmailEnviado(Boolean quintaFeiraEmailEnviado) {
        this.quintaFeiraEmailEnviado = quintaFeiraEmailEnviado;
    }

    public Boolean getSextaFeiraEmailEnviado() {
        return this.sextaFeiraEmailEnviado;
    }

    public ReservaQuadraTenis sextaFeiraEmailEnviado(Boolean sextaFeiraEmailEnviado) {
        this.setSextaFeiraEmailEnviado(sextaFeiraEmailEnviado);
        return this;
    }

    public void setSextaFeiraEmailEnviado(Boolean sextaFeiraEmailEnviado) {
        this.sextaFeiraEmailEnviado = sextaFeiraEmailEnviado;
    }

    public Boolean getSabadoEmailEnviado() {
        return this.sabadoEmailEnviado;
    }

    public ReservaQuadraTenis sabadoEmailEnviado(Boolean sabadoEmailEnviado) {
        this.setSabadoEmailEnviado(sabadoEmailEnviado);
        return this;
    }

    public void setSabadoEmailEnviado(Boolean sabadoEmailEnviado) {
        this.sabadoEmailEnviado = sabadoEmailEnviado;
    }

    public Boolean getDomingoEmailEnviado() {
        return this.domingoEmailEnviado;
    }

    public ReservaQuadraTenis domingoEmailEnviado(Boolean domingoEmailEnviado) {
        this.setDomingoEmailEnviado(domingoEmailEnviado);
        return this;
    }

    public void setDomingoEmailEnviado(Boolean domingoEmailEnviado) {
        this.domingoEmailEnviado = domingoEmailEnviado;
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
            ", segundaFeiraEmailEnviado='" + getSegundaFeiraEmailEnviado() + "'" +
            ", tercaFeiraEmailEnviado='" + getTercaFeiraEmailEnviado() + "'" +
            ", quartaFeiraEmailEnviado='" + getQuartaFeiraEmailEnviado() + "'" +
            ", quintaFeiraEmailEnviado='" + getQuintaFeiraEmailEnviado() + "'" +
            ", sextaFeiraEmailEnviado='" + getSextaFeiraEmailEnviado() + "'" +
            ", sabadoEmailEnviado='" + getSabadoEmailEnviado() + "'" +
            ", domingoEmailEnviado='" + getDomingoEmailEnviado() + "'" +
            "}";
    }
}
