package br.com.zup.bootcamp.fleamarketapi.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Entity
@Getter
@Setter
public class ProductCharacteristic {

    @Id
    @GeneratedValue(generator = "uuid")
    private UUID id;

    @NotBlank(message = "message.product.characteristic.name.mandatory")
    private String name;

    @NotBlank(message = "message.product.characteristic.description.mandatory")
    private String description;

    @ManyToOne
    private Product product;

    @Deprecated
    public ProductCharacteristic() {
    }

    public ProductCharacteristic(String name, String description, Product product) {
        this.name = name;
        this.description = description;
        this.product = product;
    }
}
