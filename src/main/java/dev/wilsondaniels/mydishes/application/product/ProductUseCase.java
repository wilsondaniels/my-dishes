package dev.wilsondaniels.mydishes.application.product;

import java.util.List;

public interface ProductUseCase {

    ProductDTO create(ProductDTO aProduct);
    ProductDTO retrieveById(String anId);
    ProductDTO update(ProductDTO aProduct);
    void deleteById(String anId);
    List<ProductDTO> retrieveAll(String ownerId);
}
