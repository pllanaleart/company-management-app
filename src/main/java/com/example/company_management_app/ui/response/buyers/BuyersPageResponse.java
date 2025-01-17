package com.example.company_management_app.ui.response.buyers;

import com.example.company_management_app.shared.dto.BuyersDto;

import java.util.List;

public class BuyersPageResponse {

    private List<BuyersDto> buyersDto;
    private int pageNo;
    private int pageSize;
    private Long totalElements;
    private int totalPages;
    private boolean isLast;

    public BuyersPageResponse() {
    }

    public BuyersPageResponse(List<BuyersDto> buyersDto, int pageNo, int pageSize, Long totalElements, int totalPages, boolean isLast) {
        this.buyersDto = buyersDto;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.isLast = isLast;
    }

    public List<BuyersDto> getBuyersDto() {
        return buyersDto;
    }

    public void setBuyersDto(List<BuyersDto> buyersDto) {
        this.buyersDto = buyersDto;
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
