package com.example.demo.models;

public class AdminUser extends User {
    private String adminLevel;
    private String department;

    public AdminUser() {
        super();
        this.setUserType("ADMIN");
    }

    public AdminUser(Long id, String username, String email, String password) {
        super(id, username, email, password);
        this.setUserType("ADMIN");
        this.adminLevel = "STANDARD";
    }

    // Getters et Setters spécifiques
    public String getAdminLevel() {
        return adminLevel;
    }

    public void setAdminLevel(String adminLevel) {
        this.adminLevel = adminLevel;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    // Méthodes spécifiques aux administrateurs
    public boolean canManageProducts() {
        return true;
    }

    public boolean canManageUsers() {
        return "HIGH".equals(adminLevel);
    }

    // Polymorphisme
    @Override
    public double applyDiscount(double amount) {
        // Les administrateurs ont 15% de réduction
        return amount * 0.85;
    }
}