package subito.codingtest.purchase_cart_service.order.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import subito.codingtest.purchase_cart_service.order.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
