package kata.supermarket;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;
import java.util.stream.Stream;

import static kata.supermarket.DiscountType.BUY_ONE_GET_ONE_FREE;
import static kata.supermarket.DiscountType.NO_DISCOUNT;
import static kata.supermarket.DiscountType.THREE_FOR_THE_PRICE_OF_TWO;
import static kata.supermarket.DiscountType.TWO_FOR_ONE_POUND;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BasketTest {

    @DisplayName("basket provides its total value when containing...")
    @MethodSource
    @ParameterizedTest(name = "{0}")
    void basketProvidesTotalValue(String description, String expectedTotal, Iterable<Item> items) {
        final Basket basket = new Basket();
        items.forEach(basket::add);
        assertEquals(new BigDecimal(expectedTotal), basket.total());
    }

    static Stream<Arguments> basketProvidesTotalValue() {
        return Stream.of(
                noItems(),
                aSingleItemPricedPerUnit(),
                multipleItemsPricedPerUnitWithNoDiscount(),
                multipleItemsPricedPerUnitWithBuyOneGetOneFree(),
                multipleItemsPricedPerUnitWithTwoForOnePound(),
                multipleItemsPricedPerUnitWithThreeForThePriceOfTwo(),
                multipleItemsPricedPerUnitWithMixedDiscounts(),
                aSingleItemPricedByWeight(),
                multipleItemsPricedByWeight()
        );
    }

    private static Arguments aSingleItemPricedByWeight() {
        return Arguments.of("a single weighed item", "1.25", Collections.singleton(twoFiftyGramsOfAmericanSweets()));
    }

    private static Arguments multipleItemsPricedByWeight() {
        return Arguments.of("multiple weighed items", "1.85",
                Arrays.asList(twoFiftyGramsOfAmericanSweets(), twoHundredGramsOfPickAndMix())
        );
    }

    private static Arguments multipleItemsPricedPerUnitWithNoDiscount() {
        return Arguments.of("multiple items with no discount priced per unit", "2.04",
                Arrays.asList(aPackOfDigestives(), aPintOfMilk()));
    }

    private static Arguments multipleItemsPricedPerUnitWithBuyOneGetOneFree() {
        final UUID id = UUID.randomUUID();
        return Arguments.of("buy 1 get 1 free items priced per unit", "3.87",
                Arrays.asList(aChocolateBar(id), aChocolateBar(id), aChocolateBar(id), aChocolateBar(id), aChocolateBar(id)));
    }

    private static Arguments multipleItemsPricedPerUnitWithTwoForOnePound() {
        final UUID id = UUID.randomUUID();
        return Arguments.of("buy 2 for 1 pound priced per unit", "2.69",
                Arrays.asList(aPackOfSugar(id), aPackOfSugar(id), aPackOfSugar(id), aPackOfSugar(id), aPackOfSugar(id)));
    }

    private static Arguments multipleItemsPricedPerUnitWithThreeForThePriceOfTwo() {
        final UUID id = UUID.randomUUID();
        return Arguments.of("3 for the price of 2 priced per unit", "6.00",
                Arrays.asList(aMango(id), aMango(id), aMango(id), aMango(id), aMango(id)));
    }

    private static Arguments multipleItemsPricedPerUnitWithMixedDiscounts() {
        final UUID chocolateId = UUID.randomUUID();
        final UUID sugarId = UUID.randomUUID();
        final UUID mangoId = UUID.randomUUID();
        final UUID cheeseId = UUID.randomUUID();
        return Arguments.of("multiple items with mixed discount priced per unit", "9.08",
                Arrays.asList(aChocolateBar(chocolateId),
                        aPackOfSugar(sugarId),
                        aPackOfSugar(sugarId),
                        aChocolateBar(chocolateId),
                        aChocolateBar(chocolateId),
                        aMango(mangoId),
                        aMango(mangoId),
                        aCheeseBag(cheeseId),
                        aCheeseBag(cheeseId),
                        aMango(mangoId)));
    }

    private static Arguments aSingleItemPricedPerUnit() {
        return Arguments.of("a single item priced per unit", "0.49", Collections.singleton(aPintOfMilk()));
    }

    private static Arguments noItems() {
        return Arguments.of("no items", "0.00", Collections.emptyList());
    }

    private static Item aPintOfMilk() {
        return new UnitProduct(UUID.randomUUID(), NO_DISCOUNT, new BigDecimal("0.49")).oneOf();
    }

    private static Item aPackOfDigestives() {
        return new UnitProduct(UUID.randomUUID(), NO_DISCOUNT, new BigDecimal("1.55")).oneOf();
    }

    private static Item aChocolateBar(final UUID id) {
        return new UnitProduct(id, BUY_ONE_GET_ONE_FREE, new BigDecimal("1.29")).oneOf();
    }

    private static Item aCheeseBag(final UUID id) {
        return new UnitProduct(id, BUY_ONE_GET_ONE_FREE, new BigDecimal("2.50")).oneOf();
    }

    private static Item aPackOfSugar(final UUID id) {
        return new UnitProduct(id, TWO_FOR_ONE_POUND, new BigDecimal("0.69")).oneOf();
    }

    private static Item aMango(final UUID id) {
        return new UnitProduct(id, THREE_FOR_THE_PRICE_OF_TWO, new BigDecimal("1.50")).oneOf();
    }

    private static WeighedProduct aKiloOfAmericanSweets() {
        return new WeighedProduct(UUID.randomUUID(), new BigDecimal("4.99"));
    }

    private static Item twoFiftyGramsOfAmericanSweets() {
        return aKiloOfAmericanSweets().weighing(new BigDecimal(".25"));
    }

    private static WeighedProduct aKiloOfPickAndMix() {
        return new WeighedProduct(UUID.randomUUID(), new BigDecimal("2.99"));
    }

    private static Item twoHundredGramsOfPickAndMix() {
        return aKiloOfPickAndMix().weighing(new BigDecimal(".2"));
    }
}