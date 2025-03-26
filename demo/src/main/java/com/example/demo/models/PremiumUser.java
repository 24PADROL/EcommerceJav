package com.example.demo.models;

import java.time.LocalDate;

public class PremiumUser extends User {
    private LocalDate membershipStart;
    private LocalDate membershipEnd;
    private String membershipTier;

    public PremiumUser() {
        super();
        this.setUserType("PREMIUM");
        this.membershipStart = LocalDate.now();
        this.membershipEnd = membershipStart.plusYears(1);
        this.membershipTier = "SILVER";
    }

    public PremiumUser(Long id, String username, String email, String password) {
        super(id, username, email, password);
        this.setUserType("PREMIUM");
        this.membershipStart = LocalDate.now();
        this.membershipEnd = membershipStart.plusYears(1);
        this.membershipTier = "SILVER";
    }

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

    // Méthodes spécifiques
    public boolean isMembershipActive() {
        return LocalDate.now().isBefore(membershipEnd);
    }

    public void extendMembership(int months) {
        this.membershipEnd = this.membershipEnd.plusMonths(months);
    }

    @Override
    public double applyDiscount(double amount) {
        if ("PLATINUM".equals(membershipTier)) {
            return amount * 0.80; // 20% de réduction
        } else if ("GOLD".equals(membershipTier)) {
            return amount * 0.85; // 15% de réduction
        } else if ("SILVER".equals(membershipTier)) {
            return amount * 0.90; // 10% de réduction
        } else {
            return amount;
        }
    }
}