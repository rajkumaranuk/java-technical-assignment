package kata.supermarket;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public enum DiscountType {
    NO_DISCOUNT(1, ((basePrice, noOfItems) -> BigDecimal.ZERO));

    private final int id;
    private final DiscountStrategy discountStrategy;
}
