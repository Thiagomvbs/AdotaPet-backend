package com.adocao.pet.api.controller;

import com.adocao.pet.api.dto.request.pet.CreatePetRequestDTO;
import com.adocao.pet.api.dto.request.pet.UpdatePetRequestDTO;
import com.adocao.pet.api.dto.response.pet.PetResponseDTO;
import com.adocao.pet.api.service.PetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador responsável pelas operações de Pets.
 */
@RestController
@RequestMapping("/pets")
@RequiredArgsConstructor
public class PetController {

    private final PetService petService;

    @PostMapping
    public ResponseEntity<PetResponseDTO> criarPet(@Valid @RequestBody CreatePetRequestDTO dto) {

        PetResponseDTO response = petService.criarPet(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<PetResponseDTO>> listarPets() {

        return ResponseEntity.ok(
                petService.listarPets()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<PetResponseDTO> buscarPorId(@PathVariable Long id) {

        return ResponseEntity.ok(
                petService.buscarPetPorId(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<PetResponseDTO> atualizarPet(@PathVariable Long id, @Valid @RequestBody UpdatePetRequestDTO dto) {
        return ResponseEntity.ok(
                petService.atualizarPet(id, dto)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPet(@PathVariable Long id) {

        petService.deletarPet(id);

        return ResponseEntity.noContent().build();
    }
}