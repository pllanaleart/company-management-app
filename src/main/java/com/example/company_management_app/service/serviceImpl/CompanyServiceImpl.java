package com.example.company_management_app.service.serviceImpl;

import com.example.company_management_app.entity.CompanyEntity;
import com.example.company_management_app.repository.CompanyRepository;
import com.example.company_management_app.service.CompanyService;
import com.example.company_management_app.shared.dto.CompanyDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    private CompanyRepository companyRepository;
    ModelMapper mapper = new ModelMapper();

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<CompanyDto> getAll() {
        List<CompanyEntity> companyEntities = companyRepository.findAll();
        List<CompanyDto> companyDtos = new ArrayList<>();
        for (CompanyEntity company : companyEntities) {
            companyDtos.add(mapper.map(company, CompanyDto.class));
        }
        return companyDtos;
    }

    @Override
    public CompanyDto findByBussinessNo(Long bussinessNo) {
        CompanyEntity companyEntity = companyRepository.findByBussinessNo(bussinessNo);
        if (companyEntity == null) {
            throw new RuntimeException("Not found!!");
        }

        return mapper.map(companyEntity, CompanyDto.class);
    }

    @Override
    public List<CompanyDto> findAllByName(String name) {
        List<CompanyEntity> companyEntities = companyRepository.findAllByNameContainingIgnoreCase(name);
        List<CompanyDto> companyDtos = new ArrayList<>();
        for (CompanyEntity companyEntity : companyEntities) {
            companyDtos.add(mapper.map(companyEntity, CompanyDto.class));
        }

        return companyDtos;
    }

    @Override
    public CompanyDto createCompany(CompanyDto companyDto) {
        CompanyEntity companyEntity = mapper.map(companyDto, CompanyEntity.class);
        CompanyEntity createdCompany = companyRepository.save(companyEntity);
        companyDto = mapper.map(createdCompany, CompanyDto.class);
        return companyDto;
    }

    @Override
    public CompanyDto updateCompany(Long bussinessNo, CompanyDto companyDto) {

        CompanyEntity foundCompany = companyRepository.findByBussinessNo(bussinessNo);
        if (foundCompany != null) {
            if (companyDto.getName() != null) {
                foundCompany.setName(companyDto.getName());
            }
            if (companyDto.getAddress() != null) {
                foundCompany.setAddress(companyDto.getAddress());
            }
            if (companyDto.getPhone() != null) {
                foundCompany.setPhone(companyDto.getPhone());
            }
            if (companyDto.getTvshNo() != null) {
                foundCompany.setTvshNo(companyDto.getTvshNo());
            }
            if (companyDto.getFiskalNo() != null) {
                foundCompany.setFiskalNo(companyDto.getFiskalNo());
            }
            if (companyDto.getEmail() != null) {
                foundCompany.setEmail(companyDto.getEmail());
            }
            if (companyDto.getLogo() != null) {
                foundCompany.setLogo(companyDto.getLogo());
            }
            if (companyDto.getBankNo() != null) {
                foundCompany.setBankNo(companyDto.getBankNo());
            }
            companyRepository.save(foundCompany);
        } else throw new RuntimeException("Not found!!");
        companyDto = mapper.map(foundCompany, CompanyDto.class);
        return companyDto;
    }


}
