package com.br.ferias.controller.dto;

import com.br.ferias.domain.Ferias;
import com.br.ferias.domain.Funcionario;

import java.time.LocalDate;

public record FeriasDTO(
        Long id,
        LocalDate dataInicio,
        LocalDate dataTermino,
        boolean aprovado,
        Long funcionarioId
) {
    // Método estático para converter de Entity para DTO
    public static FeriasDTO fromEntity(Ferias ferias) {
        return new FeriasDTO(
                ferias.getId(),
                ferias.getDataInicio(),
                ferias.getDataTermino(),
                ferias.isAprovado(),
                ferias.getFuncionario() != null ? ferias.getFuncionario().getId() : null
        );
    }

    // Método estático para converter de DTO para Entity
    public Ferias toEntity(Funcionario funcionario) {
        Ferias ferias = new Ferias();
        ferias.setId(this.id());
        ferias.setDataInicio(this.dataInicio());
        ferias.setDataTermino(this.dataTermino());
        ferias.setAprovado(this.aprovado());
        ferias.setFuncionario(funcionario);
        return ferias;
    }
}