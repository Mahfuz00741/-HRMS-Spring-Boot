package com.ibcs.attendance.api;

import com.ibcs.attendance.service.MonthlyProcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/montlyProcApi")
public class MonthlyProcApi {
    @Autowired
    MonthlyProcService monthlyProcService;

    @GetMapping("/getAll")
    public Object getAll() {

        return monthlyProcService.getAll();


    }

    @GetMapping("/getById")
    public void getById() {


    }


    @PostMapping("/postMontlyProc")

    public void postMonthlyProc() {

    }


    @PutMapping("/updateMontlyProc")
    public void updateMonthlyProc() {

    }

    @DeleteMapping("/deleteMonthlyProc")
    public void deleteMonthlyProc() {

    }

}
