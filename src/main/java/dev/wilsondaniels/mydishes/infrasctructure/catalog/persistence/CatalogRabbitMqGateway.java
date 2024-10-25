package dev.wilsondaniels.mydishes.infrasctructure.catalog.persistence;

import dev.wilsondaniels.mydishes.domain.catalog.CatalogGateway;
import org.springframework.stereotype.Service;

@Service
public class CatalogRabbitMqGateway implements CatalogGateway {
    @Override
    public void publish(String anOwnerId) {
        System.err.println("publish"); // MOCK
    }
}
