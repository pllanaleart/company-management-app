package com.example.company_management_app.entity.keys;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProductsInvoicedPK implements Serializable {

    public ProductsInvoicedPK() {
    }

    public ProductsInvoicedPK(Long invoice_id, Long product_id) {
        this.invoice_id = invoice_id;
        this.product_id = product_id;
    }

    private Long invoice_id;
    private Long product_id;

    public Long getInvoice_id() {
        return invoice_id;
    }

    public void setInvoice_id(Long invoice_id) {
        this.invoice_id = invoice_id;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ProductsInvoicedPK that = (ProductsInvoicedPK) o;
        return Objects.equals(invoice_id, that.invoice_id) && Objects.equals(product_id, that.product_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invoice_id, product_id);
    }
}

