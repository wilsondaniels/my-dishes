package dev.wilsondaniels.mydishes.infrasctructure.category.persistence;

import dev.wilsondaniels.mydishes.domain.category.Category;
import dev.wilsondaniels.mydishes.domain.category.CategoryGateway;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryMongoGateway implements CategoryGateway {

    private final CategoryRepository repository;

    public CategoryMongoGateway(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Category create(Category aCategory) {
        final var categoryDataEntity = CategoryDataEntity.from(aCategory);
        return repository.save(categoryDataEntity).toAggregate();
    }

    @Override
    public Optional<Category> retrieveById(String anId) {
        return repository.findById(anId).map(CategoryDataEntity::toAggregate);
    }

    @Override
    public Category update(Category aCategory) {
        final var categoryDataEntity = CategoryDataEntity.from(aCategory);
        return repository.save(categoryDataEntity).toAggregate();
    }

    @Override
    public void deleteById(String anId) {
        if (this.repository.existsById(anId)) {
            this.repository.deleteById(anId);
        }
    }

    @Override
    public List<Category> retrieveAll(String ownerId) {
        return repository.findByOwnerId(ownerId).stream().map(CategoryDataEntity::toAggregate).toList();
    }
}
