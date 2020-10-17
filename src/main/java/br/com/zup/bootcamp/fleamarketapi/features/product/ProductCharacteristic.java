package br.com.zup.bootcamp.fleamarketapi.features.product;

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

    @NotBlank(message = "message.product.characteristic.value.mandatory")
    private String value;

    @ManyToOne
    private Product product;

    @Deprecated
    public ProductCharacteristic() {
    }

    public ProductCharacteristic(String name, String value, Product product) {
        this.name = name;
        this.value = value;
        this.product = product;
    }
}
