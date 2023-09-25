package com.springboot.advanced_jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.advanced_jpa.data.dto.ChangeProductNameDto;
import com.springboot.advanced_jpa.data.dto.ProductDto;
import com.springboot.advanced_jpa.data.dto.ProductResponseDto;
import com.springboot.advanced_jpa.service.Productservice;






@RestController
@RequestMapping("/product")
public class ProductController {
    private final Productservice productservice;

    @Autowired
    public ProductController(Productservice productservice) {
        this.productservice = productservice;
    }

    @GetMapping()
    public ResponseEntity<ProductResponseDto> getProduct (Long number) {
        ProductResponseDto productResponseDto = productservice.getProduct(number);

        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }

    @PostMapping()
    public ResponseEntity<ProductResponseDto> createProduct (@RequestBody ProductDto productDto) {
        ProductResponseDto productResponseDto = productservice.saveProduct(productDto);
        
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }
    
    @PutMapping()
    public ResponseEntity<ProductResponseDto> changeProductName (@RequestBody ChangeProductNameDto changeProductNameDto) throws Exception {
        ProductResponseDto productResponseDto = productservice.changeProductName(
            changeProductNameDto.getNumber(), 
            changeProductNameDto.getName());

        
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }
    
    @DeleteMapping()
    public ResponseEntity<String> deleteProduct(Long number) throws Exception {
        productservice.deleteProduct(number);

        return ResponseEntity.status(HttpStatus.OK).body("정상적으로 삭제되었습니다.");
    }
}