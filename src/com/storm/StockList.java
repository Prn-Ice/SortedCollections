package com.storm;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class StockList {
    private final Map<String, StockItem> list;

    public StockList() {
        this.list = new LinkedHashMap<>();
    }

    public int add(StockItem item) {
        if (item != null) {
            StockItem inStock = this.list.getOrDefault(item.getName(), item);
            if (inStock != item) {
                item.adjustQuantity(inStock.getQuantity());
            }
            this.list.put(item.getName(), item);
            return item.getQuantity();
        }
        return 0;
    }

    public int sell(String item, int quantity) {
        StockItem inStock = this.list.getOrDefault(item, null);
        if ((inStock != null) && (inStock.getQuantity() >= quantity) && (quantity > 0)) {
            inStock.adjustQuantity(-quantity);
            inStock.checkOut();
            return quantity;
        }
        return 0;
    }

//    This method may be unnecessary
    /*public int sellItem(Basket basket, String name, int quantity) {
        StockItem item = this.list.get(name);
        if ((item == null) || (item.getQuantity() < quantity)) {
            if (item == null) {
                System.out.println("We don't sell " + name);
            } else if ((item.getQuantity() < quantity)) {
                if (item.getQuantity() == 0) {
                    System.out.println(name + " is out of stock");
                } else System.out.println("We only have " + item.getQuantity() + " units of " + name + " left");
            }
            return 0;
        }
        if (sell(name, quantity) != 0) {
            basket.addToBasket(item, quantity);
            return quantity;
        }
        System.out.println("Here 2");
        return 0;
    }*/

    public StockItem get(String key) {
        return Items().get(key);
    }

    // This method is supposed to fix that issue but it doesn't
    private Map<String, StockItem> ItemList() {
        Map<String , StockItem> copy = new LinkedHashMap<>();
        for (Map.Entry<String, StockItem> item :
                this.list.entrySet()) {
            copy.put(item.getKey(), item.getValue());
        }
        return Collections.unmodifiableMap(copy);
    }

    // Other classes can make changes to our stock using this method
    private Map<String, StockItem> Items() {
        return Collections.unmodifiableMap(this.list);
    }


    @Override
    public String toString() {
        String s = "\nStock List\n";
        double totalValue = 0;
        for (StockItem item :
                this.list.values()) {
            double value = item.getPrice() * item.getQuantity();
            s = s + item + ". There are " + item.getQuantity() + " in stock. Value of items ";
            s = s + String.format("%.2f",value) + "\n";
            totalValue += value;
        }
        return s + "Total stock value " + String.format("%.2f", totalValue);
    }
}
