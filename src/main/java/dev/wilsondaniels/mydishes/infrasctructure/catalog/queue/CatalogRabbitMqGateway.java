package dev.wilsondaniels.mydishes.infrasctructure.catalog.queue;

import dev.wilsondaniels.mydishes.domain.catalog.CatalogGateway;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CatalogRabbitMqGateway implements CatalogGateway {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public CatalogRabbitMqGateway(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void publish(String anOwnerId) {
        rabbitTemplate.convertAndSend("my_dishes", anOwnerId);
    }
}
