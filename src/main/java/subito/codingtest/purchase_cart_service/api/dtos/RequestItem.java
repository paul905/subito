package subito.codingtest.purchase_cart_service.api.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public record RequestItem(@JsonProperty("product_id")
                          Long productId,
                          int quantity) {
}
