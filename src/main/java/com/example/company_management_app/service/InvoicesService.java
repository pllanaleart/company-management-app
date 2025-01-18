package com.example.company_management_app.service;

import com.example.company_management_app.shared.dto.InvoicesDto;
import com.example.company_management_app.ui.request.invoices.InvoicesCreateRequest;
import com.example.company_management_app.ui.response.invoices.InvoicesPageResponse;

public interface InvoicesService {
    InvoicesPageResponse findAllByCompanyBussinessNo(Long bussinessNo, int page, int limit, String sortBy, String sortDir);

    InvoicesDto createInvoice(InvoicesCreateRequest request, Long bussinessNo);
}
