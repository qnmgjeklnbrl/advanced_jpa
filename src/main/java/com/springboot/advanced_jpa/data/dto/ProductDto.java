package com.springboot.advanced_jpa.data.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDto {
    private String name;
    private int price;
    private int stock;
    

    public ProductDto(String name, int price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public ProductDto() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return this.stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

}
