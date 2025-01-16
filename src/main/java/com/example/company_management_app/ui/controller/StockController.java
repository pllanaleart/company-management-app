package com.example.company_management_app.ui.controller;

import com.example.company_management_app.service.StockService;
import com.example.company_management_app.shared.AppConstants;
import com.example.company_management_app.shared.dto.StockDto;
import com.example.company_management_app.ui.request.stock.StockCreateRequest;
import com.example.company_management_app.ui.response.stock.StockPageResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("stock")
public class StockController {

    private StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping
    public StockPageResponse findAllByCompanyBussinessNo(@RequestParam Long bussinessNo,
                                                         @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NO) int page,
                                                         @RequestParam(value = "limit", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int limit,
                                                         @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY) String sortBy,
                                                         @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIR) String sortDir)
    {

        StockPageResponse response = stockService.findAllByCompanyBussinessNo(bussinessNo,page,limit,sortBy,sortDir);
        return response;
    }
    @GetMapping("/{name}")
    public List<StockDto> findAllByProductName(@PathVariable String name,@RequestParam Long bussinessNo){
        List<StockDto> stockDtoList = stockService.findAllByProductNameContainingAndCompanyBussinessNo(name,bussinessNo);

        return stockDtoList;
    }
    @GetMapping("/bar/{barcode}")
    public StockDto findByProductBarcode(@PathVariable Long barcode, @RequestParam Long bussinessNo){

        StockDto stockDto = stockService.findByProductBarcodeAndCompanyBussinessNo(barcode,bussinessNo);

        return stockDto;
    }
    @GetMapping("/id/{id}")
    public StockDto findById(@PathVariable Long id,@RequestParam Long bussinessNo){
        return stockService.findByIdAndCompanyBussinessNo(id,bussinessNo);
    }
    @PostMapping
    public StockDto createStock(@RequestBody StockCreateRequest createRequest, @RequestParam Long bussinessNo){
        return  stockService.createStock(createRequest,bussinessNo);
    }
    @PutMapping("/{id}")
    public StockDto updateStock(@RequestBody StockCreateRequest stockCreateRequest,@PathVariable Long id,@RequestParam Long bussinessNo){

        return  stockService.updateStock(stockCreateRequest,id,bussinessNo);
    }
}
