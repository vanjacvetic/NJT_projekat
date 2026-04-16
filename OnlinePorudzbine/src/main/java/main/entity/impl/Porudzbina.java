package main.entity.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import jakarta.persistence.*;
import main.entity.MyEntity;

@Entity
@Table(name = "porudzbine")
public class Porudzbina implements MyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String adresa;
    private String kontakt;

    @Column(length = 1000)
    private String napomena;

    private BigDecimal ukupanIznos;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "korisnik_id")
    private Korisnik korisnik;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "porudzbina", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StavkaPorudzbine> stavkePorudzbine;

    public Porudzbina() {
    }

    public Porudzbina(Long id, String adresa, String kontakt, String napomena,
            BigDecimal ukupanIznos, LocalDateTime createdAt, LocalDateTime updatedAt,
            Korisnik korisnik, Status status, List<StavkaPorudzbine> stavkePorudzbine) {
        this.id = id;
        this.adresa = adresa;
        this.kontakt = kontakt;
        this.napomena = napomena;
        this.ukupanIznos = ukupanIznos;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.korisnik = korisnik;
        this.status = status;
        this.stavkePorudzbine = stavkePorudzbine;
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

    public String getAdresa() {
        return adresa;
    }

    public String getKontakt() {
        return kontakt;
    }

    public String getNapomena() {
        return napomena;
    }

    public BigDecimal getUkupanIznos() {
        return ukupanIznos;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public Status getStatus() {
        return status;
    }

    public List<StavkaPorudzbine> getStavkePorudzbine() {
        return stavkePorudzbine;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public void setKontakt(String kontakt) {
        this.kontakt = kontakt;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }

    public void setUkupanIznos(BigDecimal ukupanIznos) {
        this.ukupanIznos = ukupanIznos;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setStavkePorudzbine(List<StavkaPorudzbine> stavkePorudzbine) {
        this.stavkePorudzbine = stavkePorudzbine;
    }
}