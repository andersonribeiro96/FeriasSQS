package com.br.ferias.domain;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table
public class Ferias {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dataInicio;

    private LocalDate dataTermino;

    private boolean aprovado;

    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;

    public static Ferias criarFerias(Funcionario funcionario, LocalDate dataInicio, LocalDate dataTermino) {

        if (dataInicio == null || dataTermino == null) {
            throw new IllegalArgumentException("Datas de início e término não podem ser nulas");
        }
        if (dataInicio.isAfter(dataTermino)) {
            throw new IllegalArgumentException("Data de início não pode ser depois da data de término");
        }

        Ferias ferias = new Ferias();
        ferias.setFuncionario(funcionario);
        ferias.setDataInicio(dataInicio);
        ferias.setDataTermino(dataTermino);
        ferias.setAprovado(false);

        if(funcionario.isGestor()){
            ferias.setAprovado(true);
        }

        return ferias;
    }

}
