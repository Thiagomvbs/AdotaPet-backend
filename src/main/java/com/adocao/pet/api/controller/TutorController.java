package com.adocao.pet.api.controller;

import com.adocao.pet.api.dto.request.tutor.CreateTutorRequestDTO;
import com.adocao.pet.api.dto.request.tutor.UpdateTutorRequestDTO;
import com.adocao.pet.api.dto.response.tutor.TutorResponseDTO;
import com.adocao.pet.api.service.TutorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador responsável pelas operações de Tutor.
 */
@RestController
@RequestMapping("/tutores")
@RequiredArgsConstructor
public class TutorController {

    private final TutorService tutorService;

    @PostMapping
    public ResponseEntity<TutorResponseDTO> criarTutor(@Valid @RequestBody CreateTutorRequestDTO dto) {
        TutorResponseDTO response = tutorService.criarTutor(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<TutorResponseDTO>> listarTutores() {
        return ResponseEntity.ok(
                tutorService.listarTutores()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TutorResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(
                tutorService.buscarTutorPorId(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<TutorResponseDTO> atualizarTutor(@PathVariable Long id, @Valid @RequestBody UpdateTutorRequestDTO dto) {
        return ResponseEntity.ok(
                tutorService.atualizarTutor(id, dto)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTutor(@PathVariable Long id) {

        tutorService.deletarTutor(id);

        return ResponseEntity.noContent().build();
    }
}