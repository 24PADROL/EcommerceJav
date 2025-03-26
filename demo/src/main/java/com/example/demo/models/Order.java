package com.example.demo.models;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Order {
    private Long id;
    private String orderID;
    private User user;
    private Map<Product, Integer> items;
    private String status;
    private LocalDateTime orderDate;
    private double totalAmount;
    private String shippingAddress;
    private String paymentMethod;

    public Order() {
        this.items = new HashMap<>();
        this.orderDate = LocalDateTime.now();
        this.status = "PENDING";
    }

    public Order(Long id, String orderID, User user, Map<Product, Integer> items) {
        this.id = id;
        this.orderID = orderID;
        this.user = user;
        this.items = new HashMap<>(items);
        this.orderDate = LocalDateTime.now();
        this.status = "PENDING";
        calculateTotalAmount();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
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
        calculateTotalAmount();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void processOrder() {
        this.status = "PROCESSING";
        System.out.println("Commande #" + orderID + " en cours de traitement...");
    }

    public void updateStatus(String newStatus) {
        this.status = newStatus;
        System.out.println("Statut de la commande #" + orderID + " mis à jour: " + newStatus);
    }

    private void calculateTotalAmount() {
        totalAmount = 0;
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            totalAmount += product.getPrice() * quantity;
        }
        
        if (user != null) {
            totalAmount = user.applyDiscount(totalAmount);
        }
    }

    public void displayOrderDetails() {
        System.out.println("=== DÉTAILS DE LA COMMANDE #" + orderID + " ===");
        System.out.println("Date: " + orderDate);
        System.out.println("Statut: " + status);
        System.out.println("Client: " + (user != null ? user.getUsername() : "Inconnu"));
        System.out.println("Adresse de livraison: " + shippingAddress);
        System.out.println("Méthode de paiement: " + paymentMethod);
        System.out.println("Articles:");
        
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            double lineTotal = product.getPrice() * quantity;
            System.out.println(" - " + product.getProductName() + " x" + quantity + 
                              " = " + lineTotal + " €");
        }
        
        System.out.println("Montant total: " + totalAmount + " €");
        System.out.println("======================================");
    }
}