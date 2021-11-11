package com.ibcs.tnl.api;

import com.ibcs.tnl.dto.LeaveTypeDto;
import com.ibcs.tnl.service.LeaveTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/leaveTypeApi")
public class LeaveTypeApi {

    @Autowired
    private LeaveTypeService leaveTypeService;

    @GetMapping("/")
    Page<LeaveTypeDto> all() {
        return leaveTypeService.findAll(PageRequest.of(0, 10), null);
    }
    @GetMapping("/{id}")
    LeaveTypeDto one(@PathVariable Long id) {
        return leaveTypeService.findById(id);
    }
    @PostMapping("/")
    LeaveTypeDto newDesg(@RequestBody LeaveTypeDto newLeaveTypeDto) {
        return leaveTypeService.save(newLeaveTypeDto);
    }



    @PutMapping("/{id}")
    LeaveTypeDto replaceDesg(@RequestBody LeaveTypeDto newLeaveTypeDto , @PathVariable Long id) {
//    LeaveTypeDto replaceDesg(@RequestBody Desg newDesg, @PathVariable Long id) {

        //return leaveTypeService.update(newLeaveTypeDto);
        return leaveTypeService.update(newLeaveTypeDto, id);

    }

    @DeleteMapping("/{id}")
    void deleteDesg(@PathVariable Long id) {
        leaveTypeService.deleteById(id);
    }
}
