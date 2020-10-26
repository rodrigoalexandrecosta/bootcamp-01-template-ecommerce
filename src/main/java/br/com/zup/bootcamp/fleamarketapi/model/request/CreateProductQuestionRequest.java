package br.com.zup.bootcamp.fleamarketapi.model.request;

import br.com.zup.bootcamp.fleamarketapi.model.entity.Account;
import br.com.zup.bootcamp.fleamarketapi.model.entity.Product;
import br.com.zup.bootcamp.fleamarketapi.model.entity.ProductQuestion;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.OffsetDateTime;

@Getter
@Setter
public class CreateProductQuestionRequest {

    @NotBlank(message = "message.product.question.mandatory")
    private String question;

    public ProductQuestion toProductQuestion(Account account, Product product) {
        return new ProductQuestion(this.question, OffsetDateTime.now(), account, product);
    }
}
