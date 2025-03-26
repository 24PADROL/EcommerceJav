package com.example.demo.models;
import java.util.ArrayList;
import java.util.List;


/* Cette classe représente un utilisateur dans notre application.
Comme une carte d'identité qui contient toutes les informations sur une personne! */

public class User {
    // Ces variables sont comme une boîte secrète où on garde les informations de l'utilisateur
    private Long id;                 // Un numéro unique pour identifier chaque utilisateur, comme un numéro d'élève à l'école
    private String username;         // Le nom que l'utilisateur choisit pour se connecter, comme un surnom
    private String email;            // L'adresse email de l'utilisateur pour lui envoyer des messages
    private String password;         // Le mot de passe secret pour protéger le compte, comme la clé de ta maison
    private List<Order> orderHistory; // La liste de toutes les commandes que l'utilisateur a faites, comme un carnet qui garde la trace de tous ses achats
    private String userType;         // Le type d'utilisateur (REGULAR = normal), comme une étiquette qui dit si tu es un élève, un professeur, etc.
    
    /* C'est comme préparer une nouvelle carte d'identité vide.
    On crée un nouvel utilisateur sans informations, mais on prépare quand même sa liste de commandes vide
    et on lui donne le type "REGULAR" (utilisateur normal). */

    public User() {
        this.orderHistory = new ArrayList<>();  // On crée une liste vide pour les commandes futures
        this.userType = "REGULAR";              // Par défaut, tout le monde est un utilisateur normal
    }
    

    /* C'est comme remplir une carte d'identité avec toutes les informations importantes dès le début.
    On crée un utilisateur avec ses informations de base. */

    public User(Long id, String username, String email, String password) {
        this.id = id;                          // On assigne le numéro d'identification
        this.username = username;              // On assigne le nom d'utilisateur choisi
        this.email = email;                    // On assigne l'adresse email
        this.password = password;              // On assigne le mot de passe
        this.orderHistory = new ArrayList<>(); // On prépare une liste vide pour ses futures commandes
        this.userType = "REGULAR";             // Par défaut, c'est un utilisateur normal
    }
    

    // Les méthodes "get" et "set" ci-dessous sont comme des assistants qui nous aident à:
    // - Lire les informations (get = obtenir)
    // - Modifier les informations (set = définir)

    /* Permet de connaître le numéro d'identification de l'utilisateur.
    Comme demander: "Quel est ton numéro d'élève?" */

    public Long getId() {
        return id;
    }
    

    /* Permet de changer le numéro d'identification de l'utilisateur.
    Comme si on disait: "Ton nouveau numéro d'élève est..." */

    public void setId(Long id) {
        this.id = id;
    }
    

    /* Permet de connaître le nom d'utilisateur.
    Comme demander: "Comment t'appelles-tu sur le site? */

    public String getUsername() {
        return username;
    }
    

    /**
     * Permet de changer le nom d'utilisateur.
     * Comme si on disait: "Tu t'appelleras désormais..."
     */
    public void setUsername(String username) {
        this.username = username;
    }
    
    
    /**
     * Permet de connaître l'adresse email de l'utilisateur.
     * Comme demander: "Quelle est ton adresse email?"
     */
    public String getEmail() {
        return email;
    }
    

    /**
     * Permet de changer l'adresse email de l'utilisateur.
     * Comme si on disait: "Ta nouvelle adresse email est..."
     */
    public void setEmail(String email) {
        this.email = email;
    }
    

    /**
     * Permet de connaître le mot de passe de l'utilisateur.
     * Comme demander: "Quel est ton mot de passe?" (attention, c'est secret!)
     */
    public String getPassword() {
        return password;
    }
    

    /**
     * Permet de changer le mot de passe de l'utilisateur.
     * Comme si on disait: "Ton nouveau mot de passe est..."
     */
    public void setPassword(String password) {
        this.password = password;
    }
    

    /**
     * Permet de voir toutes les commandes que l'utilisateur a faites.
     * Comme regarder l'historique des achats.
     */
    public List<Order> getOrderHistory() {
        return orderHistory;
    }
    

    /**
     * Permet de remplacer toute la liste des commandes par une nouvelle liste.
     * Comme si on changeait tout le carnet d'historique d'un coup.
     */
    public void setOrderHistory(List<Order> orderHistory) {
        this.orderHistory = orderHistory;
    }
    

    /**
     * Permet de savoir quel type d'utilisateur il est (normal, premium, etc.).
     * Comme demander: "Es-tu un élève, un professeur ou un directeur?"
     */
    public String getUserType() {
        return userType;
    }
    

    /**
     * Permet de changer le type d'utilisateur.
     * Comme si on disait: "Maintenant tu n'es plus un élève, tu es un professeur!"
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }
    

    /**
     * Cette méthode vérifie si l'email et le mot de passe sont corrects pour se connecter.
     * C'est comme vérifier si la clé correspond bien à la serrure de la porte.
     */
    public boolean login(String email, String password) {
        return this.email.equals(email) && this.password.equals(password);
    }
    

    /**
     * Cette méthode ajoute une nouvelle commande à l'historique de l'utilisateur.
     * Comme ajouter une nouvelle page dans le carnet qui garde la trace de tous les achats.
     */
    public void addOrder(Order order) {
        this.orderHistory.add(order);
    }
    
    
    /**
     * Cette méthode calcule une réduction sur un montant pour l'utilisateur.
     * Pour un utilisateur normal, il n'y a pas de réduction (on retourne le même montant).
     * C'est comme dire: "Normalement, tu paies le prix complet."
     */
    public double applyDiscount(double amount) {
        return amount;  // Pas de réduction pour les utilisateurs normaux
    }
}