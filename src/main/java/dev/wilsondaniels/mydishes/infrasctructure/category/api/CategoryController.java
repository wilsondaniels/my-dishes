package dev.wilsondaniels.mydishes.infrasctructure.category.api;

import dev.wilsondaniels.mydishes.infrasctructure.category.CategoryAPI;
import dev.wilsondaniels.mydishes.infrasctructure.category.dto.CategoryDTO;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController implements CategoryAPI {
    @Override
    public List<CategoryDTO> findAll() {
        return List.of(new CategoryDTO("1", "anTitle", "anDescription", "anOnwer"));
    }
}
