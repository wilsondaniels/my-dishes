package dev.wilsondaniels.mydishes.application.product;

import dev.wilsondaniels.mydishes.application.catalog.CatalogUseCase;
import dev.wilsondaniels.mydishes.domain.exception.DomainException;
import dev.wilsondaniels.mydishes.domain.exception.NotFoundException;
import dev.wilsondaniels.mydishes.domain.product.Product;
import dev.wilsondaniels.mydishes.domain.product.ProductGateway;
import dev.wilsondaniels.mydishes.domain.validation.handler.Notification;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class DefaultProductUseCase implements ProductUseCase {

    private final ProductGateway productGateway;

    private final CatalogUseCase catalogUseCase;

    public DefaultProductUseCase(ProductGateway productGateway, CatalogUseCase catalogUseCase) {
        this.productGateway = Objects.requireNonNull(productGateway);
        this.catalogUseCase = Objects.requireNonNull(catalogUseCase);
    }

    @Override
    public ProductDTO create(ProductDTO aProduct) {

        Objects.requireNonNull(aProduct);

        final var notification = Notification.create();

        final var newProduct = aProduct.toEntity();

        newProduct.validate(notification);

        if (notification.hasError()) {
            throw DomainException.with(notification.getErrors());
        }

        final var productCreated = ProductDTO.from(productGateway.create(newProduct));

        catalogUseCase.publish(productCreated.ownerId());

        return productCreated;
    }

    @Override
    public ProductDTO retrieveById(String anId) {

        final Optional<Product> product = productGateway.retrieveById(anId);

        if (product.isPresent()) {
            return ProductDTO.from(product.get());
        }

        throw NotFoundException.with(Product.class, anId);
    }

    @Override
    public ProductDTO update(ProductDTO aProduct) {

        Objects.requireNonNull(aProduct);

        final var notification = Notification.create();

        var optionalProduct = productGateway.retrieveById(aProduct.id());

        if (!optionalProduct.isPresent()) {
            throw NotFoundException.with(Product.class, aProduct.id());
        }

        final var actualProduct = changeDataProduct(aProduct, optionalProduct);
        actualProduct.validate(notification);

        if (notification.hasError()) {
            throw DomainException.with(notification.getErrors());
        }

        final var productUpdated = ProductDTO.from(productGateway.update(actualProduct));

        catalogUseCase.publish(productUpdated.ownerId());

        return productUpdated;
    }

    private static Product changeDataProduct(ProductDTO aProduct, Optional<Product> optionalProduct) {

        final var actualProduct = optionalProduct.get();

        if (aProduct.title() != null ) {
            actualProduct.setTitle(aProduct.title());
        }

        if (aProduct.description() != null) {
            actualProduct.setDescription(aProduct.description());
        }

        if (aProduct.price() != null) {
            actualProduct.setDescription(aProduct.description());
        }

        if (aProduct.categoryId() != null) {
            actualProduct.setCategoryId(aProduct.categoryId());
        }

        return actualProduct;
    }

    @Override
    public void deleteById(String anId) {
        Objects.requireNonNull(anId);
        productGateway.deleteById(anId);

        Objects.requireNonNull(anId);
        final var optionalProduct = productGateway.retrieveById(anId);

        if (optionalProduct.isPresent()) {
            final var ownerId = optionalProduct.get().getOwnerId();
            productGateway.deleteById(anId);
            catalogUseCase.publish(optionalProduct.get().getOwnerId());
        }
    }

    @Override
    public List<ProductDTO> retrieveAll(String ownerId) {
        return productGateway.retrieveAll(ownerId).stream().map(ProductDTO::from).toList();
    }
}
