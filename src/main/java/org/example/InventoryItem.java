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
            ageTemporal(name);
        } else if (name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            ageTemporal(name);
        } else if (name.equals("Sulfuras, Hand of Ragnaros")) {
            ageTemporal(name);
        } else {
            ageTemporal(name);
        }
    }

    private void ageTemporal(String name) {
        if (!name.equals("Aged Brie")
                && !name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            if (item.quality > 0) {
                if (!name.equals("Sulfuras, Hand of Ragnaros")) {
                    item.quality = item.quality - 1;
                }
            }
        } else {
            if (item.quality < 50) {
                item.quality = item.quality + 1;

                if (name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                    if (item.sellIn < 11) {
                        if (item.quality < 50) {
                            item.quality = item.quality + 1;
                        }
                    }

                    if (item.sellIn < 6) {
                        if (item.quality < 50) {
                            item.quality = item.quality + 1;
                        }
                    }
                }
            }
        }

        if (!name.equals("Sulfuras, Hand of Ragnaros")) {
            item.sellIn = item.sellIn - 1;
        }

        if (item.sellIn < 0) {
            if (!name.equals("Aged Brie")) {
                if (!name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                    if (item.quality > 0) {
                        if (!name.equals("Sulfuras, Hand of Ragnaros")) {
                            item.quality = item.quality - 1;
                        }
                    }
                } else {
                    item.quality = 0;
                }
            } else {
                if (item.quality < 50) {
                    item.quality = item.quality + 1;
                }
            }
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