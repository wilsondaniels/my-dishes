package dev.wilsondaniels.mydishes.infrasctructure.product.persistence;

import dev.wilsondaniels.mydishes.domain.product.Product;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
public class ProductDataEntity {

    @Id
    private String id;
    private String title;
    private String description;
    private Double price;
    private String categoryId;
    private String ownerId;

    public ProductDataEntity() {
    }

    public ProductDataEntity(String id, String title, String description, Double price, String categoryId, String ownerId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.categoryId = categoryId;
        this.ownerId = ownerId;
    }

    public static ProductDataEntity from(final Product product) {
        return new ProductDataEntity(
                product.getId(),
                product.getTitle(),
                product.getDescription(),
                product.getPrice(),
                product.getCategoryId(),
                product.getOwnerId()
        );
    }

    public Product toAggregate() {
        return new Product(id, title, description, price, categoryId, ownerId);
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }
}
