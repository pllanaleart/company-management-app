package com.example.company_management_app.repository;

import com.example.company_management_app.entity.InvoicesEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoicesRepository extends JpaRepository<InvoicesEntity,Long> {

    Page<InvoicesEntity> findAllByCompanyBussinessNo(Long bussinessNo, Pageable pageable);
    InvoicesEntity findByInvoiceNoAndCompanyBussinessNo(Long invoicesNo, Long bussinessNo);
    InvoicesEntity findByIdAndCompanyBussinessNo(Long id,Long bussinessNo);

}
