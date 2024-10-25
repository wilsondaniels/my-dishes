package dev.wilsondaniels.mydishes.application.category;

import dev.wilsondaniels.mydishes.application.catalog.CatalogUseCase;
import dev.wilsondaniels.mydishes.domain.category.Category;
import dev.wilsondaniels.mydishes.domain.category.CategoryGateway;
import dev.wilsondaniels.mydishes.domain.exception.DomainException;
import dev.wilsondaniels.mydishes.domain.exception.NotFoundException;
import dev.wilsondaniels.mydishes.domain.validation.handler.Notification;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class DefaultCategoryUseCase implements CategoryUseCase {

    private final CategoryGateway categoryGateway;

    private final CatalogUseCase catalogUseCase;

    public DefaultCategoryUseCase(CategoryGateway categoryGateway, CatalogUseCase catalogUseCase) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
        this.catalogUseCase = Objects.requireNonNull(catalogUseCase);
    }

    @Override
    public CategoryDTO create(CategoryDTO aCategory) {

        Objects.requireNonNull(aCategory);

        final var notification = Notification.create();

        final var newCategory = Category.newCategory(aCategory.title(), aCategory.description(), aCategory.ownerId());

        newCategory.validate(notification);

        if (notification.hasError()) {
            throw DomainException.with(notification.getErrors());
        }

        final var categoryCreated = CategoryDTO.from(categoryGateway.create(newCategory));

        catalogUseCase.publish(aCategory.ownerId());

        return categoryCreated;
    }

    @Override
    public CategoryDTO retrieveById(String anId) {

        final Optional<Category> category = categoryGateway.retrieveById(anId);

        if (category.isPresent()) {
            return CategoryDTO.from(category.get());
        }

        throw NotFoundException.with(Category.class, anId);
    }

    @Override
    public CategoryDTO update(CategoryDTO aCategory) {
        Objects.requireNonNull(aCategory);

        final var notification = Notification.create();

        var optionalCategory = categoryGateway.retrieveById(aCategory.id());

        if (!optionalCategory.isPresent()) {
            throw NotFoundException.with(Category.class, aCategory.id());
        }

        final var actualCategory = changeDataCategory(aCategory, optionalCategory);
        actualCategory.validate(notification);

        if (notification.hasError()) {
            throw DomainException.with(notification.getErrors());
        }

        final var categoryUpdated = CategoryDTO.from(categoryGateway.update(actualCategory));

        catalogUseCase.publish(aCategory.ownerId());

        return categoryUpdated;
    }

    private static Category changeDataCategory(CategoryDTO aCategory, Optional<Category> optionalCategory) {

        final var actualCategory = optionalCategory.get();

        if (aCategory.title() != null ) {
            actualCategory.setTitle(aCategory.title());
        }

        if (aCategory.description() != null) {
            actualCategory.setDescription(aCategory.description());
        }

        return actualCategory;
    }

    @Override
    public void deleteById(String anId) {
        Objects.requireNonNull(anId);
        final var optionalCategory = categoryGateway.retrieveById(anId);

        if (optionalCategory.isPresent()) {
            final var ownerId = optionalCategory.get().getOwnerId();
            categoryGateway.deleteById(anId);
            catalogUseCase.publish(optionalCategory.get().getOwnerId());
        }
    }

    @Override
    public List<CategoryDTO> retrieveAll(String ownerId) {
        return categoryGateway.retrieveAll(ownerId).stream().map(CategoryDTO::from).toList();
    }
}
