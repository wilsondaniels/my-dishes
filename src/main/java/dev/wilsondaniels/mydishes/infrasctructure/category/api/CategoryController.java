package dev.wilsondaniels.mydishes.infrasctructure.category.api;

import dev.wilsondaniels.mydishes.application.category.CategoryDTO;
import dev.wilsondaniels.mydishes.application.category.CategoryUseCase;
import dev.wilsondaniels.mydishes.infrasctructure.category.CategoryAPI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import static dev.wilsondaniels.mydishes.infrasctructure.util.Util.isUUIDValid;

import java.net.URI;
import java.util.List;

@RestController
public class CategoryController implements CategoryAPI {

    private final CategoryUseCase useCase;

    public CategoryController(CategoryUseCase useCase) {
        this.useCase = useCase;
    }

    @Override
    public ResponseEntity<?> create(String ownerId, CategoryDTO input) {
        final CategoryDTO categoryCreated = useCase.create(input);
        return ResponseEntity.created(URI.create("/categories/" + categoryCreated.id())).body(categoryCreated);
    }

    @Override
    public ResponseEntity<?> getById(String ownerId, String id) {
        if (!isUUIDValid(id)) {
            return ResponseEntity.notFound().build();
        }

        final var categoryDTO = useCase.retrieveById(id);
        return new ResponseEntity<>(categoryDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateById(String ownerId, String id, CategoryDTO input) {
        if (!isUUIDValid(id)) {
            return ResponseEntity.notFound().build();
        }

        final CategoryDTO categoryUpdated = useCase.update(input);
        return ResponseEntity.created(URI.create("/categories/" + categoryUpdated.id())).body(categoryUpdated);
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
    public List<CategoryDTO> findAll(String ownerId) {
        return useCase.retrieveAll(ownerId);
    }
}
