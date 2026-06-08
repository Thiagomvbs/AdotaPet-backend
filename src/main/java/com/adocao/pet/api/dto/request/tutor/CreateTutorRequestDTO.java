package com.adocao.pet.api.dto.request.tutor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateTutorRequestDTO(
        @NotBlank(message = "Nome é obrigatório")
        String nome,
        @Email(message = "Email inválido")
        @NotBlank(message = "Email é obrigatório")
        String email,
        @NotBlank(message = "CPF é obrigatório")
        String cpf,
        String telefone
) {
}
