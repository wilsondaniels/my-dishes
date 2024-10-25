package dev.wilsondaniels.mydishes.application.product;

import dev.wilsondaniels.mydishes.domain.product.Product;

public record ProductDTO(
        String id,
        String title,
        String description,
        Double price,
        String categoryId,
        String ownerId
) {

    static ProductDTO from(Product product) {

        if (product == null) {
            return null;
        }

        return new ProductDTO(
                product.getId(),
                product.getTitle(),
                product.getDescription(),
                product.getPrice(),
                product.getCategoryId(),
                product.getOwnerId()
        );
    }

    Product toEntity() {
        return new Product(
            id,
            title,
            description,
            price,
            categoryId,
            ownerId
        );
    }
}
