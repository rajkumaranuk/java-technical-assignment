package kata.supermarket;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public enum DiscountType {
    NO_DISCOUNT((basePrice, noOfItems) -> BigDecimal.ZERO),
    BUY_ONE_GET_ONE_FREE((basePrice, noOfItems) -> basePrice.multiply(new BigDecimal(noOfItems / 2))),
    TWO_FOR_ONE_POUND((basePrice, noOfItems) -> basePrice.multiply(new BigDecimal(2))
            .subtract(new BigDecimal(1))
            .multiply(new BigDecimal(noOfItems / 2))),
    THREE_FOR_THE_PRICE_OF_TWO((basePrice, noOfItems) -> basePrice.multiply(new BigDecimal(noOfItems / 3)));

    private final DiscountStrategy discountStrategy;
}
