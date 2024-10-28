package dev.wilsondaniels.mydishes.infrasctructure.configuration.usecase;

import dev.wilsondaniels.mydishes.application.catalog.CatalogUseCase;
import dev.wilsondaniels.mydishes.application.catalog.DefaultCatalogUseCase;
import dev.wilsondaniels.mydishes.application.catalogemit.CatalogEmitUseCase;
import dev.wilsondaniels.mydishes.application.catalogemit.DefaultCatalogEmitUseCase;
import dev.wilsondaniels.mydishes.application.category.CategoryUseCase;
import dev.wilsondaniels.mydishes.application.category.DefaultCategoryUseCase;
import dev.wilsondaniels.mydishes.application.product.DefaultProductUseCase;
import dev.wilsondaniels.mydishes.application.product.ProductUseCase;
import dev.wilsondaniels.mydishes.domain.catalog.CatalogGateway;
import dev.wilsondaniels.mydishes.domain.catalogemit.BucketGateway;
import dev.wilsondaniels.mydishes.domain.catalogemit.PresentationCatalogGateway;
import dev.wilsondaniels.mydishes.domain.category.CategoryGateway;
import dev.wilsondaniels.mydishes.domain.product.ProductGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCasesConfig {

    private final CategoryGateway categoryGateway;
    private final ProductGateway productGateway;
    private final CatalogGateway catalogGateway;
    private final PresentationCatalogGateway presentationCatalogGateway;
    private final BucketGateway bucketGateway;

    public UseCasesConfig(
            CategoryGateway categoryGateway,
            CatalogGateway catalogGateway,
            ProductGateway productGateway,
            PresentationCatalogGateway presentationCatalogGateway,
            BucketGateway bucketGateway) {
        this.categoryGateway = categoryGateway;
        this.catalogGateway = catalogGateway;
        this.productGateway = productGateway;
        this.presentationCatalogGateway = presentationCatalogGateway;
        this.bucketGateway = bucketGateway;
    }

    @Bean
    public CatalogUseCase getCatalogUseCase() {
        return new DefaultCatalogUseCase(catalogGateway);
    }

    @Bean
    public CategoryUseCase getCategoryUseCase() {
        return new DefaultCategoryUseCase(categoryGateway, getCatalogUseCase());
    }

    @Bean
    public ProductUseCase getProductUseCase() {
        return new DefaultProductUseCase(productGateway, getCatalogUseCase());
    }

    @Bean
    public CatalogEmitUseCase getCatalogEmitUseCase() {
        return new DefaultCatalogEmitUseCase(
                productGateway,
                categoryGateway,
                presentationCatalogGateway,
                bucketGateway);
    }
}
