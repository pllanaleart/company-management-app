package com.example.company_management_app.service.serviceImpl;

import com.example.company_management_app.entity.BuyersEntity;
import com.example.company_management_app.entity.CompanyEntity;
import com.example.company_management_app.repository.BuyersRepository;
import com.example.company_management_app.repository.CompanyRepository;
import com.example.company_management_app.service.BuyersService;
import com.example.company_management_app.shared.dto.BuyersDto;
import com.example.company_management_app.ui.response.buyers.BuyersPageResponse;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BuyersServiceImpl implements BuyersService {
    private BuyersRepository buyersRepository;
    private CompanyRepository companyRepository;
    private ModelMapper mapper = new ModelMapper();

    public BuyersServiceImpl(BuyersRepository buyersRepository, CompanyRepository companyRepository) {
        this.buyersRepository = buyersRepository;
        this.companyRepository = companyRepository;
    }

    @Override
    public BuyersPageResponse findAllByCompanyBussinessNo(Long bussinessNo, int page, int limit, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, limit, sort);
        Page<BuyersEntity> buyersEntities = buyersRepository.findAllByCompanyBussinessNo(bussinessNo,pageable);
        List<BuyersEntity> buyersList = buyersEntities.getContent();
        List<BuyersDto> buyersDtoList = new ArrayList<>();
        if(buyersList.isEmpty()){throw new RuntimeException("Not found any buyer!!");}
        for (BuyersEntity buyers:buyersList){
            buyersDtoList.add(mapper.map(buyers, BuyersDto.class));
        }
        BuyersPageResponse buyersPageResponse = new BuyersPageResponse(buyersDtoList,page,limit,buyersEntities.getTotalElements(), buyersEntities.getTotalPages(), buyersEntities.isLast());

        return buyersPageResponse;
    }

    @Override
    public List<BuyersDto> findAllByNameContainingIgnoreCaseAndCompanyBussinessNo(String name, Long bussinessNo) {
        List<BuyersEntity> buyersEntityList = buyersRepository.findAllByNameContainingIgnoreCaseAndCompanyBussinessNo(name,bussinessNo);
        if(buyersEntityList.isEmpty()){throw new RuntimeException("No buyer registerd!!");}
        List<BuyersDto> buyersDtoList = new ArrayList<>();
        for (BuyersEntity buyer:buyersEntityList){
            buyersDtoList.add(mapper.map(buyer, BuyersDto.class));
        }
        return buyersDtoList;
    }

    @Override
    public BuyersDto findByBussinessNoAndCompanyBussinessNo(Long bussinessNo, Long companyBussinessNo) {
        BuyersEntity buyer = buyersRepository.findByBussinessNoAndCompanyBussinessNo(bussinessNo,companyBussinessNo);
        if(buyer == null){throw new RuntimeException("Buyer not found!!");}
        return mapper.map(buyer,BuyersDto.class);
    }

    @Override
    public BuyersDto findByIdAndCompanyBussinessNo(Long id, Long bussinessNo) {
        BuyersEntity buyersEntity = buyersRepository.findByIdAndCompanyBussinessNo(id,bussinessNo);
        if (buyersEntity == null){throw new RuntimeException("Buyer not found!!");}

        return mapper.map(buyersEntity, BuyersDto.class);
    }

    @Override
    public BuyersDto createBuyer(BuyersDto buyersDto ,Long bussinessNo) {
        BuyersEntity buyer = buyersRepository.findByBussinessNoAndCompanyBussinessNo(buyersDto.getBussinessNo(),bussinessNo);
        if (buyer == null){
            CompanyEntity company = companyRepository.findByBussinessNo(bussinessNo);
            buyer = mapper.map(buyersDto, BuyersEntity.class);
            buyer.setCompany(company);
            buyer.setId(null);
        }else throw new RuntimeException("Buyer already registered !!");
        BuyersEntity createdBuyer = buyersRepository.save(buyer);

        return mapper.map(createdBuyer,BuyersDto.class);
    }

    @Override
    public BuyersDto updateBuyer(BuyersDto buyersDto, Long id, Long bussinessNo) {

        BuyersEntity buyer = buyersRepository.findByIdAndCompanyBussinessNo(id,bussinessNo);
        if (buyer != null ){
            if (buyersDto.getBussinessNo() != null){
                buyer.setBussinessNo(buyersDto.getBussinessNo());
            }
            if (buyersDto.getBuyerStatus() != null){
                buyer.setBuyerStatus(buyersDto.getBuyerStatus());
            }
            if (buyersDto.getBankNo() != null){
                buyer.setBankNo(buyersDto.getBankNo());
            }
            if (buyersDto.getName() != null){
                buyer.setName(buyersDto.getName());
            }
            if (buyersDto.getAddress() != null){
                buyer.setAddress(buyersDto.getAddress());
            }
            if (buyersDto.getEmail() != null){
                buyer.setEmail(buyersDto.getEmail());
            }
            if (buyersDto.getFiskalNo() != null){
                buyer.setFiskalNo(buyersDto.getFiskalNo());
            }
            if (buyersDto.getLogo() != null){
                buyer.setLogo(buyersDto.getLogo());
            }
            if (buyersDto.getPhone() != null){
                buyer.setPhone(buyersDto.getPhone());
            }
            if (buyersDto.getTvshNo() != null){
                buyer.setTvshNo(buyersDto.getTvshNo());
            }
            buyersRepository.save(buyer);
        }else throw new RuntimeException("Buyer not found!!");
        return mapper.map(buyer, BuyersDto.class);
    }


}
