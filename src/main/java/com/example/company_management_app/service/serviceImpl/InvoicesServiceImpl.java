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
import java.util.Optional;

@Service
public class InvoicesServiceImpl implements InvoicesService {

    private InvoicesRepository invoicesRepository;
    private ProductsInvoicedRepository productsInvoicedRepository;
    private ProductsRepository productsRepository;
    private BuyersRepository buyersRepository;
    private ModelMapper mapper = new ModelMapper();

    public InvoicesServiceImpl(InvoicesRepository invoicesRepository, ProductsInvoicedRepository productsInvoicedRepository, ProductsRepository productsRepository, BuyersRepository buyersRepository) {
        this.invoicesRepository = invoicesRepository;
        this.productsInvoicedRepository = productsInvoicedRepository;
        this.productsRepository = productsRepository;
        this.buyersRepository = buyersRepository;
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
        Optional<InvoicesEntity> existingInvoice = invoicesRepository.findByInvoiceNoAndCompanyBussinessNo(request.getInvoiceNo(),bussinessNo);
        if(existingInvoice.isPresent()){
            throw new RuntimeException("Invoice with number: "+request.getInvoiceNo()+ " is present");
        }
        InvoicesEntity invoiceToSave = new InvoicesEntity();
        invoiceToSave.setInvoiceNo(request.getInvoiceNo());
        invoiceToSave.setDescription(request.getDescription());
        invoiceToSave.setPaymentStatus(request.getPaymentStatus());
        invoiceToSave.setDate(LocalDateTime.now());
        invoiceToSave.setDueDate(LocalDate.from(LocalDateTime.now()));
        BuyersEntity buyersEntity = buyersRepository.findByIdAndCompanyBussinessNo(request.getBuyerId(), bussinessNo);
        if(buyersEntity==null){throw new RuntimeException("Buyer not registered!!");}
        invoiceToSave.setBuyer(buyersEntity);
        invoiceToSave.setCompany(buyersEntity.getCompany());
        List<ProductsInvoiced> productsInvoicedList = new ArrayList<>();
        double invoiceTotalAmount = 0;
        double invoiceAmountNoTvsh=0;

        for (ProductInvoicedCreateRequest productInvoicedCreateRequest : request.getProductInvoicedCreateRequests()){
                ProductsEntity product = productsRepository.findByIdAndCompanyBussinessNo(productInvoicedCreateRequest.getProduct_id(),bussinessNo);
                double totalPrice = product.getPrice() * productInvoicedCreateRequest.getQuantity();
                ProductsInvoiced productsInvoiced = new ProductsInvoiced();
                productsInvoiced.setQuantity(productInvoicedCreateRequest.getQuantity());
                productsInvoiced.setTotalPrice(totalPrice);
                productsInvoiced.setProduct(product);
                productsInvoicedList.add(productsInvoiced);
                invoiceTotalAmount = invoiceTotalAmount + totalPrice;
                totalPrice = totalPrice -(totalPrice * product.getTvsh()/100);
                invoiceAmountNoTvsh = invoiceAmountNoTvsh +totalPrice;
        }

        invoiceToSave.setTotalPrice(invoiceTotalAmount);
        invoiceToSave.setPriceNoTvsh(invoiceAmountNoTvsh);
        invoiceToSave = invoicesRepository.save(invoiceToSave);
            for (ProductsInvoiced productInvoiced :productsInvoicedList){
                productInvoiced.setInvoice(invoiceToSave);
                ProductsInvoicedPK productsInvoicedPK = new ProductsInvoicedPK(invoiceToSave.getId(),productInvoiced.getProduct().getId());
                productInvoiced.setProductsInvoicedId(productsInvoicedPK);
                productsInvoicedRepository.save(productInvoiced);
            }
        invoiceToSave.setProductsInvoiced(productsInvoicedList);
        return mapper.map(invoiceToSave, InvoicesDto.class);
    }
}
