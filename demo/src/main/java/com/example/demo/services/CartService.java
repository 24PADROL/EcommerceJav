package com.example.demo.services;

import com.example.demo.models.Cart;
import com.example.demo.models.Product;
import com.example.demo.models.User;
import com.example.demo.repositories.CartRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.repositories.UserRepository;

// Service simplifié, sans annotations Spring
public class CartService {
    private CartRepository cartRepository;
    private UserRepository userRepository;
    private ProductRepository productRepository;

    // Constructeur avec injection manuelle de dépendance
    public CartService(CartRepository cartRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    // Méthodes simplifiées
    public Cart getOrCreateCart(Long userId) {
        User user = userRepository.findById(userId);
        if (user == null) {
            System.out.println("Erreur: Utilisateur non trouvé");
            return null;
        }
        
        Cart cart = cartRepository.findByUser(user);
        
        if (cart != null) {
            return cart;
        } else {
            Cart newCart = new Cart();
            newCart.setUser(user);
            return cartRepository.save(newCart);
        }
    }

    public Cart addProductToCart(Long cartId, Long productId, int quantity) {
        if (quantity <= 0) {
            System.out.println("Erreur: La quantité doit être positive");
            return null;
        }

        Cart cart = cartRepository.findById(cartId);
        Product product = productRepository.findById(productId);

        if (cart == null) {
            System.out.println("Erreur: Panier non trouvé");
            return null;
        }
        if (product == null) {
            System.out.println("Erreur: Produit non trouvé");
            return null;
        }

        // Vérifier si le stock est suffisant
        if (product.getStockQuantity() < quantity) {
            System.out.println("Erreur: Stock insuffisant");
            return null;
        }

        cart.addProduct(product, quantity);
        return cartRepository.save(cart);
    }

    public Cart removeProductFromCart(Long cartId, Long productId) {
        Cart cart = cartRepository.findById(cartId);
        Product product = productRepository.findById(productId);

        if (cart == null) {
            System.out.println("Erreur: Panier non trouvé");
            return null;
        }
        if (product == null) {
            System.out.println("Erreur: Produit non trouvé");
            return null;
        }

        cart.removeProduct(product);
        return cartRepository.save(cart);
    }

    public Cart updateProductQuantity(Long cartId, Long productId, int quantity) {
        Cart cart = cartRepository.findById(cartId);
        Product product = productRepository.findById(productId);

        if (cart == null) {
            System.out.println("Erreur: Panier non trouvé");
            return null;
        }
        if (product == null) {
            System.out.println("Erreur: Produit non trouvé");
            return null;
        }

        if (quantity <= 0) {
            cart.removeProduct(product);
        } else {
            // Vérifier si le stock est suffisant
            if (product.getStockQuantity() < quantity) {
                System.out.println("Erreur: Stock insuffisant");
                return null;
            }
            cart.updateProductQuantity(product, quantity);
        }

        return cartRepository.save(cart);
    }

    public double calculateCartTotal(Long cartId) {
        Cart cart = cartRepository.findById(cartId);
        if (cart == null) {
            System.out.println("Erreur: Panier non trouvé");
            return 0.0;
        }
        
        return cart.calculateTotal();
    }

    public void clearCart(Long cartId) {
        Cart cart = cartRepository.findById(cartId);
        if (cart == null) {
            System.out.println("Erreur: Panier non trouvé");
            return;
        }
        
        cart.clear();
        cartRepository.save(cart);
        System.out.println("Panier vidé avec succès");
    }
    
    // Méthode pour afficher les détails du panier
    public void displayCart(Long cartId) {
        Cart cart = cartRepository.findById(cartId);
        if (cart == null) {
            System.out.println("Erreur: Panier non trouvé");
            return;
        }
        
        cart.displayCart();
    }
}