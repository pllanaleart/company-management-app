package com.example.company_management_app.entity;

import com.example.company_management_app.shared.InvoiceType;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "invoices")
public class InvoicesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long invoiceNo;
    private String description;
    @ManyToOne
    @JoinColumn(name = "buyerId")
    private BuyersEntity buyer;
    private double totalPrice;
    private double priceNoTvsh;
    private LocalDateTime date;
    private LocalDate dueDate;
    private String paymentStatus;
    private InvoiceType invoiceType;
    @OneToMany(mappedBy = "invoice")
    private List<ProductsInvoiced> productsInvoiced;
    @ManyToOne
    @JoinColumn(name = "companyNo")
    private CompanyEntity company;

    public Long getId() {
        return id;
    }

    public CompanyEntity getCompany() {
        return company;
    }

    public InvoiceType getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(InvoiceType invoiceType) {
        this.invoiceType = invoiceType;
    }

    public void setCompany(CompanyEntity company) {
        this.company = company;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ProductsInvoiced> getProductsInvoiced() {
        return productsInvoiced;
    }

    public void setProductsInvoiced(List<ProductsInvoiced> productsInvoiced) {
        this.productsInvoiced = productsInvoiced;
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

    public BuyersEntity getBuyer() {
        return buyer;
    }

    public void setBuyer(BuyersEntity buyer) {
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
}
