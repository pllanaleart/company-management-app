package com.example.company_management_app.ui.controller;

import com.example.company_management_app.service.InvoicesService;
import com.example.company_management_app.shared.AppConstants;
import com.example.company_management_app.shared.InvoiceType;
import com.example.company_management_app.shared.dto.InvoicesDto;
import com.example.company_management_app.ui.request.invoices.InvoicesCreateRequest;
import com.example.company_management_app.ui.response.invoices.InvoicesPageResponse;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("invoices")
public class InvoicesController {

    private InvoicesService invoicesService;

    public InvoicesController(InvoicesService invoicesService) {
        this.invoicesService = invoicesService;
    }

    @GetMapping
    public InvoicesPageResponse findAllByCompanyBussinessNo(@RequestParam Long bussinessNo,
                                                            @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NO) int page,
                                                            @RequestParam(value = "limit", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int limit,
                                                            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY) String sortBy,
                                                            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIR) String sortDir,
                                                            @RequestParam(value = "type", defaultValue ="SELL")String type
                                                            ) {
        return invoicesService.findAllByCompanyBussinessNo(bussinessNo, page, limit, sortBy, sortDir,type);
    }

    @PostMapping
    public InvoicesDto createInvoice(@RequestBody InvoicesCreateRequest createRequest, @RequestParam Long bussinessNo) {

        return invoicesService.createInvoice(createRequest, bussinessNo);
    }
}
