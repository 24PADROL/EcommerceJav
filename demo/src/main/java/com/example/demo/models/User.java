package com.example.demo.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {
    private Long id;
    private String username;
    private String email;
    private String password;
    private List<Order> orderHistory;
    private String userType; // pour le polymorphisme (REGULAR, ADMIN, PREMIUM)

    public User() {
        this.orderHistory = new ArrayList<>();
    }

    public User(Long id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.orderHistory = new ArrayList<>();
        this.userType = "REGULAR";
    }

    // Getter et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Order> getOrderHistory() {
        return orderHistory;
    }

    public void setOrderHistory(List<Order> orderHistory) {
        this.orderHistory = orderHistory;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    // Méthodes métier
    public void register() {
        // La logique d'enregistrement sera dans le service
    }

    public boolean login(String email, String password) {
        return this.email.equals(email) && this.password.equals(password);
    }

    public List<Order> viewOrderHistory() {
        return this.orderHistory;
    }
    
    // Méthode pour le polymorphisme
    public double applyDiscount(double amount) {
        // Implémentation de base: pas de réduction
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}