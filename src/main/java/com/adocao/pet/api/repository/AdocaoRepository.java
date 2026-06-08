package com.adocao.pet.api.repository;

import com.adocao.pet.api.entity.Adocao;
import com.adocao.pet.api.entity.Pet;
import com.adocao.pet.api.entity.Tutor;
import com.adocao.pet.api.enums.AdocaoStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdocaoRepository extends JpaRepository<Adocao, Long> {
    List<Adocao> findByTutor(Tutor tutor);
    List<Adocao> findByPet(Pet pet);
    List<Adocao> findByPetAndStatus(Pet pet, AdocaoStatus status);
}
