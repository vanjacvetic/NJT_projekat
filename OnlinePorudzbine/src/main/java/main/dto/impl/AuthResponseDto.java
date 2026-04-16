package main.dto.impl;

import main.dto.Dto;
import main.entity.impl.Rola;

public class AuthResponseDto implements Dto {

    private String token;
    private Long id;
    private String ime;
    private String prezime;
    private String email;
    private Rola rola;

    public AuthResponseDto() {
    }

    public AuthResponseDto(String token, Long id, String ime, String prezime, String email, Rola rola) {
        this.token = token;
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.rola = rola;
    }

    public String getToken() {
        return token;
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

    public Rola getRola() {
        return rola;
    }

    public void setToken(String token) {
        this.token = token;
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

    public void setRola(Rola rola) {
        this.rola = rola;
    }
}