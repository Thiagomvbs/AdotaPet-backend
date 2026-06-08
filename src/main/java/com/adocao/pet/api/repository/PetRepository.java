package com.adocao.pet.api.repository;

import com.adocao.pet.api.entity.Pet;
import com.adocao.pet.api.enums.PetStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Long> {
    List<Pet> findByStatus(PetStatus status);
}
