package com.example.company_management_app.repository;

import com.example.company_management_app.entity.ProductsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<ProductsEntity, Long> {

    Page<ProductsEntity> findAllByCompanyBussinessNo(Long bussinessNo, Pageable pageable);

    ProductsEntity findByBarcodeAndCompanyBussinessNo(Long barcode, Long bussinessNo);

    ProductsEntity findByIdAndCompanyBussinessNo(Long id, Long bussinessNo);

    List<ProductsEntity> findAllByNameContainingAndCompanyBussinessNo(String name, Long bussinessNo);
}
