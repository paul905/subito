package subito.codingtest.purchase_cart_service.product.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import subito.codingtest.purchase_cart_service.product.entities.Product;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
