package com.example.demoproduct.service.impl;

import com.example.demoproduct.entity.Product;
import com.example.demoproduct.repository.ProductRepository;
import com.example.demoproduct.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public Product get(String productId) {
        return productRepository.findById(productId).get();
    }
    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product update(Product product) {

        return productRepository.save(product);
    }

    @Override
    public void delete(String productId) {
        productRepository.deleteById(productId);
    }
}
