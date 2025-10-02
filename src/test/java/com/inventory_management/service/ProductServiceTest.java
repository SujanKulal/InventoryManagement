package com.inventory_management.service;

import com.inventory_management.entity.Product;
import com.inventory_management.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);;
    }

    @Test
    void testIncreaseStock(){
        Product product = new Product();
        product.setId(1L);
        product.setName("Laptop");
        product.setDescription("Test Laptop");
        product.setStockQuantity(10L);

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productRepository.save(any(Product.class))).thenAnswer(i->i.getArguments()[0]);

        Product updated = productService.increaseStock(1L,5);

        assertEquals(15, updated.getStockQuantity());
    }

    @Test
    void tesDecreaseStock(){
        Product product = new Product();
        product.setId(1L);
        product.setName("Laptop");
        product.setDescription("Test Laptop");
        product.setStockQuantity(10L);

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productRepository.save(any(Product.class))).thenAnswer(i->i.getArguments()[0]);

        Product updated = productService.decreaseStock(1L,5);

        assertEquals(5, updated.getStockQuantity());
    }
}