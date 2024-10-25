package dev.wilsondaniels.mydishes.domain.catalogemit;

import dev.wilsondaniels.mydishes.domain.validation.Error;
import dev.wilsondaniels.mydishes.domain.validation.ValidationHandler;
import dev.wilsondaniels.mydishes.domain.validation.Validator;

import java.util.Objects;

public class CatalogEmitValidator extends Validator {

    private final CatalogEmit catalog;

    public CatalogEmitValidator(final CatalogEmit aCatalog, final ValidationHandler aHandler) {
        super(aHandler);
        this.catalog = Objects.requireNonNull(aCatalog);
    }

    @Override
    public void validate() {
        checkOwnerConstraints();
        checkCatalogConstraints();
    }

    private void checkOwnerConstraints() {
        if (catalog.getOwnerId() == null ) {
            this.validationHandler().append(new Error("'ownerId' should not be null"));
            return;
        }

        if (catalog.getOwnerId().isBlank()) {
            this.validationHandler().append(new Error("'ownerId' should not be empty"));
            return;
        }
    }

    private void checkCatalogConstraints() {
        if (catalog.getCatalog() == null) {
            this.validationHandler().append(new Error("'catalog' should not be null"));
            return;
        }

        if (catalog.getCatalog().isBlank()) {
            this.validationHandler().append(new Error("'catalog' should not be empty"));
            return;
        }
    }
}
