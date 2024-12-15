package subito.codingtest.purchase_cart_service.order.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import subito.codingtest.purchase_cart_service.order.entities.OrderDetails;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {
}
