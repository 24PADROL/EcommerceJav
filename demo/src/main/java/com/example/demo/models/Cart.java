package com.example.demo.models;
import java.util.HashMap;
import java.util.Map;

/**
 * Cette classe représente un panier d'achat dans notre magasin.
 * C'est comme le panier ou le caddie que tu prends au supermarché pour mettre tes produits avant de payer!
 */
public class Cart {
    // Ces variables sont les informations principales sur le panier
    private Long id;                     // Un numéro unique pour identifier chaque panier
    private User user;                   // La personne à qui appartient ce panier
    private Map<Product, Integer> items; // La liste des produits dans le panier et leur quantité
    
    /**
     * C'est comme prendre un panier vide à l'entrée du magasin.
     * On crée un nouveau panier sans rien dedans.
     */
    public Cart() {
        this.items = new HashMap<>();  // On crée une liste vide pour les produits
    }
    
    /**
     * C'est comme prendre un panier vide à l'entrée du magasin 
     * et mettre une étiquette avec ton nom dessus.
     * On crée un nouveau panier avec un numéro et un propriétaire.
     */
    public Cart(Long id, User user) {
        this.id = id;                  // On assigne le numéro d'identification
        this.user = user;              // On note à qui appartient ce panier
        this.items = new HashMap<>();  // On crée une liste vide pour les produits
    }
    
    /**
     * Permet de connaître le numéro d'identification du panier.
     * Comme demander: "Quel est le numéro de ce panier?"
     */
    public Long getId() {
        return id;
    }
    
    /**
     * Permet de changer le numéro d'identification du panier.
     * Comme si on disait: "Le nouveau numéro de ce panier est..."
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * Permet de savoir à qui appartient ce panier.
     * Comme demander: "À qui est ce panier?"
     */
    public User getUser() {
        return user;
    }
    
    /**
     * Permet de changer le propriétaire du panier.
     * Comme si on disait: "Ce panier appartient maintenant à..."
     */
    public void setUser(User user) {
        this.user = user;
    }
    
    /**
     * Permet de voir la liste des produits dans le panier et leur quantité.
     * Comme regarder ce qu'il y a dans ton caddie de supermarché.
     */
    public Map<Product, Integer> getItems() {
        return items;
    }
    
    /**
     * Permet de remplacer tous les produits du panier par une nouvelle liste.
     * Comme si tu vidais ton caddie et mettais d'autres produits à la place.
     */
    public void setItems(Map<Product, Integer> items) {
        this.items = items;
    }
    
    /**
     * Cette méthode ajoute un produit au panier, ou augmente sa quantité s'il y est déjà.
     * C'est comme prendre un produit sur l'étagère et le mettre dans ton caddie.
     * Si le produit est déjà dans le panier, on augmente juste la quantité.
     */
    public void addProduct(Product product, int quantity) {
        if (items.containsKey(product)) {
            // Si le produit est déjà dans le panier, on ajoute à la quantité existante
            int currentQty = items.get(product);
            items.put(product, currentQty + quantity);
        } else {
            // Sinon, on ajoute le nouveau produit avec sa quantité
            items.put(product, quantity);
        }
    }
    
    /**
     * Cette méthode retire complètement un produit du panier.
     * C'est comme si tu décidais de ne plus acheter ce produit et le remettais sur l'étagère.
     */
    public void removeProduct(Product product) {
        items.remove(product);
    }
    
    /**
     * Cette méthode change la quantité d'un produit dans le panier.
     * C'est comme si tu décidais d'acheter plus ou moins d'exemplaires d'un produit.
     * Si la quantité devient 0 ou négative, on retire complètement le produit du panier.
     */
    public void updateProductQuantity(Product product, int quantity) {
        if (quantity <= 0) {
            // Si la quantité est 0 ou négative, on retire le produit
            removeProduct(product);
        } else {
            // Sinon, on met à jour la quantité
            items.put(product, quantity);
        }
    }
    
    /**
     * Cette méthode calcule le prix total de tous les produits dans le panier.
     * C'est comme si tu calculais combien tu vas devoir payer à la caisse.
     */
    public double calculateTotal() {
        double total = 0.0;  // On commence à zéro
        
        // Pour chaque produit dans notre panier
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product product = entry.getKey();    // Le produit
            int quantity = entry.getValue();     // La quantité
            total += product.getPrice() * quantity;  // On ajoute (prix × quantité) au total
        }
        
        return total;  // On renvoie le total calculé
    }
    
    /**
     * Cette méthode vide complètement le panier.
     * C'est comme si tu renversais ton caddie et enlevais tous les produits d'un coup.
     */
    public void clear() {
        items.clear();
    }
    
    /**
     * Cette méthode affiche tous les produits dans le panier et leur prix.
     * C'est comme si tu regardais tout ce qu'il y a dans ton caddie et calculais combien ça coûte.
     */
    public void displayCart() {
        System.out.println("Contenu du panier #" + id + ":");
        
        if (items.isEmpty()) {
            // Si le panier est vide
            System.out.println("Le panier est vide");
        } else {
            // Pour chaque produit, on affiche son nom, sa quantité et son prix total
            for (Map.Entry<Product, Integer> entry : items.entrySet()) {
                Product product = entry.getKey();
                int quantity = entry.getValue();
                System.out.println(" - " + product.getProductName() + " x" + quantity + 
                                   " = " + (product.getPrice() * quantity) + " €");
            }
            
            // On affiche le prix total du panier
            System.out.println("Total: " + calculateTotal() + " €");
        }
    }
}