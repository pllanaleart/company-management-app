package com.example.company_management_app.service.serviceImpl;

import com.example.company_management_app.entity.*;
import com.example.company_management_app.entity.keys.ProductsInvoicedPK;
import com.example.company_management_app.repository.*;
import com.example.company_management_app.service.InvoicesService;
import com.example.company_management_app.shared.InvoiceType;
import com.example.company_management_app.shared.dto.InvoicesDto;
import com.example.company_management_app.ui.request.invoices.InvoicesCreateRequest;
import com.example.company_management_app.ui.request.invoices.ProductInvoicedCreateRequest;
import com.example.company_management_app.ui.response.invoices.InvoicesPageResponse;
import jakarta.transaction.Transactional;
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
    private StockRepository stockRepository;
    private SellingsRepository sellingsRepository;
    private ModelMapper mapper = new ModelMapper();

    public InvoicesServiceImpl(InvoicesRepository invoicesRepository, ProductsInvoicedRepository productsInvoicedRepository, ProductsRepository productsRepository, BuyersRepository buyersRepository, StockRepository stockRepository, SellingsRepository sellingsRepository) {
        this.invoicesRepository = invoicesRepository;
        this.productsInvoicedRepository = productsInvoicedRepository;
        this.productsRepository = productsRepository;
        this.buyersRepository = buyersRepository;
        this.stockRepository = stockRepository;
        this.sellingsRepository = sellingsRepository;
    }

    @Override
    public InvoicesPageResponse findAllByCompanyBussinessNo(Long bussinessNo, int page, int limit, String sortBy, String sortDir,String type) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, limit, sort);
        List<InvoicesDto> invoicesDtoList = new ArrayList<>();
        InvoicesPageResponse invoicesPageResponse = new InvoicesPageResponse();
        if(type.equalsIgnoreCase("SELL")){
            Page<InvoicesEntity> invoicesEntityPageSell = invoicesRepository.findAllByCompanyBussinessNoAndInvoiceType(bussinessNo,pageable,InvoiceType.SELL);
            List<InvoicesEntity> invoicesEntityListSell = invoicesEntityPageSell.getContent();
            if (invoicesEntityListSell.isEmpty()) {
                throw new RuntimeException("No invoices Found");
            }
            for (InvoicesEntity invoice : invoicesEntityListSell) {
                invoicesDtoList.add(mapper.map(invoice, InvoicesDto.class));
            }
            invoicesPageResponse = new InvoicesPageResponse(invoicesDtoList,page,limit,invoicesEntityPageSell.getTotalElements(), invoicesEntityPageSell.getTotalPages(), invoicesEntityPageSell.isLast());
        } else if (type.equalsIgnoreCase("BUY")) {
            Page<InvoicesEntity> invoicesEntityPageBuy = invoicesRepository.findAllByCompanyBussinessNoAndInvoiceType(bussinessNo,pageable,InvoiceType.BUY);
            List<InvoicesEntity> invoicesEntityListBuy = invoicesEntityPageBuy.getContent();
            if (invoicesEntityListBuy.isEmpty()) {
                throw new RuntimeException("No invoices Found");
            }
            for (InvoicesEntity invoice : invoicesEntityListBuy) {
                invoicesDtoList.add(mapper.map(invoice, InvoicesDto.class));
            }
            invoicesPageResponse = new InvoicesPageResponse(invoicesDtoList,page,limit,invoicesEntityPageBuy.getTotalElements(), invoicesEntityPageBuy.getTotalPages(), invoicesEntityPageBuy.isLast());

        }else {
            Page<InvoicesEntity> invoicesEntityPage = invoicesRepository.findAllByCompanyBussinessNo(bussinessNo, pageable);
            List<InvoicesEntity> invoicesEntityList = invoicesEntityPage.getContent();

            if (invoicesEntityList.isEmpty()) {
                throw new RuntimeException("No invoices Found");
            }
            for (InvoicesEntity invoice : invoicesEntityList) {
                invoicesDtoList.add(mapper.map(invoice, InvoicesDto.class));
            }
            invoicesPageResponse = new InvoicesPageResponse(invoicesDtoList,page,limit,invoicesEntityPage.getTotalElements(), invoicesEntityPage.getTotalPages(), invoicesEntityPage.isLast());

        }
        return invoicesPageResponse;
    }

    @Override
    @Transactional
    public InvoicesDto createInvoice(InvoicesCreateRequest request, Long bussinessNo) {
        Optional<InvoicesEntity> existingInvoice = invoicesRepository.findByInvoiceNoAndCompanyBussinessNo(request.getInvoiceNo(), bussinessNo);
        if (existingInvoice.isPresent()) {
            throw new RuntimeException("Invoice with number: " + request.getInvoiceNo() + " is present");
        }
        InvoicesEntity invoiceToSave = new InvoicesEntity();
        invoiceToSave.setInvoiceNo(request.getInvoiceNo());
        invoiceToSave.setDescription(request.getDescription());
        invoiceToSave.setPaymentStatus(request.getPaymentStatus());
        invoiceToSave.setDate(LocalDateTime.now());
        invoiceToSave.setDueDate(LocalDate.from(LocalDateTime.now()));
        invoiceToSave.setInvoiceType(request.getInvoiceType());
        BuyersEntity buyersEntity = buyersRepository.findByIdAndCompanyBussinessNo(request.getBuyerId(), bussinessNo);
        if (buyersEntity == null) {
            throw new RuntimeException("Buyer not registered!!");
        }
        invoiceToSave.setBuyer(buyersEntity);
        invoiceToSave.setCompany(buyersEntity.getCompany());
        List<ProductsInvoiced> productsInvoicedList = new ArrayList<>();
        double invoiceTotalAmount = 0;
        double invoiceAmountNoTvsh = 0;

        for (ProductInvoicedCreateRequest productInvoicedCreateRequest : request.getProductInvoicedCreateRequests()) {
            ProductsEntity product = productsRepository.findByIdAndCompanyBussinessNo(productInvoicedCreateRequest.getProduct_id(), bussinessNo);
            double totalPrice = product.getPrice() * productInvoicedCreateRequest.getQuantity();
            ProductsInvoiced productsInvoiced = new ProductsInvoiced();
            productsInvoiced.setQuantity(productInvoicedCreateRequest.getQuantity());
            productsInvoiced.setTotalPrice(totalPrice);
            productsInvoiced.setProduct(product);
            productsInvoicedList.add(productsInvoiced);
            invoiceTotalAmount = invoiceTotalAmount + totalPrice;
            totalPrice = totalPrice - (totalPrice * product.getTvsh() / 100);
            invoiceAmountNoTvsh = invoiceAmountNoTvsh + totalPrice;
        }

        invoiceToSave.setTotalPrice(invoiceTotalAmount);
        invoiceToSave.setPriceNoTvsh(invoiceAmountNoTvsh);
        invoiceToSave = invoicesRepository.save(invoiceToSave);
        for (ProductsInvoiced productInvoiced : productsInvoicedList) {
            productInvoiced.setInvoice(invoiceToSave);
            ProductsInvoicedPK productsInvoicedPK = new ProductsInvoicedPK(invoiceToSave.getId(), productInvoiced.getProduct().getId());
            productInvoiced.setProductsInvoicedId(productsInvoicedPK);
            productsInvoicedRepository.save(productInvoiced);
            handleStock(productInvoiced.getProduct(),request.getInvoiceType(),productInvoiced.getQuantity());
        }
        invoiceToSave.setProductsInvoiced(productsInvoicedList);
        SellingsEntity sellingsEntity = new SellingsEntity();
        sellingsEntity.setCompany(invoiceToSave.getCompany());
        sellingsEntity.setDate(LocalDateTime.now());
        sellingsEntity.setName("InvoiceNo: " + invoiceToSave.getInvoiceNo());
        sellingsEntity.setPrice(invoiceToSave.getTotalPrice());
        sellingsEntity.setDescription("Regular Selling invoice");
        sellingsRepository.save(sellingsEntity);
        return mapper.map(invoiceToSave, InvoicesDto.class);
    }
    private void handleStock(ProductsEntity product, InvoiceType invoiceType,double quantity){
        if (invoiceType == InvoiceType.SELL){
            StockEntity stock = stockRepository.findByProductBarcodeAndCompanyBussinessNo(product.getBarcode(), product.getCompany().getBussinessNo());
            stock.setQuantity(stock.getQuantity()-quantity);
            stockRepository.save(stock);
        } else if (invoiceType==InvoiceType.BUY) {
            StockEntity stock = stockRepository.findByProductBarcodeAndCompanyBussinessNo(product.getBarcode(), product.getCompany().getBussinessNo());
            stock.setQuantity(stock.getQuantity()+quantity);
            stockRepository.save(stock);
        }else throw new RuntimeException("Invoice type not acceptable!!");
    }
}
