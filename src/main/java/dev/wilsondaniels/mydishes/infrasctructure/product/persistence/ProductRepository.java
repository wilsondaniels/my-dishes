package dev.wilsondaniels.mydishes.infrasctructure.product.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ProductRepository extends MongoRepository<ProductDataEntity, String> {

    @Query("{ 'ownerId': ?0 }")
    List<ProductDataEntity> findByOwnerId(String ownerId);
}
