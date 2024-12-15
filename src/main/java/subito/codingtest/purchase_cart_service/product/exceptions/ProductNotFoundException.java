package subito.codingtest.purchase_cart_service.product.exceptions;

public class ProductNotFoundException extends RuntimeException {
    private final Long productId;

    public ProductNotFoundException(Long aLong) {
        super(String.format("Product id %s not found", aLong));
        this.productId = aLong;
    }

    public Long getProductId() {
        return productId;
    }
}
