package com.example.company_management_app.repository;

import com.example.company_management_app.entity.CompanyEntity;
import com.example.company_management_app.entity.ProductsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<ProductsEntity, Long> {

    List<ProductsEntity> findAllByCompany(CompanyEntity companyEntity);
}
