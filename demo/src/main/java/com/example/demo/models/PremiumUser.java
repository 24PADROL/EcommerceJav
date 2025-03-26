package com.example.demo.models;

import java.time.LocalDate;

public class PremiumUser extends User {
    private LocalDate membershipStart;
    private LocalDate membershipEnd;
    private String membershipTier; // SILVER, GOLD, PLATINUM

    public PremiumUser() {
        super();
        this.setUserType("PREMIUM");
    }

    public PremiumUser(Long id, String username, String email, String password) {
        super(id, username, email, password);
        this.setUserType("PREMIUM");
        this.membershipStart = LocalDate.now();
        this.membershipEnd = membershipStart.plusYears(1);
        this.membershipTier = "SILVER";
    }

    // Getters et Setters spécifiques
    public LocalDate getMembershipStart() {
        return membershipStart;
    }

    public void setMembershipStart(LocalDate membershipStart) {
        this.membershipStart = membershipStart;
    }

    public LocalDate getMembershipEnd() {
        return membershipEnd;
    }

    public void setMembershipEnd(LocalDate membershipEnd) {
        this.membershipEnd = membershipEnd;
    }

    public String getMembershipTier() {
        return membershipTier;
    }

    public void setMembershipTier(String membershipTier) {
        this.membershipTier = membershipTier;
    }

    // Méthodes spécifiques aux utilisateurs premium
    public boolean isMembershipActive() {
        return LocalDate.now().isBefore(membershipEnd);
    }

    public void extendMembership(int months) {
        this.membershipEnd = this.membershipEnd.plusMonths(months);
    }

    // Polymorphisme
    @Override
    public double applyDiscount(double amount) {
        // Appliquer une réduction en fonction du niveau d'adhésion
        switch (membershipTier) {
            case "PLATINUM":
                return amount * 0.80; // 20% de réduction
            case "GOLD":
                return amount * 0.85; // 15% de réduction
            case "SILVER":
                return amount * 0.90; // 10% de réduction
            default:
                return amount;
        }
    }
}