package org.example;

public class BackstagePasses extends InventoryItem {
    public BackstagePasses(Item item) {
        super(item);
    }

    @Override
    void age() {
        increaseQuality();
        if (sellInIsLessThan(11)) {
            increaseQuality();
        }
        if (sellInIsLessThan(6)) {
            increaseQuality();
        }
        decreaseSellIn();
        if (hasExpired()) {
            dropQualityToZero();
        }
    }
}
