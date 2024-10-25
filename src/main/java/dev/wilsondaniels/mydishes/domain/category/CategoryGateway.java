package dev.wilsondaniels.mydishes.domain.category;

import java.util.List;
import java.util.Optional;

public interface CategoryGateway {

    Category create(Category aCategory);
    Optional<Category> retrieveById(String anId);
    Category update(Category aCategory);
    void deleteById(String anId);
    List<Category> retrieveAll(String ownerId);
}
