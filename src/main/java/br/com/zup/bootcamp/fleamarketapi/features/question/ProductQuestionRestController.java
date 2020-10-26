package br.com.zup.bootcamp.fleamarketapi.features.question;

import br.com.zup.bootcamp.fleamarketapi.features.account.AccountRepository;
import br.com.zup.bootcamp.fleamarketapi.features.product.ProductRepository;
import br.com.zup.bootcamp.fleamarketapi.model.entity.Account;
import br.com.zup.bootcamp.fleamarketapi.model.entity.Product;
import br.com.zup.bootcamp.fleamarketapi.model.request.CreateProductQuestionRequest;
import br.com.zup.bootcamp.fleamarketapi.model.response.ProductQuestionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/accounts/{accountId}/products/{productId}/questions", produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ProductQuestionRestController {

    private final ProductQuestionRepository productQuestionRepository;
    private final AccountRepository accountRepository;
    private final ProductRepository productRepository;

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProductQuestionResponse>> ask(@PathVariable UUID accountId, @PathVariable UUID productId,
                                                             @RequestBody @Valid CreateProductQuestionRequest request) {

        final Account account = this.accountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("message.account.not-found"));

        final Product product = this.productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("message.product.not-found"));

        this.productQuestionRepository.save(request.toProductQuestion(account, product));
        final List<ProductQuestionResponse> questions = ProductQuestionResponse.from(this.productQuestionRepository.findAll());
        return ResponseEntity.ok(questions);
    }
}
