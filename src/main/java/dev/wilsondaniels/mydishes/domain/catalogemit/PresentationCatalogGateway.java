package dev.wilsondaniels.mydishes.domain.catalogemit;

import dev.wilsondaniels.mydishes.domain.category.Category;
import dev.wilsondaniels.mydishes.domain.product.Product;

import java.util.Map;
import java.util.Set;

public interface PresentationCatalogGateway<T> {
    T create(String anOwnerId, Map<Category, Set<Product>> catalog);
}
