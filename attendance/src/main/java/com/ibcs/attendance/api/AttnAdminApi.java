package com.ibcs.attendance.api;


import com.ibcs.attendance.dto.AttnAdminDto;
import com.ibcs.attendance.repo.AttnAdminRepo;
import com.ibcs.attendance.service.AttnAdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


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
}
