package com.example.demoproduct.service;

import com.example.demoproduct.entity.Product;
import org.springframework.stereotype.Service;

public interface ProductService {
    public  Product get(String productId);
    public  Product save(Product product);
    public  Product update(Product product);
    public void delete(String productId);
}
