package org.example;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            age(new InventoryItem(item));
        }
    }

    private static void age(InventoryItem inventoryItem) {
        if (!inventoryItem.item().name.equals("Aged Brie")
                && !inventoryItem.item().name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            if (inventoryItem.item().quality > 0) {
                if (!inventoryItem.item().name.equals("Sulfuras, Hand of Ragnaros")) {
                    inventoryItem.item().quality = inventoryItem.item().quality - 1;
                }
            }
        } else {
            if (inventoryItem.item().quality < 50) {
                inventoryItem.item().quality = inventoryItem.item().quality + 1;

                if (inventoryItem.item().name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                    if (inventoryItem.item().sellIn < 11) {
                        if (inventoryItem.item().quality < 50) {
                            inventoryItem.item().quality = inventoryItem.item().quality + 1;
                        }
                    }

                    if (inventoryItem.item().sellIn < 6) {
                        if (inventoryItem.item().quality < 50) {
                            inventoryItem.item().quality = inventoryItem.item().quality + 1;
                        }
                    }
                }
            }
        }

        if (!inventoryItem.item().name.equals("Sulfuras, Hand of Ragnaros")) {
            inventoryItem.item().sellIn = inventoryItem.item().sellIn - 1;
        }

        if (inventoryItem.item().sellIn < 0) {
            if (!inventoryItem.item().name.equals("Aged Brie")) {
                if (!inventoryItem.item().name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                    if (inventoryItem.item().quality > 0) {
                        if (!inventoryItem.item().name.equals("Sulfuras, Hand of Ragnaros")) {
                            inventoryItem.item().quality = inventoryItem.item().quality - 1;
                        }
                    }
                } else {
                    inventoryItem.item().quality = 0;
                }
            } else {
                if (inventoryItem.item().quality < 50) {
                    inventoryItem.item().quality = inventoryItem.item().quality + 1;
                }
            }
        }
    }
}
