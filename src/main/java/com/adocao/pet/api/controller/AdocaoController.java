package com.adocao.pet.api.controller;

import com.adocao.pet.api.dto.request.adocao.CreateAdocaoRequestDTO;
import com.adocao.pet.api.dto.response.adocao.AdocaoResponseDTO;
import com.adocao.pet.api.service.AdocaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * Controlador responsável pelas operações de Adoçoes.
 */
@RestController
@RequestMapping("/adocoes")
@RequiredArgsConstructor
public class AdocaoController {

    private final AdocaoService adocaoService;

    @PostMapping
    public ResponseEntity<AdocaoResponseDTO> solicitarAdocao(@Valid @RequestBody CreateAdocaoRequestDTO dto) {

        AdocaoResponseDTO response =
                adocaoService.solicitarAdocao(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<AdocaoResponseDTO>> listarAdocoes() {
        return ResponseEntity.ok(
                adocaoService.listarAdocoes()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdocaoResponseDTO> buscarPorId(@PathVariable Long id) {

        return ResponseEntity.ok(
                adocaoService.buscarPorId(id)
        );
    }

    @PutMapping("/{id}/aprovar")
    public ResponseEntity<AdocaoResponseDTO> aprovarAdocao(@PathVariable Long id) {

        return ResponseEntity.ok(
                adocaoService.aprovarAdocao(id)
        );
    }

    @PutMapping("/{id}/recusar")
    public ResponseEntity<AdocaoResponseDTO> recusarAdocao(@PathVariable Long id) {

        return ResponseEntity.ok(
                adocaoService.recusarAdocao(id)
        );
    }
}