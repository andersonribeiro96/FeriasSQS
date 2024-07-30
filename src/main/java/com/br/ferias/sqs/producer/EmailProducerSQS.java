package com.br.ferias.sqs.producer;

import com.br.ferias.sqs.EmailMessage;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EmailProducerSQS {

    @Value("${aws.sqs.email-queue.url}")
    private String emailQueueUrl;

    private final SqsTemplate sqsTemplate;

    public EmailProducerSQS(SqsTemplate sqsTemplate) {
        this.sqsTemplate = sqsTemplate;
    }

    public void send(EmailMessage emailMessage) {
        sqsTemplate.send(emailQueueUrl, emailMessage);
    }

}
