package com.example.demo.models;

/**
 * Cette classe représente un utilisateur administrateur qui a des pouvoirs spéciaux dans notre application.
 * C'est comme un professeur ou un directeur dans une école qui a plus de droits que les élèves!
 * Cette classe "hérite" de la classe User, ce qui veut dire qu'elle a toutes les caractéristiques
 * d'un utilisateur normal, plus des choses supplémentaires pour gérer l'application.
 */
public class AdminUser extends User {
    // Ces variables contiennent les informations spéciales pour les administrateurs
    private String adminLevel;   // Le niveau d'administration (STANDARD ou HIGH), comme être un simple professeur ou le directeur
    private String department;   // Le département où travaille cet administrateur, comme la matière qu'enseigne un professeur
    
    /**
     * C'est comme créer un nouveau badge d'administrateur avec les réglages par défaut.
     * Quand on crée un administrateur sans donner d'informations:
     * - On commence par créer un utilisateur normal (avec super())
     * - On change son type en "ADMIN"
     * - On lui donne le niveau d'administration "STANDARD" (niveau de base)
     */
    public AdminUser() {
        super();                      // On appelle le constructeur de la classe parent (User)
        this.setUserType("ADMIN");    // On change le type d'utilisateur en "ADMIN"
        this.adminLevel = "STANDARD"; // Le niveau d'administration par défaut est STANDARD
    }
    
    /**
     * C'est comme créer un nouveau badge d'administrateur avec les informations de base.
     * Quand on crée un administrateur en donnant ses informations principales:
     * - On commence par créer un utilisateur normal avec ses informations (avec super())
     * - On change son type en "ADMIN"
     * - On lui donne le niveau d'administration "STANDARD" (niveau de base)
     */
    public AdminUser(Long id, String username, String email, String password) {
        super(id, username, email, password); // On appelle le constructeur de la classe parent avec les infos
        this.setUserType("ADMIN");            // On change le type d'utilisateur en "ADMIN"
        this.adminLevel = "STANDARD";         // Le niveau d'administration par défaut est STANDARD
    }
    
    /**
     * Permet de connaître le niveau d'administration (STANDARD ou HIGH).
     * Comme demander: "Es-tu un simple professeur ou le directeur de l'école?"
     */
    public String getAdminLevel() {
        return adminLevel;
    }
    
    /**
     * Permet de changer le niveau d'administration.
     * Comme si on disait: "Tu n'es plus un simple professeur, tu es maintenant le directeur!"
     */
    public void setAdminLevel(String adminLevel) {
        this.adminLevel = adminLevel;
    }
    
    /**
     * Permet de connaître le département où travaille cet administrateur.
     * Comme demander: "Quelle matière enseignes-tu à l'école?"
     */
    public String getDepartment() {
        return department;
    }
    
    /**
     * Permet de changer le département de l'administrateur.
     * Comme si on disait: "Tu n'enseignes plus les maths, tu enseignes maintenant la musique!"
     */
    public void setDepartment(String department) {
        this.department = department;
    }
    
    /**
     * Cette méthode vérifie si cet administrateur peut gérer les produits.
     * Tous les administrateurs, quel que soit leur niveau, peuvent gérer les produits.
     * C'est comme dire: "Tous les professeurs peuvent choisir les fournitures scolaires."
     */
    public boolean canManageProducts() {
        return true;  // Tous les administrateurs peuvent gérer les produits
    }
    
    /**
     * Cette méthode vérifie si cet administrateur peut gérer les utilisateurs.
     * Seuls les administrateurs de niveau HIGH peuvent gérer les utilisateurs.
     * C'est comme dire: "Seul le directeur peut décider qui peut entrer dans l'école."
     */
    public boolean canManageUsers() {
        return "HIGH".equals(adminLevel);  // Seuls les administrateurs de niveau HIGH peuvent gérer les utilisateurs
    }
    
    /**
     * Cette méthode calcule une réduction sur un montant pour l'administrateur.
     * Elle "remplace" (override) la méthode du même nom dans la classe User.
     * Les administrateurs bénéficient d'une réduction de 15% sur tous leurs achats.
     * C'est comme dire: "Les professeurs ont toujours 15% de réduction dans la boutique de l'école."
     */
    @Override
    public double applyDiscount(double amount) {
        return amount * 0.85;  // 15% de réduction (on paie 85% du prix)
    }
}