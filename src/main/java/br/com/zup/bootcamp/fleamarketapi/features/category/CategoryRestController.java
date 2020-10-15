package br.com.zup.bootcamp.fleamarketapi.features.category;

import br.com.zup.bootcamp.fleamarketapi.features.category.domain.Category;
import br.com.zup.bootcamp.fleamarketapi.features.category.domain.CreateCategoryRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import java.net.URI;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/categories", produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class CategoryRestController {

    private final CategoryRepository categoryRepository;

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@RequestBody @Valid CreateCategoryRequest request) {
        Category category = this.categoryRepository.save(request.toCategory());
        return ResponseEntity.created(URI.create(String.format("/categories/%s", category.getId()))).build();
    }
}
