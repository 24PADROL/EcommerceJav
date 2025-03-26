package com.example.demo.models;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Cette classe représente une commande dans notre magasin.
 * C'est comme un ticket de caisse qui contient tous les produits achetés par un client!
 */
public class Order {
    // Ces variables sont comme les différentes informations qu'on écrit sur un ticket de caisse
    private Long id;                     // Un numéro unique pour identifier chaque commande dans notre système
    private String orderID;              // Un code spécial pour la commande, comme le numéro sur ton ticket de caisse
    private User user;                   // La personne qui a fait cette commande
    private Map<Product, Integer> items; // La liste des produits achetés et leur quantité (comme "3 ballons, 2 poupées")
    private String status;               // L'état de la commande: en attente, en préparation, expédiée, etc.
    private LocalDateTime orderDate;     // La date et l'heure quand la commande a été passée
    private double totalAmount;          // Le montant total à payer pour tous les produits
    private String shippingAddress;      // L'adresse où envoyer les produits
    private String paymentMethod;        // Comment le client va payer (carte bancaire, espèces, etc.)

    /**
     * C'est comme créer un nouveau ticket de caisse vide.
     * Quand on crée une nouvelle commande sans informations:
     * - On prépare une liste vide pour les produits (items)
     * - On note la date et l'heure actuelles
     * - On définit le statut sur "PENDING" (en attente)
     */
    public Order() {
        this.items = new HashMap<>();           // On crée une liste vide pour les produits
        this.orderDate = LocalDateTime.now();   // On prend la date et l'heure actuelles
        this.status = "PENDING";                // La commande est "en attente" au début
    }

    /**
     * C'est comme remplir un ticket de caisse avec les informations principales.
     * Quand on crée une commande avec ses informations de base:
     * - On note toutes les informations données
     * - On prend une copie de la liste des produits (pour éviter des problèmes si la liste originale change)
     * - On note la date et l'heure actuelles
     * - On définit le statut sur "PENDING" (en attente)
     * - On calcule le montant total à payer
     */
    public Order(Long id, String orderID, User user, Map<Product, Integer> items) {
        this.id = id;                        // On assigne le numéro d'identification
        this.orderID = orderID;              // On assigne le code de la commande
        this.user = user;                    // On note qui a fait cette commande
        this.items = new HashMap<>(items);   // On prend une copie de la liste des produits
        this.orderDate = LocalDateTime.now(); // On prend la date et l'heure actuelles
        this.status = "PENDING";             // La commande est "en attente" au début
        calculateTotalAmount();              // On calcule combien ça coûte au total
    }

    // Les méthodes "get" et "set" ci-dessous sont comme des assistants qui nous aident à:
    // - Lire les informations (get = obtenir)
    // - Modifier les informations (set = définir)
    
    /**
     * Permet de connaître le numéro d'identification de la commande.
     * Comme demander: "Quel est le numéro de cette commande dans notre système?"
     */
    public Long getId() {
        return id;
    }

    /**
     * Permet de changer le numéro d'identification de la commande.
     * Comme si on disait: "Le nouveau numéro de cette commande est..."
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Permet de connaître le code de la commande.
     * Comme demander: "Quel est le numéro sur ce ticket de caisse?"
     */
    public String getOrderID() {
        return orderID;
    }

    /**
     * Permet de changer le code de la commande.
     * Comme si on disait: "Le nouveau code de cette commande est..."
     */
    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    /**
     * Permet de savoir qui a fait cette commande.
     * Comme demander: "Qui a acheté ces produits?"
     */
    public User getUser() {
        return user;
    }

    /**
     * Permet de changer qui a fait cette commande.
     * Comme si on disait: "Cette commande a été faite par..."
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Permet de voir la liste des produits achetés et leur quantité.
     * Comme demander: "Qu'est-ce qui a été acheté dans cette commande?"
     */
    public Map<Product, Integer> getItems() {
        return items;
    }

    /**
     * Permet de changer la liste des produits achetés.
     * Comme si on disait: "Voici la nouvelle liste des produits pour cette commande..."
     * Après avoir changé la liste, on recalcule automatiquement le montant total.
     */
    public void setItems(Map<Product, Integer> items) {
        this.items = items;
        calculateTotalAmount();  // On recalcule le montant total quand la liste change
    }

    /**
     * Permet de connaître l'état de la commande (en attente, en préparation, expédiée, etc.)
     * Comme demander: "Où en est cette commande?"
     */
    public String getStatus() {
        return status;
    }

    /**
     * Permet de changer l'état de la commande.
     * Comme si on disait: "Cette commande est maintenant..."
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Permet de connaître la date et l'heure quand la commande a été passée.
     * Comme demander: "Quand cette commande a-t-elle été faite?"
     */
    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    /**
     * Permet de changer la date et l'heure de la commande.
     * Comme si on disait: "Cette commande a été passée le..."
     */
    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * Permet de connaître le montant total à payer pour cette commande.
     * Comme demander: "Combien coûte tout ça au total?"
     */
    public double getTotalAmount() {
        return totalAmount;
    }

    /**
     * Permet de changer le montant total de la commande.
     * Comme si on disait: "Le nouveau prix total pour cette commande est..."
     */
    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    /**
     * Permet de connaître l'adresse où les produits doivent être livrés.
     * Comme demander: "Où faut-il livrer ces produits?"
     */
    public String getShippingAddress() {
        return shippingAddress;
    }

    /**
     * Permet de changer l'adresse de livraison.
     * Comme si on disait: "Les produits doivent maintenant être livrés à..."
     */
    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    /**
     * Permet de savoir comment le client va payer (carte bancaire, espèces, etc.)
     * Comme demander: "Comment va-t-il payer?"
     */
    public String getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * Permet de changer la méthode de paiement.
     * Comme si on disait: "Le client va maintenant payer avec..."
     */
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    /**
     * Cette méthode commence à traiter la commande.
     * Elle change l'état de la commande de "en attente" à "en cours de traitement".
     * C'est comme quand le vendeur prend ton ticket et commence à préparer ta commande.
     */
    public void processOrder() {
        this.status = "PROCESSING";  // On change l'état en "en cours de traitement"
        System.out.println("Commande #" + orderID + " en cours de traitement..."); // On affiche un message
    }

    /**
     * Cette méthode met à jour l'état de la commande avec un nouvel état.
     * C'est comme quand le vendeur met à jour le statut de ta commande 
     * (préparée, expédiée, livrée, etc.)
     */
    public void updateStatus(String newStatus) {
        this.status = newStatus;  // On change l'état avec le nouvel état
        System.out.println("Statut de la commande #" + orderID + " mis à jour: " + newStatus); // On affiche un message
    }

    /**
     * Cette méthode privée calcule le montant total de la commande.
     * Elle additionne le prix de chaque produit multiplié par sa quantité.
     * Ensuite, elle applique les réductions si l'utilisateur y a droit.
     * C'est comme quand le vendeur calcule combien tu dois payer au total.
     */
    private void calculateTotalAmount() {
        totalAmount = 0;  // On commence à zéro
        
        // Pour chaque produit dans notre liste
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product product = entry.getKey();    // Le produit
            int quantity = entry.getValue();     // La quantité achetée
            totalAmount += product.getPrice() * quantity;  // On ajoute (prix × quantité) au total
        }
        
        // Si un utilisateur est associé à cette commande, on applique ses réductions
        if (user != null) {
            totalAmount = user.applyDiscount(totalAmount);
        }
    }
    
    /**
     * Cette méthode affiche tous les détails de la commande.
     * C'est comme lire à haute voix toutes les informations sur un ticket de caisse.
     */
    public void displayOrderDetails() {
        System.out.println("=== DÉTAILS DE LA COMMANDE #" + orderID + " ===");
        System.out.println("Date: " + orderDate);
        System.out.println("Statut: " + status);
        System.out.println("Client: " + (user != null ? user.getUsername() : "Inconnu"));
        System.out.println("Adresse de livraison: " + shippingAddress);
        System.out.println("Méthode de paiement: " + paymentMethod);
        System.out.println("Articles:");
        
        // Pour chaque produit, on affiche son nom, sa quantité et son prix total
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            double lineTotal = product.getPrice() * quantity;
            System.out.println(" - " + product.getProductName() + " x" + quantity + 
                              " = " + lineTotal + " €");
        }
        
        System.out.println("Montant total: " + totalAmount + " €");
        System.out.println("======================================");
    }
}