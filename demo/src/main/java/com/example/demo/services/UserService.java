package com.example.demo.services;

import com.example.demo.models.User;
import com.example.demo.models.AdminUser;
import com.example.demo.models.PremiumUser;
import com.example.demo.repositories.UserRepository;
import com.example.demo.repositories.CartRepository;
import java.util.List;

// Service simplifié, sans annotations Spring
public class UserService {
    private UserRepository userRepository;
    private CartRepository cartRepository;

    // Constructeur avec injection manuelle de dépendance
    public UserService(UserRepository userRepository, CartRepository cartRepository) {
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
    }

    // Méthodes simplifiées
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User registerUser(User user) {
        // Vérifier si l'e-mail existe déjà
        User existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser != null) {
            System.out.println("Erreur: Email déjà enregistré");
            return null;
        }
        
        return userRepository.save(user);
    }

    public User registerAdminUser(AdminUser adminUser) {
        // Vérifier si l'e-mail existe déjà
        User existingUser = userRepository.findByEmail(adminUser.getEmail());
        if (existingUser != null) {
            System.out.println("Erreur: Email déjà enregistré");
            return null;
        }
        
        return userRepository.save(adminUser);
    }

    public User registerPremiumUser(PremiumUser premiumUser) {
        // Vérifier si l'e-mail existe déjà
        User existingUser = userRepository.findByEmail(premiumUser.getEmail());
        if (existingUser != null) {
            System.out.println("Erreur: Email déjà enregistré");
            return null;
        }
        
        return userRepository.save(premiumUser);
    }

    public User login(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && user.login(email, password)) {
            System.out.println("Connexion réussie pour " + user.getUsername());
            return user;
        }
        System.out.println("Échec de la connexion");
        return null;
    }

    public User updateUser(User user) {
        // Vérifie si l'utilisateur existe
        User existingUser = userRepository.findById(user.getId());
        if (existingUser == null) {
            System.out.println("Erreur: Utilisateur non trouvé");
            return null;
        }
        return userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
        System.out.println("Utilisateur supprimé avec succès");
    }
    
    // Méthode pour afficher tous les utilisateurs (utile pour tests)
    public void displayAllUsers() {
        userRepository.displayAllUsers();
    }
}