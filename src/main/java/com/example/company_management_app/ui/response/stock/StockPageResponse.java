package com.example.company_management_app.ui.response.stock;

import com.example.company_management_app.shared.dto.StockDto;

import java.util.List;

public class StockPageResponse {
    private List<StockDto> productsDtos;
    private int pageNo;
    private int pageSize;
    private Long totalElements;
    private int totalPages;
    private boolean isLast;

    public StockPageResponse() {
    }

    public StockPageResponse(List<StockDto> productsDtos, int pageNo, int pageSize, Long totalElements, int totalPages, boolean isLast) {
        this.productsDtos = productsDtos;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.isLast = isLast;
    }

    public List<StockDto> getProductsDtos() {
        return productsDtos;
    }

    public void setProductsDtos(List<StockDto> productsDtos) {
        this.productsDtos = productsDtos;
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
