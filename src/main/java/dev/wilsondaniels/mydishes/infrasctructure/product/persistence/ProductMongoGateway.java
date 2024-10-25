package dev.wilsondaniels.mydishes.infrasctructure.product.persistence;

import dev.wilsondaniels.mydishes.domain.product.Product;
import dev.wilsondaniels.mydishes.domain.product.ProductGateway;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductMongoGateway implements ProductGateway {

    private final ProductRepository repository;

    public ProductMongoGateway(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product create(Product aProduct) {
        final var productDataEntity = ProductDataEntity.from(aProduct);
        return repository.save(productDataEntity).toAggregate();
    }

    @Override
    public Optional<Product> retrieveById(String anId) {
        return repository.findById(anId).map(ProductDataEntity::toAggregate);
    }

    @Override
    public Product update(Product aProduct) {
        final var productDataEntity = ProductDataEntity.from(aProduct);
        return repository.save(productDataEntity).toAggregate();
    }

    @Override
    public void deleteById(String anId) {
        if (this.repository.existsById(anId)) {
            this.repository.deleteById(anId);
        }
    }

    @Override
    public List<Product> retrieveAll(String ownerId) {
        return repository.findByOwnerId(ownerId).stream().map(ProductDataEntity::toAggregate).toList();
    }
}
