package org.example;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {
    @Test
    void works_without_items() {
        Item[] items = {};
        GildedRose gildedRose = new GildedRose(items);

        assertDoesNotThrow(gildedRose::updateQuality);
    }

    @Test
    void decreases_quality_by_one() {
        int quality = 10;
        int sellIn = 10;
        Item[] items = {createNormal(sellIn, quality)};
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        Item item = items[0];
        assertEquals(quality - 1, item.quality);
    }

    @Test
    void decreases_sell_in_by_one() {
        int quality = 10;
        int sellIn = 10;
        Item[] items = {createNormal(sellIn, quality)};
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        Item item = items[0];
        assertEquals(sellIn - 1, item.sellIn);
    }

    @Test
    void decreases_quality_at_double_rate_after_sell_in_day_passes() {
        int quality = 10;
        int sellIn = 0;
        Item[] items = {createNormal(sellIn, quality)};
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        Item item = items[0];

        assertEquals(quality - 2, item.quality);
    }

    @Test
    void quality_cant_be_negative() {
        int quality = 0;
        int sellIn = 0;
        Item[] items = {createNormal(sellIn, quality)};
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        Item item = items[0];
        assertEquals(quality, item.quality);
    }

    @Test
    void updates_the_quality_of_multiple_elements() {
        int quality = 10;
        int sellIn = 10;
        Item[] items = {createNormal(sellIn, quality), createNormal(sellIn, quality)};
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        Item item1 = items[0];
        Item item2 = items[1];
        assertEquals(quality - 1, item1.quality);
        assertEquals(quality - 1, item2.quality);
    }

    @Nested
    class AgedBrie {

        @Test
        void increases_quality() {
            int quality = 10;
            int sellIn = 10;
            Item[] items = {createBrie(sellIn, quality)};
            GildedRose gildedRose = new GildedRose(items);

            gildedRose.updateQuality();

            Item item = items[0];
            assertEquals(quality + 1, item.quality);
        }

        @Test
        void increases_quality_by_double_rate_after_expired() {
            int quality = 10;
            int sellIn = 0;
            Item[] items = {createBrie(sellIn, quality)};
            GildedRose gildedRose = new GildedRose(items);

            gildedRose.updateQuality();

            Item item = items[0];
            assertEquals(quality + 2, item.quality);
        }

        @Test
        void quality_does_not_exceed_maximum() {
            int quality = 50;
            int sellIn = 0;
            Item[] items = {createBrie(sellIn, quality)};
            GildedRose gildedRose = new GildedRose(items);

            gildedRose.updateQuality();

            Item item = items[0];
            assertEquals(50, item.quality);
        }

        @Test
        void quality_cant_be_bigger_than_maximum() {
            int quality = 50;
            int sellIn = 10;
            Item[] items = {createBrie(sellIn, quality)};
            GildedRose gildedRose = new GildedRose(items);

            gildedRose.updateQuality();

            Item item = items[0];
            assertEquals(quality, item.quality);
        }

    }

    @Nested
    class Sulfuras {

        @Test
        void wont_change_sell_in_nor_quality() {
            int quality = 10;
            int sellIn = 10;
            Item[] items = {createSulfuras(sellIn, quality)};
            GildedRose gildedRose = new GildedRose(items);

            gildedRose.updateQuality();

            Item item = items[0];
            assertEquals(sellIn, item.sellIn);
            assertEquals(quality, item.quality);
        }

        @Test
        void does_not_age() {
            int quality = 10;
            int sellIn = -1;
            Item[] items = {createSulfuras(sellIn, quality)};
            GildedRose gildedRose = new GildedRose(items);

            gildedRose.updateQuality();

            Item item = items[0];
            assertEquals(sellIn, item.sellIn);
            assertEquals(quality, item.quality);
        }

    }

    @Nested
    class BackstagePasses {

        @Test
        void increases_quality() {
            int quality = 10;
            int sellIn = 20;
            Item[] items = {createBackstagePass(sellIn, quality)};
            GildedRose gildedRose = new GildedRose(items);

            gildedRose.updateQuality();

            Item item = items[0];

            assertEquals(quality + 1, item.quality);
        }

        @Test
        void increases_at_double_rate_with_ten_days_sell_in() {
            int quality = 10;
            int sellIn = 10;
            Item[] items = {createBackstagePass(sellIn, quality)};
            GildedRose gildedRose = new GildedRose(items);

            gildedRose.updateQuality();

            Item item = items[0];

            assertEquals(quality + 2, item.quality);
        }

        @Test
        void increases_at_triple_rate_with_five_days_sell_in() {
            int quality = 10;
            int sellIn = 5;
            Item[] items = {createBackstagePass(sellIn, quality)};
            GildedRose gildedRose = new GildedRose(items);

            gildedRose.updateQuality();

            Item item = items[0];

            assertEquals(quality + 3, item.quality);
        }

        @Test
        void drops_quality_when_expired() {
            int quality = 10;
            int sellIn = 0;
            Item[] items = {createBackstagePass(sellIn, quality)};
            GildedRose gildedRose = new GildedRose(items);

            gildedRose.updateQuality();

            Item item = items[0];

            assertEquals(0, item.quality);
        }

        @Test
        void wont_change_quality_after_sell_in() {
            int quality = 0;
            int sellIn = -4;
            Item[] items = {createBackstagePass(sellIn, quality)};
            GildedRose gildedRose = new GildedRose(items);

            gildedRose.updateQuality();

            Item item = items[0];

            assertEquals(0, item.quality);
        }

        @Test
        void increases_quality_in_day_eleven() {
            int quality = 10;
            int sellIn = 11;
            Item[] items = {createBackstagePass(sellIn, quality)};
            GildedRose gildedRose = new GildedRose(items);

            gildedRose.updateQuality();

            Item item = items[0];

            assertEquals(quality + 1, item.quality);
        }

        @Test
        void increases_quality_in_day_six() {
            int quality = 10;
            int sellIn = 6;
            Item[] items = {createBackstagePass(sellIn, quality)};
            GildedRose gildedRose = new GildedRose(items);

            gildedRose.updateQuality();

            Item item = items[0];

            assertEquals(quality + 2, item.quality);
        }

        @Test
        void quality_cant_go_over_maximum() {
            int quality = 50;
            int sellIn = 6;
            Item[] items = {createBackstagePass(sellIn, quality)};
            GildedRose gildedRose = new GildedRose(items);

            gildedRose.updateQuality();

            Item item = items[0];

            assertEquals(quality, item.quality);
        }
    }

    private static Item createNormal(int sellIn, int quality) {
        return new Item("SomeItem", sellIn, quality);
    }

    private Item createBrie(int sellIn, int quality) {
        return new Item("Aged Brie", sellIn, quality);
    }

    private Item createSulfuras(int sellIn, int quality) {
        return new Item("Sulfuras, Hand of Ragnaros", sellIn, quality);
    }

    private Item createBackstagePass(int sellIn, int quality) {
        return new Item("Backstage passes to a TAFKAL80ETC concert", sellIn, quality);
    }
}