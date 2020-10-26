package br.com.zup.bootcamp.fleamarketapi.model.request;

import br.com.zup.bootcamp.fleamarketapi.model.entity.ProductCategory;
import br.com.zup.bootcamp.fleamarketapi.model.validation.annotation.ExistingParentCategory;
import br.com.zup.bootcamp.fleamarketapi.model.validation.annotation.UniqueCategory;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Getter
@Setter
public class CreateProductCategoryRequest {

    @NotBlank(message = "message.product.category.name.mandatory")
    @Length(max = 255, message = "message.product.category.name.length")
    @UniqueCategory
    private String name;

    @ExistingParentCategory
    private UUID parentId;

    @Deprecated
    public CreateProductCategoryRequest() {
    }

    public CreateProductCategoryRequest(final String name, final UUID parentId) {
        this.name = name;
        this.parentId = parentId;
    }

    public ProductCategory toProductCategory() {
        return new ProductCategory(this.name);
    }

    public ProductCategory toProductCategory(ProductCategory parent) {
        return new ProductCategory(this.name, parent);
    }
}
