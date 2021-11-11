package com.ibcs.hr.api;

import com.ibcs.hr.dto.DeptDto;
import com.ibcs.hr.dto.EmpDto;
import com.ibcs.hr.service.DeptService;
import com.ibcs.hr.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/deptApi")

public class DeptApi {


    @Autowired
    private DeptService deptService;

    @GetMapping("/")
    Page<DeptDto> all() {

        return deptService.findAll(PageRequest.of(0, 10), null);
    }

    @PostMapping("/")
    DeptDto newDept(@RequestBody DeptDto newDept) {

        return deptService.save(newDept);
    }

    @GetMapping("/{id}")
    DeptDto one(@PathVariable Long id) {

        return deptService.findById(id);
    }

    @PutMapping("/{id}")
    DeptDto replaceDept(@RequestBody DeptDto newDept, @PathVariable Long id) {

        return deptService.update(newDept, id);
    }

    @DeleteMapping("/{id}")
    void deleteDept(@PathVariable Long id) {
        deptService.deleteById(id);
    }

}
