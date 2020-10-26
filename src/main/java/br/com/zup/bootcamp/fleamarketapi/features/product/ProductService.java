package br.com.zup.bootcamp.fleamarketapi.features.product;

import br.com.zup.bootcamp.fleamarketapi.model.entity.Account;
import br.com.zup.bootcamp.fleamarketapi.features.account.AccountRepository;
import br.com.zup.bootcamp.fleamarketapi.model.entity.ProductCategory;
import br.com.zup.bootcamp.fleamarketapi.features.product.category.ProductCategoryRepository;
import br.com.zup.bootcamp.fleamarketapi.model.request.CreateProductRequest;
import br.com.zup.bootcamp.fleamarketapi.model.entity.Product;
import br.com.zup.bootcamp.fleamarketapi.model.response.ProductDetailsResponse;
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
    private final ProductCategoryRepository productCategoryRepository;

    @Transactional
    public Product create(final UUID accountId, final CreateProductRequest request) {
        final Account account = this.accountRepository.findById(accountId)
                .orElseThrow(() -> new EntityNotFoundException("message.product.account.not-found"));

        final ProductCategory productCategory = this.productCategoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("message.product.category.not-found"));

        return this.productRepository.save(request.toProduct(account, productCategory));
    }

    public ProductDetailsResponse findDetailsById(final UUID productId) {
        final Product product = this.productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("message.product.account.not-found"));

        return new ProductDetailsResponse(product);
    }

}
