package com.adocao.pet.api.entity;

import com.adocao.pet.api.enums.AdocaoStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "adocao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Adocao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_solicitacao", nullable = false)
    private LocalDate dataSolicitacao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AdocaoStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tutor_id", nullable = false)
    private Tutor tutor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id", nullable = false)
    private Pet pet;
}