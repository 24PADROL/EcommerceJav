package com.example.demo.repositories;

import com.example.demo.models.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserRepository {
    private Map<Long, User> users = new HashMap<>();
    private long nextId = 1;

    public UserRepository() {
        // Ajouter quelques utilisateurs pour tester
        User user1 = new User(nextId++, "john_doe", "john@example.com", "password123");
        User user2 = new User(nextId++, "jane_smith", "jane@example.com", "password456");
        
        users.put(user1.getId(), user1);
        users.put(user2.getId(), user2);
    }

    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }

    public Optional<User> findById(Long id) {
        return Optional.ofNullable(users.get(id));
    }

    public Optional<User> findByEmail(String email) {
        return users.values().stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst();
    }

    public User save(User user) {
        if (user.getId() == null) {
            user.setId(nextId++);
        }
        users.put(user.getId(), user);
        return user;
    }

    public void delete(User user) {
        users.remove(user.getId());
    }

    public void deleteById(Long id) {
        users.remove(id);
    }
}
