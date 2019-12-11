package kata.supermarket;

import java.math.BigDecimal;

public interface DiscountStrategy {
    BigDecimal discountAmount(BigDecimal basePrice, int noOfItems);
}
