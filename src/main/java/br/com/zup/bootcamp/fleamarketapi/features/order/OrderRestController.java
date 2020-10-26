package br.com.zup.bootcamp.fleamarketapi.features.order;

import br.com.zup.bootcamp.fleamarketapi.model.entity.Order;
import br.com.zup.bootcamp.fleamarketapi.model.request.CreateOrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/orders", produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class OrderRestController {

    private final OrderService orderService;

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> create(@RequestBody @Valid CreateOrderRequest request) {
        final Order order = this.orderService.create(request);
        return ResponseEntity.ok(order);
    }
}
