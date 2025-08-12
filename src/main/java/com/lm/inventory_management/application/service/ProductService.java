package com.lm.inventory_management.application.service;

import com.lm.inventory_management.application.repository.ProductRepository;
import com.lm.inventory_management.domain.model.Product;
import com.lm.inventory_management.domain.stock.StockDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final StockDomainService stockService;

    @Transactional
    public Product addProduct(String name, int quantity, double price) {
        Product product = stockService.addProduct(name, quantity, price);
        return productRepository.save(product);
    }

    @Transactional(readOnly = true)
    public Integer checkStock(String productName){
        if (productName == null || productName.isEmpty()) {
            throw new IllegalArgumentException("product name connot be emppty");
        }

        Optional<Product> productOptional = productRepository.findByName(productName);
        return productOptional.map(stockService::getStockQuantity)
                .orElseThrow(() -> new RuntimeException("product: " + productName + " not found"));
    }

    @Transactional
    public void sellProduct(String productName, int qtyToSell){
        if (productName == null || productName.isEmpty()) {
            throw new IllegalArgumentException("product name connot be emppty");
        }
        if(qtyToSell <= 0){
            throw new IllegalArgumentException("Quantity to sell cannot be negative");
        }

        Optional<Product> productOptional = productRepository.findByName(productName);
        Product product = productOptional.orElseThrow(() -> new RuntimeException("product: " + productName + " not found"));
        Product productUpdated = stockService.sellProduct(product, qtyToSell);
        productRepository.save(productUpdated);
    }
}
