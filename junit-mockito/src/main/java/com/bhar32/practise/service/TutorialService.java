package com.bhar32.practise.service;

import com.bhar32.practise.model.Tutorial;
import com.bhar32.practise.repository.TutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class TutorialService {

    @Autowired(required = false)
    private TutorialRepository productRepository;
    public ResponseEntity<Tutorial> addProduct(Tutorial product) {
        productRepository.save(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    public ResponseEntity<List<Tutorial>> getAllProducts() {
        List<Tutorial> productList = new ArrayList<>();
        productRepository.findAll().forEach(productList::add);
        if(productList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }
}