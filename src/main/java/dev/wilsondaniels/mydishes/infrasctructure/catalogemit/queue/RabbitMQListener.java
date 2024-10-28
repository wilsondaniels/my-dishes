package dev.wilsondaniels.mydishes.infrasctructure.catalogemit.queue;

import dev.wilsondaniels.mydishes.application.catalogemit.CatalogEmitUseCase;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQListener {

    private final CatalogEmitUseCase catalogEmitUseCase;

    public RabbitMQListener(CatalogEmitUseCase catalogEmitUseCase) {
        this.catalogEmitUseCase = catalogEmitUseCase;
    }

    @RabbitListener(queues = "my_dishes")
    public void receiveMessage(String message) {
        catalogEmitUseCase.refresh(message);
    }
}

