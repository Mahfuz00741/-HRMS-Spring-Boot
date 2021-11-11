package com.ibcs.hr.api;

import com.ibcs.hr.dto.*;
import com.ibcs.hr.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/empApi")
@Slf4j
public class EmpApi {
    @Autowired
    private EmpService empService;

    @GetMapping("/")
    public Page<EmpDto> all() {
        return empService.findAll(PageRequest.of(0, 10), null);
    }

    @GetMapping("/userDetails/{id}")
    public ResponsFeignClientDto userView(@PathVariable Long id) {
        log.info("Request received for get user details for id {}", id);
        ResponsFeignClientDto response = empService.findUserFeignClient(id);
        log.info("Response return for {} object {}", id, response);
        return response;
    }

    @GetMapping(value = "/{id}")
    UserDto ByWeb(@PathVariable("id") Integer id) {
        return  empService.findByUserWebId(id);
    }

    @PostMapping("/")
    public EmpDto newEmp(@RequestBody EmpDto newEmp) {
        return empService.save(newEmp);
    }

    @GetMapping("/emp/{id}")
    public EmpDto EmpOneView(@PathVariable Long id) {

        log.info("Request received for get user details for id {}", id);
        EmpDto response = empService.findById(id);
        log.info("Response return for {} object {}", id, response);
        return response;

    }

    @PutMapping("/{id}")
    public EmpDto replaceEmp(@RequestBody EmpDto newEmp, @PathVariable Long id) {
        return empService.update(newEmp, id);
    }

    @DeleteMapping("/{id}")
    public void deleteEmp(@PathVariable Long id) {
        empService.deleteById(id);
    }

}
