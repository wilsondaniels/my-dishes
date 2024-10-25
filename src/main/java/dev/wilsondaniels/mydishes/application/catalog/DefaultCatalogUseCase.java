package dev.wilsondaniels.mydishes.application.catalog;

import dev.wilsondaniels.mydishes.domain.catalog.CatalogGateway;

import java.util.Objects;

public class DefaultCatalogUseCase implements CatalogUseCase {

    private final CatalogGateway catalogGateway;

    public DefaultCatalogUseCase(CatalogGateway catalogGateway) {
        this.catalogGateway = Objects.requireNonNull(catalogGateway);
    }

    @Override
    public void publish(String anOwnerId) {
        catalogGateway.publish(Objects.requireNonNull(anOwnerId));
    }
}
