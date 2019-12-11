package kata.supermarket;

import java.math.BigDecimal;
import java.util.UUID;

public class UnitProduct extends Product {

    private final BigDecimal pricePerUnit;

    public UnitProduct(final UUID id, final DiscountType discountType, final BigDecimal pricePerUnit) {
        super(id, discountType);
        this.pricePerUnit = pricePerUnit;
    }

    public BigDecimal pricePerUnit() {
        return pricePerUnit;
    }

    public Item oneOf() {
        return new ItemByUnit(this);
    }
}
