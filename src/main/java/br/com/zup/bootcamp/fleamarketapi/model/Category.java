package br.com.zup.bootcamp.fleamarketapi.model;

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
public class Category {

    @Id
    @GeneratedValue(generator = "uuid")
    private UUID id;

    @NotBlank(message = "message.category.name.mandatory")
    @Length(max = 255, message = "message.account.email.length")
    private String name;

    @ManyToOne
    private Category parent;

    @Deprecated
    public Category() {
    }

    public Category(final String name) {
        this.name = name;
    }

    public Category(final String name, final Category parent) {
        this.name = name;
        this.parent = parent;
    }
}
