package dev.wilsondaniels.mydishes.infrasctructure.configuration.usecase;

import dev.wilsondaniels.mydishes.application.catalog.CatalogUseCase;
import dev.wilsondaniels.mydishes.application.catalog.DefaultCatalogUseCase;
import dev.wilsondaniels.mydishes.application.category.CategoryUseCase;
import dev.wilsondaniels.mydishes.application.category.DefaultCategoryUseCase;
import dev.wilsondaniels.mydishes.application.product.DefaultProductUseCase;
import dev.wilsondaniels.mydishes.application.product.ProductUseCase;
import dev.wilsondaniels.mydishes.domain.catalog.CatalogGateway;
import dev.wilsondaniels.mydishes.domain.category.CategoryGateway;
import dev.wilsondaniels.mydishes.domain.product.ProductGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCasesConfig {

    private final CategoryGateway categoryGateway;
    private final ProductGateway productGateway;
    private final CatalogGateway catalogGateway;

    public UseCasesConfig(
            CategoryGateway categoryGateway,
            CatalogGateway catalogGateway,
            ProductGateway productGateway) {
        this.categoryGateway = categoryGateway;
        this.catalogGateway = catalogGateway;
        this.productGateway = productGateway;
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
}
