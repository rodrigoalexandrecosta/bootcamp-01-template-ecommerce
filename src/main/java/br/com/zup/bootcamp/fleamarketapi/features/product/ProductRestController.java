package br.com.zup.bootcamp.fleamarketapi.features.product;

import br.com.zup.bootcamp.fleamarketapi.model.entity.ProductPhoto;
import br.com.zup.bootcamp.fleamarketapi.model.request.CreateProductRequest;
import br.com.zup.bootcamp.fleamarketapi.model.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

import java.net.URI;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
@RequestMapping(value = "/api/v1/accounts/{accountId}/products", produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ProductRestController {

    private final ProductService productService;
    private final ProductCharacteristicRepository productCharacteristicRepository;
    private final ProductPhotoService productPhotoService;

    @Transactional
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@PathVariable UUID accountId,
                                       @RequestBody @Valid CreateProductRequest request) {

        final Product product = this.productService.create(accountId, request);

        this.productCharacteristicRepository.saveAll(
                request.getCharacteristics()
                        .stream()
                        .map(charRequest -> charRequest.toCharacteristic(product))
                        .collect(Collectors.toList())
        );

        return ResponseEntity.created(URI.create(String.format("/products/%s", product.getId()))).build();
    }

    @PostMapping(value = "/{productId}/photos", consumes = MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadPhoto(@PathVariable UUID productId,
                                            @PathVariable UUID accountId,
                                            @RequestParam(value = "photo") MultipartFile photo) {

        final ProductPhoto productPhoto = this.productPhotoService.create(productId, accountId, photo);
        return ResponseEntity.created(URI.create(String.format("/photos/%s", productPhoto.getId()))).build();
    }
}
