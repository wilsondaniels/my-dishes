package dev.wilsondaniels.mydishes.infrasctructure.category;

import dev.wilsondaniels.mydishes.application.category.CategoryDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/{ownerid}/categories")
public interface CategoryAPI {

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<?> create(@PathVariable(name = "ownerid") String ownerId, @RequestBody CategoryDTO input);

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
    ResponseEntity<?> updateById(@PathVariable(name = "ownerid") String ownerId, @PathVariable(name = "id") String id, @RequestBody CategoryDTO input);

    @DeleteMapping(
            value = "{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<?> deleteById(@PathVariable(name = "ownerid") String ownerId, @PathVariable(name = "id") String id);

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE)
    List<CategoryDTO> findAll(@PathVariable(name = "ownerid") String ownerId);


}
