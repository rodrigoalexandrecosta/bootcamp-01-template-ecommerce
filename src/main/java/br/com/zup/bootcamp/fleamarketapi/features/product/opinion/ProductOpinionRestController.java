package br.com.zup.bootcamp.fleamarketapi.features.product.opinion;

import br.com.zup.bootcamp.fleamarketapi.features.account.AccountRepository;
import br.com.zup.bootcamp.fleamarketapi.features.product.ProductRepository;
import br.com.zup.bootcamp.fleamarketapi.model.entity.Account;
import br.com.zup.bootcamp.fleamarketapi.model.entity.Product;
import br.com.zup.bootcamp.fleamarketapi.model.entity.ProductOpinion;
import br.com.zup.bootcamp.fleamarketapi.model.request.CreateProductOpinionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/accounts/{accountId}/products/{productId}/opinions", produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ProductOpinionRestController {

    private final ProductOpinionRepository productOpinionRepository;
    private final AccountRepository accountRepository;
    private final ProductRepository productRepository;

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> publish(@PathVariable UUID accountId, @PathVariable UUID productId,
                                        @RequestBody @Valid CreateProductOpinionRequest request) {

        final Account account = this.accountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("message.account.not-found"));

        final Product product = this.productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("message.product.not-found"));

        final ProductOpinion productOpinion = this.productOpinionRepository.save(request.toProductOpinion(account, product));
        return ResponseEntity.created(URI.create(String.format("/opinions/%s", productOpinion.getId()))).build();
    }
}
