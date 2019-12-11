package kata.supermarket;

import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
public class ItemByUnit implements Item {

    private final UnitProduct unitProduct;

    public BigDecimal price() {
        return unitProduct.pricePerUnit();
    }

    @Override
    public UUID productId() {
        return unitProduct.getId();
    }

    @Override
    public DiscountType discountType() {
        return unitProduct.getDiscountType();
    }
}
