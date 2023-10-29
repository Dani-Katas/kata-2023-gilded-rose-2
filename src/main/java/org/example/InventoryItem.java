package org.example;

import java.util.Objects;

public class InventoryItem {
    public static final String AGED_BRIE_NAME = "Aged Brie";
    public static final String BACKSTAGE_PASSES_NAME = "Backstage passes to a TAFKAL80ETC concert";
    public static final String SULFURAS_NAME = "Sulfuras, Hand of Ragnaros";
    private final Item item;

    InventoryItem(Item item) {
        this.item = item;
    }

    public static InventoryItem create(Item item) {
        return switch (item.name) {
            case AGED_BRIE_NAME -> new AgedBrie(item);
            case BACKSTAGE_PASSES_NAME -> new BackstagePasses(item);
            case SULFURAS_NAME -> new Sulfuras(item);
            default -> new InventoryItem(item);
        };
    }

    void age() {
        decreaseQuality();
        decreaseSellIn();
        if (hasExpired()) {
            decreaseQuality();
        }
    }

    protected String getName() {
        return item.name;
    }

    protected boolean hasExpired() {
        return sellInIsLessThan(0);
    }

    protected boolean sellInIsLessThan(int amount) {
        return item.sellIn < amount;
    }

    protected void dropQualityToZero() {
        item.quality = 0;
    }

    protected void decreaseSellIn() {
        item.sellIn = item.sellIn - 1;
    }

    protected void decreaseQuality() {
        if (item.quality > 0) {
            item.quality = item.quality - 1;
        }
    }

    protected void increaseQuality() {
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