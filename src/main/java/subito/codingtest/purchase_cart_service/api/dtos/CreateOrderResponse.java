package subito.codingtest.purchase_cart_service.api.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public record CreateOrderResponse(
        @JsonProperty("order_id")
        Long orderId,
        @JsonProperty("order_price")
        BigDecimal orderPrice,
        @JsonProperty("order_vat")
        BigDecimal orderVat,
        List<ResponseItem> items
) {
    public static final class Builder {
        Long orderId;
        BigDecimal orderPrice;
        BigDecimal orderVat;
        List<ResponseItem> items;

        public Builder() {
        }

        public Builder orderId(Long orderId) {
            this.orderId = orderId;
            return this;
        }

        public Builder orderPrice(BigDecimal orderPrice) {
            this.orderPrice = orderPrice;
            return this;
        }

        public Builder orderVat(BigDecimal orderVat) {
            this.orderVat = orderVat;
            return this;
        }

        public Builder items(List<ResponseItem> items) {
            this.items = List.copyOf(items);
            return this;
        }

        public CreateOrderResponse build() {
            List<String> missing = new ArrayList<>();
            if (this.orderId == null) {
                missing.add("orderId");
            }
            if (this.orderPrice == null) {
                missing.add("orderPrice");
            }
            if (this.orderVat == null) {
                missing.add("orderVat");
            }
            if (this.items == null) {
                missing.add("items");
            }
            if (!missing.isEmpty()) {
                throw new IllegalArgumentException("Missing required properties: "
                        + String.join(",", missing));
            }

            return new CreateOrderResponse(this.orderId, this.orderPrice, this.orderVat, this.items);

        }


    }


}
