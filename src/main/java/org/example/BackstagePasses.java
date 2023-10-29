package org.example;

public class BackstagePasses extends InventoryItem {
    public BackstagePasses(Item item) {
        super(item);
    }

    @Override
    void age() {
        switch (getName()) {
            case AGED_BRIE_NAME:
                increaseQuality();
                decreaseSellIn();
                if (hasExpired()) {
                    increaseQuality();
                }
                break;
            case BACKSTAGE_PASSES_NAME:
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
                break;
            case SULFURAS_NAME:
                break;
            default:
                decreaseQuality();
                decreaseSellIn();
                if (hasExpired()) {
                    decreaseQuality();
                }
                break;
        }
    }
}
