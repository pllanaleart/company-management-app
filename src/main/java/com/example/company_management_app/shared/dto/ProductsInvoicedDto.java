package com.example.company_management_app.shared.dto;

public class ProductsInvoicedDto {
    private ProductsDto product;
    private double quantity;
    private double totalPrice;


    public ProductsDto getProduct() {
        return product;
    }

    public void setProduct(ProductsDto product) {
        this.product = product;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
