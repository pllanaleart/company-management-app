package com.example.company_management_app.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity(name = "stock")
public class StockEntity {

    @Id
    @OneToOne
    @JoinColumn(name = "productId", referencedColumnName = "id")
    private ProductsEntity product;
    private double unit;
    private double quantity;
    private LocalDateTime lastUpdated;
    @ManyToOne
    @JoinColumn(name = "companyNo")
    private CompanyEntity company;

    public ProductsEntity getProduct() {
        return product;
    }

    public void setProduct(ProductsEntity product) {
        this.product = product;
    }

    public double getUnit() {
        return unit;
    }

    public void setUnit(double unit) {
        this.unit = unit;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public CompanyEntity getCompany() {
        return company;
    }

    public void setCompany(CompanyEntity company) {
        this.company = company;
    }
}
