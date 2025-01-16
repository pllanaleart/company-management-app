package com.example.company_management_app.repository;

import com.example.company_management_app.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {

    List<CompanyEntity> findAllByNameContainingIgnoreCase(String name);

    CompanyEntity findByBussinessNo(Long bussinessNo);
}
