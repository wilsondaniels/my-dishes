package dev.wilsondaniels.mydishes.infrasctructure.product;

import dev.wilsondaniels.mydishes.application.product.ProductDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/{ownerid}/products")
public interface ProductAPI {

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<?> create(@PathVariable(name = "ownerid") String ownerId, @RequestBody ProductDTO input);

    @GetMapping(
            value = "{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<?> getById(@PathVariable(name = "ownerid") String ownerId, @PathVariable(name = "id") String id);

    @PutMapping(
            value = "{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<?> updateById(@PathVariable(name = "ownerid") String ownerId, @PathVariable(name = "id") String id, @RequestBody ProductDTO input);

    @DeleteMapping(
            value = "{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<?> deleteById(@PathVariable(name = "ownerid") String ownerId, @PathVariable(name = "id") String id);

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE)
    List<ProductDTO> findAll(@PathVariable(name = "ownerid") String ownerId);


}
