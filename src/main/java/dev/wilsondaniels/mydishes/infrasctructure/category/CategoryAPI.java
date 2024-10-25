package dev.wilsondaniels.mydishes.infrasctructure.category;

import dev.wilsondaniels.mydishes.infrasctructure.category.dto.CategoryDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping(value = "categories")
public interface CategoryAPI {

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE)
    List<CategoryDTO> findAll();


}
