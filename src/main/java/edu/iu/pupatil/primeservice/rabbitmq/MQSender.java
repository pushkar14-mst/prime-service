package edu.iu.pupatil.primeservice.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.Queue;

@Component
public class MQSender {
    private final Queue queue;
    private final RabbitTemplate rabbitTemplate;

    public MQSender(Queue queue, RabbitTemplate rabbitTemplate){
        this.queue = queue;
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(long n, boolean isPrime){
        String message =
                MessageFormat
                        .format("n: {0}, isprime: {1}"
                                , n, isPrime);
        rabbitTemplate.convertAndSend("primes", message);
    }
}