package subito.codingtest.purchase_cart_service.api.dtos;

import java.util.List;
import java.util.Objects;

public record CreateOrderRequest(RequestOrder order) {

    public CreateOrderRequest {
        Objects.requireNonNull(order, "order data is missing");
    }

    public record RequestOrder(List<RequestItem> items) {
        public RequestOrder {
            Objects.requireNonNull(items, "order items must not be null");
            if (items.isEmpty())
                throw new IllegalArgumentException("an oder must contain at least one item");

            items.forEach(item -> Objects.requireNonNull(item, "item must not be null"));
        }
    }

}

