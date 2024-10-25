package dev.wilsondaniels.mydishes.application.category;

import java.util.List;

public interface CategoryUseCase {

    CategoryDTO create(CategoryDTO aCategory);
    CategoryDTO retrieveById(String anId);
    CategoryDTO update(CategoryDTO aCategory);
    void deleteById(String anId);
    List<CategoryDTO> retrieveAll(String ownerId);
}
