package com.example.company_management_app.repository;

import com.example.company_management_app.entity.StockEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<StockEntity, Long> {

    Page<StockEntity> findAllByCompanyBussinessNo(Long bussinessNo, Pageable pageable);
    List<StockEntity> findAllByProductNameContainingIgnoreCaseAndCompanyBussinessNo(String name, Long bussinessNo);
    StockEntity findByProductBarcodeAndCompanyBussinessNo(Long barcode, Long bussinessNo);
    StockEntity findByIdAndCompanyBussinessNo(Long id,Long bussinessNo);
}
