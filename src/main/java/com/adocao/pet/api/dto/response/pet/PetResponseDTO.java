package com.adocao.pet.api.dto.response.pet;

import com.adocao.pet.api.enums.PetStatus;

public record PetResponseDTO(
        Long id,
        String nome,
        String especie,
        String raca,
        Integer idade,
        String descricao,
        PetStatus status
) {
}
