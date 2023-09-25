package com.springboot.advanced_jpa.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.advanced_jpa.data.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
}
