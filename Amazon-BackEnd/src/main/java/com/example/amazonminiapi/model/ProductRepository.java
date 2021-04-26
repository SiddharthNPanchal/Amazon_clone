package com.example.amazonminiapi.model;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository <Product,String> {
    List<Product> findBycategory(String category);
    List<Product> findBybestSeller(Boolean bestSeller);
}
