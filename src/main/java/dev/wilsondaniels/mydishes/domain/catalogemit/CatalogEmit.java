package dev.wilsondaniels.mydishes.domain.catalogemit;

import dev.wilsondaniels.mydishes.domain.ValueObject;
import dev.wilsondaniels.mydishes.domain.validation.ValidationHandler;

public class CatalogEmit extends ValueObject {
    
    private final String ownerId;
    private final String catalog;

    public CatalogEmit(String ownerId, String catalog) {
        this.ownerId = ownerId;
        this.catalog = catalog;
    }

    @Override
    public void validate(ValidationHandler handler) {
        new CatalogEmitValidator(this, handler).validate();
    }

    public String getOwnerId() {
        return ownerId;
    }

    public String getCatalog() {
        return catalog;
    }
}
