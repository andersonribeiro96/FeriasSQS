package com.br.ferias.controller.dto;

import java.time.LocalDate;

public record SolicitarFeriasDTO(Long funcionarioId, LocalDate dataInicio, LocalDate dataFim) { }