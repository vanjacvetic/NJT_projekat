package main.dto.impl;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import main.dto.Dto;
import main.entity.impl.Rola;

public class KorisnikDto implements Dto {

    private Long id;

    @NotBlank(message = "Ime je obavezno.")
    @Size(min = 2, max = 100, message = "Ime mora imati izmedju 2 i 100 karaktera.")
    private String ime;

    @NotBlank(message = "Prezime je obavezno.")
    @Size(min = 2, max = 100, message = "Prezime mora imati izmedju 2 i 100 karaktera.")
    private String prezime;

    @NotBlank(message = "Email je obavezan.")
    @Email(message = "Email nije u ispravnom formatu.")
    private String email;

    @NotBlank(message = "Lozinka je obavezna.")
    @Size(min = 6, max = 255, message = "Lozinka mora imati najmanje 6 karaktera.")
    private String lozinka;

    @NotBlank(message = "Broj telefona je obavezan.")
    @Size(max = 30, message = "Broj telefona ne sme biti duzi od 30 karaktera.")
    private String brojTelefona;

    @NotNull(message = "Rola je obavezna.")
    private Rola rola;

    public KorisnikDto() {
    }

    public KorisnikDto(Long id, String ime, String prezime, String email, String lozinka, String brojTelefona, Rola rola) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.lozinka = lozinka;
        this.brojTelefona = brojTelefona;
        this.rola = rola;
    }

    public Long getId() {
        return id;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public String getEmail() {
        return email;
    }

    public String getLozinka() {
        return lozinka;
    }

    public String getBrojTelefona() {
        return brojTelefona;
    }

    public Rola getRola() {
        return rola;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public void setBrojTelefona(String brojTelefona) {
        this.brojTelefona = brojTelefona;
    }

    public void setRola(Rola rola) {
        this.rola = rola;
    }
}