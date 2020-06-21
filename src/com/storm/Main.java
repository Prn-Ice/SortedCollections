package com.storm;

public class Main {
    private static StockList stockList = new StockList();

    public static void main(String[] args) {
        StockItem temp = new StockItem("Bread", 0.86, 100);
        stockList.add(temp);

        temp = new StockItem("Cake", 1.10, 7);
        stockList.add(temp);

        temp = new StockItem("Car", 12.50, 2);
        stockList.add(temp);

        temp = new StockItem("Chair", 62.0, 10);
        stockList.add(temp);

        temp = new StockItem("Cup", 0.45, 200);
        stockList.add(temp);

        temp = new StockItem("Door", 72.95, 4);
        stockList.add(temp);

        temp = new StockItem("Juice", 2.50, 36);
        stockList.add(temp);

        temp = new StockItem("Phone", 96.99, 35);
        stockList.add(temp);

        temp = new StockItem("Towel", 2.40, 80);
        stockList.add(temp);

        temp = new StockItem("Vase", 8.76, 40);
        stockList.add(temp);
        System.out.println(stockList);

        Basket princesBasket = new Basket("Prince's basket");
        princesBasket.addToBasket(stockList, "Car", 1);
        System.out.println(princesBasket);

        princesBasket.addToBasket(stockList, "Car", 1);
        System.out.println(princesBasket);

        princesBasket.addToBasket(stockList, "Car", 1);

        princesBasket.addToBasket(stockList, "Spanner", 5);

        princesBasket.addToBasket(stockList, "Juice", 4);
        princesBasket.addToBasket(stockList, "Cup", 12);
        princesBasket.addToBasket(stockList, "Bread", 1);
//        System.out.println(princesBasket);

        Basket basket = new Basket("Customer");
        basket.addToBasket(stockList, "Cup", 100);
        basket.addToBasket(stockList, "Juice", 5);
        basket.removeFromBasket(stockList, "Cup", 1);
        System.out.println(basket);

        princesBasket.removeFromBasket(stockList, "Car", 1);
        princesBasket.removeFromBasket(stockList, "Cup", 9);
        princesBasket.removeFromBasket(stockList, "Car", 1);
        princesBasket.removeFromBasket(stockList, "Car", 1);// should not work

        System.out.println(princesBasket);

        // remove all items from princesBasket
        princesBasket.removeFromBasket(stockList, "Bread", 1);
        princesBasket.removeFromBasket(stockList, "Cup", 3);
        princesBasket.removeFromBasket(stockList, "Juice", 4);
        System.out.println(princesBasket.removeFromBasket(stockList, "Cup", 3));
        System.out.println(princesBasket);

        System.out.println("\nDisplay stock list before and after checkout");
        System.out.println(basket);
        System.out.println(stockList);
        basket.checkOut(stockList);
        System.out.println(basket);
        System.out.println(stockList);

        /*princesBasket.addToBasket(stockList, "Phone", 1);
        princesBasket.addToBasket(stockList, "Juice", 3);
        princesBasket.addToBasket(stockList, "GP", 10);
        System.out.println(princesBasket.removeFromBasket(stockList, "GP", 10));
        System.out.println(princesBasket);*/

        /*System.out.println(stockList);
        princesBasket.checkOut(stockList);
        System.out.println(stockList);
        System.out.println(princesBasket);*/

        princesBasket.checkOut(stockList);
        System.out.println(princesBasket);
    }

}
