package dev.wilsondaniels.mydishes.domain.category;

import dev.wilsondaniels.mydishes.domain.validation.Error;
import dev.wilsondaniels.mydishes.domain.validation.ValidationHandler;
import dev.wilsondaniels.mydishes.domain.validation.Validator;

import java.util.Objects;

public class CategoryValidator extends Validator {

    private final Category category;

    public CategoryValidator(final Category aCategory, final ValidationHandler aHandler) {
        super(aHandler);
        this.category = Objects.requireNonNull(aCategory);
    }

    @Override
    public void validate() {
        checkTitleConstraints();
        checkDescriptionConstraints();
        checkOwnerConstraints();
    }

    private void checkTitleConstraints() {
        if (category.getTitle() == null) {
            this.validationHandler().append(new Error("'title' should not be null"));
            return;
        }

        if (category.getTitle().isBlank()) {
            this.validationHandler().append(new Error("'title' should not be empty"));
            return;
        }
    }

    private void checkDescriptionConstraints() {
        if (category.getDescription() == null ) {
            this.validationHandler().append(new Error("'description' should not be null"));
            return;
        }

        if (category.getDescription().isBlank()) {
            this.validationHandler().append(new Error("'description' should not be empty"));
            return;
        }
    }

    private void checkOwnerConstraints() {
        if (category.getOwnerId() == null ) {
            this.validationHandler().append(new Error("'ownerId' should not be null"));
            return;
        }

        if (category.getOwnerId().isBlank()) {
            this.validationHandler().append(new Error("'ownerId' should not be empty"));
            return;
        }
    }
}
