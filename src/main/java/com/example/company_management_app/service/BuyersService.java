package com.example.company_management_app.service;

import com.example.company_management_app.ui.response.buyers.BuyersPageResponse;

public interface BuyersService {

    BuyersPageResponse findAllByCompanyBussinessNo(Long bussinessNo, int page, int limit, String sortBy, String sortDir);
}
