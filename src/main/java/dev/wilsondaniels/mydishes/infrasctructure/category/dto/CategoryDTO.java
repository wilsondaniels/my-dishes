package dev.wilsondaniels.mydishes.infrasctructure.category.dto;

public record CategoryDTO(
        String id,
        String title,
        String description,
        String ownerId) {
}
