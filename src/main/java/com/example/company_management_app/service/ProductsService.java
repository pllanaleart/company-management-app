package com.example.company_management_app.service;


import com.example.company_management_app.shared.dto.ProductsDto;

import java.util.List;

public interface ProductsService {

    List<ProductsDto> findAll();
    List<ProductsDto> findAllByCompany(Long bussinessNo);
}
