package com.example.demo.repositories;

import com.example.demo.models.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Repository simplifié, sans annotations Spring
public class UserRepository {
    private Map<Long, User> users;
    private long nextId;

    public UserRepository() {
        this.users = new HashMap<>();
        this.nextId = 1;
        
        // Ajouter quelques utilisateurs pour test
        initDemoUsers();
    }

    // Initialisation des données de démonstration
    private void initDemoUsers() {
        User user1 = new User(nextId++, "john_doe", "john@example.com", "password123");
        User user2 = new User(nextId++, "jane_smith", "jane@example.com", "password456");
        
        // Sauvegarder dans la collection
        users.put(user1.getId(), user1);
        users.put(user2.getId(), user2);
    }

    // Méthodes CRUD simplifiées
    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }

    public User findById(Long id) {
        return users.get(id);
    }

    public User findByEmail(String email) {
        for (User user : users.values()) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }

    public User save(User user) {
        if (user.getId() == null) {
            user.setId(nextId++);
        }
        users.put(user.getId(), user);
        return user;
    }

    public void delete(User user) {
        if (user != null && user.getId() != null) {
            users.remove(user.getId());
        }
    }

    public void deleteById(Long id) {
        users.remove(id);
    }
    
    // Méthode pour afficher tous les utilisateurs (pour testing)
    public void displayAllUsers() {
        System.out.println("=== LISTE DES UTILISATEURS ===");
        for (User user : users.values()) {
            System.out.println("ID: " + user.getId());
            System.out.println("Nom: " + user.getUsername());
            System.out.println("Email: " + user.getEmail());
            System.out.println("Type: " + user.getUserType());
            System.out.println("------------------------");
        }
    }
}