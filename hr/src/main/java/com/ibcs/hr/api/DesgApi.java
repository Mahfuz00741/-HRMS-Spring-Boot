package com.ibcs.hr.api;

import com.ibcs.hr.dto.DeptDto;
import com.ibcs.hr.dto.DesgDto;
import com.ibcs.hr.dto.UserDto;
import com.ibcs.hr.service.DeptService;
import com.ibcs.hr.service.DesgService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/desgApi")

public class DesgApi {

    @Autowired
    private DesgService desgService;

    @GetMapping("/")
    Page<DesgDto> all() {

        return desgService.findAll(PageRequest.of(0, 10), null);
    }


    @GetMapping(value = "/{id}")
    Mono<UserDto> IdByWeb(@PathVariable("id") Integer  id) {
        return  desgService.findByUserWebId(id);
    }

    @PostMapping("/")
    DesgDto newDesg(@RequestBody DesgDto newDesg) {

        return desgService.save(newDesg);
    }

    @GetMapping("/desg/{id}")
    DesgDto one(@PathVariable Long id) {

        return desgService.findById(id);
    }

    @PutMapping("/{id}")
    DesgDto replaceDept(@RequestBody DesgDto newDesg, @PathVariable Long id) {

        return desgService.update(newDesg,id);
    }

    @DeleteMapping("/{id}")
    void deleteDesg(@PathVariable Long id) {

        desgService.deleteById(id);
    }


}
