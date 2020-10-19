package br.com.zup.bootcamp.fleamarketapi.model.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CreateProductPhotoRequest {

    @NotNull(message = "message.product.photo-file.mandatory")
    private MultipartFile photoFile;


    @Deprecated
    public CreateProductPhotoRequest() {
    }

    public CreateProductPhotoRequest(MultipartFile photoFile) {
        this.photoFile = photoFile;
    }
}
