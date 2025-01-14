package com.example.company_management_app.entity;

import jakarta.persistence.*;

@Entity(name = "products")
public class ProductsEntity {

    @Id
    private Long barcode;
    private String name;
    private String description;
    private String category;
    @ManyToOne
    @JoinColumn(name = "companyNo")
    private CompanyEntity company;
    private double price;
    private double priceNoTvsh;
    private double tvsh;
    private String status;

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

    public CompanyEntity getCompany() {
        return company;
    }

    public void setCompany(CompanyEntity company) {
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
