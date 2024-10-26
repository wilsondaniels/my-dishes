package dev.wilsondaniels.mydishes.infrasctructure.catalogemit.queue;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQListener {

    @RabbitListener(queues = "my_dishes")
    public void receiveMessage(String message) {
        System.err.println("Mensagem recebida da fila: " + message);
        // Aqui você pode processar a mensagem
    }
}

