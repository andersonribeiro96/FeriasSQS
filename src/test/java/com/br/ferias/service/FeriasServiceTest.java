package com.br.ferias.service;

import com.br.ferias.controller.dto.AprovaFeriasDTO;
import com.br.ferias.controller.dto.SolicitarFeriasDTO;
import com.br.ferias.domain.Ferias;
import com.br.ferias.domain.Funcionario;
import com.br.ferias.exception.FeriasNotFoundException;
import com.br.ferias.repository.FeriasRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class FeriasServiceTest {

    @Mock
    private FeriasRepository feriasRepository;

    @Mock
    private FuncionarioService funcionarioService;

    @Mock
    private EmailService emailService;

    @InjectMocks
    private FeriasService feriasService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSolicitarFeriasComGestor() {
        // Arrange
        Long funcionarioId = 1L;
        Funcionario funcionario = new Funcionario();
        funcionario.setId(funcionarioId);
        funcionario.setNome("João");
        Funcionario gestor = new Funcionario();
        funcionario.setGestor(gestor);

        SolicitarFeriasDTO solicitarFeriasDTO = new SolicitarFeriasDTO(funcionarioId, LocalDate.now(), LocalDate.now().plusDays(10));
        Ferias ferias = Ferias.criarFerias(funcionario, solicitarFeriasDTO.dataInicio(), solicitarFeriasDTO.dataFim());

        when(funcionarioService.buscarFuncionario(funcionarioId)).thenReturn(funcionario);
        when(feriasRepository.save(any(Ferias.class))).thenReturn(ferias);

        // Act
        Ferias result = feriasService.solicitarFerias(solicitarFeriasDTO);

        // Assert
        assertNotNull(result);
        assertEquals(ferias, result);
        verify(emailService, times(1)).notificarGestorSolicitacaoDeFerias(ferias);
    }

    @Test
    void testSolicitarFeriasSemGestor() {
        // Arrange
        Long funcionarioId = 2L;
        Funcionario funcionario = new Funcionario();
        funcionario.setId(funcionarioId);
        funcionario.setNome("Maria");

        SolicitarFeriasDTO solicitarFeriasDTO = new SolicitarFeriasDTO(funcionarioId, LocalDate.now(), LocalDate.now().plusDays(10));
        Ferias ferias = Ferias.criarFerias(funcionario, solicitarFeriasDTO.dataInicio(), solicitarFeriasDTO.dataFim());

        when(funcionarioService.buscarFuncionario(funcionarioId)).thenReturn(funcionario);
        when(feriasRepository.save(any(Ferias.class))).thenReturn(ferias);

        // Act
        Ferias result = feriasService.solicitarFerias(solicitarFeriasDTO);

        // Assert
        assertNotNull(result);
        assertEquals(ferias, result);
        verify(emailService, never()).notificarGestorSolicitacaoDeFerias(any(Ferias.class));
    }

    @Test
    void testProcessarSolicitacaoDeFerias() {
        // Arrange
        Long feriasId = 3L;
        Ferias ferias = new Ferias();
        ferias.setId(feriasId);
        ferias.setAprovado(false);

        AprovaFeriasDTO aprovaFeriasDTO = new AprovaFeriasDTO(feriasId, true);

        when(feriasRepository.findById(feriasId)).thenReturn(Optional.of(ferias));

        // Act
        Ferias result = feriasService.processarSolicitacaoDeFerias(aprovaFeriasDTO);

        // Assert
        assertTrue(result.isAprovado());
        verify(feriasRepository, times(1)).save(ferias);
        verify(emailService, times(1)).notificarFuncionarioAprovacaoDeFerias(ferias);
    }

    @Test
    void testProcessarSolicitacaoDeFeriasFeriasNaoEncontradas() {
        // Arrange
        Long feriasId = 4L;
        AprovaFeriasDTO aprovaFeriasDTO = new AprovaFeriasDTO(feriasId, true);

        when(feriasRepository.findById(feriasId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(FeriasNotFoundException.class, () -> feriasService.processarSolicitacaoDeFerias(aprovaFeriasDTO));
    }
}