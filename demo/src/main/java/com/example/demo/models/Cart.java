package com.example.demo.models;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Long id;
    private User user;
    private Map<Product, Integer> items;

    public Cart() {
        this.items = new HashMap<>();
    }

    public Cart(Long id, User user) {
        this.id = id;
        this.user = user;
        this.items = new HashMap<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Map<Product, Integer> getItems() {
        return items;
    }

    public void setItems(Map<Product, Integer> items) {
        this.items = items;
    }

    public void addProduct(Product product, int quantity) {
        if (items.containsKey(product)) {
            int currentQty = items.get(product);
            items.put(product, currentQty + quantity);
        } else {
            items.put(product, quantity);
        }
    }

    public void removeProduct(Product product) {
        items.remove(product);
    }

    public void updateProductQuantity(Product product, int quantity) {
        if (quantity <= 0) {
            removeProduct(product);
        } else {
            items.put(product, quantity);
        }
    }

    public double calculateTotal() {
        double total = 0.0;
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            total += product.getPrice() * quantity;
        }
        return total;
    }

    public void clear() {
        items.clear();
    }
    
    public void displayCart() {
        System.out.println("Contenu du panier #" + id + ":");
        if (items.isEmpty()) {
            System.out.println("Le panier est vide");
        } else {
            for (Map.Entry<Product, Integer> entry : items.entrySet()) {
                Product product = entry.getKey();
                int quantity = entry.getValue();
                System.out.println(" - " + product.getProductName() + " x" + quantity + 
                                   " = " + (product.getPrice() * quantity) + " €");
            }
            System.out.println("Total: " + calculateTotal() + " €");
        }
    }
}