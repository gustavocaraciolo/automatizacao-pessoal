package com.br.gustavocaraciolo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A CronogramaDiario.
 */
@Entity
@Table(name = "cronograma_diario")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CronogramaDiario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "dia")
    private LocalDate dia;

    @OneToMany(mappedBy = "cronogramaDiario")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "cronogramaDiario", "atividades" }, allowSetters = true)
    private Set<Blocos> blocos = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public CronogramaDiario id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDia() {
        return this.dia;
    }

    public CronogramaDiario dia(LocalDate dia) {
        this.setDia(dia);
        return this;
    }

    public void setDia(LocalDate dia) {
        this.dia = dia;
    }

    public Set<Blocos> getBlocos() {
        return this.blocos;
    }

    public void setBlocos(Set<Blocos> blocos) {
        if (this.blocos != null) {
            this.blocos.forEach(i -> i.setCronogramaDiario(null));
        }
        if (blocos != null) {
            blocos.forEach(i -> i.setCronogramaDiario(this));
        }
        this.blocos = blocos;
    }

    public CronogramaDiario blocos(Set<Blocos> blocos) {
        this.setBlocos(blocos);
        return this;
    }

    public CronogramaDiario addBlocos(Blocos blocos) {
        this.blocos.add(blocos);
        blocos.setCronogramaDiario(this);
        return this;
    }

    public CronogramaDiario removeBlocos(Blocos blocos) {
        this.blocos.remove(blocos);
        blocos.setCronogramaDiario(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CronogramaDiario)) {
            return false;
        }
        return id != null && id.equals(((CronogramaDiario) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CronogramaDiario{" +
            "id=" + getId() +
            ", dia='" + getDia() + "'" +
            "}";
    }
}
