package com.example.company_management_app.service;


import com.example.company_management_app.shared.dto.ProductsDto;

import java.util.List;

public interface ProductsService {

    List<ProductsDto> findAllByCompanyBussinessNo(Long bussinessNo);
    List<ProductsDto> findAllByNameAndCompanyBussinessNo(String name,Long bussinessNo);
    ProductsDto createProduct(ProductsDto productsDto);
    ProductsDto updateProduct(Long barcode, ProductsDto product,Long bussinessNo);
    ProductsDto findByBarcodeAndCompanyBussinessNo(Long barcode,Long bussinessNo);
    ProductsDto findByIdAndCompanyBussinessNo(Long id,Long bussinessNo);
    String deleteProduct(Long barcode,Long bussinessNo);
}
