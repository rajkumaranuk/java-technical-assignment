package kata.supermarket;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static kata.supermarket.DiscountType.NO_DISCOUNT;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductTest {

    @Test
    void singleItemHasExpectedUnitPriceFromProduct() {
        final BigDecimal price = new BigDecimal("2.49");
        assertEquals(price, new UnitProduct(UUID.randomUUID(), NO_DISCOUNT, price).oneOf().price());
    }
}