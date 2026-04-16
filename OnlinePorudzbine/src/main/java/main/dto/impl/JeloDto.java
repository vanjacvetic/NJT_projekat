package main.dto.impl;

import java.math.BigDecimal;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import main.dto.Dto;

public class JeloDto implements Dto {

    private Long id;

    @NotBlank(message = "Naziv jela je obavezan.")
    @Size(min = 2, max = 100, message = "Naziv jela mora imati izmedju 2 i 100 karaktera.")
    private String naziv;

    @Size(max = 1000, message = "Opis moze imati najvise 1000 karaktera.")
    private String opis;

    @NotNull(message = "Cena je obavezna.")
    @DecimalMin(value = "0.01", message = "Cena mora biti veca od 0.")
    private BigDecimal cena;

    @NotBlank(message = "Jedinica mere je obavezna.")
    @Size(max = 50, message = "Jedinica mere ne sme biti duza od 50 karaktera.")
    private String jedinicaMere;

    @Size(max = 500, message = "Putanja do slike ne sme biti duza od 500 karaktera.")
    private String slika;

    @NotNull(message = "Polje dostupan je obavezno.")
    private Boolean dostupan;

    @NotNull(message = "Kategorija jela je obavezna.")
    private Long kategorijaJelaId;

    public JeloDto() {
    }

    public JeloDto(Long id, String naziv, String opis, BigDecimal cena, String jedinicaMere,
            String slika, Boolean dostupan, Long kategorijaJelaId) {
        this.id = id;
        this.naziv = naziv;
        this.opis = opis;
        this.cena = cena;
        this.jedinicaMere = jedinicaMere;
        this.slika = slika;
        this.dostupan = dostupan;
        this.kategorijaJelaId = kategorijaJelaId;
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

    public Long getKategorijaJelaId() {
        return kategorijaJelaId;
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

    public void setKategorijaJelaId(Long kategorijaJelaId) {
        this.kategorijaJelaId = kategorijaJelaId;
    }
}