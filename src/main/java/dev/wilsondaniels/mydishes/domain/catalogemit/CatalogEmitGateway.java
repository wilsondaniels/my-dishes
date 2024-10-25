package dev.wilsondaniels.mydishes.domain.catalogemit;

public interface CatalogEmitGateway {
    String consume();
    String find(String anOwnerId);
    void upload(String anOwnerId, String catalog);
}
