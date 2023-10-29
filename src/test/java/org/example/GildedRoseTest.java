package org.example;

import org.approvaltests.combinations.CombinationApprovals;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class GildedRoseTest {
    @Test
    void approval() {
        CombinationApprovals.verifyAllCombinations(
                this::updateItem,
                new String[]{
                        "Any Item",
                        "Aged Brie",
                        "Backstage passes to a TAFKAL80ETC concert",
                        "Sulfuras, Hand of Ragnaros"},
                range(-1, 12),
                range(-1, 51)
        );
        final Item item = updateItem("anyItem", 1, 1);

        assertThat(item.quality).isEqualTo(0);
        assertThat(item.sellIn).isEqualTo(0);
    }

    private Item updateItem(String name, int sellIn, int quality) {
        final Item item = new Item(name, sellIn, quality);
        Item[] items = new Item[]{item};
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        return item;
    }

    private Integer[] range(int min, int max) {
        return IntStream.range(min, max).boxed().toArray(Integer[]::new);
    }
}
