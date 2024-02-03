package com.bhar32.practise.service;

import com.bhar32.practise.model.Product;
import com.bhar32.practise.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    public void addProduct(Product product) {
        productRepository.save(product);
    }
}
