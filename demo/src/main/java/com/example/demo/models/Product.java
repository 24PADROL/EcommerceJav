package com.example.demo.models;

/**
 * Cette classe représente un produit dans notre magasin.
 * C'est comme une fiche qui décrit un jouet ou un objet qu'on peut acheter!
 */
public class Product {
    // Ces variables sont comme une étiquette qui contient toutes les informations sur le produit
    private Long id;                // Un numéro unique pour identifier chaque produit dans notre système
    private String productName;     // Le nom du produit, comme "Poupée Barbie" ou "Ballon de foot"
    private String productID;       // Un code spécial pour le produit, comme un code-barre au supermarché
    private double price;           // Le prix du produit en euros
    private int stockQuantity;      // Combien de ce produit nous avons dans notre magasin
    private String description;     // Une description qui explique ce qu'est le produit
    private String category;        // La catégorie du produit, comme "Jouets" ou "Nourriture" ou "Vêtements"
    
    /**
     * C'est comme créer une étiquette vide qu'on remplira plus tard.
     * On crée un nouveau produit sans informations.
     */
    public Product() {
    }
    
    /**
     * C'est comme remplir une étiquette avec les informations principales du produit dès le début.
     * On crée un produit avec ses informations de base.
     */
    public Product(Long id, String productName, String productID, double price, int stockQuantity) {
        this.id = id;                      // On assigne le numéro d'identification
        this.productName = productName;    // On assigne le nom du produit
        this.productID = productID;        // On assigne le code-barre du produit
        this.price = price;                // On assigne le prix
        this.stockQuantity = stockQuantity; // On assigne combien on en a en stock
    }
    
    // Les méthodes "get" et "set" ci-dessous sont comme des assistants qui nous aident à:
    // - Lire les informations (get = obtenir)
    // - Modifier les informations (set = définir)
    
    /**
     * Permet de connaître le numéro d'identification du produit.
     * Comme demander: "Quel est le numéro de ce produit dans notre système?"
     */
    public Long getId() {
        return id;
    }
    
    /**
     * Permet de changer le numéro d'identification du produit.
     * Comme si on disait: "Le nouveau numéro de ce produit est..."
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * Permet de connaître le nom du produit.
     * Comme demander: "Comment s'appelle ce produit?"
     */
    public String getProductName() {
        return productName;
    }
    
    /**
     * Permet de changer le nom du produit.
     * Comme si on disait: "Ce produit s'appellera désormais..."
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }
    
    /**
     * Permet de connaître le code-barre du produit.
     * Comme demander: "Quel est le code-barre de ce produit?"
     */
    public String getProductID() {
        return productID;
    }
    
    /**
     * Permet de changer le code-barre du produit.
     * Comme si on disait: "Le nouveau code-barre de ce produit est..."
     */
    public void setProductID(String productID) {
        this.productID = productID;
    }
    
    /**
     * Permet de connaître le prix du produit.
     * Comme demander: "Combien coûte ce produit?"
     */
    public double getPrice() {
        return price;
    }
    
    /**
     * Permet de changer le prix du produit.
     * Comme si on disait: "Le nouveau prix de ce produit est..."
     */
    public void setPrice(double price) {
        this.price = price;
    }
    
    /**
     * Permet de savoir combien d'exemplaires de ce produit nous avons en stock.
     * Comme demander: "Combien de ballons de foot nous reste-t-il?"
     */
    public int getStockQuantity() {
        return stockQuantity;
    }
    
    /**
     * Permet de changer la quantité en stock de ce produit.
     * Comme si on disait: "Nous avons maintenant 10 ballons de foot."
     */
    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
    
    /**
     * Permet de connaître la description du produit.
     * Comme demander: "Peux-tu me décrire ce produit?"
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * Permet de changer la description du produit.
     * Comme si on disait: "Voici la nouvelle description de ce produit..."
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
     * Permet de connaître la catégorie du produit.
     * Comme demander: "Ce produit est-il un jouet, un vêtement ou autre chose?"
     */
    public String getCategory() {
        return category;
    }
    
    /**
     * Permet de changer la catégorie du produit.
     * Comme si on disait: "Ce produit fait partie de la catégorie..."
     */
    public void setCategory(String category) {
        this.category = category;
    }
    
    /**
     * Cette méthode met à jour la quantité en stock.
     * Si 'quantity' est positif, on ajoute au stock (on a reçu une livraison).
     * Si 'quantity' est négatif, on retire du stock (quelqu'un a acheté).
     * On ne peut jamais avoir moins que 0 produits en stock.
     * C'est comme ajouter ou enlever des billes de ton sac, mais tu ne peux jamais avoir moins que 0 billes.
     */
    public void updateStock(int quantity) {
        this.stockQuantity += quantity;
        if (this.stockQuantity < 0) {
            this.stockQuantity = 0;
        }
    }
    
    /**
     * Cette méthode nous donne toutes les informations importantes sur le produit en un seul texte.
     * C'est comme lire l'étiquette complète d'un produit à haute voix.
     */
    public String getProductDetails() {
        return "Produit: " + productName + " (ID: " + productID + ")\n" +
               "Prix: " + price + " €\n" +
               "Stock: " + stockQuantity + "\n" +
               "Catégorie: " + category + "\n" +
               "Description: " + description;
    }
    
    /**
     * Cette méthode spéciale vérifie si deux produits sont les mêmes.
     * Nous considérons que deux produits sont identiques s'ils ont le même code-barre (productID).
     * C'est comme si tu demandais: "Ces deux jouets sont-ils exactement les mêmes modèles?"
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Product) {
            Product other = (Product) obj;
            return this.productID.equals(other.productID);
        }
        return false;
    }
}