package subito.codingtest.purchase_cart_service.unittests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import subito.codingtest.purchase_cart_service.api.dtos.CreateOrderRequest;
import subito.codingtest.purchase_cart_service.api.dtos.CreateOrderResponse;
import subito.codingtest.purchase_cart_service.api.dtos.RequestItem;
import subito.codingtest.purchase_cart_service.order.entities.Order;
import subito.codingtest.purchase_cart_service.order.entities.OrderDetails;
import subito.codingtest.purchase_cart_service.order.repositories.OrderRepository;
import subito.codingtest.purchase_cart_service.order.services.impl.OrderServiceImpl;
import subito.codingtest.purchase_cart_service.product.entities.Product;
import subito.codingtest.purchase_cart_service.product.exceptions.ProductNotFoundException;
import subito.codingtest.purchase_cart_service.product.repositories.ProductRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    private CreateOrderRequest createOrderRequest;

    @BeforeEach
    public void init() {
        List<RequestItem> requestItems = List.of(
                new RequestItem(1L, 1),
                new RequestItem(2L, 5),
                new RequestItem(3L, 1)
        );
        createOrderRequest = new CreateOrderRequest(new CreateOrderRequest.RequestOrder(requestItems));
    }

    @Test
    public void successfulOrderCreation() {

        // GIVEN
        Product p1 = new Product();
        p1.setId(1L);
        p1.setCurrentPrice(BigDecimal.valueOf(2.00));
        p1.setCurrentVat(BigDecimal.valueOf(0.20));
        given(productRepository.findById(1L)).willReturn(Optional.of(p1));

        Product p2 = new Product();
        p2.setId(2L);
        p2.setCurrentPrice(BigDecimal.valueOf(1.50));
        p2.setCurrentVat(BigDecimal.valueOf(0.15));
        given(productRepository.findById(2L)).willReturn(Optional.of(p2));

        Product p3 = new Product();
        p3.setId(3L);
        p3.setCurrentPrice(BigDecimal.valueOf(3.00));
        p3.setCurrentVat(BigDecimal.valueOf(0.30));
        given(productRepository.findById(3L)).willReturn(Optional.of(p3));

        Order savedOrder = new Order();
        savedOrder.setId(1L);

        Set<OrderDetails> allOrderDetails = new HashSet<>();
        OrderDetails orderDetails1 = new OrderDetails();
        orderDetails1.setOrder(savedOrder);
        orderDetails1.setProduct(p1);
        orderDetails1.setUnitPrice(p1.getCurrentPrice());
        orderDetails1.setUnitVat(p1.getCurrentVat());
        orderDetails1.setQuantity(1);
        allOrderDetails.add(orderDetails1);

        OrderDetails orderDetails2 = new OrderDetails();
        orderDetails2.setOrder(savedOrder);
        orderDetails2.setProduct(p2);
        orderDetails2.setUnitPrice(p2.getCurrentPrice());
        orderDetails2.setUnitVat(p2.getCurrentVat());
        orderDetails2.setQuantity(5);
        allOrderDetails.add(orderDetails2);

        OrderDetails orderDetails3 = new OrderDetails();
        orderDetails3.setOrder(savedOrder);
        orderDetails3.setProduct(p3);
        orderDetails3.setUnitPrice(p3.getCurrentPrice());
        orderDetails3.setUnitVat(p3.getCurrentVat());
        orderDetails3.setQuantity(1);
        allOrderDetails.add(orderDetails3);

        savedOrder.setOrderDate(LocalDateTime.now());
        savedOrder.setOrderDetails(allOrderDetails);
        given(orderRepository.save(any(Order.class))).willReturn(savedOrder);

        // WHEN
        CreateOrderResponse response = orderService.createOrder(createOrderRequest);

        // THEN
        assertThat(response).isNotNull();

        BigDecimal totPrice = allOrderDetails.stream()
                .map(orderDetails -> orderDetails.getUnitPrice().multiply(BigDecimal.valueOf(orderDetails.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        assertEquals(response.orderPrice(), totPrice);

        BigDecimal totVat = allOrderDetails.stream()
                .map(orderDetails -> orderDetails.getUnitVat().multiply(BigDecimal.valueOf(orderDetails.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        assertEquals(response.orderVat(), totVat);

    }

    @Test
    public void orderCreationProductNotFound() {

        List<RequestItem> requestItems = List.of(
                new RequestItem(1L, 1)
        );
        createOrderRequest = new CreateOrderRequest(new CreateOrderRequest.RequestOrder(requestItems));

        given(productRepository.findById(1L)).willReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class,
                () -> orderService.createOrder(createOrderRequest)
        );
        verify(productRepository).findById(1L);

    }
}