package com.adocao.pet.api.dto.response.adocao;

import com.adocao.pet.api.enums.AdocaoStatus;

import java.time.LocalDate;

public record AdocaoResponseDTO(
        Long id,
        LocalDate dataSolicitacao,
        AdocaoStatus status,
        Long tutorId,
        String nomeTutor,
        Long petId,
        String nomePet
) {
}
