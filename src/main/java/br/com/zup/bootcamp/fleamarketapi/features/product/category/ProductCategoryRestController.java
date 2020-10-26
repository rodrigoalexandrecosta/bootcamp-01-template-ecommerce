package br.com.zup.bootcamp.fleamarketapi.features.product.category;

import br.com.zup.bootcamp.fleamarketapi.model.entity.ProductCategory;
import br.com.zup.bootcamp.fleamarketapi.model.request.CreateProductCategoryRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import java.net.URI;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/categories", produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ProductCategoryRestController {

    private final ProductCategoryRepository productCategoryRepository;

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@RequestBody @Valid CreateProductCategoryRequest request) {
        if (request.getParentId() == null) {
            ProductCategory productCategory = this.productCategoryRepository.save(request.toProductCategory());
            return ResponseEntity.created(URI.create(String.format("/categories/%s", productCategory.getId()))).build();
        }

        ProductCategory parent = this.productCategoryRepository.findById(request.getParentId())
                .orElseThrow(() -> new EntityNotFoundException("message.category.parent.not-found"));

        ProductCategory productCategory = this.productCategoryRepository.save(request.toProductCategory(parent));
        return ResponseEntity.created(URI.create(String.format("/categories/%s", productCategory.getId()))).build();
    }
}
