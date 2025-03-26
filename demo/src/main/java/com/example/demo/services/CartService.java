package com.example.demo.services;

import com.example.demo.models.Cart;
import com.example.demo.models.Product;
import com.example.demo.models.User;
import com.example.demo.repositories.CartRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Autowired
    public CartService(CartRepository cartRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public Cart getOrCreateCart(Long userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (!userOpt.isPresent()) {
            throw new IllegalArgumentException("User not found");
        }
        
        User user = userOpt.get();
        Optional<Cart> cartOpt = cartRepository.findByUser(user);
        
        if (cartOpt.isPresent()) {
            return cartOpt.get();
        } else {
            Cart newCart = new Cart();
            newCart.setUser(user);
            return cartRepository.save(newCart);
        }
    }

    public Cart addProductToCart(Long cartId, Long productId, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }

        Optional<Cart> cartOpt = cartRepository.findById(cartId);
        Optional<Product> productOpt = productRepository.findById(productId);

        if (!cartOpt.isPresent()) {
            throw new IllegalArgumentException("Cart not found");
        }
        if (!productOpt.isPresent()) {
            throw new IllegalArgumentException("Product not found");
        }

        Cart cart = cartOpt.get();
        Product product = productOpt.get();

        // Vérifier si le stock est suffisant
        if (product.getStockQuantity() < quantity) {
            throw new IllegalArgumentException("Not enough stock available");
        }

        cart.addProduct(product, quantity);
        return cartRepository.save(cart);
    }

    public Cart removeProductFromCart(Long cartId, Long productId) {
        Optional<Cart> cartOpt = cartRepository.findById(cartId);
        Optional<Product> productOpt = productRepository.findById(productId);

        if (!cartOpt.isPresent()) {
            throw new IllegalArgumentException("Cart not found");
        }
        if (!productOpt.isPresent()) {
            throw new IllegalArgumentException("Product not found");
        }

        Cart cart = cartOpt.get();
        Product product = productOpt.get();

        cart.removeProduct(product);
        return cartRepository.save(cart);
    }

    public Cart updateProductQuantity(Long cartId, Long productId, int quantity) {
        Optional<Cart> cartOpt = cartRepository.findById(cartId);
        Optional<Product> productOpt = productRepository.findById(productId);

        if (!cartOpt.isPresent()) {
            throw new IllegalArgumentException("Cart not found");
        }
        if (!productOpt.isPresent()) {
            throw new IllegalArgumentException("Product not found");
        }

        Cart cart = cartOpt.get();
        Product product = productOpt.get();

        if (quantity <= 0) {
            cart.removeProduct(product);
        } else {
            // Vérifier si le stock est suffisant
            if (product.getStockQuantity() < quantity) {
                throw new IllegalArgumentException("Not enough stock available");
            }
            cart.updateProductQuantity(product, quantity);
        }

        return cartRepository.save(cart);
    }

    public double calculateCartTotal(Long cartId) {
        Optional<Cart> cartOpt = cartRepository.findById(cartId);
        if (!cartOpt.isPresent()) {
            throw new IllegalArgumentException("Cart not found");
        }
        
        return cartOpt.get().calculateTotal();
    }

    public void clearCart(Long cartId) {
        Optional<Cart> cartOpt = cartRepository.findById(cartId);
        if (!cartOpt.isPresent()) {
            throw new IllegalArgumentException("Cart not found");
        }
        
        Cart cart = cartOpt.get();
        cart.clear();
        cartRepository.save(cart);
    }
}