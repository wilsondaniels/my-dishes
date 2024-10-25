package dev.wilsondaniels.mydishes.domain.product;

import java.util.List;
import java.util.Optional;

public interface ProductGateway {

    Product create(Product aProduct);
    Optional<Product> retrieveById(String anId);
    Product update(Product aProduct);
    void deleteById(String anId);
    List<Product> retrieveAll(String ownerId);
}
