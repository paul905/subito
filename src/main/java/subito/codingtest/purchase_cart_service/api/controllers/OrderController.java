package subito.codingtest.purchase_cart_service.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import subito.codingtest.purchase_cart_service.api.dtos.CreateOrderRequest;
import subito.codingtest.purchase_cart_service.api.dtos.CreateOrderResponse;
import subito.codingtest.purchase_cart_service.order.services.OrderService;

@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(value = "/order", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreateOrderResponse> create(@RequestBody CreateOrderRequest orderRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(orderService.createOrder(orderRequest));
    }


}
