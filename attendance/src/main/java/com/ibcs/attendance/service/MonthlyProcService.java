package com.ibcs.attendance.service;

import com.ibcs.attendance.dto.MonthlyProcDto;
import com.ibcs.attendance.model.MonthlyProc;
import com.ibcs.attendance.repo.MonthlyProcRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class MonthlyProcService {


    @Autowired
    MonthlyProcRepo monthlyProcRepo;

    public Object getAll(){

        List<MonthlyProcDto> monDto= new ArrayList<>();
        List<MonthlyProc> monList= monthlyProcRepo.findAll();
        BeanUtils.copyProperties(monList,monDto);
        return monDto;



    }

    public Object getById(Long id){

        MonthlyProcDto monDto= new MonthlyProcDto();
        MonthlyProc mon= monthlyProcRepo.getById(id);
        BeanUtils.copyProperties(mon,monDto);
        return monDto;



    }

    public Object post(MonthlyProcDto monthlyProcDto)
    {

        MonthlyProc monthlyProc = new MonthlyProc();

        BeanUtils.copyProperties(monthlyProcDto,monthlyProc,"id");
         monthlyProc= monthlyProcRepo.save(monthlyProc);

         BeanUtils.copyProperties(monthlyProc, monthlyProcDto);



        return  monthlyProcDto;


    }


    public Object update(MonthlyProcDto monthlyProcDto, Long id){

       // MonthlyProc monthlyProc = new MonthlyProc();

        MonthlyProc monthlyProc= monthlyProcRepo.getById(id);
        BeanUtils.copyProperties(monthlyProcDto,monthlyProc,"id");
        monthlyProc= monthlyProcRepo.save(monthlyProc);

        BeanUtils.copyProperties(monthlyProc, monthlyProcDto);

        return  monthlyProcDto;


    }


    public void delete(Long id)
    {
         monthlyProcRepo.deleteById(id);

    }



}
