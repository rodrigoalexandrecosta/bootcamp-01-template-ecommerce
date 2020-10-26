package br.com.zup.bootcamp.fleamarketapi.model.response;

import br.com.zup.bootcamp.fleamarketapi.model.entity.ProductPhoto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
public class ProductPhotoResponse {

    private String photoUrl;

    
    @Deprecated
    public ProductPhotoResponse() {
    }

    public ProductPhotoResponse(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public static List<ProductPhotoResponse> from(List<ProductPhoto> productPhotos) {
        return productPhotos.stream()
                .map(photo -> new ProductPhotoResponse(photo.getPhotoUrl()))
                .collect(Collectors.toList());
    }
}
