package subito.codingtest.purchase_cart_service.api.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public record ResponseItem(@JsonProperty("product_id")
                           Long productId,
                           Integer quantity,
                           BigDecimal price,
                           BigDecimal vat) {

    public static final class Builder {
        Long productId;
        Integer quantity;
        BigDecimal price;
        BigDecimal vat;

        public Builder() {
        }

        public Builder productId(Long productId) {
            this.productId = productId;
            return this;
        }

        public Builder quantity(Integer quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public Builder vat(BigDecimal vat) {
            this.vat = vat;
            return this;
        }

        public ResponseItem build() {
            List<String> missing = new ArrayList<>();
            if (this.productId == null) {
                missing.add("productId");
            }
            if (this.quantity == null) {
                missing.add("quantity");
            }
            if (this.price == null) {
                missing.add("price");
            }
            if (this.vat == null) {
                missing.add("vat");
            }
            if (!missing.isEmpty()) {
                throw new IllegalArgumentException("Missing required properties: "
                        + String.join(",", missing));
            }

            return new ResponseItem(this.productId, this.quantity, this.price, this.vat);

        }


    }


}
