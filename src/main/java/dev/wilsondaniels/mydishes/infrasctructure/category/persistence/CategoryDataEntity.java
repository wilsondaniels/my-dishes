package dev.wilsondaniels.mydishes.infrasctructure.category.persistence;

import dev.wilsondaniels.mydishes.domain.category.Category;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "categories")
public class CategoryDataEntity {

    @Id
    private String id;
    private String title;
    private String description;
    private String ownerId;

    public CategoryDataEntity() {
    }

    public CategoryDataEntity(String id, String title, String description, String ownerId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.ownerId = ownerId;
    }

    public static CategoryDataEntity from(final Category category) {
        return new CategoryDataEntity(
                category.getId(),
                category.getTitle(),
                category.getDescription(),
                category.getOwnerId()
        );
    }

    public Category toAggregate() {
        return new Category(id, title, description, ownerId);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }
}
