package com.example.company_management_app.service;

import com.example.company_management_app.shared.dto.StockDto;
import com.example.company_management_app.ui.request.stock.StockCreateRequest;
import com.example.company_management_app.ui.response.stock.StockPageResponse;

import java.util.List;

public interface StockService {
    StockPageResponse findAllByCompanyBussinessNo(Long bussinessNo, int page, int limit, String sortBy, String sortDir);
    List<StockDto> findAllByProductNameContainingAndCompanyBussinessNo(String name,Long bussinessNo);
    StockDto findByProductBarcodeAndCompanyBussinessNo(Long barcode,Long bussinessNo);
    StockDto findByIdAndCompanyBussinessNo(Long id, Long bussinessNo);
    StockDto createStock(StockCreateRequest stock, Long bussinessNo);
    StockDto updateStock(StockCreateRequest stockCreateRequest,Long id, Long bussinessNo);

}
