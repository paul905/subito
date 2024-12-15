package subito.codingtest.purchase_cart_service.order.entities;

import jakarta.persistence.*;
import subito.codingtest.purchase_cart_service.product.entities.Product;

import java.math.BigDecimal;

@Entity(name = "T_ORDER_DETAIL")
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    @Column(name = "UNIT_PRICE", nullable = false)
    private BigDecimal unitPrice;

    @Column(name = "UNIT_VAT", nullable = false)
    private BigDecimal unitVat;

    @Column(name = "QUANTITY", nullable = false)
    private Integer quantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getUnitVat() {
        return unitVat;
    }

    public void setUnitVat(BigDecimal unitVat) {
        this.unitVat = unitVat;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "id=" + id +
                ", product=" + product +
                ", order=" + order +
                ", unitPrice=" + unitPrice +
                ", unitVat=" + unitVat +
                ", quantity=" + quantity +
                '}';
    }
}
