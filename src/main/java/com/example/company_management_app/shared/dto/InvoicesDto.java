package com.example.company_management_app.shared.dto;

import com.example.company_management_app.shared.InvoiceType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class InvoicesDto {
    private Long id;
    private Long invoiceNo;
    private String description;
    private BuyersDto buyer;
    private double totalPrice;
    private double priceNoTvsh;
    private LocalDateTime date;
    private LocalDate dueDate;
    private String paymentStatus;
    private InvoiceType invoiceType;
    private List<ProductsInvoicedDto> productsInvoiced;
    private CompanyDto company;

    public InvoiceType getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(InvoiceType invoiceType) {
        this.invoiceType = invoiceType;
    }

    public CompanyDto getCompany() {
        return company;
    }

    public void setCompany(CompanyDto company) {
        this.company = company;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(Long invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BuyersDto getBuyer() {
        return buyer;
    }

    public void setBuyer(BuyersDto buyer) {
        this.buyer = buyer;
    }


    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getPriceNoTvsh() {
        return priceNoTvsh;
    }

    public void setPriceNoTvsh(double priceNoTvsh) {
        this.priceNoTvsh = priceNoTvsh;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public List<ProductsInvoicedDto> getProductsInvoiced() {
        return productsInvoiced;
    }

    public void setProductsInvoiced(List<ProductsInvoicedDto> productsInvoiced) {
        this.productsInvoiced = productsInvoiced;
    }
}
