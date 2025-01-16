package com.example.company_management_app.ui.controller;

import com.example.company_management_app.service.CompanyService;
import com.example.company_management_app.service.ProductsService;
import com.example.company_management_app.shared.AppConstants;
import com.example.company_management_app.shared.dto.CompanyDto;
import com.example.company_management_app.shared.dto.ProductsDto;
import com.example.company_management_app.ui.response.ProductsPageResponse;
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
    public ProductsPageResponse findAllByCompany(@RequestParam Long bussinessNo,
                                                 @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NO) int page,
                                                 @RequestParam(value = "limit", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int limit,
                                                 @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY) String sortBy,
                                                 @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIR) String sortDir) {
        ProductsPageResponse productsResponse = productsService.findAllByCompanyBussinessNo(bussinessNo, page, limit, sortBy, sortDir);
        return productsResponse;
    }

    @GetMapping("/{name}")
    public List<ProductsDto> findAllByNameAndCompany(@PathVariable String name, @RequestParam Long bussinessNo) {
        List<ProductsDto> productsDtos = productsService.findAllByNameAndCompanyBussinessNo(name, bussinessNo);
        if (productsDtos.isEmpty()) throw new RuntimeException("Not found!!!");

        return productsDtos;
    }

    @GetMapping("/i/{id}")
    public ProductsDto findById(@PathVariable Long id, @RequestParam Long bussinessNo) {
        ProductsDto productsDto = productsService.findByIdAndCompanyBussinessNo(id, bussinessNo);
        if (productsDto == null) throw new RuntimeException("Not found!!!");
        return productsDto;
    }

    @GetMapping("/id/{barcode}")
    public ProductsDto findByBarcode(@PathVariable Long barcode, @RequestParam Long bussinessNo) {
        ProductsDto product = productsService.findByBarcodeAndCompanyBussinessNo(barcode, bussinessNo);
        if (product == null) throw new RuntimeException("Not Found!!!");

        return product;
    }

    @PostMapping
    public ProductsDto createProduct(@RequestBody ProductsDto productsDto, @RequestParam Long bussinessNo) {
        CompanyDto companyDto = companyService.findByBussinessNo(bussinessNo);
        if (companyDto == null) {
            throw new RuntimeException("Company not found!!");
        }
        productsDto.setCompany(companyDto);
        ProductsDto createdProduct = productsService.createProduct(productsDto);
        if (createdProduct == null) throw new RuntimeException("Cannot create product!!");
        return createdProduct;
    }

    @PutMapping("/{id}")
    public ProductsDto updateProduct(@PathVariable Long id, @RequestBody ProductsDto product, @RequestParam Long bussinessNo) {

        ProductsDto productsDto = productsService.updateProduct(id, product, bussinessNo);
        if (productsDto == null) throw new RuntimeException("Not updated");
        return productsDto;
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id, @RequestParam Long bussinessNo) {

        String message = productsService.deleteProduct(id, bussinessNo);

        return message;
    }
}
