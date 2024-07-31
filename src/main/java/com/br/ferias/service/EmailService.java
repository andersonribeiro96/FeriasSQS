package com.br.ferias.service;

import com.br.ferias.domain.Ferias;
import com.br.ferias.domain.Funcionario;
import com.br.ferias.sqs.EmailMessage;
import com.br.ferias.sqs.producer.EmailProducerSQS;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;

@Service
public class EmailService {

    private final EmailProducerSQS emailProducerSQS;

    public EmailService(EmailProducerSQS emailProducerSQS) {
        this.emailProducerSQS = emailProducerSQS;
    }

    private void enviarEmail(String email, String assunto, String corpo) {
        EmailMessage emailMessage = new EmailMessage(email, assunto, corpo);
        emailProducerSQS.send(emailMessage);
}

    public void notificarGestorSolicitacaoDeFerias(Ferias ferias) {
        Funcionario funcionario = ferias.getFuncionario();
        long dias = ChronoUnit.DAYS.between(ferias.getDataInicio(), ferias.getDataTermino());
        String assunto = "Aprovação de Férias";
        String corpo = String.format("O funcionário %s solicitou férias para o período: %s. Por favor, revise e aprove.",
                funcionario.getNome(), dias);
        enviarEmail(funcionario.getGestor().getEmail(), assunto, corpo);
    }

    public void notificarFuncionarioAprovacaoDeFerias(Ferias ferias) {
        Funcionario funcionario = ferias.getFuncionario();
        String assunto = ferias.isAprovado() ? "Férias Aprovadas" : "Férias Rejeitadas";
        String mensagem = String.format("Olá %s,%n%nSua solicitação de férias foi %s.%n" +
                        "Período: %s a %s.%n%n" +
                        "Atenciosamente,%nEquipe de RH",
                funcionario.getNome(), ferias.isAprovado() ? "aprovada" : "rejeitada", ferias.getDataInicio(), ferias.getDataTermino());
        enviarEmail(funcionario.getEmail(), assunto, mensagem);
    }




}
