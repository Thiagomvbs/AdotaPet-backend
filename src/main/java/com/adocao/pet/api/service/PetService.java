package com.adocao.pet.api.service;

import com.adocao.pet.api.dto.request.pet.CreatePetRequestDTO;
import com.adocao.pet.api.dto.request.pet.UpdatePetRequestDTO;
import com.adocao.pet.api.dto.response.pet.PetResponseDTO;
import com.adocao.pet.api.entity.Pet;
import com.adocao.pet.api.enums.PetStatus;
import com.adocao.pet.api.exception.ResourceNotFoundException;
import com.adocao.pet.api.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PetService {

    private final PetRepository petRepository;

    public PetResponseDTO criarPet(CreatePetRequestDTO dto) {

        Pet pet = Pet.builder()
                .nome(dto.nome())
                .especie(dto.especie())
                .raca(dto.raca())
                .idade(dto.idade())
                .descricao(dto.descricao())
                .status(PetStatus.DISPONIVEL)
                .build();

        Pet petSalvo = petRepository.save(pet);

        return converterParaResponse(petSalvo);
    }

    public List<PetResponseDTO> listarPets() {

        return petRepository.findAll()
                .stream()
                .map(this::converterParaResponse)
                .toList();
    }

    public PetResponseDTO buscarPetPorId(Long id) {

        Pet pet = buscarEntidadePorId(id);

        return converterParaResponse(pet);
    }

    public PetResponseDTO atualizarPet(Long id,
                                       UpdatePetRequestDTO dto) {

        Pet pet = buscarEntidadePorId(id);

        pet.setNome(dto.nome());
        pet.setEspecie(dto.especie());
        pet.setRaca(dto.raca());
        pet.setIdade(dto.idade());
        pet.setDescricao(dto.descricao());

        Pet petAtualizado = petRepository.save(pet);

        return converterParaResponse(petAtualizado);
    }

    public void deletarPet(Long id) {

        Pet pet = buscarEntidadePorId(id);

        petRepository.delete(pet);
    }

    private Pet buscarEntidadePorId(Long id) {

        return petRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Pet não encontrado com id: " + id));
    }

    private PetResponseDTO converterParaResponse(Pet pet) {

        return new PetResponseDTO(
                pet.getId(),
                pet.getNome(),
                pet.getEspecie(),
                pet.getRaca(),
                pet.getIdade(),
                pet.getDescricao(),
                pet.getStatus()
        );
    }
}