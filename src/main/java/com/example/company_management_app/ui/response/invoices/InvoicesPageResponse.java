package com.example.company_management_app.ui.response.invoices;

import com.example.company_management_app.shared.dto.InvoicesDto;

import java.util.List;

public class InvoicesPageResponse {
    List<InvoicesDto> invoicesDtoList;
    private int pageNo;
    private int pageSize;
    private Long totalElements;
    private int totalPages;
    private boolean isLast;

    public InvoicesPageResponse() {
    }

    public InvoicesPageResponse(List<InvoicesDto> invoicesDtoList, int pageNo, int pageSize, Long totalElements, int totalPages, boolean isLast) {
        this.invoicesDtoList = invoicesDtoList;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.isLast = isLast;
    }

    public List<InvoicesDto> getInvoicesDtoList() {
        return invoicesDtoList;
    }

    public void setInvoicesDtoList(List<InvoicesDto> invoicesDtoList) {
        this.invoicesDtoList = invoicesDtoList;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public boolean isLast() {
        return isLast;
    }

    public void setLast(boolean last) {
        isLast = last;
    }
}
