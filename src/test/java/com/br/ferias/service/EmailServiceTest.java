package com.br.ferias.service;

import com.br.ferias.domain.Ferias;
import com.br.ferias.domain.Funcionario;
import com.br.ferias.sqs.EmailMessage;
import com.br.ferias.sqs.producer.EmailProducerSQS;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;

public class EmailServiceTest {

    @Mock
    private EmailProducerSQS emailProducerSQS;

    @InjectMocks
    private EmailService emailService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testNotificarGestorSolicitacaoDeFerias() {
        // Arrange
        Funcionario gestor = new Funcionario();
        gestor.setEmail("gestor@example.com");
        Funcionario funcionario = new Funcionario();
        funcionario.setNome("João");
        funcionario.setGestor(gestor);

        Ferias ferias = new Ferias();
        ferias.setFuncionario(funcionario);
        ferias.setDataInicio(LocalDate.of(2024, 8, 1));
        ferias.setDataTermino(LocalDate.of(2024, 8, 10));

        // Act
        emailService.notificarGestorSolicitacaoDeFerias(ferias);

        // Assert
        ArgumentCaptor<EmailMessage> captor = ArgumentCaptor.forClass(EmailMessage.class);
        verify(emailProducerSQS).send(captor.capture());

        EmailMessage emailMessage = captor.getValue();
        assertEquals("gestor@example.com", emailMessage.destinatario());
        assertEquals("Aprovação de Férias", emailMessage.assunto());
        assertEquals("O funcionário João solicitou férias para o período: 9. Por favor, revise e aprove.", emailMessage.corpo());
    }

    @Test
    void testNotificarFuncionarioAprovacaoDeFerias() {
        // Arrange
        Funcionario funcionario = new Funcionario();
        funcionario.setEmail("funcionario@example.com");
        funcionario.setNome("João");

        Ferias ferias = new Ferias();
        ferias.setFuncionario(funcionario);
        ferias.setAprovado(true);
        ferias.setDataInicio(LocalDate.of(2024, 8, 1));
        ferias.setDataTermino(LocalDate.of(2024, 8, 10));

        // Act
        emailService.notificarFuncionarioAprovacaoDeFerias(ferias);

        // Assert
        ArgumentCaptor<EmailMessage> captor = ArgumentCaptor.forClass(EmailMessage.class);
        verify(emailProducerSQS).send(captor.capture());

        EmailMessage emailMessage = captor.getValue();
        assertEquals("funcionario@example.com", emailMessage.destinatario());
        assertEquals("Férias Aprovadas", emailMessage.assunto());
        assertTrue(emailMessage.corpo().contains("Olá João,"));
        assertTrue(emailMessage.corpo().contains("Sua solicitação de férias foi aprovada."));
        assertTrue(emailMessage.corpo().contains("Período: 2024-08-01 a 2024-08-10."));
    }

    @Test
    void testNotificarFuncionarioRejeicaoDeFerias() {
        // Arrange
        Funcionario funcionario = new Funcionario();
        funcionario.setEmail("funcionario@example.com");
        funcionario.setNome("João");

        Ferias ferias = new Ferias();
        ferias.setFuncionario(funcionario);
        ferias.setAprovado(false);
        ferias.setDataInicio(LocalDate.of(2024, 8, 1));
        ferias.setDataTermino(LocalDate.of(2024, 8, 10));

        // Act
        emailService.notificarFuncionarioAprovacaoDeFerias(ferias);

        // Assert
        ArgumentCaptor<EmailMessage> captor = ArgumentCaptor.forClass(EmailMessage.class);
        verify(emailProducerSQS).send(captor.capture());

        EmailMessage emailMessage = captor.getValue();
        assertEquals("funcionario@example.com", emailMessage.destinatario());
        assertEquals("Férias Rejeitadas", emailMessage.assunto());
        assertTrue(emailMessage.corpo().contains("Olá João,"));
        assertTrue(emailMessage.corpo().contains("Sua solicitação de férias foi rejeitada."));
        assertTrue(emailMessage.corpo().contains("Período: 2024-08-01 a 2024-08-10."));
    }

}
