package dev.wilsondaniels.mydishes.domain.exception;

import dev.wilsondaniels.mydishes.domain.validation.Error;

import java.util.Collections;
import java.util.List;

public class NotFoundException extends DomainException {
    protected NotFoundException(String aMessage, List<Error> anErrors) {
        super(aMessage, anErrors);
    }

    public static NotFoundException with(Class<?> clazz, String anId) {
        return new NotFoundException("%s with ID %s was not found".formatted(clazz.getSimpleName(), anId),
                Collections.emptyList());
    }
}
