package com.example.company_management_app.ui.request.invoices;

import com.example.company_management_app.shared.InvoiceType;

import java.util.List;

public class InvoicesCreateRequest {

    private Long invoiceNo;
    private String description;
    private Long buyerId;
    private String paymentStatus;
    private InvoiceType invoiceType;
    private List<ProductInvoicedCreateRequest> productInvoicedCreateRequests;

    public InvoiceType getInvoiceType() {
        return invoiceType;
    }

    public Long getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceType(InvoiceType invoiceType) {
        this.invoiceType = invoiceType;
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

    public Long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public List<ProductInvoicedCreateRequest> getProductInvoicedCreateRequests() {
        return productInvoicedCreateRequests;
    }

    public void setProductInvoicedCreateRequests(List<ProductInvoicedCreateRequest> productInvoicedCreateRequests) {
        this.productInvoicedCreateRequests = productInvoicedCreateRequests;
    }
}
