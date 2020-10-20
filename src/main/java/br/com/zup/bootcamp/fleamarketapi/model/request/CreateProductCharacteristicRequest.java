package br.com.zup.bootcamp.fleamarketapi.model.request;

import br.com.zup.bootcamp.fleamarketapi.model.entity.Product;
import br.com.zup.bootcamp.fleamarketapi.model.entity.ProductCharacteristic;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CreateProductCharacteristicRequest {

    @NotBlank(message = "message.product.characteristic.name.mandatory")
    private String name;

    @NotBlank(message = "message.product.characteristic.value.mandatory")
    private String description;


    @Deprecated
    public CreateProductCharacteristicRequest() {
    }

    public CreateProductCharacteristicRequest(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public ProductCharacteristic toCharacteristic(Product product) {
        return new ProductCharacteristic(this.name, this.description, product);
    }
}
