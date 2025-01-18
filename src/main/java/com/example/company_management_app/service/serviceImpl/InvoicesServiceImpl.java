package com.example.company_management_app.service.serviceImpl;

import com.example.company_management_app.entity.*;
import com.example.company_management_app.entity.keys.ProductsInvoicedPK;
import com.example.company_management_app.repository.*;
import com.example.company_management_app.service.InvoicesService;
import com.example.company_management_app.shared.AppConstants;
import com.example.company_management_app.shared.dto.InvoicesDto;
import com.example.company_management_app.ui.request.invoices.InvoicesCreateRequest;
import com.example.company_management_app.ui.request.invoices.ProductInvoicedCreateRequest;
import com.example.company_management_app.ui.response.invoices.InvoicesPageResponse;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class InvoicesServiceImpl implements InvoicesService {

    private InvoicesRepository invoicesRepository;
    private ProductsInvoicedRepository productsInvoicedRepository;
    private ProductsRepository productsRepository;
    private BuyersRepository buyersRepository;
    private CompanyRepository companyRepository;
    private ModelMapper mapper = new ModelMapper();

    public InvoicesServiceImpl(InvoicesRepository invoicesRepository, ProductsInvoicedRepository productsInvoicedRepository, ProductsRepository productsRepository, BuyersRepository buyersRepository, CompanyRepository companyRepository) {
        this.invoicesRepository = invoicesRepository;
        this.productsInvoicedRepository = productsInvoicedRepository;
        this.productsRepository = productsRepository;
        this.buyersRepository = buyersRepository;
        this.companyRepository = companyRepository;
    }

    @Override
    public InvoicesPageResponse findAllByCompanyBussinessNo(Long bussinessNo, int page, int limit, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, limit, sort);
        Page<InvoicesEntity> invoicesEntityPage = invoicesRepository.findAllByCompanyBussinessNo(bussinessNo,pageable);
        List<InvoicesEntity> invoicesEntityList = invoicesEntityPage.getContent();
        List<InvoicesDto> invoicesDtoList = new ArrayList<>();
        if (invoicesEntityList.isEmpty()){throw new RuntimeException("No invoices Found");}
        for (InvoicesEntity invoice:invoicesEntityList){
            invoicesDtoList.add(mapper.map(invoice, InvoicesDto.class));
        }

        return new InvoicesPageResponse(invoicesDtoList,page,limit, invoicesEntityPage.getTotalElements(), invoicesEntityPage.getTotalPages(), invoicesEntityPage.isLast());
    }

    @Override
    public InvoicesDto createInvoice(InvoicesCreateRequest request, Long bussinessNo) {
        InvoicesEntity invoiceEntity = invoicesRepository.findByInvoiceNoAndCompanyBussinessNo(request.getInvoiceNo(),bussinessNo);
        InvoicesEntity createdInvoice = new InvoicesEntity();
        if(invoiceEntity == null){
            invoiceEntity = new InvoicesEntity();
            invoiceEntity.setInvoiceNo(request.getInvoiceNo());
            invoiceEntity.setDescription(request.getDescription());
            invoiceEntity.setPaymentStatus(request.getPaymentStatus());
            List<ProductsInvoiced> productsInvoicedList = new ArrayList<>();
            double invoiceTotalAmount = 0;
            for (ProductInvoicedCreateRequest productInvoicedCreateRequest : request.getProductInvoicedCreateRequests()){
                ProductsEntity product = productsRepository.findByIdAndCompanyBussinessNo(productInvoicedCreateRequest.getProduct_id(),bussinessNo);
                double totalPrice = product.getPrice() * productInvoicedCreateRequest.getQuantity();
                ProductsInvoiced productsInvoiced = new ProductsInvoiced();
                productsInvoiced.setQuantity(productInvoicedCreateRequest.getQuantity());
                productsInvoiced.setTotalPrice(totalPrice);
                productsInvoiced.setProduct(product);
                productsInvoicedList.add(productsInvoiced);
                invoiceTotalAmount = invoiceTotalAmount + totalPrice;
            }

            invoiceEntity.setTotalPrice(invoiceTotalAmount);
            invoiceEntity.setDate(LocalDateTime.now());
            invoiceEntity.setDueDate(LocalDate.from(LocalDateTime.now()));
            invoiceEntity.setPriceNoTvsh(invoiceTotalAmount-(invoiceTotalAmount * AppConstants.DEFAULT_TVSH/100));
            BuyersEntity buyersEntity = buyersRepository.findByIdAndCompanyBussinessNo(request.getBuyerId(), bussinessNo);
            if(buyersEntity == null){throw  new RuntimeException("Buyer not foound");}
            invoiceEntity.setBuyer(buyersEntity);
            CompanyEntity company = companyRepository.findByBussinessNo(bussinessNo);
            invoiceEntity.setCompany(company);
            createdInvoice = invoicesRepository.save(invoiceEntity);
            for (ProductsInvoiced productInvoiced :productsInvoicedList){
                productInvoiced.setInvoice(createdInvoice);
                ProductsInvoicedPK productsInvoicedPK = new ProductsInvoicedPK(createdInvoice.getId(),productInvoiced.getProduct().getId());
                productInvoiced.setProductsInvoicedId(productsInvoicedPK);
                productsInvoicedRepository.save(productInvoiced);
            }

        }else throw new RuntimeException("Invoice found");
        InvoicesEntity finalInvoice = invoicesRepository.findByIdAndCompanyBussinessNo(createdInvoice.getId(), bussinessNo);
        return mapper.map(finalInvoice, InvoicesDto.class);
    }
}
