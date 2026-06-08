package com.adocao.pet.api.dto.response.tutor;

public record TutorResponseDTO(
        Long id,
        String nome,
        String email,
        String cpf,
        String telefone
) {
}
