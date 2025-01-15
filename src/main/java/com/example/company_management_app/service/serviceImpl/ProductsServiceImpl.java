package com.example.company_management_app.service.serviceImpl;

import com.example.company_management_app.entity.CompanyEntity;
import com.example.company_management_app.entity.ProductsEntity;
import com.example.company_management_app.repository.CompanyRepository;
import com.example.company_management_app.repository.ProductsRepository;
import com.example.company_management_app.service.ProductsService;
import com.example.company_management_app.shared.dto.ProductsDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductsServiceImpl implements ProductsService {
    private ProductsRepository productsRepository;
    private CompanyRepository companyRepository;
    private ModelMapper mapper = new ModelMapper();

    public ProductsServiceImpl(ProductsRepository productsRepository, CompanyRepository companyRepository) {
        this.productsRepository = productsRepository;
        this.companyRepository = companyRepository;
    }

    @Override
    public List<ProductsDto> findAllByCompanyBussinessNo(Long bussinessNo) {
        List<ProductsEntity> productsEntities = productsRepository.findAllByCompanyBussinessNo(bussinessNo);
        List<ProductsDto> productsDtos = new ArrayList<>();
        if(productsEntities.isEmpty()){throw new RuntimeException("Not Found!!!");}
        else {
            for(ProductsEntity productsEntity:productsEntities){
                productsDtos.add(mapper.map(productsEntity, ProductsDto.class));
            }
        }
        return productsDtos;
    }
}
