package com.example.company_management_app.ui.controller;

import com.example.company_management_app.service.ProductsService;
import com.example.company_management_app.shared.dto.ProductsDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductsController {
    private ProductsService productsService;

    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }


    @GetMapping
    public List<ProductsDto> findAllByCompany(@RequestParam Long bussinessNo){
        List<ProductsDto> productsDtos = productsService.findAllByCompanyBussinessNo(bussinessNo);
        return productsDtos;
    }
}
