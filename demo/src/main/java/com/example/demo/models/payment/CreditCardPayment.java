package com.example.demo.models.payment;

import com.example.demo.models.Order;

public class CreditCardPayment extends PaymentMethod {
    private String cardNumber;
    private String cardHolderName;
    private String expiryDate;
    private String cvv;

    public CreditCardPayment() {
        super();
    }

    public CreditCardPayment(String cardNumber, String cardHolderName, String expiryDate, String cvv) {
        super();
        this.cardNumber = maskCardNumber(cardNumber);
        this.cardHolderName = cardHolderName;
        this.expiryDate = expiryDate;
        this.cvv = "***"; // Ne jamais stocker le vrai CVV
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

    // Implémentation des méthodes abstraites
    @Override
    public boolean processPayment(Order order) {
        // Simulation du traitement de paiement
        this.amount = order.getTotalAmount();
        this.paymentId = "CC-" + System.currentTimeMillis();
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
        return "Credit Card Payment: " + this.cardNumber + ", " + this.cardHolderName + ", Exp: " + this.expiryDate;
    }

    // Méthode utilitaire
    private String maskCardNumber(String cardNumber) {
        if (cardNumber == null || cardNumber.length() < 4) {
            return cardNumber;
        }
        String lastFourDigits = cardNumber.substring(cardNumber.length() - 4);
        return "****-****-****-" + lastFourDigits;
    }
}
