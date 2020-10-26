package br.com.zup.bootcamp.fleamarketapi.features.product.photo;

import br.com.zup.bootcamp.fleamarketapi.model.entity.ProductPhoto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
@RequestMapping(value = "/api/v1/accounts/{accountId}/products/{productId}/photos", produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ProductPhotoRestController {

    private final ProductPhotoService productPhotoService;

    @PostMapping(consumes = MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadPhoto(@PathVariable UUID productId,
                                            @PathVariable UUID accountId,
                                            @RequestParam(value = "photo") MultipartFile photo) {

        final ProductPhoto productPhoto = this.productPhotoService.create(productId, accountId, photo);
        return ResponseEntity.created(URI.create(String.format("/photos/%s", productPhoto.getId()))).build();
    }
}
