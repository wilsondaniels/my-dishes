package dev.wilsondaniels.mydishes.infrasctructure.category.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CategoryRepository extends MongoRepository<CategoryDataEntity, String> {

    @Query("{ 'ownerId': ?0 }")
    List<CategoryDataEntity> findByOwnerId(String ownerId);
}
