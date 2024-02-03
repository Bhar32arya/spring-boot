package com.bhar32.practise.model;

import lombok.Data;
import java.io.Serializable;

@Data
public class Product implements Serializable {
    private int id;
    private String name;
    private double price;

    public Product() {
    }

    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
