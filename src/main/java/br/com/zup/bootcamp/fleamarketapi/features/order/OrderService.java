package br.com.zup.bootcamp.fleamarketapi.features.order;

import br.com.zup.bootcamp.fleamarketapi.features.account.AccountRepository;
import br.com.zup.bootcamp.fleamarketapi.features.product.ProductRepository;
import br.com.zup.bootcamp.fleamarketapi.model.entity.Account;
import br.com.zup.bootcamp.fleamarketapi.model.entity.Order;
import br.com.zup.bootcamp.fleamarketapi.model.entity.Product;
import br.com.zup.bootcamp.fleamarketapi.model.request.CreateOrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final AccountRepository accountRepository;
    private final ProductRepository productRepository;

    @Transactional
    public Order create(final CreateOrderRequest request) {

        final Account account = this.accountRepository.findById(request.getAccountId())
                .orElseThrow(() -> new IllegalArgumentException("message.account.not-found"));

        final Product product = this.productRepository.findById(request.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("message.product.not-found"));

        this.validateProductQuantity(request.getQuantity(), product);

        final Order order = this.orderRepository.save(request.toOrder(account, product));
        return order;
    }

    private void validateProductQuantity(Integer quantity, Product product) {
        if (quantity > product.getQuantity()) {
            throw new IllegalArgumentException("message.order.product.quantity.invalid");
        }
        product.setQuantity(product.getQuantity() - quantity);
        this.productRepository.save(product);
    }
}
