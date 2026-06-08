package com.adocao.pet.api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tutor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tutor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, unique = true, length = 14)
    private String cpf;

    @Column(length = 20)
    private String telefone;

    @Column
    private String senha;

    @OneToMany(mappedBy = "tutor")
    @Builder.Default
    private List<Adocao> adocoes = new ArrayList<>();

}
