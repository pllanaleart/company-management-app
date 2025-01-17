package com.example.company_management_app.ui.controller;

import com.example.company_management_app.service.BuyersService;
import com.example.company_management_app.shared.AppConstants;
import com.example.company_management_app.shared.dto.BuyersDto;
import com.example.company_management_app.ui.response.buyers.BuyersPageResponse;
import com.example.company_management_app.ui.response.products.ProductsPageResponse;

import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/businessNo/{buyerBussinessNo}")
    public BuyersDto findBybussinessNo(@PathVariable Long buyerBussinessNo, @RequestParam Long bussinessNo){

        return buyersService.findByBussinessNoAndCompanyBussinessNo(buyerBussinessNo,bussinessNo);
    }
    @GetMapping("/id/{id}")
    public BuyersDto findById(@PathVariable Long id,@RequestParam Long bussinessNo){
        return buyersService.findByIdAndCompanyBussinessNo(id,bussinessNo);
    }

    @GetMapping("/name/{name}")
    public List<BuyersDto> findByName(@PathVariable String name,@RequestParam Long bussinessNo){
        return buyersService.findAllByNameContainingIgnoreCaseAndCompanyBussinessNo(name,bussinessNo);
    }

    @PostMapping
    public BuyersDto createBuyer(@RequestBody BuyersDto buyersDto,@RequestParam Long bussinessNo){
        return buyersService.createBuyer(buyersDto,bussinessNo);
    }
    @PutMapping("/{id}")
    public BuyersDto updateBuyer(@RequestBody BuyersDto buyersDto, @PathVariable Long id,@RequestParam Long bussinessNo){
        return buyersService.updateBuyer(buyersDto,id,bussinessNo);
    }
}
