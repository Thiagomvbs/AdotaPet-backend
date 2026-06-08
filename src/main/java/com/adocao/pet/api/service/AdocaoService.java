package com.adocao.pet.api.service;

import com.adocao.pet.api.dto.request.adocao.CreateAdocaoRequestDTO;
import com.adocao.pet.api.dto.response.adocao.AdocaoResponseDTO;
import com.adocao.pet.api.entity.Adocao;
import com.adocao.pet.api.entity.Pet;
import com.adocao.pet.api.entity.Tutor;
import com.adocao.pet.api.enums.AdocaoStatus;
import com.adocao.pet.api.enums.PetStatus;
import com.adocao.pet.api.exception.BusinessRuleException;
import com.adocao.pet.api.exception.ResourceNotFoundException;
import com.adocao.pet.api.repository.AdocaoRepository;
import com.adocao.pet.api.repository.PetRepository;
import com.adocao.pet.api.repository.TutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdocaoService {

    private final AdocaoRepository adocaoRepository;
    private final TutorRepository tutorRepository;
    private final PetRepository petRepository;

    public AdocaoResponseDTO solicitarAdocao(CreateAdocaoRequestDTO dto) {

        Tutor tutor = buscarTutor(dto.tutorId());
        Pet pet = buscarPet(dto.petId());

        validarDisponibilidadePet(pet);

        Adocao adocao = Adocao.builder()
                .tutor(tutor)
                .pet(pet)
                .status(AdocaoStatus.PENDENTE)
                .dataSolicitacao(LocalDate.now())
                .build();

        Adocao adocaoSalva = adocaoRepository.save(adocao);

        return converterParaResponse(adocaoSalva);
    }

    public List<AdocaoResponseDTO> listarAdocoes() {

        return adocaoRepository.findAll()
                .stream()
                .map(this::converterParaResponse)
                .toList();
    }

    public AdocaoResponseDTO buscarPorId(Long id) {

        return converterParaResponse(buscarAdocao(id));
    }

    @Transactional
    public AdocaoResponseDTO aprovarAdocao(Long id) {

        Adocao adocao = buscarAdocao(id);

        validarAprovacao(adocao);

        aprovarSolicitacao(adocao);

        atualizarStatusPet(adocao.getPet());

        recusarDemaisSolicitacoes(adocao);

        return converterParaResponse(adocao);
    }

    public AdocaoResponseDTO recusarAdocao(Long id) {

        Adocao adocao = buscarAdocao(id);

        if (adocao.getStatus() != AdocaoStatus.PENDENTE) {
            throw new BusinessRuleException(
                    "Somente adoções pendentes podem ser recusadas");
        }

        adocao.setStatus(AdocaoStatus.RECUSADA);

        Adocao adocaoAtualizada =
                adocaoRepository.save(adocao);

        return converterParaResponse(adocaoAtualizada);
    }

    private void validarDisponibilidadePet(Pet pet) {

        if (pet.getStatus() != PetStatus.DISPONIVEL) {
            throw new BusinessRuleException(
                    "Este pet não está disponível para adoção");
        }
    }

    private void validarAprovacao(Adocao adocao) {

        if (adocao.getStatus() != AdocaoStatus.PENDENTE) {
            throw new BusinessRuleException(
                    "Somente adoções pendentes podem ser aprovadas");
        }

        if (adocao.getPet().getStatus() == PetStatus.ADOTADO) {
            throw new BusinessRuleException(
                    "Este pet já foi adotado");
        }
    }

    private void aprovarSolicitacao(Adocao adocao) {

        adocao.setStatus(AdocaoStatus.APROVADA);

        adocaoRepository.save(adocao);
    }

    private void atualizarStatusPet(Pet pet) {

        pet.setStatus(PetStatus.ADOTADO);

        petRepository.save(pet);
    }

    private void recusarDemaisSolicitacoes(Adocao adocaoAprovada) {

        List<Adocao> pendentes =
                adocaoRepository.findByPetAndStatus(
                        adocaoAprovada.getPet(),
                        AdocaoStatus.PENDENTE
                );

        pendentes.removeIf(adocao ->
                adocao.getId().equals(adocaoAprovada.getId()));

        pendentes.forEach(adocao ->
                adocao.setStatus(AdocaoStatus.RECUSADA));

        adocaoRepository.saveAll(pendentes);
    }

    private Tutor buscarTutor(Long id) {

        return tutorRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Tutor não encontrado"));
    }

    private Pet buscarPet(Long id) {

        return petRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Pet não encontrado"));
    }

    private Adocao buscarAdocao(Long id) {

        return adocaoRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Adoção não encontrada"));
    }

    private AdocaoResponseDTO converterParaResponse(Adocao adocao) {

        return new AdocaoResponseDTO(
                adocao.getId(),
                adocao.getDataSolicitacao(),
                adocao.getStatus(),

                adocao.getTutor().getId(),
                adocao.getTutor().getNome(),

                adocao.getPet().getId(),
                adocao.getPet().getNome()
        );
    }
}