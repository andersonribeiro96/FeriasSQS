package com.br.ferias.service;

import com.br.ferias.domain.Funcionario;
import com.br.ferias.exception.FuncionarioNotFoundException;
import com.br.ferias.repository.FuncionarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FuncionarioServiceTest {

    @Mock
    private FuncionarioRepository funcionarioRepository;

    @InjectMocks
    private FuncionarioService funcionarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testBuscarFuncionarioExistente() {
        // Arrange
        Long funcionarioId = 1L;
        Funcionario funcionario = new Funcionario();
        funcionario.setId(funcionarioId);
        funcionario.setNome("João");

        when(funcionarioRepository.findById(funcionarioId)).thenReturn(Optional.of(funcionario));

        // Act
        Funcionario result = funcionarioService.buscarFuncionario(funcionarioId);

        // Assert
        assertNotNull(result);
        assertEquals(funcionarioId, result.getId());
        assertEquals("João", result.getNome());
        verify(funcionarioRepository, times(1)).findById(funcionarioId);
    }

    @Test
    void testBuscarFuncionarioNaoExistente() {

        Long funcionarioId = 2L;
        when(funcionarioRepository.findById(funcionarioId)).thenReturn(Optional.empty());

        assertThrows(FuncionarioNotFoundException.class, () -> {
            funcionarioService.buscarFuncionario(funcionarioId);
        });
        verify(funcionarioRepository, times(1)).findById(funcionarioId);
    }


}
