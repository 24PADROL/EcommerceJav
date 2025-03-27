package com.example.demo.services;

import com.example.demo.models.Cart;
import com.example.demo.models.Order;
import com.example.demo.models.Product;
import com.example.demo.models.User;
import com.example.demo.repositories.CartRepository;
import com.example.demo.repositories.OrderRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.repositories.UserRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

// Service simplifié, sans annotations Spring
public class OrderService {
    private OrderRepository orderRepository;
    private UserRepository userRepository;
    private CartRepository cartRepository;
    private ProductRepository productRepository;
    private ProductService productService;

    // Constructeur avec injection manuelle de dépendance
    public OrderService(OrderRepository orderRepository, UserRepository userRepository,
                       CartRepository cartRepository, ProductRepository productRepository,
                       ProductService productService) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.productService = productService;
    }

    // Méthodes simplifiées
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public Order getOrderByOrderID(String orderID) {
        return orderRepository.findByOrderID(orderID);
    }

    public List<Order> getOrdersByUser(Long userId) {
        User user = userRepository.findById(userId);
        if (user == null) {
            System.out.println("Erreur: Utilisateur non trouvé");
            return null;
        }
        
        return orderRepository.findByUser(user);
    }

    public List<Order> getOrdersByStatus(String status) {
        return orderRepository.findByStatus(status);
    }

    public Order placeOrder(Long userId, Long cartId, String shippingAddress, String paymentMethod) {
        User user = userRepository.findById(userId);
        Cart cart = cartRepository.findById(cartId);
        
        if (user == null) {
            System.out.println("Erreur: Utilisateur non trouvé");
            return null;
        }
        if (cart == null) {
            System.out.println("Erreur: Panier non trouvé");
            return null;
        }
        
        // Vérifier si le panier appartient à l'utilisateur
        if (cart.getUser() == null || !cart.getUser().getId().equals(user.getId())) {
            System.out.println("Erreur: Le panier n'appartient pas à cet utilisateur");
            return null;
        }
        
        // Vérifier si le panier est vide
        if (cart.getItems().isEmpty()) {
            System.out.println("Erreur: Impossible de passer une commande avec un panier vide");
            return null;
        }
        
        // Vérifier la disponibilité du stock pour tous les produits
        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            
            if (product.getStockQuantity() < quantity) {
                System.out.println("Erreur: Stock insuffisant pour " + product.getProductName());
                return null;
            }
        }
        
        // Créer une nouvelle commande
        Order order = new Order();
        order.setUser(user);
        order.setItems(cart.getItems());
        order.setOrderID(generateOrderID());
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("PROCESSING");
        order.setShippingAddress(shippingAddress);
        order.setPaymentMethod(paymentMethod);
        
        // Mettre à jour le stock des produits
        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            
            productService.updateStock(product.getId(), -quantity);
        }
        
        // Enregistrer la commande
        Order savedOrder = orderRepository.save(order);
        
        // Ajouter la commande à l'historique de l'utilisateur
        user.addOrder(savedOrder);
        userRepository.save(user);
        
        // Vider le panier
        cart.clear();
        cartRepository.save(cart);
        
        System.out.println("Commande #" + order.getOrderID() + " créée avec succès");
        return savedOrder;
    }

    public Order updateOrderStatus(Long orderId, String newStatus) {
        Order order = orderRepository.findById(orderId);
        if (order == null) {
            System.out.println("Erreur: Commande non trouvée");
            return null;
        }
        
        order.updateStatus(newStatus);
        return orderRepository.save(order);
    }

    public boolean cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId);
        if (order == null) {
            System.out.println("Erreur: Commande non trouvée");
            return false;
        }
        
        // Vérifier si la commande peut être annulée (seulement si elle est en traitement)
        if (!"PROCESSING".equals(order.getStatus())) {
            System.out.println("Erreur: Impossible d'annuler une commande qui n'est pas en cours de traitement");
            return false;
        }
        
        // Mettre à jour le statut
        order.updateStatus("CANCELLED");
        
        // Remettre les produits en stock
        for (Map.Entry<Product, Integer> entry : order.getItems().entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            
            productService.updateStock(product.getId(), quantity);
        }
        
        orderRepository.save(order);
        System.out.println("Commande #" + order.getOrderID() + " annulée avec succès");
        return true;
    }
    
    // Méthode pour afficher toutes les commandes
    public void displayAllOrders() {
        orderRepository.displayAllOrders();
    }
    
    // Méthode pour afficher les détails d'une commande spécifique
    public void displayOrderDetails(Long orderId) {
        Order order = orderRepository.findById(orderId);
        if (order == null) {
            System.out.println("Erreur: Commande non trouvée");
            return;
        }
        
        order.displayOrderDetails();
    }

    // Génération d'un ID de commande unique
    private String generateOrderID() {
        return "ORD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}