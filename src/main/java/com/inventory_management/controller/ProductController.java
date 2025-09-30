package com.inventory_management.controller;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.inventory_management.entity.Product;
import com.inventory_management.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    //getAll
    @GetMapping()
    ResponseEntity<List<Product>> getAllProducts(){
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    //getById
    @GetMapping("/{id}")
    ResponseEntity<Product> getProductById(@PathVariable Long id){
        return new ResponseEntity<>(productService.getProductById(id),HttpStatus.OK);
    }

    //update
    @PutMapping("/{id}")
    ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product){
        return new ResponseEntity<>(productService.updateProduct(id,product),HttpStatus.OK);
    }

    //create
    @PostMapping()
    ResponseEntity<Product> createProduct(@RequestBody Product product){
        return new ResponseEntity<>(productService.createProduct(product),HttpStatus.CREATED);
    }

    //delete
    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return new ResponseEntity<>("Deleted Successfully",HttpStatus.OK);
    }

    @PatchMapping("/{id}/increase")
    ResponseEntity<Product> increaseStock(@PathVariable Long id, @RequestBody Map<String,Long> request){
        long quantity = request.get("quantity");
        return new ResponseEntity<>(productService.increaseStock(id,quantity),HttpStatus.OK);
    }

    @PatchMapping("/{id}/decrease")
    ResponseEntity<Product> decreaseStock(@PathVariable Long id, @RequestBody Map<String,Long> request){
        long quantity = request.get("quantity");
        return new ResponseEntity<>(productService.decreaseStock(id,quantity), HttpStatus.OK);
    }
}
