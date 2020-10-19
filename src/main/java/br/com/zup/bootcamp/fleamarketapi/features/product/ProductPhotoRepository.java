package br.com.zup.bootcamp.fleamarketapi.features.product;

import br.com.zup.bootcamp.fleamarketapi.model.ProductPhoto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductPhotoRepository extends CrudRepository<ProductPhoto, UUID> {

}
