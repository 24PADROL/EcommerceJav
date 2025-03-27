package com.example.demo.models.payment;

import com.example.demo.models.Order;

// Classe CreditCardPayment simplifiée
public class CreditCardPayment extends PaymentMethod {
    private String cardNumber;
    private String cardHolderName;
    private String expiryDate;
    private String cvv;

    // Constructeur par défaut
    public CreditCardPayment() {
        super();
    }

    // Constructeur avec paramètres
    public CreditCardPayment(String cardNumber, String cardHolderName, String expiryDate, String cvv) {
        super();
        this.cardNumber = maskCardNumber(cardNumber);
        this.cardHolderName = cardHolderName;
        this.expiryDate = expiryDate;
        this.cvv = "***"; // On ne stocke jamais le vrai CVV
    }

    // Getters et Setters
    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = maskCardNumber(cardNumber);
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    // Méthodes de l'implémentation
    @Override
    public boolean processPayment(Order order) {
        // Simulation du traitement de paiement
        System.out.println("Traitement du paiement par carte de crédit pour la commande #" + order.getOrderID());
        
        this.amount = order.getTotalAmount();
        this.paymentId = "CC-" + System.currentTimeMillis();
        this.status = "COMPLETED";
        
        System.out.println("Paiement complété avec succès: " + this.paymentId);
        return true;
    }

    @Override
    public boolean refundPayment(Order order) {
        if ("COMPLETED".equals(this.status)) {
            System.out.println("Remboursement du paiement par carte de crédit: " + this.paymentId);
            this.status = "REFUNDED";
            return true;
        }
        System.out.println("Impossible de rembourser un paiement qui n'est pas complété");
        return false;
    }

    @Override
    public String getPaymentDetails() {
        return "Paiement par carte de crédit: " + this.cardNumber + ", " + this.cardHolderName + ", Exp: " + this.expiryDate;
    }

    // Méthode utilitaire pour masquer le numéro de carte
    private String maskCardNumber(String cardNumber) {
        if (cardNumber == null || cardNumber.length() < 4) {
            return cardNumber;
        }
        String lastFourDigits = cardNumber.substring(cardNumber.length() - 4);
        return "****-****-****-" + lastFourDigits;
    }
}