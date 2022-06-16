package com.br.gustavocaraciolo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Atividade.
 */
@Entity
@Table(name = "atividade")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Atividade implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "cor")
    private String cor;

    @Column(name = "descricao")
    private String descricao;

    @ManyToOne
    @JsonIgnoreProperties(value = { "blocos", "atividades" }, allowSetters = true)
    private Atividade blocos;

    @OneToMany(mappedBy = "blocos")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "blocos", "atividades" }, allowSetters = true)
    private Set<Atividade> atividades = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Atividade id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCor() {
        return this.cor;
    }

    public Atividade cor(String cor) {
        this.setCor(cor);
        return this;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public Atividade descricao(String descricao) {
        this.setDescricao(descricao);
        return this;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Atividade getBlocos() {
        return this.blocos;
    }

    public void setBlocos(Atividade atividade) {
        this.blocos = atividade;
    }

    public Atividade blocos(Atividade atividade) {
        this.setBlocos(atividade);
        return this;
    }

    public Set<Atividade> getAtividades() {
        return this.atividades;
    }

    public void setAtividades(Set<Atividade> atividades) {
        if (this.atividades != null) {
            this.atividades.forEach(i -> i.setBlocos(null));
        }
        if (atividades != null) {
            atividades.forEach(i -> i.setBlocos(this));
        }
        this.atividades = atividades;
    }

    public Atividade atividades(Set<Atividade> atividades) {
        this.setAtividades(atividades);
        return this;
    }

    public Atividade addAtividade(Atividade atividade) {
        this.atividades.add(atividade);
        atividade.setBlocos(this);
        return this;
    }

    public Atividade removeAtividade(Atividade atividade) {
        this.atividades.remove(atividade);
        atividade.setBlocos(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Atividade)) {
            return false;
        }
        return id != null && id.equals(((Atividade) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Atividade{" +
            "id=" + getId() +
            ", cor='" + getCor() + "'" +
            ", descricao='" + getDescricao() + "'" +
            "}";
    }
}
