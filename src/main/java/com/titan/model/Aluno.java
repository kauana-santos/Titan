package com.titan.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @NotBlank(message = "O CPF é obrigatório")
    @Column(unique = true)
    private String cpf;

    @NotBlank(message = "A data de nascimento é obrigatória")
    private LocalDate dtNascimento;

    @NotBlank(message = "A matricula é obrigatória")
    private Boolean matriculaAtiva;

    @NotBlank(message = "O plano é obrigatório")
    @Enumerated(EnumType.STRING)
    private Plano plano;
}
