package com.ibcs.attendance.api;


import com.ibcs.attendance.dto.AttnStageDto;
import com.ibcs.attendance.service.AttnStageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/attnStageApi")
public class AttnStageApi {

    @Autowired
    private AttnStageService attnStageService;

    @PostMapping("/")
    public AttnStageDto create(@RequestBody AttnStageDto newAttnStage) {
        return attnStageService.save(newAttnStage);
    }

    @PutMapping("/{id}")
    public AttnStageDto update(@RequestBody AttnStageDto newAttnStage, @PathVariable Long id) {
        return attnStageService.update(newAttnStage, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        attnStageService.deleteById(id);
    }

    @GetMapping("/{id}")
    public AttnStageDto findOne(@PathVariable Long id) {
        return attnStageService.findById(id);
    }

    @GetMapping("/")
    public Page<AttnStageDto> all() {
        return attnStageService.findAll(PageRequest.of(0, 10), null);
    }

}
