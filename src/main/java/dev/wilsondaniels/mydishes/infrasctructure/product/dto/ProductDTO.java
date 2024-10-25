package dev.wilsondaniels.mydishes.infrasctructure.product.dto;

import dev.wilsondaniels.mydishes.domain.category.Category;

public record ProductDTO(
        String id,
        String title,
        String description,
        String price,
        Category category,
        String ownerId) {
}
