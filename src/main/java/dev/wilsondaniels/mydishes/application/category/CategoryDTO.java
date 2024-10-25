package dev.wilsondaniels.mydishes.application.category;

import dev.wilsondaniels.mydishes.domain.category.Category;

public record CategoryDTO(
        String id,
        String title,
        String description,
        String ownerId
) {
    static CategoryDTO from(Category category) {

        if (category == null) {
            return null;
        }

        return new CategoryDTO(
                category.getId(),
                category.getTitle(),
                category.getDescription(),
                category.getOwnerId()
        );
    }

    Category toEntity() {
        return new Category(
                id,
                title,
                description,
                ownerId
        );
    }
}
