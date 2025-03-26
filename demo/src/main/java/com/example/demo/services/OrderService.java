package com.example.demo.services;

import com.example.demo.models.Cart;
import com.example.demo.models.Order;
import com.example.demo.models.Product;
import com.example.demo.models.User;
import com.example.demo.repositories.CartRepository;
import com.example.demo.repositories.OrderRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final ProductService productService;

    @Autowired
    public OrderService(OrderRepository orderRepository, UserRepository userRepository, 
                       CartRepository cartRepository, ProductRepository productRepository,
                       ProductService productService) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.productService = productService;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public Optional<Order> getOrderByOrderID(String orderID) {
        return orderRepository.findByOrderID(orderID);
    }

    public List<Order> getOrdersByUser(Long userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (!userOpt.isPresent()) {
            throw new IllegalArgumentException("User not found");
        }
        
        return orderRepository.findByUser(userOpt.get());
    }

    public List<Order> getOrdersByStatus(String status) {
        return orderRepository.findByStatus(status);
    }

    public Order placeOrder(Long userId, Long cartId, String shippingAddress, String paymentMethod) {
        Optional<User> userOpt = userRepository.findById(userId);
        Optional<Cart> cartOpt = cartRepository.findById(cartId);
        
        if (!userOpt.isPresent()) {
            throw new IllegalArgumentException("User not found");
        }
        if (!cartOpt.isPresent()) {
            throw new IllegalArgumentException("Cart not found");
        }
        
        User user = userOpt.get();
        Cart cart = cartOpt.get();
        
        // Vérifier si le panier appartient à l'utilisateur
        if (!cart.getUser().equals(user)) {
            throw new IllegalArgumentException("Cart does not belong to the user");
        }
        
        // Vérifier si le panier est vide
        if (cart.getItems().isEmpty()) {
            throw new IllegalArgumentException("Cannot place an order with an empty cart");
        }
        
        // Vérifier la disponibilité du stock pour tous les produits
        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            
            if (product.getStockQuantity() < quantity) {
                throw new IllegalArgumentException("Not enough stock for product: " + product.getProductName());
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
        user.getOrderHistory().add(savedOrder);
        userRepository.save(user);
        
        // Vider le panier
        cart.clear();
        cartRepository.save(cart);
        
        return savedOrder;
    }

    public Order updateOrderStatus(Long orderId, String newStatus) {
        Optional<Order> orderOpt = orderRepository.findById(orderId);
        if (!orderOpt.isPresent()) {
            throw new IllegalArgumentException("Order not found");
        }
        
        Order order = orderOpt.get();
        order.updateStatus(newStatus);
        
        return orderRepository.save(order);
    }

    public boolean cancelOrder(Long orderId) {
        Optional<Order> orderOpt = orderRepository.findById(orderId);
        if (!orderOpt.isPresent()) {
            return false;
        }
        
        Order order = orderOpt.get();
        
        // Vérifier si la commande peut être annulée (seulement si elle est en traitement)
        if (!"PROCESSING".equals(order.getStatus())) {
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
        return true;
    }

    private String generateOrderID() {
        return "ORD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}