package subito.codingtest.purchase_cart_service.api.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public record RequestItem(@JsonProperty("product_id")
                          Long productId,
                          int quantity) {
    public RequestItem {
        Objects.requireNonNull(productId, "productId cannot be null");
        if (quantity <= 0)
            throw new IllegalArgumentException("quantity cannot be negative or zero");
    }
}
