package com.functional.demo.controller;


import com.functional.demo.dto.ProductDto;
import com.functional.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/demo/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/products")
    List<ProductDto> all(){
        productRepository.findAll();
        return List.of(new ProductDto());
    }

}
