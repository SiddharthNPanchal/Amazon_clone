package com.example.amazonminiapi.service;

import com.example.amazonminiapi.model.Product;
import com.example.amazonminiapi.model.ProductRepository;
import com.example.amazonminiapi.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repo;

    public List<Product> getProducts(){
        return repo.findAll();
    }
    public void insertProducts(Product product){
        repo.insert(product);
    }

    public List<Product> getProductsfromCat(String category){
        List<Product> products = repo.findBycategory(category);
        return products;
    }

    public List<Product> getBestSellers(){

        List<Product> products = repo.findBybestSeller(true);
        return products;
    }

    public Optional<Product> getProduct(String id)
    {
        return repo.findById(id);
    }

    public Product updateProduct(String id, Product p){
        Optional<Product> ProductData = repo.findById(id);
        if (ProductData.isPresent()) {
            Product setData = ProductData.get();
            setData.setPrice(p.getPrice());
            setData.setQty(p.getQty());
            return repo.save(setData);
        }
        else{
            return null;
        }
    }

    public void deleteAProduct(String id)
    {
        repo.deleteById(id);
    }

}
