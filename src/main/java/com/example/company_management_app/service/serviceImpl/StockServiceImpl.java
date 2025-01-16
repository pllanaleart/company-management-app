package com.example.company_management_app.service.serviceImpl;

import com.example.company_management_app.entity.ProductsEntity;
import com.example.company_management_app.entity.StockEntity;
import com.example.company_management_app.repository.ProductsRepository;
import com.example.company_management_app.repository.StockRepository;
import com.example.company_management_app.service.StockService;
import com.example.company_management_app.shared.dto.ProductsDto;
import com.example.company_management_app.shared.dto.StockDto;
import com.example.company_management_app.ui.request.stock.StockCreateRequest;
import com.example.company_management_app.ui.response.stock.StockPageResponse;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class StockServiceImpl implements StockService {

    private StockRepository stockRepository;
    private ProductsRepository productsRepository;
    private ModelMapper mapper = new ModelMapper();

    public StockServiceImpl(StockRepository stockRepository, ProductsRepository productsRepository) {
        this.stockRepository = stockRepository;
        this.productsRepository = productsRepository;
    }

    @Override
    public StockPageResponse findAllByCompanyBussinessNo(Long bussinessNo, int page, int limit, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, limit, sort);
        Page<StockEntity> stockEntityPage = stockRepository.findAllByCompanyBussinessNo(bussinessNo,pageable);
        List<StockEntity> stockEntities = stockEntityPage.getContent();
        if (stockEntities.isEmpty())throw new RuntimeException("Not Found!!");
        List<StockDto> stockDtos = new ArrayList<>();
        for (StockEntity stock : stockEntities){
            stockDtos.add(mapper.map(stock, StockDto.class));
        }
        StockPageResponse response = new StockPageResponse(stockDtos,page,limit,stockEntityPage.getTotalElements(),stockEntityPage.getTotalPages(),stockEntityPage.isLast());

        return response;
    }

    @Override
    public List<StockDto> findAllByProductNameContainingAndCompanyBussinessNo(String name, Long bussinessNo) {
        List<StockEntity> stockEntities = stockRepository.findAllByProductNameContainingAndCompanyBussinessNo(name,bussinessNo);
        if (stockEntities.isEmpty())throw new RuntimeException("Not found!!");
        List<StockDto> stockDtos = new ArrayList<>();
        for (StockEntity stock:stockEntities){
            stockDtos.add(mapper.map(stock,StockDto.class));
        }
        return stockDtos;
    }

    @Override
    public StockDto findByProductBarcodeAndCompanyBussinessNo(Long barcode, Long bussinessNo) {
        StockEntity stock = stockRepository.findByProductBarcodeAndCompanyBussinessNo(barcode,bussinessNo);
        if(stock == null){throw new RuntimeException("Stock not found!!");}
        return mapper.map(stock, StockDto.class);
    }

    @Override
    public StockDto findByIdAndCompanyBussinessNo(Long id, Long bussinessNo) {
        StockEntity stock = stockRepository.findByIdAndCompanyBussinessNo(id,bussinessNo);
        if (stock == null){throw new RuntimeException("Stock not found!!");}
        return mapper.map(stock, StockDto.class);
    }

    @Override
    public StockDto createStock(StockCreateRequest stock, Long bussinessNo) {
        StockEntity productForStock = stockRepository.findByProductBarcodeAndCompanyBussinessNo(stock.getBarcode(),bussinessNo);
        StockEntity createdStock;
        StockDto stockDto;
        if(productForStock == null){
            productForStock=new StockEntity();
            ProductsEntity product = productsRepository.findByBarcodeAndCompanyBussinessNo(stock.getBarcode(),bussinessNo);
            if (product == null){throw new RuntimeException("Product not found!!!");}
            productForStock.setUnit(stock.getUnit());
            productForStock.setQuantity(stock.getQuantity());
            productForStock.setProduct(product);
            productForStock.setCompany(product.getCompany());
            productForStock.setLastUpdated(LocalDateTime.now());
            createdStock = stockRepository.save(productForStock);
            stockDto = mapper.map(createdStock, StockDto.class);
        }else throw new RuntimeException("Product is registered in stock");

        return  stockDto;
    }

    @Override
    public StockDto updateStock(StockCreateRequest stockCreateRequest,Long id, Long bussinessNo) {
        StockEntity productToUpdate = stockRepository.findByIdAndCompanyBussinessNo(id,bussinessNo);
        if(productToUpdate != null ){
            if(stockCreateRequest.getQuantity() != 0){
                productToUpdate.setQuantity(stockCreateRequest.getQuantity());
            }
            if (stockCreateRequest.getUnit() != null){
                productToUpdate.setUnit(stockCreateRequest.getUnit());
            }
            productToUpdate.setLastUpdated(LocalDateTime.now());
            stockRepository.save(productToUpdate);

        }else throw  new RuntimeException("Product is not in stock");

        return mapper.map(productToUpdate, StockDto.class);
    }
}
