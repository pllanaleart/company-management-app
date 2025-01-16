package com.example.company_management_app.ui.controller;

import com.example.company_management_app.service.CompanyService;
import com.example.company_management_app.shared.dto.CompanyDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("company")
public class CompanyController {

    private CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public List<CompanyDto> getAll() {
        List<CompanyDto> companyDtos = companyService.getAll();
        return companyDtos;
    }

    @GetMapping("id/{bussinessNo}")
    public CompanyDto findByBussinessNo(@PathVariable Long bussinessNo) {
        CompanyDto companyDto = companyService.findByBussinessNo(bussinessNo);
        return companyDto;
    }

    @GetMapping("/{name}")
    public List<CompanyDto> findAllByName(@PathVariable String name) {
        List<CompanyDto> companyDtos = companyService.findAllByName(name);
        if (companyDtos.isEmpty()) {
            throw new RuntimeException("Not found!!");
        }
        return companyDtos;
    }

    @PostMapping
    public String createCompany(@RequestBody CompanyDto companyDto) {
        CompanyDto createdCompany = companyService.createCompany(companyDto);
        String message = "";
        if (createdCompany != null) {
            message = "Company created successfully with bussinnessNo: " + createdCompany.getBussinessNo();
        }
        return message;
    }

    @PutMapping("/{bussinessNo}")
    public CompanyDto updateCompany(@PathVariable Long bussinessNo, @RequestBody CompanyDto companyDto) {
        CompanyDto companyUpdated = companyService.updateCompany(bussinessNo, companyDto);


        return companyUpdated;
    }

}
