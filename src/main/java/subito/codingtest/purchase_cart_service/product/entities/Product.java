package subito.codingtest.purchase_cart_service.product.entities;

import jakarta.persistence.*;
import subito.codingtest.purchase_cart_service.order.entities.OrderDetails;

import java.math.BigDecimal;
import java.util.Set;

@Entity(name = "T_PRODUCT")
public class Product {

    public Product() {
    }

    public Product(BigDecimal currentPrice, BigDecimal currentVat) {
        this.currentPrice = currentPrice;
        this.currentVat = currentVat;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CURRENT_PRICE", nullable = false)
    private BigDecimal currentPrice;

    @Column(name = "CURRENT_VAT", nullable = false)
    private BigDecimal currentVat;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<OrderDetails> orderDetails;

    public Set<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(Set<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }

    public BigDecimal getCurrentVat() {
        return currentVat;
    }

    public void setCurrentVat(BigDecimal currentVat) {
        this.currentVat = currentVat;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", currentPrice=" + currentPrice +
                ", currentVat= " + currentVat +
                '}';
    }
}
