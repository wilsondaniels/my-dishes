package dev.wilsondaniels.mydishes.application.category;

import dev.wilsondaniels.mydishes.domain.product.Product;

import java.util.List;

public interface CategoryUseCase {

    CategoryDTO create(CategoryDTO aCategory);
    CategoryDTO retrieveById(String anId);
    CategoryDTO update(CategoryDTO aCategory);
    void deleteById(String anId);
    List<CategoryDTO> retrieveAll(String ownerId);
}
