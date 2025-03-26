package com.example.demo.repositories;

import com.example.demo.models.Cart;
import com.example.demo.models.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class CartRepository {
    private Map<Long, Cart> carts = new HashMap<>();
    private long nextId = 1;

    public Optional<Cart> findById(Long id) {
        return Optional.ofNullable(carts.get(id));
    }

    public Optional<Cart> findByUser(User user) {
        return carts.values().stream()
                .filter(cart -> cart.getUser().equals(user))
                .findFirst();
    }

    public Cart save(Cart cart) {
        if (cart.getId() == null) {
            cart.setId(nextId++);
        }
        carts.put(cart.getId(), cart);
        return cart;
    }

    public void delete(Cart cart) {
        carts.remove(cart.getId());
    }

    public void deleteById(Long id) {
        carts.remove(id);
    }
}