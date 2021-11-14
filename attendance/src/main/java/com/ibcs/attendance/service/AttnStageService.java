package com.ibcs.attendance.service;

import com.ibcs.attendance.dto.AttnAdminDto;
import com.ibcs.attendance.dto.AttnStageDto;
import com.ibcs.attendance.model.AttnAdmin;
import com.ibcs.attendance.model.AttnStage;
import com.ibcs.attendance.repo.AttnStageRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AttnStageService {

    @Autowired
    private AttnStageRepo attnStageRepo;

    private AttnStageDto conv(AttnStage attnStage) {

        AttnStageDto attnStageDto = new AttnStageDto();

        BeanUtils.copyProperties(attnStage, attnStageDto);

        return attnStageDto;
    }

    public AttnStageDto save(AttnStageDto attnStageDto) {
        AttnStage attnStage = new AttnStage();

        BeanUtils.copyProperties(attnStageDto, attnStage);

        return conv(attnStageRepo.save(attnStage));
    }

    public AttnStageDto update(AttnStageDto attnStageDto, Long id) {
        AttnStage attnStage = attnStageRepo.getById(id);

        BeanUtils.copyProperties(attnStageDto, attnStage, "id");

        return conv(attnStageRepo.save(attnStage));
    }

    public AttnStageDto findById(Long id) {
        AttnStageDto attnStageDto = new AttnStageDto();

        AttnStage attnStage = attnStageRepo.getById(id);
        if (attnStage == null) {
            return new AttnStageDto( null, null, null, null, "User not found");
        }
        else{
            BeanUtils.copyProperties(attnStage, attnStageDto);
            attnStageDto.setUserMessage("Successfully get user information.");
        }
        return attnStageDto;
    }

    public Page<AttnStageDto> findAll(Pageable pageable, String sText) {
        Page<AttnStage> attnStage = attnStageRepo.findAllCustom(pageable, sText);

        //PageRequest<EmpDto> empDtos= PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());

        List<AttnStageDto> ss = new ArrayList(pageable.getPageSize());
        for (AttnStage pp : attnStage.getContent()) {
            ss.add(conv(pp));
        }

        Page<AttnStageDto> attnStageDtos = new PageImpl(ss, pageable, attnStage.getTotalElements());


        return attnStageDtos;
    }

    public void deleteById(Long id){
        attnStageRepo.deleteById(id);
    }
}
