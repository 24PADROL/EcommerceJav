package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.repositories.*;
import com.example.demo.services.*;
import com.example.demo.controllers.*;

@Configuration
public class AppConfig {

    // Repositories
    @Bean
    public UserRepository userRepository() {
        return new UserRepository();
    }

    @Bean
    public ProductRepository productRepository() {
        return new ProductRepository();
    }

    @Bean
    public CartRepository cartRepository() {
        return new CartRepository();
    }

    @Bean
    public OrderRepository orderRepository() {
        return new OrderRepository();
    }

    // Services
    @Bean
    public ProductService productService() {
        return new ProductService(productRepository());
    }

    @Bean
    public UserService userService() {
        return new UserService(userRepository(), cartRepository());
    }

    @Bean
    public CartService cartService() {
        return new CartService(cartRepository(), userRepository(), productRepository());
    }

    @Bean
    public OrderService orderService() {
        return new OrderService(orderRepository(), userRepository(), 
                               cartRepository(), productRepository(), productService());
    }

    // Controllers
    @Bean
    public CartController cartController() {
        return new CartController(cartService());
    }

    @Bean
    public ProductController productController() {
        return new ProductController(productService());
    }

    @Bean
    public OrderController orderController() {
        return new OrderController(orderService());
    }

    @Bean
    public UserController userController() {
        return new UserController(userService(), orderService());
    }
}