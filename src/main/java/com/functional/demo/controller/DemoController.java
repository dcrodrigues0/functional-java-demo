package com.functional.demo.controller;


import com.functional.demo.dto.OrderDto;
import com.functional.demo.dto.ProductDto;
import com.functional.demo.entity.Order;
import com.functional.demo.entity.Product;
import com.functional.demo.repository.OrderRepository;
import com.functional.demo.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/demo")
public class DemoController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    //Obtain a list of products belongs to category “Books” with price > 100
    @GetMapping("/ex1")
    public List<ProductDto> ex1(){
        ModelMapper mapper = new ModelMapper();

        return (productRepository.findAll()
                .stream()
                .filter(product -> product.getCategory().equals("Books") && product.getPrice() > 100)
                .map(product -> mapper.map(product,ProductDto.class))
                .collect(Collectors.toList()));

    }

    //Obtain a list of order with products belong to category “Baby”
    @GetMapping("/ex2")
    public List<OrderDto> ex2(){
        ModelMapper mapper = new ModelMapper();

        return (orderRepository.findAll()
                .stream()
                .filter(order -> order.getProducts().stream().anyMatch(product -> product.getCategory().equals("Baby")))
                .map(order -> mapper.map(order, OrderDto.class))
                .collect(Collectors.toList()));

    }

    //Obtain a list of product with category = “Toys” and then apply 10% discount
    @GetMapping("/ex3")
    public List<ProductDto> ex3(){
        ModelMapper mapper = new ModelMapper();

        return (productRepository.findAll()
                .stream()
                .filter(product -> product.getCategory().equals("Toys"))
                .map(product -> {
                    product.setPrice(product.getPrice() * 0.90);
                    return product;
                })
                .map(product -> mapper.map(product,ProductDto.class))
                .collect(Collectors.toList()));

    }

    //Obtain a list of products ordered by customer of tier 2 between 01-Feb-2021 and 01-Apr-2021
    @GetMapping("/ex4")
    public List<ProductDto> ex4(){
        ModelMapper mapper = new ModelMapper();

        return (productRepository.findAll()
                .stream()
                .sorted()
                .map(product -> mapper.map(product,ProductDto.class))
                .collect(Collectors.toList()));

    }

}
