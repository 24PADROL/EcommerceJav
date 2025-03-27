package com.example.demo.repositories;

import com.example.demo.models.Order;
import com.example.demo.models.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Repository simplifié, sans annotations Spring
public class OrderRepository {
    private Map<Long, Order> orders;
    private long nextId;

    public OrderRepository() {
        this.orders = new HashMap<>();
        this.nextId = 1;
    }

    // Méthodes CRUD simplifiées
    public List<Order> findAll() {
        return new ArrayList<>(orders.values());
    }

    public Order findById(Long id) {
        return orders.get(id);
    }

    public Order findByOrderID(String orderID) {
        for (Order order : orders.values()) {
            if (order.getOrderID().equals(orderID)) {
                return order;
            }
        }
        return null;
    }

    public List<Order> findByUser(User user) {
        List<Order> userOrders = new ArrayList<>();
        for (Order order : orders.values()) {
            if (order.getUser() != null && order.getUser().getId().equals(user.getId())) {
                userOrders.add(order);
            }
        }
        return userOrders;
    }

    public List<Order> findByStatus(String status) {
        List<Order> statusOrders = new ArrayList<>();
        for (Order order : orders.values()) {
            if (status.equals(order.getStatus())) {
                statusOrders.add(order);
            }
        }
        return statusOrders;
    }

    public Order save(Order order) {
        if (order.getId() == null) {
            order.setId(nextId++);
        }
        
        // Générer un ID de commande s'il n'existe pas
        if (order.getOrderID() == null || order.getOrderID().isEmpty()) {
            order.setOrderID("ORD-" + System.currentTimeMillis());
        }
        
        orders.put(order.getId(), order);
        return order;
    }

    public void delete(Order order) {
        if (order != null && order.getId() != null) {
            orders.remove(order.getId());
        }
    }

    public void deleteById(Long id) {
        orders.remove(id);
    }
    
    // Méthode pour afficher toutes les commandes (pour testing)
    public void displayAllOrders() {
        System.out.println("=== LISTE DES COMMANDES ===");
        if (orders.isEmpty()) {
            System.out.println("Aucune commande trouvée");
        } else {
            for (Order order : orders.values()) {
                System.out.println("Commande #" + order.getOrderID());
                System.out.println("Date: " + order.getOrderDate());
                System.out.println("Statut: " + order.getStatus());
                System.out.println("Client: " + (order.getUser() != null ? order.getUser().getUsername() : "Inconnu"));
                System.out.println("Montant: " + order.getTotalAmount() + " €");
                System.out.println("------------------------");
            }
        }
    }
}