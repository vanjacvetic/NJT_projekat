package main.entity.impl;

import java.time.LocalDateTime;
import java.util.List;
import jakarta.persistence.*;
import main.entity.MyEntity;

@Entity
@Table(name = "kategorije_jela")
public class KategorijaJela implements MyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String naziv;
    private String opis;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "kategorijaJela", cascade = CascadeType.ALL)
    private List<Jelo> jela;

    public KategorijaJela() {
    }

    public KategorijaJela(Long id, String naziv, String opis, LocalDateTime createdAt,
            LocalDateTime updatedAt, List<Jelo> jela) {
        this.id = id;
        this.naziv = naziv;
        this.opis = opis;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.jela = jela;
    }

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<Jelo> getJela() {
        return jela;
    }

    public void setJela(List<Jelo> jela) {
        this.jela = jela;
    }
}