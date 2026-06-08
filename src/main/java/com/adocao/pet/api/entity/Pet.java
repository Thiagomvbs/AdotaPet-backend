package com.adocao.pet.api.entity;

import com.adocao.pet.api.enums.PetStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pet")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 50)
    private String especie;

    @Column(length = 50)
    private String raca;

    private Integer idade;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PetStatus status;

    @OneToMany(mappedBy = "pet")
    @Builder.Default
    private List<Adocao> adocoes = new ArrayList<>();
}