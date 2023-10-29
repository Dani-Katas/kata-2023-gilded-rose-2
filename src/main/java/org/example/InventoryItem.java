package org.example;

import java.util.Objects;

public final class InventoryItem {
    private final Item item;

    public InventoryItem(Item item) {
        this.item = item;
    }

    void age() {
        final String name = item.name;
        if (name.equals("Aged Brie")) {
            increaseQuality();
            decreaseSellIn();
            if (item.sellIn < 0) {
                increaseQuality();
            }
        } else {
            if (name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                increaseQuality();

                if (item.sellIn < 11) {
                    increaseQuality();
                }

                if (item.sellIn < 6) {
                    increaseQuality();
                }
                decreaseSellIn();
                if (item.sellIn < 0) {
                    dropQualityToZero();
                }
            } else {
                if (name.equals("Sulfuras, Hand of Ragnaros")) {
                    if (item.sellIn < 0) {
                    }
                } else {
                    decreaseQuality();
                    decreaseSellIn();
                    if (item.sellIn < 0) {
                        decreaseQuality();
                    }
                }
            }
        }
    }

    private void dropQualityToZero() {
        item.quality = 0;
    }

    private void decreaseSellIn() {
        item.sellIn = item.sellIn - 1;
    }

    private void decreaseQuality() {
        if (item.quality > 0) {
            item.quality = item.quality - 1;
        }
    }

    private void increaseQuality() {
        if (item.quality < 50) {
            item.quality = item.quality + 1;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (InventoryItem) obj;
        return Objects.equals(this.item, that.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(item);
    }

    @Override
    public String toString() {
        return "InventoryItem[" +
                "item=" + item + ']';
    }

}