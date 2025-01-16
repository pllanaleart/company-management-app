package com.example.company_management_app.service;


import com.example.company_management_app.shared.dto.ProductsDto;
import com.example.company_management_app.ui.response.products.ProductsPageResponse;

import java.util.List;

public interface ProductsService {

    ProductsPageResponse findAllByCompanyBussinessNo(Long bussinessNo, int page, int limit, String sortBy, String sortDir);

    List<ProductsDto> findAllByNameAndCompanyBussinessNo(String name, Long bussinessNo);

    ProductsDto createProduct(ProductsDto productsDto, Long bussinessNo);

    ProductsDto updateProduct(Long barcode, ProductsDto product, Long bussinessNo);

    ProductsDto findByBarcodeAndCompanyBussinessNo(Long barcode, Long bussinessNo);

    ProductsDto findByIdAndCompanyBussinessNo(Long id, Long bussinessNo);

    String deleteProduct(Long barcode, Long bussinessNo);
}
