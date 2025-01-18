package com.example.company_management_app.repository;

import com.example.company_management_app.entity.ProductsInvoiced;
import com.example.company_management_app.entity.keys.ProductsInvoicedPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsInvoicedRepository extends JpaRepository<ProductsInvoiced, ProductsInvoicedPK> {

}
