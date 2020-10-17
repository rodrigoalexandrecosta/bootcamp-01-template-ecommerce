package br.com.zup.bootcamp.fleamarketapi.features.product;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CharacteristicRepository extends CrudRepository<Characteristic, UUID> {

}
