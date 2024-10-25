package dev.wilsondaniels.mydishes.domain;

import dev.wilsondaniels.mydishes.domain.validation.ValidationHandler;

public abstract class ValueObject {
    public abstract void validate(ValidationHandler handler);
}
