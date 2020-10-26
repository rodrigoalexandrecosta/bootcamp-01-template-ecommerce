package br.com.zup.bootcamp.fleamarketapi.features.product.opinion;

import br.com.zup.bootcamp.fleamarketapi.model.entity.ProductOpinion;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ProductOpinionRepository extends CrudRepository<ProductOpinion, UUID> {

}
