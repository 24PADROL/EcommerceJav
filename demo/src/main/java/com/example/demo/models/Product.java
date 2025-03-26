package com.example.demo.models;
import java.util.ArrayList;
import java.util.List;

public class User {
    private Long id;
    private String username;
    private String email;
    private String password;
    private List<Order> orderHistory;
    private String userType;
    
    public User() {
        this.orderHistory = new ArrayList<>();
        this.userType = "REGULAR";
    }
    
    public User(Long id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.orderHistory = new ArrayList<>();
        this.userType = "REGULAR";
    }
    
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
    
    public boolean login(String email, String password) {
        return this.email.equals(email) && this.password.equals(password);
    }
    
    public void addOrder(Order order) {
        this.orderHistory.add(order);
    }
    
    public double applyDiscount(double amount) {
        return amount;
    }
}