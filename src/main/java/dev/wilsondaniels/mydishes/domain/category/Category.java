package dev.wilsondaniels.mydishes.domain.category;

import dev.wilsondaniels.mydishes.domain.Entity;
import dev.wilsondaniels.mydishes.domain.validation.ValidationHandler;

import java.util.Comparator;
import java.util.UUID;

public class Category extends Entity<String> implements Comparator<Category> {

    private String title;
    private String description;
    private String ownerId;

    private Category() {
        super(UUID.randomUUID().toString());
    }

    public Category(String title, String description, String ownerId) {
        this();
        this.title = title;
        this.description = description;
        this.ownerId = ownerId;
    }

    public Category(String id, String title, String description, String ownerId) {
        super(id);
        this.title = title;
        this.description = description;
        this.ownerId = ownerId;
    }

    @Override
    public void validate(ValidationHandler handler) {
        new CategoryValidator(this, handler).validate();
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int compare(Category c1, Category c2) {
        return c1.getTitle().compareTo(c2.getTitle());
    }
}