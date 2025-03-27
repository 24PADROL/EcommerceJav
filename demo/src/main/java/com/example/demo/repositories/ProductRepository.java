package com.example.demo.repositories;

import com.example.demo.models.Product;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Repository simplifié, sans annotations Spring
public class ProductRepository {
    private Map<Long, Product> products;
    private long nextId;

    public ProductRepository() {
        this.products = new HashMap<>();
        this.nextId = 1;
        
        // Ajouter quelques produits pour test
        initDemoProducts();
    }

    // Initialisation des données de démonstration
    private void initDemoProducts() {
        Product product1 = new Product(nextId++, "Smartphone X", "PROD-001", 699.99, 100);
        product1.setDescription("Le dernier smartphone avec des fonctionnalités avancées");
        product1.setCategory("Électronique");
        
        Product product2 = new Product(nextId++, "Ordinateur Portable Pro", "PROD-002", 1299.99, 50);
        product2.setDescription("Ordinateur haute performance pour les professionnels");
        product2.setCategory("Électronique");
        
        Product product3 = new Product(nextId++, "Écouteurs sans fil", "PROD-003", 159.99, 200);
        product3.setDescription("Écouteurs sans fil premium avec annulation de bruit");
        product3.setCategory("Audio");
        
        // Sauvegarder dans la collection
        products.put(product1.getId(), product1);
        products.put(product2.getId(), product2);
        products.put(product3.getId(), product3);
    }

    // Méthodes CRUD simplifiées
    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    public Product findById(Long id) {
        return products.get(id);
    }

    public Product findByProductID(String productID) {
        for (Product product : products.values()) {
            if (product.getProductID().equals(productID)) {
                return product;
            }
        }
        return null;
    }

    public List<Product> findByCategory(String category) {
        List<Product> categoryProducts = new ArrayList<>();
        for (Product product : products.values()) {
            if (category.equals(product.getCategory())) {
                categoryProducts.add(product);
            }
        }
        return categoryProducts;
    }

    public Product save(Product product) {
        if (product.getId() == null) {
            product.setId(nextId++);
        }
        products.put(product.getId(), product);
        return product;
    }

    public void delete(Product product) {
        if (product != null && product.getId() != null) {
            products.remove(product.getId());
        }
    }

    public void deleteById(Long id) {
        products.remove(id);
    }
    
    // Méthode pour afficher tous les produits (pour testing)
    public void displayAllProducts() {
        System.out.println("=== LISTE DES PRODUITS ===");
        for (Product product : products.values()) {
            System.out.println(product.getProductDetails());
            System.out.println("------------------------");
        }
    }
}