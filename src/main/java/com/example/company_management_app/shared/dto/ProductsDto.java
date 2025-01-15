package com.example.company_management_app.shared.dto;

import com.example.company_management_app.entity.CompanyEntity;

public class ProductsDto {
    private Long id;
    private Long barcode;
    private String name;
    private String description;
    private String category;
    private CompanyDto company;
    private double price;
    private double priceNoTvsh;
    private double tvsh;
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBarcode() {
        return barcode;
    }

    public void setBarcode(Long barcode) {
        this.barcode = barcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public CompanyDto getCompany() {
        return company;
    }

    public void setCompany(CompanyDto company) {
        this.company = company;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPriceNoTvsh() {
        return priceNoTvsh;
    }

    public void setPriceNoTvsh(double priceNoTvsh) {
        this.priceNoTvsh = priceNoTvsh;
    }

    public double getTvsh() {
        return tvsh;
    }

    public void setTvsh(double tvsh) {
        this.tvsh = tvsh;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
