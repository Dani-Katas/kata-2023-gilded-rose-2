package org.example;

public class BackstagePasses extends InventoryItem {
    public BackstagePasses(Item item) {
        super(item);
    }

    @Override
    void age() {
        increaseQuality();
        decreaseSellIn();
        if (sellInIsLessThan(10)) {
            increaseQuality();
        }
        if (sellInIsLessThan(5)) {
            increaseQuality();
        }
        if (hasExpired()) {
            dropQualityToZero();
        }
    }
}
