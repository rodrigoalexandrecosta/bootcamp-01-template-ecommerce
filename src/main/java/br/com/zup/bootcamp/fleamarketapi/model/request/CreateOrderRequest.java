package br.com.zup.bootcamp.fleamarketapi.model.request;

import br.com.zup.bootcamp.fleamarketapi.model.entity.Account;
import br.com.zup.bootcamp.fleamarketapi.model.entity.Order;
import br.com.zup.bootcamp.fleamarketapi.model.entity.Product;
import br.com.zup.bootcamp.fleamarketapi.model.enums.OrderStatus;
import br.com.zup.bootcamp.fleamarketapi.model.enums.PaymentGateway;
import br.com.zup.bootcamp.fleamarketapi.model.validation.annotation.EnumNamePattern;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class CreateOrderRequest {

    @NotNull(message = "message.order.quantity.mandatory")
    @Positive(message = "message.order.quantity.value")
    private Integer quantity;

    @NotNull(message = "message.order.payment-gateway.mandatory")
    @EnumNamePattern(regexp = "PAYPAL|PAGSEGURO", message = "message.order.payment-gateway.name-pattern")
    private PaymentGateway paymentGateway;

    @NotNull(message = "message.order.account-id.mandatory")
    private UUID accountId;

    @NotNull(message = "message.order.product-id.mandatory")
    private UUID productId;

    public Order toOrder(Account account, Product product) {
        final BigDecimal totalPrice = new BigDecimal(this.quantity.toString()).multiply(product.getPrice());

        return Order.builder()
                .quantity(this.quantity)
                .paymentGateway(this.paymentGateway)
                .totalPrice(totalPrice)
                .status(OrderStatus.NEW)
                .account(account)
                .product(product)
                .build();
    }
}
