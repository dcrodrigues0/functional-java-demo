package com.functional.demo.dto;

import com.functional.demo.entity.Order;

import java.util.Set;

public class ProductDto {

    private String name;
    private String category;
    private Double price;
    private Set<Order> orders;

}
