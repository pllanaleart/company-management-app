package com.example.company_management_app.ui.request.invoices;

import com.example.company_management_app.entity.keys.ProductsInvoicedPK;

public class ProductInvoicedCreateRequest {

    private Long invoice_id;
    private Long product_id;
    private double quantity;

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

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
}
