package com.ibcs.attendance.api;

import com.ibcs.attendance.dto.AttnDailyProcDto;
import com.ibcs.attendance.service.AttnDailyProcService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/attnDailyProcApi")
@Slf4j
public class AttnDailyProcApi {

    @Autowired
    private AttnDailyProcService attnDailyProcService;

    @GetMapping("/")
    public Page<AttnDailyProcDto> all() {
        return attnDailyProcService.findAll(PageRequest.of(0, 10), null);
    }

//    @GetMapping("/{id}")
//    AttnDailyProcDto one(@PathVariable Long id) {
//        return attnDailyProcService.findById(id);
//    }

    @PostMapping("/")
    AttnDailyProcDto newAttnDailyProc(@RequestBody AttnDailyProcDto newAttnDailyProcDto) {
        return attnDailyProcService.save(newAttnDailyProcDto);
    }

    @PutMapping("/{id}")
    AttnDailyProcDto replaceAttnDailyProc(@RequestBody AttnDailyProcDto newAttnDailyProcDto , @PathVariable Long id) {

        return attnDailyProcService.update(newAttnDailyProcDto, id);

    }

    @DeleteMapping("/{id}")
    void deleteAttnDailyProc(@PathVariable Long id) {
        attnDailyProcService.deleteById(id);
    }
}
