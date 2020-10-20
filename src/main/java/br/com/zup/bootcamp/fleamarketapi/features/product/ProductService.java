package br.com.zup.bootcamp.fleamarketapi.features.product;

import br.com.zup.bootcamp.fleamarketapi.model.entity.Account;
import br.com.zup.bootcamp.fleamarketapi.features.account.AccountRepository;
import br.com.zup.bootcamp.fleamarketapi.model.entity.Category;
import br.com.zup.bootcamp.fleamarketapi.features.category.CategoryRepository;
import br.com.zup.bootcamp.fleamarketapi.model.request.CreateProductRequest;
import br.com.zup.bootcamp.fleamarketapi.model.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final AccountRepository accountRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public Product create(final UUID accountId, final CreateProductRequest request) {
        final Account account = this.accountRepository.findById(accountId)
                .orElseThrow(() -> new EntityNotFoundException("message.product.account.not-found"));

        final Category category = this.categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("message.product.category.not-found"));

        return this.productRepository.save(request.toProduct(account, category));
    }
}
