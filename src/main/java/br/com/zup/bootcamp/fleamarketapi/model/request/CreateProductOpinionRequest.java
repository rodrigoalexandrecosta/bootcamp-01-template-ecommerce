package br.com.zup.bootcamp.fleamarketapi.model.request;

import br.com.zup.bootcamp.fleamarketapi.model.entity.Account;
import br.com.zup.bootcamp.fleamarketapi.model.entity.Product;
import br.com.zup.bootcamp.fleamarketapi.model.entity.ProductOpinion;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CreateProductOpinionRequest {

    @NotNull(message = "message.product.opinion.stars.mandatory")
    @DecimalMin(value = "1", message = "message.product.opinion.starts.min-valeu")
    @DecimalMax(value = "5", message = "message.product.opinion.starts.max-valeu")
    private Integer stars;

    @NotBlank(message = "message.product.opinion.title.mandatory")
    private String title;

    @NotBlank(message = "message.product.opinion.description.mandatory")
    @Length(max = 500, message = "message.product.opinion.description.length")
    private String description;


    public ProductOpinion toProductOpinion(Account account, Product product) {
        return ProductOpinion.builder()
                .stars(this.stars)
                .title(this.title)
                .description(this.description)
                .account(account)
                .product(product)
                .build();
    }
}
