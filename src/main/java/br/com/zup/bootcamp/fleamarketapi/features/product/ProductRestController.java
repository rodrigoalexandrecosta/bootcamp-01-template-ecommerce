package br.com.zup.bootcamp.fleamarketapi.features.product;

import br.com.zup.bootcamp.fleamarketapi.features.account.Account;
import br.com.zup.bootcamp.fleamarketapi.features.account.AccountRepository;
import br.com.zup.bootcamp.fleamarketapi.features.category.Category;
import br.com.zup.bootcamp.fleamarketapi.features.category.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import java.net.URI;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/accounts/{accountId}/products", produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ProductRestController {

    private final ProductRepository productRepository;
    private final CharacteristicRepository characteristicRepository;
    private final AccountRepository accountRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@PathVariable UUID accountId,
                                       @RequestBody @Valid CreateProductRequest request) {

        final Account account = this.accountRepository.findById(accountId)
                .orElseThrow(() -> new EntityNotFoundException("message.product.account.not-found"));

        final Category category = this.categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("message.product.category.not-found"));

        final Product product = this.productRepository.save(request.toProduct(account, category));

        this.characteristicRepository.saveAll(
                request.getCharacteristics()
                        .stream()
                        .map(charRequest -> charRequest.toCharacteristic(product))
                        .collect(Collectors.toList())
        );

        return ResponseEntity.created(URI.create(String.format("/categories/%s", product.getId()))).build();
    }
}
