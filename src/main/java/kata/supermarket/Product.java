package kata.supermarket;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public abstract class Product {
    private final UUID id;
    private final DiscountType discountType;
}
