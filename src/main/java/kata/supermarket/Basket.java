package kata.supermarket;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class Basket {
    private final List<Item> items;

    public Basket() {
        this.items = new ArrayList<>();
    }

    public void add(final Item item) {
        this.items.add(item);
    }

    List<Item> items() {
        return Collections.unmodifiableList(items);
    }

    Map<UUID, List<Item>> groupedItemsMap() {
        return items.stream()
                .collect(Collectors.groupingBy(
                        Item::productId,
                        Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList)));
    }

    public BigDecimal total() {
        return new TotalCalculator().calculate();
    }

    private class TotalCalculator {
        private final List<Item> items;

        TotalCalculator() {
            this.items = items();
        }

        private BigDecimal subtotal() {
            return items.stream().map(Item::price)
                    .reduce(BigDecimal::add)
                    .orElse(BigDecimal.ZERO)
                    .setScale(2, RoundingMode.HALF_UP);
        }

        private BigDecimal discounts() {
            return groupedItemsMap().values()
                    .stream()
                    .map(groupedItems -> {
                        final DiscountType discountType = groupedItems.get(0).discountType();
                        final BigDecimal basePrice = groupedItems.get(0).price();
                        return discountType.getDiscountStrategy().discountAmount(basePrice, groupedItems.size());
                    })
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        }

        private BigDecimal calculate() {
            return subtotal().subtract(discounts());
        }
    }
}
