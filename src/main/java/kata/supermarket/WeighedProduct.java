package kata.supermarket;

import java.math.BigDecimal;
import java.util.UUID;

public class WeighedProduct extends Product {

    private final BigDecimal pricePerKilo;

    public WeighedProduct(final UUID id, final BigDecimal pricePerKilo) {
        super(id, DiscountType.NO_DISCOUNT);
        this.pricePerKilo = pricePerKilo;
    }

    public BigDecimal pricePerKilo() {
        return pricePerKilo;
    }

    public Item weighing(final BigDecimal kilos) {
        return new ItemByWeight(this, kilos);
    }
}
