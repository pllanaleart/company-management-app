package com.example.company_management_app.service.serviceImpl;

import com.example.company_management_app.entity.BuyersEntity;
import com.example.company_management_app.repository.BuyersRepository;
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
    private ModelMapper mapper = new ModelMapper();

    public BuyersServiceImpl(BuyersRepository buyersRepository) {
        this.buyersRepository = buyersRepository;
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
}
