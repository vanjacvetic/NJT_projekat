package main.entity.impl;

import java.math.BigDecimal;
import jakarta.persistence.*;
import main.entity.MyEntity;

@Entity
@Table(name = "stavke_porudzbine")
public class StavkaPorudzbine implements MyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal kolicina;
    private BigDecimal jedinicnaCena;
    private BigDecimal iznosStavke;

    @ManyToOne
    @JoinColumn(name = "porudzbina_id")
    private Porudzbina porudzbina;

    @ManyToOne
    @JoinColumn(name = "jelo_id")
    private Jelo jelo;

    public StavkaPorudzbine() {
    }

    public StavkaPorudzbine(Long id, BigDecimal kolicina, BigDecimal jedinicnaCena,
            BigDecimal iznosStavke, Porudzbina porudzbina, Jelo jelo) {
        this.id = id;
        this.kolicina = kolicina;
        this.jedinicnaCena = jedinicnaCena;
        this.iznosStavke = iznosStavke;
        this.porudzbina = porudzbina;
        this.jelo = jelo;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getKolicina() {
        return kolicina;
    }

    public BigDecimal getJedinicnaCena() {
        return jedinicnaCena;
    }

    public BigDecimal getIznosStavke() {
        return iznosStavke;
    }

    public Porudzbina getPorudzbina() {
        return porudzbina;
    }

    public Jelo getJelo() {
        return jelo;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setKolicina(BigDecimal kolicina) {
        this.kolicina = kolicina;
    }

    public void setJedinicnaCena(BigDecimal jedinicnaCena) {
        this.jedinicnaCena = jedinicnaCena;
    }

    public void setIznosStavke(BigDecimal iznosStavke) {
        this.iznosStavke = iznosStavke;
    }

    public void setPorudzbina(Porudzbina porudzbina) {
        this.porudzbina = porudzbina;
    }

    public void setJelo(Jelo jelo) {
        this.jelo = jelo;
    }
}