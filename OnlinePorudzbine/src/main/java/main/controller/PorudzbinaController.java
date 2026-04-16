package main.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import main.dto.impl.PorudzbinaDto;
import main.servis.PorudzbinaServis;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/porudzbine")
public class PorudzbinaController {

    private final PorudzbinaServis porudzbinaServis;

    public PorudzbinaController(PorudzbinaServis porudzbinaServis) {
        this.porudzbinaServis = porudzbinaServis;
    }

    @GetMapping
    @Operation(summary = "Retrieve all Porudzbina entities.")
    @ApiResponse(responseCode = "200", content = {
        @Content(schema = @Schema(implementation = PorudzbinaDto.class), mediaType = "application/json")
    })
    public ResponseEntity<List<PorudzbinaDto>> getAll() {
        return new ResponseEntity<>(porudzbinaServis.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PorudzbinaDto> getById(
            @NotNull(message = "Id ne sme biti null.")
            @PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity<>(porudzbinaServis.findById(id), HttpStatus.OK);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "PorudzbinaController exception: " + ex.getMessage());
        }
    }

    @GetMapping("/korisnik/{korisnikId}")
    public ResponseEntity<List<PorudzbinaDto>> getByKorisnikId(@PathVariable Long korisnikId) {
        return new ResponseEntity<>(porudzbinaServis.findByKorisnikId(korisnikId), HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Create a new Porudzbina entity.")
    public ResponseEntity<PorudzbinaDto> addPorudzbina(@Valid @RequestBody @NotNull PorudzbinaDto dto) {
        try {
            PorudzbinaDto saved = porudzbinaServis.create(dto);
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error while saving porudzbina: " + ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing Porudzbina entity.")
    public ResponseEntity<PorudzbinaDto> updatePorudzbina(
            @PathVariable Long id,
            @Valid @RequestBody PorudzbinaDto dto) {
        try {
            dto.setId(id);
            PorudzbinaDto updated = porudzbinaServis.update(dto);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error while updating porudzbina: " + ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") Long id) {
        try {
            porudzbinaServis.deleteById(id);
            return new ResponseEntity<>("Porudzbina successfully deleted.", HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>("Porudzbina does not exist: " + id, HttpStatus.NOT_FOUND);
        }
    }
}