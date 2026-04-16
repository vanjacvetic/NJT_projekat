package main.entity.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import jakarta.persistence.*;
import main.entity.MyEntity;

@Entity
@Table(name = "jela")
public class Jelo implements MyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String naziv;

    @Column(length = 1000)
    private String opis;

    private BigDecimal cena;
    private String jedinicaMere;
    private String slika;
    private Boolean dostupan;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "kategorija_jela_id")
    private KategorijaJela kategorijaJela;

    public Jelo() {
    }

    public Jelo(Long id, String naziv, String opis, BigDecimal cena, String jedinicaMere,
            String slika, Boolean dostupan, LocalDateTime createdAt,
            LocalDateTime updatedAt, KategorijaJela kategorijaJela) {
        this.id = id;
        this.naziv = naziv;
        this.opis = opis;
        this.cena = cena;
        this.jedinicaMere = jedinicaMere;
        this.slika = slika;
        this.dostupan = dostupan;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.kategorijaJela = kategorijaJela;
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

    public String getOpis() {
        return opis;
    }

    public BigDecimal getCena() {
        return cena;
    }

    public String getJedinicaMere() {
        return jedinicaMere;
    }

    public String getSlika() {
        return slika;
    }

    public Boolean getDostupan() {
        return dostupan;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public KategorijaJela getKategorijaJela() {
        return kategorijaJela;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public void setCena(BigDecimal cena) {
        this.cena = cena;
    }

    public void setJedinicaMere(String jedinicaMere) {
        this.jedinicaMere = jedinicaMere;
    }

    public void setSlika(String slika) {
        this.slika = slika;
    }

    public void setDostupan(Boolean dostupan) {
        this.dostupan = dostupan;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setKategorijaJela(KategorijaJela kategorijaJela) {
        this.kategorijaJela = kategorijaJela;
    }
}