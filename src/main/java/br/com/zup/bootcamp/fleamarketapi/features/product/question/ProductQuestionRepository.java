package br.com.zup.bootcamp.fleamarketapi.features.product.question;

import br.com.zup.bootcamp.fleamarketapi.model.entity.ProductQuestion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductQuestionRepository extends CrudRepository<ProductQuestion, UUID> {
    List<ProductQuestion> findAll();
}
