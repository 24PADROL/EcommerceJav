package com.example.demo;

import com.example.demo.models.*;
import com.example.demo.models.payment.*;
import com.example.demo.repositories.*;
import com.example.demo.services.*;

/**
 * Classe principale pour tester l'application e-commerce simplifiée.
 * Ce programme permet de tester les fonctionnalités de base de l'application
 * en mode console, sans utiliser Spring Boot.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== APPLICATION E-COMMERCE SIMPLIFIÉE ===");
        System.out.println("Démarrage du programme de test...\n");
        
        // Initialisation des repositories
        ProductRepository productRepository = new ProductRepository();
        UserRepository userRepository = new UserRepository();
        CartRepository cartRepository = new CartRepository();
        OrderRepository orderRepository = new OrderRepository();
        
        // Initialisation des services
        ProductService productService = new ProductService(productRepository);
        UserService userService = new UserService(userRepository, cartRepository);
        CartService cartService = new CartService(cartRepository, userRepository, productRepository);
        OrderService orderService = new OrderService(orderRepository, userRepository, 
                                                    cartRepository, productRepository, productService);
        
        // Afficher les produits disponibles
        System.out.println("\n--- PRODUITS DISPONIBLES ---");
        productService.displayAllProducts();
        
        // Afficher les utilisateurs existants
        System.out.println("\n--- UTILISATEURS EXISTANTS ---");
        userService.displayAllUsers();
        
        // Création d'un nouvel utilisateur Premium
        System.out.println("\n--- CRÉATION D'UN NOUVEL UTILISATEUR PREMIUM ---");
        PremiumUser premiumUser = new PremiumUser();
        premiumUser.setUsername("premium_user");
        premiumUser.setEmail("premium@example.com");
        premiumUser.setPassword("premium123");
        premiumUser.setMembershipTier("GOLD");
        userService.registerPremiumUser(premiumUser);
        
        // Afficher tous les utilisateurs après l'ajout
        System.out.println("\n--- UTILISATEURS APRÈS AJOUT ---");
        userService.displayAllUsers();
        
        // Test de connexion
        System.out.println("\n--- TEST DE CONNEXION ---");
        User loggedInUser = userService.login("premium@example.com", "premium123");
        
        // Création d'un panier pour l'utilisateur connecté
        System.out.println("\n--- CRÉATION D'UN PANIER ---");
        Cart cart = cartService.getOrCreateCart(loggedInUser.getId());
        System.out.println("Panier créé avec ID: " + cart.getId());
        
        // Ajout de produits au panier
        System.out.println("\n--- AJOUT DE PRODUITS AU PANIER ---");
        cartService.addProductToCart(cart.getId(), 1L, 2); // 2 Smartphones
        cartService.addProductToCart(cart.getId(), 3L, 1); // 1 paire d'écouteurs
        
        // Afficher le contenu du panier
        System.out.println("\n--- CONTENU DU PANIER ---");
        cartService.displayCart(cart.getId());
        
        // Passer une commande avec PayPal
        System.out.println("\n--- PASSER UNE COMMANDE (PAYPAL) ---");
        Order orderPaypal = orderService.placeOrder(
            loggedInUser.getId(), 
            cart.getId(), 
            "123 Rue des Fleurs, 75000 Paris", 
            "PayPal"
        );
        
        // Traiter le paiement avec PayPal
        System.out.println("\n--- TRAITEMENT DU PAIEMENT PAYPAL ---");
        PaymentMethod paypalPayment = new PayPalPayment("premium@example.com");
        paypalPayment.processPayment(orderPaypal);
        paypalPayment.displayPaymentInfo();
        
        // Créer un nouveau panier pour le même utilisateur
        cart = cartService.getOrCreateCart(loggedInUser.getId());
        
        // Ajouter d'autres produits
        cartService.addProductToCart(cart.getId(), 2L, 1); // 1 Ordinateur portable
        
        // Afficher le contenu du nouveau panier
        System.out.println("\n--- CONTENU DU NOUVEAU PANIER ---");
        cartService.displayCart(cart.getId());
        
        // Passer une commande avec Carte de Crédit
        System.out.println("\n--- PASSER UNE COMMANDE (CARTE DE CRÉDIT) ---");
        Order orderCC = orderService.placeOrder(
            loggedInUser.getId(), 
            cart.getId(), 
            "123 Rue des Fleurs, 75000 Paris", 
            "Carte de crédit"
        );
        
        // Traiter le paiement avec Carte de Crédit
        System.out.println("\n--- TRAITEMENT DU PAIEMENT PAR CARTE ---");
        PaymentMethod creditCardPayment = new CreditCardPayment(
            "4111111111111111", 
            "Premium User", 
            "12/25", 
            "123"
        );
        creditCardPayment.processPayment(orderCC);
        creditCardPayment.displayPaymentInfo();
        
        // Rembourser le paiement
        System.out.println("\n--- TEST DE REMBOURSEMENT ---");
        creditCardPayment.refundPayment(orderCC);
        creditCardPayment.displayPaymentInfo();
        
        // Afficher toutes les commandes
        System.out.println("\n--- TOUTES LES COMMANDES ---");
        orderService.displayAllOrders();
        
        // Vérifier l'état du stock après les commandes
        System.out.println("\n--- ÉTAT DU STOCK APRÈS COMMANDES ---");
        productService.displayAllProducts();
        
        System.out.println("\n=== FIN DU PROGRAMME DE TEST ===");
    }
}