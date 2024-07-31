package com.br.ferias.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class FeriasTest {

    @Test
    void testCriarFeriasComDadosValidos() {
        // Arrange
        Funcionario funcionario = new Funcionario();
        LocalDate dataInicio = LocalDate.of(2024, 8, 1);
        LocalDate dataTermino = LocalDate.of(2024, 8, 10);

        // Act
        Ferias ferias = Ferias.criarFerias(funcionario, dataInicio, dataTermino);

        // Assert
        assertNotNull(ferias);
        assertEquals(funcionario, ferias.getFuncionario());
        assertEquals(dataInicio, ferias.getDataInicio());
        assertEquals(dataTermino, ferias.getDataTermino());
        assertFalse(ferias.isAprovado());
    }

    @Test
    void testCriarFeriasComFuncionarioGestor() {
        // Arrange
        Funcionario gestor = new Funcionario();
        gestor.setIsGestor(true); // Configura como gestor
        LocalDate dataInicio = LocalDate.of(2024, 8, 1);
        LocalDate dataTermino = LocalDate.of(2024, 8, 10);

        // Act
        Ferias ferias = Ferias.criarFerias(gestor, dataInicio, dataTermino);

        // Assert
        assertNotNull(ferias);
        assertEquals(gestor, ferias.getFuncionario());
        assertEquals(dataInicio, ferias.getDataInicio());
        assertEquals(dataTermino, ferias.getDataTermino());
        assertTrue(ferias.isAprovado());
    }

    @Test
    void testCriarFeriasComDataInicioNula() {
        // Arrange
        Funcionario funcionario = new Funcionario();
        LocalDate dataTermino = LocalDate.of(2024, 8, 10);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                Ferias.criarFerias(funcionario, null, dataTermino)
        );
        assertEquals("Datas de início e término não podem ser nulas", exception.getMessage());
    }

    @Test
    void testCriarFeriasComDataTerminoNula() {
        // Arrange
        Funcionario funcionario = new Funcionario();
        LocalDate dataInicio = LocalDate.of(2024, 8, 1);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                Ferias.criarFerias(funcionario, dataInicio, null)
        );
        assertEquals("Datas de início e término não podem ser nulas", exception.getMessage());
    }

    @Test
    void testCriarFeriasComDataInicioDepoisDeDataTermino() {
        // Arrange
        Funcionario funcionario = new Funcionario();
        LocalDate dataInicio = LocalDate.of(2024, 8, 10);
        LocalDate dataTermino = LocalDate.of(2024, 8, 1);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                Ferias.criarFerias(funcionario, dataInicio, dataTermino)
        );
        assertEquals("Data de início não pode ser depois da data de término", exception.getMessage());
    }
}