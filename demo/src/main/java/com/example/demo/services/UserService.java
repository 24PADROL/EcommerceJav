package com.example.demo.services;

import com.example.demo.models.AdminUser;
import com.example.demo.models.PremiumUser;
import com.example.demo.models.User;
import com.example.demo.repositories.CartRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final CartRepository cartRepository;

    @Autowired
    public UserService(UserRepository userRepository, CartRepository cartRepository) {
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User registerUser(User user) {
        // Vérifier si l'e-mail existe déjà
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("Email already registered");
        }
        
        return userRepository.save(user);
    }

    public User registerAdminUser(AdminUser adminUser) {
        // Vérifier si l'e-mail existe déjà
        Optional<User> existingUser = userRepository.findByEmail(adminUser.getEmail());
        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("Email already registered");
        }
        
        return userRepository.save(adminUser);
    }

    public User registerPremiumUser(PremiumUser premiumUser) {
        // Vérifier si l'e-mail existe déjà
        Optional<User> existingUser = userRepository.findByEmail(premiumUser.getEmail());
        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("Email already registered");
        }
        
        return userRepository.save(premiumUser);
    }

    public Optional<User> login(String email, String password) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isPresent() && userOpt.get().login(email, password)) {
            return userOpt;
        }
        return Optional.empty();
    }

    public void updateUser(User user) {
        // Vérifie si l'utilisateur existe
        if (!userRepository.findById(user.getId()).isPresent()) {
            throw new IllegalArgumentException("User not found");
        }
        userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}