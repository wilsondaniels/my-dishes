package dev.wilsondaniels.mydishes.application.catalogemit;

import dev.wilsondaniels.mydishes.domain.catalogemit.BucketGateway;
import dev.wilsondaniels.mydishes.domain.catalogemit.CatalogEmitGateway;
import dev.wilsondaniels.mydishes.domain.catalogemit.PresentationCatalogGateway;
import dev.wilsondaniels.mydishes.domain.category.Category;
import dev.wilsondaniels.mydishes.domain.category.CategoryGateway;
import dev.wilsondaniels.mydishes.domain.product.Product;
import dev.wilsondaniels.mydishes.domain.product.ProductGateway;

import java.util.*;
import java.util.stream.Collectors;

public class DefaultCatalogEmitUseCase implements CatalogEmitUseCase {

    private final CatalogEmitGateway catalogEmitGateway;
    private final ProductGateway productGateway;
    private final CategoryGateway categoryGateway;
    private final PresentationCatalogGateway<String> presentationCatalogGateway;
    private final BucketGateway bucketGateway;

    public DefaultCatalogEmitUseCase(
             CatalogEmitGateway catalogEmitGateway,
             ProductGateway productGateway,
             CategoryGateway categoryGateway,
             PresentationCatalogGateway presentationCatalogGateway,
             BucketGateway bucketGateway) {
        this.catalogEmitGateway = Objects.requireNonNull(catalogEmitGateway);
        this.productGateway = Objects.requireNonNull(productGateway);
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
        this.presentationCatalogGateway = Objects.requireNonNull(presentationCatalogGateway);
        this.bucketGateway = Objects.requireNonNull(bucketGateway);
    }

    @Override
    public void refresh(final String anOwnerId) {
        if (anOwnerId != null) {
            final var catalog = presentationCatalogGateway.create(anOwnerId, findCompleteCatalogByCategory(anOwnerId));
            upload(anOwnerId, catalog);
        }
    }

    private Map<Category, Set<Product>> findCompleteCatalogByCategory(String anOwnerId) {

        final Map<String, Category> categories = getCategoriesHashMap(anOwnerId);
        final var allProducts = productGateway.retrieveAll(anOwnerId);

        final Map<Category, Set<Product>> catalogMap = new TreeMap<>();

        for (final var p : allProducts) {

            Category category = categories.get(p.getCategoryId());
            if (category != null) {
                final Set<Product> productsByCategory = catalogMap
                        .putIfAbsent(category, new TreeSet<>());
                productsByCategory.add(p);
            }
        }

        return catalogMap;
    }

    private Map<String, Category> getCategoriesHashMap(String anOwnerId) {
        return categoryGateway.retrieveAll(anOwnerId)
                .stream()
                .collect(Collectors.toMap(
                        Category::getId,
                        category -> category,
                        (existing, replacement) -> existing,
                        HashMap::new));
    }

    public void upload(String anOwnerId, String catalog) {
        bucketGateway.upload(anOwnerId, catalog);
    }
}
