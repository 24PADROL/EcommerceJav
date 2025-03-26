package com.example.demo.models;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
        this.calculateTotalAmount();
    }

    // Getters et Setters
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
        this.calculateTotalAmount();
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

    // Méthodes métier
    public void placeOrder() {
        this.status = "PROCESSING";
        // Mise à jour du stock se fera dans le service
    }

    public void updateStatus(String newStatus) {
        this.status = newStatus;
    }

    private void calculateTotalAmount() {
        this.totalAmount = items.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
        
        // Appliquer réduction si l'utilisateur est éligible
        if (user != null) {
            this.totalAmount = user.applyDiscount(this.totalAmount);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(orderID, order.orderID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderID);
    }
}