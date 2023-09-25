package com.springboot.advanced_jpa.data.dto;

public class ChangeProductNameDto {
    private Long number;
    private String name;

    public ChangeProductNameDto() {
    }


    public ChangeProductNameDto(Long number, String name) {
        this.number = number;
        this.name = name;
    }

    public Long getNumber() {
        return this.number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


}