package br.com.zup.bootcamp.fleamarketapi.model.entity;

import br.com.zup.bootcamp.fleamarketapi.model.enums.OrderStatus;
import br.com.zup.bootcamp.fleamarketapi.model.enums.PaymentGateway;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(generator = "uuid")
    private UUID id;

    private Integer quantity;

    private BigDecimal totalPrice;

    private PaymentGateway paymentGateway;

    private OrderStatus status;

    @ManyToOne
    private Account account;

    @ManyToOne
    private Product product;

    @Deprecated
    public Order() {
    }
}
