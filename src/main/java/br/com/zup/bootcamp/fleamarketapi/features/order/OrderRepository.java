package br.com.zup.bootcamp.fleamarketapi.features.order;

import br.com.zup.bootcamp.fleamarketapi.model.entity.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderRepository extends CrudRepository<Order, UUID> {

}
