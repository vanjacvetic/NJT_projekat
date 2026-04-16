package main.dto.impl;

import java.math.BigDecimal;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import main.dto.Dto;

public class StavkaPorudzbineDto implements Dto {

    private Long id;

    @NotNull(message = "Kolicina je obavezna.")
    @DecimalMin(value = "0.01", message = "Kolicina mora biti veca od 0.")
    private BigDecimal kolicina;

    @NotNull(message = "Jedinicna cena je obavezna.")
    @DecimalMin(value = "0.0", inclusive = false, message = "Jedinicna cena mora biti veca od 0.")
    private BigDecimal jedinicnaCena;

    @NotNull(message = "Iznos stavke je obavezan.")
    @DecimalMin(value = "0.0", inclusive = true, message = "Iznos stavke ne moze biti negativan.")
    private BigDecimal iznosStavke;

    @NotNull(message = "Porudzbina je obavezna.")
    private Long porudzbinaId;

    @NotNull(message = "Jelo je obavezno.")
    private Long jeloId;

    public StavkaPorudzbineDto() {
    }

    public StavkaPorudzbineDto(Long id, BigDecimal kolicina, BigDecimal jedinicnaCena,
            BigDecimal iznosStavke, Long porudzbinaId, Long jeloId) {
        this.id = id;
        this.kolicina = kolicina;
        this.jedinicnaCena = jedinicnaCena;
        this.iznosStavke = iznosStavke;
        this.porudzbinaId = porudzbinaId;
        this.jeloId = jeloId;
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

    public Long getPorudzbinaId() {
        return porudzbinaId;
    }

    public Long getJeloId() {
        return jeloId;
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

    public void setPorudzbinaId(Long porudzbinaId) {
        this.porudzbinaId = porudzbinaId;
    }

    public void setJeloId(Long jeloId) {
        this.jeloId = jeloId;
    }
}