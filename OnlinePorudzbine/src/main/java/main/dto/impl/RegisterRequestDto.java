package main.dto.impl;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import main.dto.Dto;
import main.entity.impl.Rola;

public class RegisterRequestDto implements Dto {

    @NotBlank(message = "Ime je obavezno.")
    private String ime;

    @NotBlank(message = "Prezime je obavezno.")
    private String prezime;

    @NotBlank(message = "Email je obavezan.")
    @Email(message = "Email nije u dobrom formatu.")
    private String email;

    @NotBlank(message = "Lozinka je obavezna.")
    @Size(min = 6, message = "Lozinka mora imati najmanje 6 karaktera.")
    private String lozinka;

    @NotBlank(message = "Broj telefona je obavezan.")
    private String brojTelefona;

    @NotNull(message = "Rola je obavezna.")
    private Rola rola;

    public RegisterRequestDto() {
    }

    public RegisterRequestDto(String ime, String prezime, String email, String lozinka, String brojTelefona, Rola rola) {
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.lozinka = lozinka;
        this.brojTelefona = brojTelefona;
        this.rola = rola;
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