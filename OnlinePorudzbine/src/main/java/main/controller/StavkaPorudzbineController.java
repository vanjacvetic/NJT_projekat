package main.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import main.dto.impl.StavkaPorudzbineDto;
import main.servis.StavkaPorudzbineServis;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/stavke-porudzbine")
public class StavkaPorudzbineController {

    private final StavkaPorudzbineServis stavkaPorudzbineServis;

    public StavkaPorudzbineController(StavkaPorudzbineServis stavkaPorudzbineServis) {
        this.stavkaPorudzbineServis = stavkaPorudzbineServis;
    }

    @GetMapping
    @Operation(summary = "Retrieve all StavkaPorudzbine entities.")
    @ApiResponse(responseCode = "200", content = {
        @Content(schema = @Schema(implementation = StavkaPorudzbineDto.class), mediaType = "application/json")
    })
    public ResponseEntity<List<StavkaPorudzbineDto>> getAll() {
        return new ResponseEntity<>(stavkaPorudzbineServis.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StavkaPorudzbineDto> getById(
            @NotNull(message = "Id ne sme biti null.")
            @PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity<>(stavkaPorudzbineServis.findById(id), HttpStatus.OK);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "StavkaPorudzbineController exception: " + ex.getMessage());
        }
    }

    @GetMapping("/porudzbina/{porudzbinaId}")
    public ResponseEntity<List<StavkaPorudzbineDto>> getByPorudzbinaId(@PathVariable Long porudzbinaId) {
        return new ResponseEntity<>(stavkaPorudzbineServis.findByPorudzbinaId(porudzbinaId), HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Create a new StavkaPorudzbine entity.")
    public ResponseEntity<StavkaPorudzbineDto> addStavka(@Valid @RequestBody @NotNull StavkaPorudzbineDto dto) {
        try {
            StavkaPorudzbineDto saved = stavkaPorudzbineServis.create(dto);
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error while saving stavka porudzbine: " + ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing StavkaPorudzbine entity.")
    public ResponseEntity<StavkaPorudzbineDto> updateStavka(
            @PathVariable Long id,
            @Valid @RequestBody StavkaPorudzbineDto dto) {
        try {
            dto.setId(id);
            StavkaPorudzbineDto updated = stavkaPorudzbineServis.update(dto);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error while updating stavka porudzbine: " + ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") Long id) {
        try {
            stavkaPorudzbineServis.deleteById(id);
            return new ResponseEntity<>("Stavka porudzbine successfully deleted.", HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>("Stavka porudzbine does not exist: " + id, HttpStatus.NOT_FOUND);
        }
    }
}