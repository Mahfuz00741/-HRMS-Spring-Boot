package com.ibcs.attendance.service;

import com.ibcs.attendance.dto.AttnAdminDto;
import com.ibcs.attendance.model.AttnAdmin;
import com.ibcs.attendance.repo.AttnAdminRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class AttnAdminService {

    @Autowired
    private AttnAdminRepo attnAdminRepo;

    private AttnAdminDto conv(AttnAdmin attnAdmin) {

        AttnAdminDto attnAdminDto = new AttnAdminDto();

        BeanUtils.copyProperties(attnAdmin, attnAdminDto, "userId", "empId");
        attnAdminDto.setEmpId(attnAdmin.getEmpId());
        attnAdminDto.setUserId(attnAdmin.getUserId());
        return attnAdminDto;
    }

    public AttnAdminDto update(AttnAdminDto attnAdminDto, Long id) {

        AttnAdmin attnAdmin = attnAdminRepo.getById(id);

        BeanUtils.copyProperties(attnAdminDto, attnAdmin, "id", "userId", "empId");
        attnAdminDto.setEmpId(attnAdmin.getEmpId());
        attnAdminDto.setUserId(attnAdmin.getUserId());

        return conv(attnAdminRepo.save(attnAdmin));
    }

    public AttnAdminDto save(AttnAdminDto attnAdminDto) {

        AttnAdmin attnAdmin = new AttnAdmin();

        BeanUtils.copyProperties(attnAdminDto, attnAdmin, "userId", "empId");
        attnAdminDto.setEmpId(attnAdmin.getEmpId());
        attnAdminDto.setUserId(attnAdmin.getUserId());

        return conv(attnAdminRepo.save(attnAdmin));
    }


    public Page<AttnAdminDto> findAll(Pageable pageable, String sText) {
        Page<AttnAdmin> attnAdmin = attnAdminRepo.findAllCustom(pageable, sText);

        //PageRequest<EmpDto> empDtos= PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());

        List<AttnAdminDto> ss = new ArrayList(pageable.getPageSize());
        for (AttnAdmin pp : attnAdmin.getContent()) {
            ss.add(conv(pp));
        }

        Page<AttnAdminDto> attnAdminDtos = new PageImpl(ss, pageable, attnAdmin.getTotalElements());


        return attnAdminDtos;
    }

    public AttnAdminDto findById(Long id) {

        try {
            AttnAdminDto attnAdminDto = new AttnAdminDto();
            AttnAdmin attnAdmin = attnAdminRepo.getById(id);
            if (attnAdmin == null) {
                return new AttnAdminDto( null, null, null, null, null, null, null, "User not found");
            } else {

                BeanUtils.copyProperties(attnAdmin, attnAdminDto);
                attnAdminDto.setUserMessage("Successfully get user information.");

                return attnAdminDto;
            }
        } catch (Exception e) {
            log.error("Exception occurred during getting user info", e);
            return new AttnAdminDto(null, null, null, null, null, null, null, "User not found");

        }
    }


    public void deleteById(Long id) {
        attnAdminRepo.deleteById(id);
    }


}
