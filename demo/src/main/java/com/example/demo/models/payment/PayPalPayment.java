package com.example.demo.models.payment;

import com.example.demo.models.Order;

// Classe PayPalPayment simplifiée
public class PayPalPayment extends PaymentMethod {
    private String email;
    private String paypalTransactionId;

    // Constructeur par défaut
    public PayPalPayment() {
        super();
    }

    // Constructeur avec paramètres
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

    // Méthodes de l'implémentation
    @Override
    public boolean processPayment(Order order) {
        // Simulation du traitement de paiement PayPal
        System.out.println("Traitement du paiement PayPal pour la commande #" + order.getOrderID());
        
        this.amount = order.getTotalAmount();
        this.paymentId = "PP-" + System.currentTimeMillis();
        this.paypalTransactionId = "PPID-" + System.currentTimeMillis();
        this.status = "COMPLETED";
        
        System.out.println("Paiement PayPal complété avec succès: " + this.paymentId);
        System.out.println("ID de transaction PayPal: " + this.paypalTransactionId);
        return true;
    }

    @Override
    public boolean refundPayment(Order order) {
        if ("COMPLETED".equals(this.status)) {
            System.out.println("Remboursement du paiement PayPal: " + this.paymentId);
            System.out.println("Transaction PayPal: " + this.paypalTransactionId);
            this.status = "REFUNDED";
            return true;
        }
        System.out.println("Impossible de rembourser un paiement qui n'est pas complété");
        return false;
    }

    @Override
    public String getPaymentDetails() {
        return "Paiement PayPal: " + this.email + ", Transaction ID: " + this.paypalTransactionId;
    }
}