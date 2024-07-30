package com.br.ferias.sqs;

public record EmailMessage(String destinatario, String assunto, String corpo) {
}