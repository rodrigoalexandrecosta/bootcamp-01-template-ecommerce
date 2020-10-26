package br.com.zup.bootcamp.fleamarketapi.features.photo;

import br.com.zup.bootcamp.fleamarketapi.model.entity.ProductPhoto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductPhotoRepository extends CrudRepository<ProductPhoto, UUID> {

}
