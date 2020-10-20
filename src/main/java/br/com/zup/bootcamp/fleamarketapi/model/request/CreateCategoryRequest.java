package br.com.zup.bootcamp.fleamarketapi.model.request;

import br.com.zup.bootcamp.fleamarketapi.model.validation.annotation.ExistingParentCategory;
import br.com.zup.bootcamp.fleamarketapi.model.validation.annotation.UniqueCategory;
import br.com.zup.bootcamp.fleamarketapi.model.entity.Category;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Getter
@Setter
public class CreateCategoryRequest {

    @NotBlank(message = "message.category.name.mandatory")
    @Length(max = 255, message = "message.account.email.length")
    @UniqueCategory
    private String name;

    @ExistingParentCategory
    private UUID parentId;

    @Deprecated
    public CreateCategoryRequest() {
    }

    public CreateCategoryRequest(final String name, final UUID parentId) {
        this.name = name;
        this.parentId = parentId;
    }

    public Category toCategory() {
        return new Category(this.name);
    }

    public Category toCategory(Category parent) {
        return new Category(this.name, parent);
    }
}
