package main.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import main.dto.impl.AuthResponseDto;
import main.dto.impl.LoginRequestDto;
import main.dto.impl.RegisterRequestDto;
import main.servis.AuthServis;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthServis authServis;

    public AuthController(AuthServis authServis) {
        this.authServis = authServis;
    }

    @PostMapping("/register")
    @Operation(summary = "Registracija korisnika")
    public ResponseEntity<AuthResponseDto> register(@Valid @RequestBody RegisterRequestDto dto) {
        try {
            AuthResponseDto response = authServis.register(dto);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Greška pri registraciji: " + ex.getMessage());
        }
    }

    @PostMapping("/login")
    @Operation(summary = "Prijava korisnika")
    public ResponseEntity<AuthResponseDto> login(@Valid @RequestBody LoginRequestDto dto) {
        try {
            AuthResponseDto response = authServis.login(dto);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (BadCredentialsException ex) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Pogrešan email ili lozinka.");
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Greška pri prijavi: " + ex.getMessage());
        }
    }
}