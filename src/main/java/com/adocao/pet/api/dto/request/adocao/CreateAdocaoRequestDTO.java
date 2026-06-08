package com.adocao.pet.api.dto.request.adocao;

import jakarta.validation.constraints.NotNull;

public record CreateAdocaoRequestDTO(
        @NotNull(message = "Tutor é obrigatório")
        Long tutorId,

        @NotNull(message = "Pet é obrigatório")
        Long petId
) {
}
