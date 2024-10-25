package dev.wilsondaniels.mydishes.domain.product;

import dev.wilsondaniels.mydishes.domain.validation.Error;
import dev.wilsondaniels.mydishes.domain.validation.ValidationHandler;
import dev.wilsondaniels.mydishes.domain.validation.Validator;

import java.util.Objects;

public class ProductValidator extends Validator {

    private final Product product;

    public ProductValidator(final Product aProduct, final ValidationHandler aHandler) {
        super(aHandler);
        this.product = Objects.requireNonNull(aProduct);
    }

    @Override
    public void validate() {
        checkTitleConstraints();
        checkDescriptionConstraints();
        checkPriceConstraints();
        checkCategoryConstraints();
        checkOwnerConstraints();
    }

    private void checkTitleConstraints() {
        if (product.getTitle() == null) {
            this.validationHandler().append(new Error("'title' should not be null"));
            return;
        }

        if (product.getTitle().isBlank()) {
            this.validationHandler().append(new Error("'title' should not be empty"));
            return;
        }
    }

    private void checkDescriptionConstraints() {
        if (product.getDescription() == null ) {
            this.validationHandler().append(new Error("'description' should not be null"));
            return;
        }

        if (product.getDescription().isBlank()) {
            this.validationHandler().append(new Error("'description' should not be empty"));
            return;
        }
    }

    private void checkPriceConstraints() {
        if (product.getPrice() == null ) {
            this.validationHandler().append(new Error("'price' should not be null"));
            return;
        }

        if (product.getPrice() <= 0D) {
            this.validationHandler().append(new Error("'price' should be more than zero"));
            return;
        }
    }

    private void checkCategoryConstraints() {
        if (product.getCategoryId() == null ) {
            this.validationHandler().append(new Error("'categoryId' should not be null"));
            return;
        }

        if (product.getOwnerId().isBlank()) {
            this.validationHandler().append(new Error("'categoryId' should not be empty"));
            return;
        }
    }

    private void checkOwnerConstraints() {
        if (product.getOwnerId() == null ) {
            this.validationHandler().append(new Error("'ownerId' should not be null"));
            return;
        }

        if (product.getOwnerId().isBlank()) {
            this.validationHandler().append(new Error("'ownerId' should not be empty"));
            return;
        }
    }
}
