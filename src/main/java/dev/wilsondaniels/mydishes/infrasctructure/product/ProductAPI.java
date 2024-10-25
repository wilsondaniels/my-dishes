package dev.wilsondaniels.mydishes.infrasctructure.product;

import dev.wilsondaniels.mydishes.infrasctructure.category.dto.CategoryDTO;
import dev.wilsondaniels.mydishes.infrasctructure.product.dto.ProductDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping(value = "produtcts")
public interface ProductAPI {

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE)
    List<ProductDTO> findAll();


}
