package com.example.company_management_app.ui.controller;

import com.example.company_management_app.service.CompanyService;
import com.example.company_management_app.service.ProductsService;
import com.example.company_management_app.shared.dto.CompanyDto;
import com.example.company_management_app.shared.dto.ProductsDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductsController {
    private ProductsService productsService;
    private CompanyService companyService;

    public ProductsController(ProductsService productsService, CompanyService companyService) {
        this.productsService = productsService;
        this.companyService = companyService;
    }

    @GetMapping
    public List<ProductsDto> findAllByCompany(@RequestParam Long bussinessNo){
        List<ProductsDto> productsDtos = productsService.findAllByCompanyBussinessNo(bussinessNo);
        return productsDtos;
    }
    @GetMapping("/{barcode}")
    public ProductsDto findByBarcode(@PathVariable Long barcode, @RequestParam Long bussinessNo){
        ProductsDto product = productsService.findByBarcodeAndCompanyBussinessNo(barcode,bussinessNo);
        if (product == null)throw new RuntimeException("Not Found!!!");

        return  product;
    }
    @PostMapping
    public ProductsDto createProduct(@RequestBody ProductsDto productsDto,@RequestParam Long bussinessNo){
        CompanyDto companyDto= companyService.findByBussinessNo(bussinessNo);
        if(companyDto == null){throw new RuntimeException("Company not found!!");}
        productsDto.setCompany(companyDto);
        ProductsDto createdProduct = productsService.createProduct(productsDto);
        if(createdProduct == null)throw new RuntimeException("Cannot create product!!");
        return createdProduct;
    }
    @PutMapping("/{barcode}")
    public ProductsDto updateProduct(@PathVariable Long barcode,@RequestBody ProductsDto product,@RequestParam Long bussinessNo){

        ProductsDto productsDto = productsService.updateProduct(barcode,product,bussinessNo);
        if (productsDto == null)throw new RuntimeException("Not updated");
        return productsDto;
    }
}
