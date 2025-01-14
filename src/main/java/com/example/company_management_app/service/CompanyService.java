package com.example.company_management_app.service;

import java.util.List;

import com.example.company_management_app.shared.dto.CompanyDto;

public interface CompanyService {

  List<CompanyDto> getAll();
  CompanyDto findByBussinessNo(Long bussinessNo);
  List<CompanyDto> findAllByName(String name);
  CompanyDto createCompany(CompanyDto companyDto);
  CompanyDto updateCompany(Long bussinessNo,CompanyDto companyDto);


}
