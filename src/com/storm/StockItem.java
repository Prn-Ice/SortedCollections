package com.storm;

import java.util.Objects;

public class StockItem implements Comparable<StockItem> {
    private final String name;
    private double price;
    private int quantity;
    private int reserved =0;
    private int availableStock;

    public StockItem(String name, double price) {
        this.name = name;
        this.price = price;
        this.quantity = 0;
        this.availableStock = 0;
    }

    public StockItem(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.availableStock = quantity;
    }

    public void setPrice(double price) {
        if (price > 0.0) {
            this.price = price;
        }
    }

    public boolean reserve(int quantity) {
        if (quantity > 0) {
            if (quantity > this.availableStock) {
                if (this.availableStock == 0) {
                    System.out.println(this.name + " is out of stock");
                } else System.out.println("We only have " + this.availableStock + " units of " + this.name + " left");
                return false;
            }
            this.reserved += quantity;
            this.availableStock -= quantity;
            return true;
        }
        return false;
    }

    public void checkOut() {
        this.reserved = 0;
        this.availableStock = this.quantity;
    }

    public boolean unreserve(int quantity) {
        if (quantity > 0) {
            if (quantity > this.reserved) {
                if (this.reserved == 0) {
                    System.out.println(this.name + " is not reserved");
                } else System.out.println("You only reserved " + this.reserved + " units of " + this.name);
                return false;
            }
            this.reserved -= quantity;
            this.availableStock += quantity;
            return true;
        }
        return false;
    }

    public void adjustQuantity(int quantity) {
        int newQuantity = this.quantity + quantity;
        if (newQuantity >= 0) {
            this.quantity = newQuantity;
        }
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if ((o == null) || (o.getClass() != this.getClass())) return false;
        StockItem stockItem = (StockItem) o;
        return Objects.equals(getName(), stockItem.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    @Override
    public int compareTo(StockItem o) {
//        System.out.println(".compareTo Called");
        if (o != null) {
            return this.getName().compareTo(o.getName());
        }
        throw new NullPointerException();
    }

    @Override
    public String toString() {
        return name + ", Price: " + String.format("%.2f", price) + ", Reserved: " + reserved;
    }
}
