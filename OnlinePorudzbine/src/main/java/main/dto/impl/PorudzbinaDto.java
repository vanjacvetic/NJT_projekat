package main.dto.impl;

import java.math.BigDecimal;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import main.dto.Dto;
import main.entity.impl.Status;

public class PorudzbinaDto implements Dto {

    private Long id;

    @NotBlank(message = "Adresa je obavezna.")
    @Size(max = 255, message = "Adresa ne sme biti duza od 255 karaktera.")
    private String adresa;

    @NotBlank(message = "Kontakt je obavezan.")
    @Size(max = 100, message = "Kontakt ne sme biti duzi od 100 karaktera.")
    private String kontakt;

    @Size(max = 1000, message = "Napomena moze imati najvise 1000 karaktera.")
    private String napomena;

    @NotNull(message = "Ukupan iznos je obavezan.")
    @DecimalMin(value = "0.0", inclusive = true, message = "Ukupan iznos ne moze biti negativan.")
    private BigDecimal ukupanIznos;

    @NotNull(message = "Korisnik je obavezan.")
    private Long korisnikId;

    @NotNull(message = "Status je obavezan.")
    private Status status;

    public PorudzbinaDto() {
    }

    public PorudzbinaDto(Long id, String adresa, String kontakt, String napomena,
            BigDecimal ukupanIznos, Long korisnikId, Status status) {
        this.id = id;
        this.adresa = adresa;
        this.kontakt = kontakt;
        this.napomena = napomena;
        this.ukupanIznos = ukupanIznos;
        this.korisnikId = korisnikId;
        this.status = status;
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

    public Long getKorisnikId() {
        return korisnikId;
    }

    public Status getStatus() {
        return status;
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

    public void setKorisnikId(Long korisnikId) {
        this.korisnikId = korisnikId;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}