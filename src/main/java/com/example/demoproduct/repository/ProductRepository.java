package com.example.demoproduct.repository;

import com.example.demoproduct.entity.Product;
import com.example.demoproduct.service.ProductService;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ProductRepository extends MongoRepository<Product,String> {
}
