package com.example.demo.models.payment;

import com.example.demo.models.Order;

public class PayPalPayment extends PaymentMethod {
    private String email;
    private String paypalTransactionId;

    public PayPalPayment() {
        super();
    }

    public PayPalPayment(String email) {
        super();
        this.email = email;
    }

    // Getters et Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPaypalTransactionId() {
        return paypalTransactionId;
    }

    public void setPaypalTransactionId(String paypalTransactionId) {
        this.paypalTransactionId = paypalTransactionId;
    }

    // Implémentation des méthodes abstraites
    @Override
    public boolean processPayment(Order order) {
        // Simulation du traitement de paiement PayPal
        this.amount = order.getTotalAmount();
        this.paymentId = "PP-" + System.currentTimeMillis();
        this.paypalTransactionId = "PPID-" + System.currentTimeMillis();
        this.status = "COMPLETED";
        return true;
    }

    @Override
    public boolean refundPayment(Order order) {
        if ("COMPLETED".equals(this.status)) {
            this.status = "REFUNDED";
            return true;
        }
        return false;
    }

    @Override
    public String getPaymentDetails() {
        return "PayPal Payment: " + this.email + ", Transaction ID: " + this.paypalTransactionId;
    }
}
