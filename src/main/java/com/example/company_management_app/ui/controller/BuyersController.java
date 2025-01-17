package com.example.company_management_app.ui.controller;

import com.example.company_management_app.service.BuyersService;
import com.example.company_management_app.shared.AppConstants;
import com.example.company_management_app.ui.response.buyers.BuyersPageResponse;
import com.example.company_management_app.ui.response.products.ProductsPageResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("buyers")
public class BuyersController {

    private BuyersService buyersService;

    public BuyersController(BuyersService buyersService) {
        this.buyersService = buyersService;
    }

    @GetMapping
    public BuyersPageResponse findAllByCompany(@RequestParam Long bussinessNo,
                                                 @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NO) int page,
                                                 @RequestParam(value = "limit", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int limit,
                                                 @RequestParam(value = "sortBy", defaultValue = "bussinessNo") String sortBy,
                                                 @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIR) String sortDir) {
        BuyersPageResponse buyersPageResponse = buyersService.findAllByCompanyBussinessNo(bussinessNo, page, limit, sortBy, sortDir);
        return buyersPageResponse;
    }
}
