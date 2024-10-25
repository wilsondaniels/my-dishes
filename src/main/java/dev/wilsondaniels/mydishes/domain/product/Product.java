package dev.wilsondaniels.mydishes.domain.product;

import dev.wilsondaniels.mydishes.domain.Entity;
import dev.wilsondaniels.mydishes.domain.validation.ValidationHandler;

import java.util.Comparator;
import java.util.UUID;

public class Product extends Entity<String> implements Comparator<Product> {

    private String title;
    private String description;
    private Double price;
    private String categoryId;
    private String ownerId;

    private Product() {
        super(UUID.randomUUID().toString());
    }

    public Product(String title, String description, Double price, String categoryId, String ownerId) {
        this();
        this.title = title;
        this.description = description;
        this.price = price;
        this.categoryId = categoryId;
        this.ownerId = ownerId;
    }

    public Product(String id, String title, String description, Double price, String categoryId, String ownerId) {
        super(id);
        this.title = title;
        this.description = description;
        this.price = price;
        this.categoryId = categoryId;
        this.ownerId = ownerId;
    }

    @Override
    public void validate(ValidationHandler handler) {
        new ProductValidator(this, handler).validate();
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public String getCategoryId() {
        return categoryId;
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

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public int compare(Product o1, Product o2) {
        return o1.getDescription().compareTo(o2.getDescription());
    }
}
