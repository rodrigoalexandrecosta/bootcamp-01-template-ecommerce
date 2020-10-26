package br.com.zup.bootcamp.fleamarketapi.features.product.category;

import br.com.zup.bootcamp.fleamarketapi.model.entity.ProductCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductCategoryRepository extends CrudRepository<ProductCategory, UUID> {

    Optional<ProductCategory> findByName(String name);
}
