package com.example.demo.models;

import java.util.Objects;

public class Product {
    private Long id;
    private String productName;
    private String productID;
    private double price;
    private int stockQuantity;
    private String description;
    private String category;

    public Product() {
    }

    public Product(Long id, String productName, String productID, double price, int stockQuantity) {
        this.id = id;
        this.productName = productName;
        this.productID = productID;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    // Méthodes métier
    public void updateStock(int quantity) {
        this.stockQuantity += quantity;
    }

    public String getProductDetails() {
        return "Product{" +
                "id=" + id +
                ", name='" + productName + '\'' +
                ", productID='" + productID + '\'' +
                ", price=" + price +
                ", stockQuantity=" + stockQuantity +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(productID, product.productID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productID);
    }
}