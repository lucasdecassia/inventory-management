package com.lm.inventory_management.core;

import java.util.ArrayList;
import java.util.List;

public class StockManager {
    private List<Product> products = new ArrayList<>();

    public void addProduct(String name, int qty, double price) {
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Error: Product name cannot be empty");
            return;
        }
        if (qty < 0) {
            System.out.println("Error: Quantity cannot be negative");
            return;
        }
        if (price < 0) {
            System.out.println("Error: Price cannot be negative");
            return;
        }
        Product p = new Product();
        p.setName(name);
        p.setQuantity(qty);
        p.setPrice(price);
        products.add(p);
        System.out.println("Product added: " + name);
    }

    public void checkStock(String productName) {
        for (Product p : products) {
            if (p.getName().equals(productName)) {
                System.out.println("Stock for " + productName + ": " + p.getQuantity());
                return;
            }
        }
        System.out.println("Product " + productName + " not found");
    }

    public void sellProduct(String productName, int qtyToSell) {
        for (Product p : products) {
            if (p.getName().equals(productName)) {
                if (p.getQuantity() >= qtyToSell) {
                    p.setQuantity(p.getQuantity() - qtyToSell);
                    System.out.println("Sold " + qtyToSell + " units of " + productName);
                    return;
                } else {
                    System.out.println("Not enough stock for " + productName);
                    return;
                }
            }
        }
        System.out.println("Product " + productName + " not found");
    }

    public void addProductAgain(String name, int qty, double price) {
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Error: Product name cannot be empty");
            return;
        }
        if (qty < 0) {
            System.out.println("Error: Quantity cannot be negative");
            return;
        }
        if (price < 0) {
            System.out.println("Error: Price cannot be negative");
            return;
        }
        Product p = new Product();
        p.setName(name);
        p.setQuantity(qty);
        p.setPrice(price);
        products.add(p);
        System.out.println("Product added again: " + name);
    }
}

class Product {
    private String name;
    private int quantity;
    private double price;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}

class Main {
    public static void main(String[] args) {
        StockManager stock = new StockManager();
        stock.addProduct("Laptop", 10, 999.99);
        stock.addProductAgain("Mouse", 50, 29.99);
        stock.checkStock("Laptop");
        stock.sellProduct("Laptop", 3);
        stock.checkStock("Laptop");
        stock.sellProduct("Keyboard", 1); // Should fail
    }
}