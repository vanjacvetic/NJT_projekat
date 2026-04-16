package main.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import main.dto.impl.KategorijaJelaDto;
import main.servis.KategorijaJelaServis;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/kategorije-jela")
public class KategorijaJelaController {

    private final KategorijaJelaServis kategorijaJelaServis;

    public KategorijaJelaController(KategorijaJelaServis kategorijaJelaServis) {
        this.kategorijaJelaServis = kategorijaJelaServis;
    }

    @GetMapping
    @Operation(summary = "Retrieve all KategorijaJela entities.")
    @ApiResponse(responseCode = "200", content = {
        @Content(schema = @Schema(implementation = KategorijaJelaDto.class), mediaType = "application/json")
    })
    public ResponseEntity<List<KategorijaJelaDto>> getAll() {
        return new ResponseEntity<>(kategorijaJelaServis.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<KategorijaJelaDto> getById(
            @NotNull(message = "Id ne sme biti null.")
            @PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity<>(kategorijaJelaServis.findById(id), HttpStatus.OK);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "KategorijaJelaController exception: " + ex.getMessage());
        }
    }

    @PostMapping
    @Operation(summary = "Create a new KategorijaJela entity.")
    public ResponseEntity<KategorijaJelaDto> addKategorija(@Valid @RequestBody @NotNull KategorijaJelaDto dto) {
        try {
            KategorijaJelaDto saved = kategorijaJelaServis.create(dto);
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error while saving kategorija jela: " + ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing KategorijaJela entity.")
    public ResponseEntity<KategorijaJelaDto> updateKategorija(
            @PathVariable Long id,
            @Valid @RequestBody KategorijaJelaDto dto) {
        try {
            dto.setId(id);
            KategorijaJelaDto updated = kategorijaJelaServis.update(dto);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error while updating kategorija jela: " + ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") Long id) {
        try {
            kategorijaJelaServis.deleteById(id);
            return new ResponseEntity<>("Kategorija jela successfully deleted.", HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>("Kategorija jela does not exist: " + id, HttpStatus.NOT_FOUND);
        }
    }
}