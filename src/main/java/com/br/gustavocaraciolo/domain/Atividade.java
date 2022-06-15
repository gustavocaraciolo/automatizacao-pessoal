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

    @ManyToMany
    @JoinTable(
        name = "rel_atividade__blocos",
        joinColumns = @JoinColumn(name = "atividade_id"),
        inverseJoinColumns = @JoinColumn(name = "blocos_id")
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "cronogramaDiario", "atividades" }, allowSetters = true)
    private Set<Blocos> blocos = new HashSet<>();

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

    public Set<Blocos> getBlocos() {
        return this.blocos;
    }

    public void setBlocos(Set<Blocos> blocos) {
        this.blocos = blocos;
    }

    public Atividade blocos(Set<Blocos> blocos) {
        this.setBlocos(blocos);
        return this;
    }

    public Atividade addBlocos(Blocos blocos) {
        this.blocos.add(blocos);
        blocos.getAtividades().add(this);
        return this;
    }

    public Atividade removeBlocos(Blocos blocos) {
        this.blocos.remove(blocos);
        blocos.getAtividades().remove(this);
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
