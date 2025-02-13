package com.example.company_management_app.shared.dto;

import java.time.LocalDateTime;

public class StockDto {

    private Long id;
    private ProductsDto product;
    private String unit;
    private double quantity;
    private LocalDateTime lastUpdated;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
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

    public ProductsDto getProduct() {
        return product;
    }

    public void setProduct(ProductsDto product) {
        this.product = product;
    }


}
