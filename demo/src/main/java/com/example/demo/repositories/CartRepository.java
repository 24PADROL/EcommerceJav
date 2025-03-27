package com.example.demo.repositories;

import com.example.demo.models.Cart;
import com.example.demo.models.User;
import java.util.HashMap;
import java.util.Map;

// Repository simplifié, sans annotations Spring
public class CartRepository {
    private Map<Long, Cart> carts;
    private long nextId;

    public CartRepository() {
        this.carts = new HashMap<>();
        this.nextId = 1;
    }

    // Méthodes CRUD simplifiées
    public Cart findById(Long id) {
        return carts.get(id);
    }

    public Cart findByUser(User user) {
        for (Cart cart : carts.values()) {
            if (cart.getUser() != null && cart.getUser().getId().equals(user.getId())) {
                return cart;
            }
        }
        return null;
    }

    public Cart save(Cart cart) {
        if (cart.getId() == null) {
            cart.setId(nextId++);
        }
        carts.put(cart.getId(), cart);
        return cart;
    }

    public void delete(Cart cart) {
        if (cart != null && cart.getId() != null) {
            carts.remove(cart.getId());
        }
    }

    public void deleteById(Long id) {
        carts.remove(id);
    }
    
    // Méthode pour afficher tous les paniers (pour testing)
    public void displayAllCarts() {
        System.out.println("=== LISTE DES PANIERS ===");
        if (carts.isEmpty()) {
            System.out.println("Aucun panier trouvé");
        } else {
            for (Cart cart : carts.values()) {
                System.out.println("Panier #" + cart.getId());
                System.out.println("Utilisateur: " + (cart.getUser() != null ? cart.getUser().getUsername() : "Non assigné"));
                System.out.println("Nombre d'articles: " + cart.getItems().size());
                System.out.println("Total: " + cart.calculateTotal() + " €");
                System.out.println("------------------------");
            }
        }
    }
}