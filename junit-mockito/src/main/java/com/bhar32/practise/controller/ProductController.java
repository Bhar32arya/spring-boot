package com.bhar32.practise.controller;

import com.bhar32.practise.model.Product;
import com.bhar32.practise.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/addProduct")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product _product  = new Product(product.getId(), product.getName(), product.getPrice());
        return productService.addProduct(_product);
    }

    @GetMapping("/getAllProducts")
    public ResponseEntity<List<Product>> getProducts() {
        return productService.getAllProducts();
    }
}
