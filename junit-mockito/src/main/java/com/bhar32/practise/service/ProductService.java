package com.bhar32.practise.service;

import com.bhar32.practise.model.Product;
import com.bhar32.practise.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    public void addProduct(Product product) {
        productRepository.save(product);
    }

    public ResponseEntity<List<Product>> getAllProdcts() {
        List<Product> productList = new ArrayList<>();
        productRepository.findAll().forEach(productList::add);
        if(productList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }
}
