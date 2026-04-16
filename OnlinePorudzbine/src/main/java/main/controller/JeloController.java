package main.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import main.dto.impl.JeloDto;
import main.servis.JeloServis;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/jela")
public class JeloController {

    private final JeloServis jeloServis;

    public JeloController(JeloServis jeloServis) {
        this.jeloServis = jeloServis;
    }

    @GetMapping
    @Operation(summary = "Retrieve all Jelo entities.")
    @ApiResponse(responseCode = "200", content = {
        @Content(schema = @Schema(implementation = JeloDto.class), mediaType = "application/json")
    })
    public ResponseEntity<List<JeloDto>> getAll() {
        return new ResponseEntity<>(jeloServis.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JeloDto> getById(
            @NotNull(message = "Id ne sme biti null.")
            @PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity<>(jeloServis.findById(id), HttpStatus.OK);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "JeloController exception: " + ex.getMessage());
        }
    }

    @GetMapping("/kategorija/{kategorijaId}")
    public ResponseEntity<List<JeloDto>> getByKategorijaId(@PathVariable Long kategorijaId) {
        return new ResponseEntity<>(jeloServis.findByKategorijaId(kategorijaId), HttpStatus.OK);
    }

    @GetMapping("/dostupna")
    public ResponseEntity<List<JeloDto>> getDostupnaJela() {
        return new ResponseEntity<>(jeloServis.findDostupnaJela(), HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Create a new Jelo entity.")
    public ResponseEntity<JeloDto> addJelo(@Valid @RequestBody @NotNull JeloDto dto) {
        try {
            JeloDto saved = jeloServis.create(dto);
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error while saving jelo: " + ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing Jelo entity.")
    public ResponseEntity<JeloDto> updateJelo(
            @PathVariable Long id,
            @Valid @RequestBody JeloDto dto) {
        try {
            dto.setId(id);
            JeloDto updated = jeloServis.update(dto);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error while updating jelo: " + ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") Long id) {
        try {
            jeloServis.deleteById(id);
            return new ResponseEntity<>("Jelo successfully deleted.", HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>("Jelo does not exist: " + id, HttpStatus.NOT_FOUND);
        }
    }
}