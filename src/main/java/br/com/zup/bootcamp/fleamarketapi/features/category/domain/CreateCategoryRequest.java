package br.com.zup.bootcamp.fleamarketapi.features.category.domain;

import br.com.zup.bootcamp.fleamarketapi.features.category.validation.UniqueCategory;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CreateCategoryRequest {

    @NotBlank(message = "message.category.name.mandatory")
    @Length(max = 255, message = "message.account.email.length")
    @UniqueCategory
    private String name;

    @Deprecated
    public CreateCategoryRequest() {
    }

    public CreateCategoryRequest(final String name) {
        this.name = name;
    }

    public Category toCategory() {
        return new Category(this.name);
    }
}
