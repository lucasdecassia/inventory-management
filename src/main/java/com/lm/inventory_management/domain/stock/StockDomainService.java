package com.lm.inventory_management.domain.stock;

import com.lm.inventory_management.domain.model.Product;

public class StockDomainService {

    public Product addProduct(String name, int qty, double price){
        return new Product(name, qty, price);
    }

    public int getStockQuantity(Product product) {
            if (product == null) {
                throw  new IllegalArgumentException("Product connot be null");
            }
            return product.getQuantity();
    }

    public Product sellProduct(Product product, int qtyToSell){
        if(product == null ){
            throw new IllegalArgumentException("Product not be null");
        }
        if(qtyToSell < 0){
            throw new IllegalArgumentException("Quantity to sell cannot be negative");
        }
        if(product.getQuantity() < qtyToSell){
            throw new IllegalArgumentException("Not enough stock for " + product.getName());
        }
        product.setQuantity(product.getQuantity() - qtyToSell);
        return product;
    }
}
