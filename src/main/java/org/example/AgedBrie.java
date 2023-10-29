package org.example;

public class AgedBrie extends InventoryItem {
    public AgedBrie(Item item) {
        super(item);
    }

    @Override
    void age() {
        increaseQuality();
        decreaseSellIn();
        if (hasExpired()) {
            increaseQuality();
        }
    }
}
