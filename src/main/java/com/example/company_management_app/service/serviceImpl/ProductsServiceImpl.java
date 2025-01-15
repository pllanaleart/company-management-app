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

    @Override
    public List<ProductsDto> findAllByNameAndCompanyBussinessNo(String name, Long bussinessNo) {
        List<ProductsEntity> productsEntities = productsRepository.findAllByNameContainingAndCompanyBussinessNo(name,bussinessNo);
        List<ProductsDto> productsDtos = new ArrayList<>();
        if (productsEntities.isEmpty())throw new RuntimeException("Not found!!");
        for(ProductsEntity product: productsEntities){
            productsDtos.add(mapper.map(product, ProductsDto.class));
        }
        return productsDtos;
    }

    @Override
    public ProductsDto createProduct(ProductsDto productsDto) {
        ProductsEntity productsEntity = mapper.map(productsDto, ProductsEntity.class);
        ProductsEntity createdProduct = productsRepository.save(productsEntity);
        ProductsDto returnProduct = mapper.map(createdProduct, ProductsDto.class);
        return returnProduct;
    }

    @Override
    public ProductsDto updateProduct(Long barcode, ProductsDto product, Long bussinessNo) {

        ProductsDto oldProduct = findByBarcodeAndCompanyBussinessNo(barcode,bussinessNo);
        if(product.getName() != null){
            oldProduct.setName(product.getName());
        }
        if(product.getDescription() != null){
            oldProduct.setDescription(product.getDescription());
        }
        if(product.getCategory() != null){
            oldProduct.setCategory(product.getCategory());
        }
        if(product.getPrice() != oldProduct.getPrice()){
            oldProduct.setPrice(product.getPrice());
        }
        if(product.getPriceNoTvsh() != oldProduct.getPriceNoTvsh()){
            oldProduct.setPriceNoTvsh(product.getPriceNoTvsh());
        }
        if(product.getTvsh() != oldProduct.getTvsh()){
            oldProduct.setTvsh(product.getTvsh());
        }
        if(product.getStatus() != null){
            oldProduct.setStatus(product.getStatus());
        }
        ProductsEntity updatedProduct = mapper.map(oldProduct, ProductsEntity.class);
        ProductsEntity createdProduct = productsRepository.save(updatedProduct);

        return mapper.map(createdProduct, ProductsDto.class);
    }

    @Override
    public ProductsDto findByBarcodeAndCompanyBussinessNo(Long barcode, Long bussinessNo) {

        ProductsEntity product = productsRepository.findByBarcodeAndCompanyBussinessNo(barcode,bussinessNo);
        if (product == null)throw new RuntimeException("Not found!!");
        ProductsDto productsDto = mapper.map(product, ProductsDto.class);

        return productsDto;
    }
}
