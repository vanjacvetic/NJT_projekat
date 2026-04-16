package main.dto.impl;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import main.dto.Dto;

public class LoginRequestDto implements Dto {

    @NotBlank(message = "Email je obavezan.")
    @Email(message = "Email nije u dobrom formatu.")
    private String email;

    @NotBlank(message = "Lozinka je obavezna.")
    private String lozinka;

    public LoginRequestDto() {
    }

    public LoginRequestDto(String email, String lozinka) {
        this.email = email;
        this.lozinka = lozinka;
    }

    public String getEmail() {
        return email;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }
}