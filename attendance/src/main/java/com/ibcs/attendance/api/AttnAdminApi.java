package com.ibcs.attendance.api;


import com.ibcs.attendance.dto.AttnAdminDto;
import com.ibcs.attendance.repo.AttnAdminRepo;
import com.ibcs.attendance.service.AttnAdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/attnAdminApi")
@Slf4j
public class AttnAdminApi {

    @Autowired
    private AttnAdminService attnAdminService;

    @GetMapping("/")
    public Page<AttnAdminDto> all() {
        return attnAdminService.findAll(PageRequest.of(0, 10), null);
    }

    @PostMapping("/save")
    public ResponseEntity newattnAdmin(@RequestBody AttnAdminDto newattnAdmin) {
        System.out.println("TEST:::"+newattnAdmin);
        return attnAdminService.save(newattnAdmin);
    }

    @GetMapping("/attnAdmin/{id}")
    public AttnAdminDto attnAdminOneView(@PathVariable Long id) {

        log.info("Request received for get user details for id {}", id);
        AttnAdminDto response = attnAdminService.findById(id);
        log.info("Response return for {} object {}", id, response);
        return response;

    }

    @PutMapping("/{id}")
    public AttnAdminDto replaceEmp(@RequestBody AttnAdminDto newEmp, @PathVariable Long id) {
        return attnAdminService.update(newEmp, id);
    }

    @DeleteMapping("/{id}")
    public void deleteEmp(@PathVariable Long id) {
        attnAdminService.deleteById(id);
    }

}
