package dev.wilsondaniels.mydishes.infrasctructure.product.controller;

import dev.wilsondaniels.mydishes.application.product.ProductDTO;
import dev.wilsondaniels.mydishes.application.product.ProductUseCase;
import dev.wilsondaniels.mydishes.infrasctructure.product.ProductAPI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

import static dev.wilsondaniels.mydishes.infrasctructure.util.Util.isUUIDValid;

@RestController
public class ProductController implements ProductAPI {

    private final ProductUseCase useCase;

    public ProductController(ProductUseCase useCase) {
        this.useCase = useCase;
    }

    @Override
    public ResponseEntity<?> create(String ownerId, ProductDTO input) {
        final ProductDTO productCreated = useCase.create(input);
        return ResponseEntity.created(URI.create("/products/" + productCreated.id())).body(productCreated);
    }

    @Override
    public ResponseEntity<?> getById(String ownerId, String id) {
        if (!isUUIDValid(id)) {
            return ResponseEntity.notFound().build();
        }

        final var productDTO = useCase.retrieveById(id);
        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateById(String ownerId, String id, ProductDTO input) {
        if (!isUUIDValid(id)) {
            return ResponseEntity.notFound().build();
        }

        final ProductDTO productUpdated = useCase.update(input);
        return ResponseEntity.created(URI.create("/products/" + productUpdated.id())).body(productUpdated);
    }

    @Override
    public ResponseEntity<?> deleteById(String ownerId, String id) {
        if (!isUUIDValid(id)) {
            return ResponseEntity.notFound().build();
        }

        useCase.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public List<ProductDTO> findAll(String ownerId) {
        return useCase.retrieveAll(ownerId);
    }
}
