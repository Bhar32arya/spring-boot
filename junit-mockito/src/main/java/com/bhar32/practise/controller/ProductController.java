package com.bhar32.practise.controller;

import com.bhar32.practise.model.Product;
import com.bhar32.practise.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired(required = false)
    private ProductService productService;

    @PostMapping("/addProduct")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        log.info("addProduct method called ...");
        Product _product  = new Product(product.getId(), product.getName(), product.getPrice());
        return productService.addProduct(_product);
    }

    @GetMapping("/getAllProducts")
    public ResponseEntity<List<Product>> getProducts() {
        log.info("getProducts method called ...");
        return productService.getAllProducts();
    }
}
