package com.bhar32.practise.controller;

import com.bhar32.practise.model.ApiResponse;
import com.bhar32.practise.model.Product;
import com.bhar32.practise.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/addProduct")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody Product product) {
        productService.addProduct(product);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}