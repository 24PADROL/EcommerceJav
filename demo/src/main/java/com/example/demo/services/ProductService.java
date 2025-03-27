package com.example.demo.services;

import com.example.demo.models.Product;
import com.example.demo.repositories.ProductRepository;
import java.util.List;

// Service simplifié, sans annotations Spring
public class ProductService {
    private ProductRepository productRepository;

    // Constructeur avec injection manuelle de dépendance
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Méthodes simplifiées
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product getProductByProductID(String productID) {
        return productRepository.findByProductID(productID);
    }

    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    public Product addProduct(Product product) {
        // Vérifier si le productID existe déjà
        Product existingProduct = productRepository.findByProductID(product.getProductID());
        if (existingProduct != null) {
            System.out.println("Erreur: ID de produit déjà existant");
            return null;
        }
        
        return productRepository.save(product);
    }

    public Product updateProduct(Product product) {
        // Vérifier si le produit existe
        Product existingProduct = productRepository.findById(product.getId());
        if (existingProduct == null) {
            System.out.println("Erreur: Produit non trouvé");
            return null;
        }
        
        return productRepository.save(product);
    }

    public boolean deleteProduct(Long id) {
        Product product = productRepository.findById(id);
        if (product != null) {
            productRepository.deleteById(id);
            return true;
        }
        System.out.println("Erreur: Impossible de supprimer, produit non trouvé");
        return false;
    }

    public boolean updateStock(Long productId, int quantity) {
        Product product = productRepository.findById(productId);
        if (product != null) {
            // Si on réduit le stock, vérifier qu'il y a assez
            if (quantity < 0 && Math.abs(quantity) > product.getStockQuantity()) {
                System.out.println("Erreur: Stock insuffisant");
                return false;
            }
            
            product.updateStock(quantity);
            productRepository.save(product);
            return true;
        }
        return false;
    }
    
    // Méthode pour afficher tous les produits (utile pour tests)
    public void displayAllProducts() {
        productRepository.displayAllProducts();
    }
}