package com.ibcs.attendance.service;

import com.ibcs.attendance.dto.AttnDailyProcDto;
import com.ibcs.attendance.model.AttnDailyProc;
import com.ibcs.attendance.repo.AttnDailyProcRepo;
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
public class AttnDailyProcService {

    @Autowired
    private AttnDailyProcRepo attnDailyProcRepo;

    private AttnDailyProcDto conv(AttnDailyProc attnDailyProc) {

        AttnDailyProcDto attnDailyProcDto = new AttnDailyProcDto();

        BeanUtils.copyProperties(attnDailyProc, attnDailyProcDto,  "employeeId");
        attnDailyProcDto.setEmployeeId(attnDailyProc.getEmployeeId());
        return attnDailyProcDto;
    }

    public AttnDailyProcDto save(AttnDailyProcDto attnDailyProcDto) {

        AttnDailyProc attnDailyProc = new AttnDailyProc();

        BeanUtils.copyProperties(attnDailyProcDto, attnDailyProc);

        return conv(attnDailyProcRepo.save(attnDailyProc));

        //return conv(attnDailyProcRepo.save(attnDailyProc));
    }

    public AttnDailyProcDto update(AttnDailyProcDto attnDailyProcDto, Long id) {

        AttnDailyProc attnDailyProc = attnDailyProcRepo.getById(id);

        BeanUtils.copyProperties(attnDailyProcDto, attnDailyProc, "id","employeeId");
        attnDailyProcDto.setEmployeeId(attnDailyProc.getEmployeeId());

        return conv(attnDailyProcRepo.save(attnDailyProc));
    }



    public Page<AttnDailyProcDto> findAll(Pageable pageable, String sText) {
        Page<AttnDailyProc> attnDailyProc = attnDailyProcRepo.findAllCustom(pageable, sText);


        List<AttnDailyProcDto> ss = new ArrayList(pageable.getPageSize());
        for (AttnDailyProc ppp : attnDailyProc.getContent()) {
            ss.add(conv(ppp));
        }

        Page<AttnDailyProcDto> attnDailyProcDtos = new PageImpl(ss, pageable, attnDailyProc.getTotalElements());


        return attnDailyProcDtos;
    }

    public void deleteById(Long id) {
        attnDailyProcRepo.deleteById(id);
    }

//    public AttnDailyProcDto findById(Long id) {
//    }
}
