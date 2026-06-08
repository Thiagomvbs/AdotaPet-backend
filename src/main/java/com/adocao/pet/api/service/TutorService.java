package com.adocao.pet.api.service;

import com.adocao.pet.api.dto.request.tutor.CreateTutorRequestDTO;
import com.adocao.pet.api.dto.request.tutor.UpdateTutorRequestDTO;
import com.adocao.pet.api.dto.response.tutor.TutorResponseDTO;
import com.adocao.pet.api.entity.Tutor;
import com.adocao.pet.api.exception.BusinessRuleException;
import com.adocao.pet.api.exception.ResourceNotFoundException;
import com.adocao.pet.api.repository.TutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TutorService {
    private final TutorRepository tutorRepository;

    public TutorResponseDTO criarTutor(CreateTutorRequestDTO dto) {

        validarCpf(dto.cpf());
        validarEmail(dto.email());

        Tutor tutor = Tutor.builder()
                .nome(dto.nome())
                .email(dto.email())
                .cpf(dto.cpf())
                .telefone(dto.telefone())
                .senha("123456")
                .build();

        Tutor tutorSalvo = tutorRepository.save(tutor);

        return converterParaResponse(tutorSalvo);
    }

    public List<TutorResponseDTO> listarTutores() {

        return tutorRepository.findAll()
                .stream()
                .map(this::converterParaResponse)
                .toList();
    }

    public TutorResponseDTO buscarTutorPorId(Long id) {

        Tutor tutor = buscarEntidadePorId(id);

        return converterParaResponse(tutor);
    }

    public TutorResponseDTO atualizarTutor(Long id,
                                           UpdateTutorRequestDTO dto) {

        Tutor tutor = buscarEntidadePorId(id);

        if (!tutor.getCpf().equals(dto.cpf())) {
            validarCpf(dto.cpf());
        }

        if (!tutor.getEmail().equals(dto.email())) {
            validarEmail(dto.email());
        }

        tutor.setNome(dto.nome());
        tutor.setEmail(dto.email());
        tutor.setCpf(dto.cpf());
        tutor.setTelefone(dto.telefone());

        Tutor tutorAtualizado = tutorRepository.save(tutor);

        return converterParaResponse(tutorAtualizado);
    }

    public void deletarTutor(Long id) {

        Tutor tutor = buscarEntidadePorId(id);

        tutorRepository.delete(tutor);
    }

    private Tutor buscarEntidadePorId(Long id) {

        return tutorRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Tutor não encontrado com id: " + id));
    }

    private void validarCpf(String cpf) {
        if (tutorRepository.existsByCpf(cpf)) {
            throw new BusinessRuleException(
                    "Já existe um tutor cadastrado com esse CPF");
        }
    }

    private void validarEmail(String email) {

        if (tutorRepository.existsByEmail(email)) {
            throw new BusinessRuleException(
                    "Já existe um tutor cadastrado com esse email");
        }
    }

    private TutorResponseDTO converterParaResponse(Tutor tutor) {
        return new TutorResponseDTO(
                tutor.getId(),
                tutor.getNome(),
                tutor.getEmail(),
                tutor.getCpf(),
                tutor.getTelefone()
        );
    }

}
