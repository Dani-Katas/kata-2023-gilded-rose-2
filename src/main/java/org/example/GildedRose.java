package org.example;

import java.util.Arrays;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        Arrays.stream(items).map(InventoryItem::create).forEach(InventoryItem::age);
    }

}
