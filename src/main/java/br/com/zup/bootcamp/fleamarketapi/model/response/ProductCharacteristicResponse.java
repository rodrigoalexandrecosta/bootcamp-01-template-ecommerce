package br.com.zup.bootcamp.fleamarketapi.model.response;

import br.com.zup.bootcamp.fleamarketapi.model.entity.ProductCharacteristic;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ProductCharacteristicResponse {

    private String name;
    private String description;


    @Deprecated
    public ProductCharacteristicResponse() {
    }

    public ProductCharacteristicResponse(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public ProductCharacteristicResponse(ProductCharacteristic characteristic) {
        this.name = characteristic.getName();
        this.description = characteristic.getDescription();
    }

    public static List<ProductCharacteristicResponse> from(List<ProductCharacteristic> characteristic) {
        return characteristic.stream().map(ProductCharacteristicResponse::new).collect(Collectors.toList());
    }
}
