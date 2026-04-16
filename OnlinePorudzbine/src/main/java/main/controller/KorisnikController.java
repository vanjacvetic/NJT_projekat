package main.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import main.dto.impl.KorisnikDto;
import main.servis.KorisnikServis;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/korisnici")
public class KorisnikController {

    private final KorisnikServis korisnikServis;

    public KorisnikController(KorisnikServis korisnikServis) {
        this.korisnikServis = korisnikServis;
    }

    @GetMapping
    @Operation(summary = "Retrieve all Korisnik entities.")
    @ApiResponse(responseCode = "200", content = {
        @Content(schema = @Schema(implementation = KorisnikDto.class), mediaType = "application/json")
    })
    public ResponseEntity<List<KorisnikDto>> getAll() {
        return new ResponseEntity<>(korisnikServis.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<KorisnikDto> getById(
            @NotNull(message = "Id ne sme biti null.")
            @PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity<>(korisnikServis.findById(id), HttpStatus.OK);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "KorisnikController exception: " + ex.getMessage());
        }
    }

    @PostMapping
    @Operation(summary = "Create a new Korisnik entity.")
    public ResponseEntity<KorisnikDto> addKorisnik(@Valid @RequestBody @NotNull KorisnikDto korisnikDto) {
        try {
            KorisnikDto saved = korisnikServis.create(korisnikDto);
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error while saving korisnik: " + ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing Korisnik entity.")
    public ResponseEntity<KorisnikDto> updateKorisnik(
            @PathVariable Long id,
            @Valid @RequestBody KorisnikDto korisnikDto) {
        try {
            korisnikDto.setId(id);
            KorisnikDto updated = korisnikServis.update(korisnikDto);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error while updating korisnik: " + ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") Long id) {
        try {
            korisnikServis.deleteById(id);
            return new ResponseEntity<>("Korisnik successfully deleted.", HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>("Korisnik does not exist: " + id, HttpStatus.NOT_FOUND);
        }
    }
}