package com.example.company_management_app.service;

import com.example.company_management_app.entity.BuyersEntity;
import com.example.company_management_app.shared.dto.BuyersDto;
import com.example.company_management_app.ui.response.buyers.BuyersPageResponse;

import java.util.List;

public interface BuyersService {

    BuyersPageResponse findAllByCompanyBussinessNo(Long bussinessNo, int page, int limit, String sortBy, String sortDir);
    List<BuyersDto> findAllByNameContainingIgnoreCaseAndCompanyBussinessNo(String name, Long bussinessNo);
    BuyersDto findByBussinessNoAndCompanyBussinessNo(Long bussinessNo,Long companyBussinessNo);
    BuyersDto findByIdAndCompanyBussinessNo(Long id,Long bussinessNo);
    BuyersDto createBuyer(BuyersDto buyersDto,Long bussinessNo);
    BuyersDto updateBuyer(BuyersDto buyersDto,Long id,Long bussinessNo);
}
