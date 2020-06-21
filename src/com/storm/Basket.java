package com.storm;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class Basket {
    private final String name;
    private final Map<StockItem, Integer> list;

    public Basket(String name) {
        this.name = name;
        this.list = new TreeMap<>();
    }

    public int addToBasket(StockList list, String itemName, int quantity) {
        StockItem item = list.get(itemName);
        if ((item != null) && item.reserve(quantity) && (quantity > 0)) {
            int inStock = this.list.getOrDefault(item, 0);
            this.list.put(item, inStock + quantity);
            return inStock;
        }
        if (item == null) {
            System.out.println("Don't have "+ itemName);
        }
        return 0;
    }

    public int removeFromBasket(StockList list, String itemName, int quantity) {
        StockItem item = list.get(itemName);
        int inStock = this.list.getOrDefault(item, -1);
        if ((inStock != -1) && (item != null) && item.unreserve(quantity) && (quantity > 0)) {

                this.list.put(item, inStock - quantity);
                if (this.list.get(item) == 0) {
                    this.list.remove(item);
                }
                return inStock;

        }
        return -1;
    }

    public void checkOut(StockList list) {
        for (StockItem item :
                this.list.keySet()) {
            String name = item.getName();
            int quantity = this.list.get(item);
            list.sell(name, quantity);
        }
        this.list.clear();
    }

    public String getName() {
        return name;
    }

    public Map<StockItem, Integer> Items() {
        return Collections.unmodifiableMap(list);
    }

    @Override
    public String toString() {
        String s = "\nShopping basket " + this.name + " contains " + this.list.size() + " items.\n";
        double totalValue = 0.0;
        for (StockItem item :
                this.list.keySet()) {
            s = s + item + ". " + this.list.get(item) + " purchased\n";
            totalValue += item.getPrice() * this.list.get(item);
        }
        return s + "Total Value " + String.format("%.2f", totalValue);
    }
}
