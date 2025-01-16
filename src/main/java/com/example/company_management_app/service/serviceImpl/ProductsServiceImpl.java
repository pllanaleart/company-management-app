package com.example.company_management_app.service.serviceImpl;

import com.example.company_management_app.entity.CompanyEntity;
import com.example.company_management_app.entity.ProductsEntity;
import com.example.company_management_app.repository.CompanyRepository;
import com.example.company_management_app.repository.ProductsRepository;
import com.example.company_management_app.service.ProductsService;
import com.example.company_management_app.shared.dto.ProductsDto;
import com.example.company_management_app.ui.response.ProductsPageResponse;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public ProductsPageResponse findAllByCompanyBussinessNo(Long bussinessNo, int page, int limit , String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page,limit,sort);
        Page<ProductsEntity> productsEntities = productsRepository.findAllByCompanyBussinessNo(bussinessNo, pageable);
        List<ProductsEntity> productsEntitiesList = productsEntities.getContent();
        List<ProductsDto> productsDtos = new ArrayList<>();
        if(productsEntitiesList.isEmpty()){throw new RuntimeException("Not Found!!!");}
        else {
            for(ProductsEntity productsEntity:productsEntitiesList){
                productsDtos.add(mapper.map(productsEntity, ProductsDto.class));
            }
        }
        ProductsPageResponse response = new ProductsPageResponse(productsDtos,page,limit,productsEntities.getTotalElements(),productsEntities.getTotalPages(),productsEntities.isLast());

        return response;
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
    public ProductsDto updateProduct(Long id, ProductsDto product, Long bussinessNo) {

        ProductsDto oldProduct = findByIdAndCompanyBussinessNo(id,bussinessNo);
        if(product.getBarcode() != null){
            oldProduct.setBarcode(product.getBarcode());
        }
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

    @Override
    public ProductsDto findByIdAndCompanyBussinessNo(Long id, Long bussinessNo) {
        ProductsEntity product = productsRepository.findByIdAndCompanyBussinessNo(id, bussinessNo);

        if(product == null)throw new RuntimeException("Product not Found!!");
        ProductsDto productsDto = mapper.map(product, ProductsDto.class);
        return productsDto;
    }

    @Override
    public String deleteProduct(Long id, Long bussinessNo) {
        ProductsEntity product = productsRepository.findByIdAndCompanyBussinessNo(id,bussinessNo);
        String message = "";
        if(product != null){
            productsRepository.delete(product);
            message = product.getName() + " deleted successfully";
        }else throw new RuntimeException("Product with id "+ id + " not found!!");
        return message;
    }
}
