package com.adocao.pet.api.repository;

import com.adocao.pet.api.entity.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TutorRepository extends JpaRepository<Tutor, Long> {
    Optional<Tutor> findByEmail(String email);
    Optional<Tutor> findByCpf(String cpf);
    boolean existsByCpf(String cpf);
    boolean existsByEmail(String email);

}
