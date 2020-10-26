package br.com.zup.bootcamp.fleamarketapi.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Entity
@Getter
@Setter
public class ProductCategory {

    @Id
    @GeneratedValue(generator = "uuid")
    private UUID id;

    @NotBlank(message = "message.product.category.name.mandatory")
    @Length(max = 255, message = "message.product.category.length")
    private String name;

    @ManyToOne
    private ProductCategory parent;

    @Deprecated
    public ProductCategory() {
    }

    public ProductCategory(final String name) {
        this.name = name;
    }

    public ProductCategory(final String name, final ProductCategory parent) {
        this.name = name;
        this.parent = parent;
    }
}
