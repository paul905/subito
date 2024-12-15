package subito.codingtest.purchase_cart_service.order.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import subito.codingtest.purchase_cart_service.api.dtos.CreateOrderRequest;
import subito.codingtest.purchase_cart_service.api.dtos.CreateOrderResponse;
import subito.codingtest.purchase_cart_service.api.dtos.ResponseItem;
import subito.codingtest.purchase_cart_service.order.entities.Order;
import subito.codingtest.purchase_cart_service.order.entities.OrderDetails;
import subito.codingtest.purchase_cart_service.order.repositories.OrderRepository;
import subito.codingtest.purchase_cart_service.order.services.OrderService;
import subito.codingtest.purchase_cart_service.product.entities.Product;
import subito.codingtest.purchase_cart_service.product.exceptions.ProductNotFoundException;
import subito.codingtest.purchase_cart_service.product.repositories.ProductRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderServiceImpl(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public CreateOrderResponse createOrder(CreateOrderRequest orderRequest) {

        LocalDateTime now = LocalDateTime.now();
        Order order = new Order();
        order.setOrderDate(now);

        Set<OrderDetails> allOrderDetails = orderRequest.order().items().stream()
                .map(item -> {
                    Product product = productRepository.findById(item.productId())
                            .orElseThrow(() -> new ProductNotFoundException(item.productId()));

                    OrderDetails orderDetails = new OrderDetails();
                    orderDetails.setOrder(order);
                    orderDetails.setProduct(product);
                    orderDetails.setUnitPrice(product.getCurrentPrice());
                    orderDetails.setUnitVat(product.getCurrentVat());
                    orderDetails.setQuantity(item.quantity());

                    return orderDetails;
                }).collect(Collectors.toSet());

        order.setOrderDetails(allOrderDetails);
        Order savedOrder = orderRepository.save(order);
        log.info("Created order {}", savedOrder);

        return buildCreateOrderResponse(savedOrder);
    }

    private CreateOrderResponse buildCreateOrderResponse(Order savedOrder) {

        List<ResponseItem> responseItems = new ArrayList<>();

        BigDecimal totOrderPrice = BigDecimal.ZERO;
        BigDecimal totOrderVat = BigDecimal.ZERO;
        for (OrderDetails orderDetails : savedOrder.getOrderDetails()) {
            BigDecimal itemPrice = orderDetails.getUnitPrice().multiply(BigDecimal.valueOf(orderDetails.getQuantity()));
            BigDecimal itemVat = orderDetails.getUnitVat().multiply(BigDecimal.valueOf(orderDetails.getQuantity()));
            totOrderPrice = totOrderPrice.add(itemPrice);
            totOrderVat = totOrderVat.add(itemVat);

            responseItems.add(new ResponseItem.Builder()
                    .productId(orderDetails.getProduct().getId())
                    .quantity(orderDetails.getQuantity())
                    .price(itemPrice)
                    .vat(itemVat)
                    .build());

            responseItems.sort(Comparator.comparing(ResponseItem::productId));
        }

        return new CreateOrderResponse.Builder()
                .orderId(savedOrder.getId())
                .orderPrice(totOrderPrice)
                .orderVat(totOrderVat)
                .items(responseItems)
                .build();

    }


}


