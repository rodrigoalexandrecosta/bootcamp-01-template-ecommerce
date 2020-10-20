package br.com.zup.bootcamp.fleamarketapi.features.product;

import br.com.zup.bootcamp.fleamarketapi.model.entity.ProductCharacteristic;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductCharacteristicRepository extends CrudRepository<ProductCharacteristic, UUID> {

}
