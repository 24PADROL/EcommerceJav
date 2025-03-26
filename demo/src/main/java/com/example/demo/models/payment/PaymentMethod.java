package com.example.demo.models.payment;

import com.example.demo.models.Order;

public abstract class PaymentMethod {
    protected String paymentId;
    protected double amount;
    protected String status;

    public PaymentMethod() {
        this.status = "PENDING";
    }

    // Getters et Setters
    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Méthodes abstraites
    public abstract boolean processPayment(Order order);
    public abstract boolean refundPayment(Order order);
    public abstract String getPaymentDetails();
}