package com.br.ferias.sqs.consumer;

import com.br.ferias.sqs.EmailMessage;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumerSQS {

    @SqsListener("${aws.sqs.email-queue.name}")
    public void listen(@Payload EmailMessage message) {
        System.out.println(message);
    }

}
