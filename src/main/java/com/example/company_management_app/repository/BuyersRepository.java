package com.example.company_management_app.repository;

import com.example.company_management_app.entity.BuyersEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BuyersRepository extends JpaRepository<BuyersEntity, Long> {

  Page<BuyersEntity> findAllByCompanyBussinessNo(Long bussinessNo, Pageable pageable);
  List<BuyersEntity> findAllByNameContainingIgnoreCaseAndCompanyBussinessNo(String name, Long bussinessNo);
  BuyersEntity findByBussinessNoAndCompanyBussinessNo(Long buyersbussinessNo,Long bussinessNo);
  BuyersEntity findByIdAndCompanyBussinessNo(Long id,Long bussinessNo);
}
