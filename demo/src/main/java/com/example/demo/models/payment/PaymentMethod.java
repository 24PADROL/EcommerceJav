package com.example.demo.models.payment;

import com.example.demo.models.Order;

// Classe abstraite PaymentMethod simplifiée
public abstract class PaymentMethod {
    protected String paymentId;
    protected double amount;
    protected String status;

    // Constructeur par défaut
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

    // Méthodes abstraites à implémenter par les sous-classes
    public abstract boolean processPayment(Order order);
    public abstract boolean refundPayment(Order order);
    public abstract String getPaymentDetails();
    
    // Méthode pour afficher les informations de paiement
    public void displayPaymentInfo() {
        System.out.println("=== INFORMATION DE PAIEMENT ===");
        System.out.println("ID de paiement: " + paymentId);
        System.out.println("Montant: " + amount + " €");
        System.out.println("Statut: " + status);
        System.out.println("Détails: " + getPaymentDetails());
    }
}