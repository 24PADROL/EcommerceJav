package com.example.demo.services;

import com.example.demo.models.Product;
import com.example.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Optional<Product> getProductByProductID(String productID) {
        return productRepository.findByProductID(productID);
    }

    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    public Product addProduct(Product product) {
        // Vérifier si le productID existe déjà
        Optional<Product> existingProduct = productRepository.findByProductID(product.getProductID());
        if (existingProduct.isPresent()) {
            throw new IllegalArgumentException("Product ID already exists");
        }
        
        return productRepository.save(product);
    }

    public Product updateProduct(Product product) {
        // Vérifier si le produit existe
        if (!productRepository.findById(product.getId()).isPresent()) {
            throw new IllegalArgumentException("Product not found");
        }
        
        return productRepository.save(product);
    }

    public boolean deleteProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean updateStock(Long productId, int quantity) {
        Optional<Product> productOpt = productRepository.findById(productId);
        if (productOpt.isPresent()) {
            Product product = productOpt.get();
            
            // Vérifier si le stock disponible est suffisant (si on réduit le stock)
            if (quantity < 0 && Math.abs(quantity) > product.getStockQuantity()) {
                return false;
            }
            
            product.updateStock(quantity);
            productRepository.save(product);
            return true;
        }
        return false;
    }
}