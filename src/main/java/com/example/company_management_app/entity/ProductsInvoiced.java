package com.example.company_management_app.entity;

import com.example.company_management_app.entity.keys.ProductsInvoicedPK;
import jakarta.persistence.*;

@Entity(name = "productsInvoiced")
public class ProductsInvoiced {

    @EmbeddedId
    private ProductsInvoicedPK productsInvoicedId;
    @ManyToOne
    @MapsId("invoice_id")
    @JoinColumn(name = "invoice_id")
    private InvoicesEntity invoice;
    @ManyToOne
    @MapsId("product_id")
    @JoinColumn(name = "product_id")
    private ProductsEntity product;
    private double quantity;
    private double totalPrice;

    public ProductsInvoiced() {
    }

    public ProductsInvoiced(ProductsInvoicedPK productsInvoicedId, double quantity, double totalPrice) {
        this.productsInvoicedId = productsInvoicedId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public InvoicesEntity getInvoice() {
        return invoice;
    }

    public void setInvoice(InvoicesEntity invoice) {
        this.invoice = invoice;
    }

    public ProductsEntity getProduct() {
        return product;
    }

    public void setProduct(ProductsEntity product) {
        this.product = product;
    }

    public ProductsInvoicedPK getProductsInvoicedId() {
        return productsInvoicedId;
    }

    public void setProductsInvoicedId(ProductsInvoicedPK productsInvoicedId) {
        this.productsInvoicedId = productsInvoicedId;
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
