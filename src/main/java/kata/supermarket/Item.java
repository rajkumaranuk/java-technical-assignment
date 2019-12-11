package kata.supermarket;

import java.math.BigDecimal;
import java.util.UUID;

public interface Item {
    BigDecimal price();

    UUID productId();

    DiscountType discountType();
}
