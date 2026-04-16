package main.dto.impl;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import main.dto.Dto;

public class KategorijaJelaDto implements Dto {

    private Long id;

    @NotBlank(message = "Naziv kategorije je obavezan.")
    @Size(min = 2, max = 100, message = "Naziv kategorije mora imati izmedju 2 i 100 karaktera.")
    private String naziv;

    @Size(max = 500, message = "Opis moze imati najvise 500 karaktera.")
    private String opis;

    public KategorijaJelaDto() {
    }

    public KategorijaJelaDto(Long id, String naziv, String opis) {
        this.id = id;
        this.naziv = naziv;
        this.opis = opis;
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }
}