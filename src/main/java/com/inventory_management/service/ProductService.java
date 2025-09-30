package com.inventory_management.service;

import com.inventory_management.entity.Product;
import com.inventory_management.exception.BadRequestException;
import com.inventory_management.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.prefs.BackingStoreException;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


    //save/create
    public Product createProduct(Product product){
        return productRepository.save(product);
    }

    //getAll
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    //getById
    public Product getProductById(Long id){
        return productRepository.findById(id).orElseThrow(()-> new RuntimeException("Product not found with Id: " + id));
    }

    //update
    public Product updateProduct(Long id, Product product){
        Product existing = productRepository.findById(id).orElseThrow(()-> new RuntimeException("Not found"));
        existing.setName(product.getName());
        existing.setDescription(product.getDescription());
        existing.setStockQuantity(product.getStockQuantity());
        return productRepository.save(existing);
    }

    //delete
    public void deleteProduct(Long id){
        Product existing = getProductById(id);
        productRepository.delete(existing);
    }

    //increase
    public Product increaseStock(Long id, long quantity){
        Product found = getProductById(id);
        found.setStockQuantity(found.getStockQuantity() + quantity);
        return productRepository.save(found);
    }

    //decrease
    public Product decreaseStock(Long id, long quantity){
        Product found = getProductById(id);
        if(found.getStockQuantity() < quantity){
            throw new BadRequestException("Insufficient stock Available: " + found.getStockQuantity());
        }else {
            found.setStockQuantity(found.getStockQuantity() - quantity);
            return productRepository.save(found);
        }
    }
}
