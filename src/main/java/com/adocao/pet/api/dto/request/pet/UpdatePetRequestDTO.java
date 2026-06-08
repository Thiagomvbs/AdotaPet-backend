package com.adocao.pet.api.dto.request.pet;

import jakarta.validation.constraints.NotBlank;

public record UpdatePetRequestDTO(
        @NotBlank(message = "Nome é obrigatório")
        String nome,
        @NotBlank(message = "Espécie é obrigatória")
        String especie,
        String raca,
        Integer idade,
        String descricao

) {
}
