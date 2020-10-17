package br.com.zup.bootcamp.fleamarketapi.features.product;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CreateCharacteristicRequest {

    @NotBlank(message = "message.product.characteristic.name.mandatory")
    private String name;

    @NotBlank(message = "message.product.characteristic.value.mandatory")
    private String value;


    @Deprecated
    public CreateCharacteristicRequest() {
    }

    public CreateCharacteristicRequest(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public Characteristic toCharacteristic(Product product) {
        return new Characteristic(this.name, this.value, product);
    }
}
