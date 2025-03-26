package com.example.demo.repositories;

import com.example.demo.models.Order;
import com.example.demo.models.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class OrderRepository {
    private Map<Long, Order> orders = new HashMap<>();
    private long nextId = 1;

    public List<Order> findAll() {
        return new ArrayList<>(orders.values());
    }

    public Optional<Order> findById(Long id) {
        return Optional.ofNullable(orders.get(id));
    }

    public Optional<Order> findByOrderID(String orderID) {
        return orders.values().stream()
                .filter(order -> order.getOrderID().equals(orderID))
                .findFirst();
    }

    public List<Order> findByUser(User user) {
        return orders.values().stream()
                .filter(order -> order.getUser().equals(user))
                .toList();
    }

    public List<Order> findByStatus(String status) {
        return orders.values().stream()
                .filter(order -> status.equals(order.getStatus()))
                .toList();
    }

    public Order save(Order order) {
        if (order.getId() == null) {
            order.setId(nextId++);
        }
        if (order.getOrderID() == null || order.getOrderID().isEmpty()) {
            order.setOrderID("ORD-" + System.currentTimeMillis());
        }
        orders.put(order.getId(), order);
        return order;
    }

    public void delete(Order order) {
        orders.remove(order.getId());
    }

    public void deleteById(Long id) {
        orders.remove(id);
    }
}