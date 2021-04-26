package com.example.amazonminiapi.controller;

import com.example.amazonminiapi.customizedResponse;
import com.example.amazonminiapi.model.Product;
import com.example.amazonminiapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "https://amazonclonebysid.herokuapp.com")
@RestController
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/products")
    public ResponseEntity getProducts(){
        System.out.println("Hello");
//        var cust = new customizedResponse("A list of all Products", service.getProducts());

        return new ResponseEntity(service.getProducts(), HttpStatus.OK);
    }

    @PostMapping(value = "/addproduct" , consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity addProduct(@RequestBody Product product){
        service.insertProducts(product);
        return  new ResponseEntity(product, HttpStatus.OK);
    }

    @GetMapping("/product-category/{category}")
    public ResponseEntity getProductsfromCat(@PathVariable String category){
//        var cust = new customizedResponse("A list of all Products by category", service.getProductsfromCat(category));
        return new ResponseEntity(service.getProductsfromCat(category), HttpStatus.OK);
    }

    @GetMapping("/products/bestsellers")
    public ResponseEntity getBestSellers(){
//        var cust = new customizedResponse("A list of all Bestsellers", service.getBestSellers());

        return new ResponseEntity(service.getBestSellers(), HttpStatus.OK);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity getProduct(@PathVariable String id){

//        var cust = new customizedResponse("A list of all Products by category", Collections.singletonList(service.getProduct(id)));
////      service.getCategories();
        Optional<Product> p = service.getProduct(id);
//        System.out.println(cust.getBody());
//        if ( {
//            System.out.println("Not found!");
//            return new ResponseEntity(HttpStatus.NO_CONTENT);
//        } else {
            return new ResponseEntity(p, HttpStatus.OK);
//        }
    }

    @PutMapping("/products/{id}")
    public ResponseEntity updateProduct(@PathVariable String id, @RequestBody Product p){
//        System.out.println("Hello");
//        var cust = new customizedResponse("Updated Product", Collections.singletonList(service.updateProduct(id, p)));
//      service.getCategories();
        List<Product> product = (List<Product>) service.updateProduct(id, p);
        return new ResponseEntity(product, HttpStatus.OK);

    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity deleteAProduct(@PathVariable String id)
    {
        service.deleteAProduct(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
