package com.example.demo.repositories;

import com.example.demo.models.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class ProductRepository {
    private Map<Long, Product> products = new HashMap<>();
    private long nextId = 1;

    public ProductRepository() {
        // Ajouter quelques produits pour tester
        Product product1 = new Product(nextId++, "Smartphone X", "PROD-001", 699.99, 100);
        product1.setDescription("Latest smartphone with advanced features");
        product1.setCategory("Electronics");
        
        Product product2 = new Product(nextId++, "Laptop Pro", "PROD-002", 1299.99, 50);
        product2.setDescription("High-performance laptop for professionals");
        product2.setCategory("Electronics");
        
        Product product3 = new Product(nextId++, "Wireless Headphones", "PROD-003", 159.99, 200);
        product3.setDescription("Premium wireless headphones with noise cancellation");
        product3.setCategory("Audio");
        
        products.put(product1.getId(), product1);
        products.put(product2.getId(), product2);
        products.put(product3.getId(), product3);
    }

    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(products.get(id));
    }

    public Optional<Product> findByProductID(String productID) {
        return products.values().stream()
                .filter(product -> product.getProductID().equals(productID))
                .findFirst();
    }

    public List<Product> findByCategory(String category) {
        return products.values().stream()
                .filter(product -> category.equals(product.getCategory()))
                .toList();
    }

    public Product save(Product product) {
        if (product.getId() == null) {
            product.setId(nextId++);
        }
        products.put(product.getId(), product);
        return product;
    }

    public void delete(Product product) {
        products.remove(product.getId());
    }

    public void deleteById(Long id) {
        products.remove(id);
    }
}