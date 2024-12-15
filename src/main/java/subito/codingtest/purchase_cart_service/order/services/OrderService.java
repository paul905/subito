package subito.codingtest.purchase_cart_service.order.services;

import subito.codingtest.purchase_cart_service.api.dtos.CreateOrderRequest;
import subito.codingtest.purchase_cart_service.api.dtos.CreateOrderResponse;

public interface OrderService {
    CreateOrderResponse createOrder(CreateOrderRequest orderRequest);
}
