package com.ibcs.tnl.api;

import com.ibcs.tnl.dto.LeaveAppDto;
import com.ibcs.tnl.service.LeaveAppService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import javax.ws.rs.QueryParam;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Date;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/leaveAppApi")
public class LeaveAppApi {

    @Autowired
    private LeaveAppService leaveAppService;

    @GetMapping("/")
    Page<LeaveAppDto> all() {
        return leaveAppService.findAll(PageRequest.of(0, 10), null);
    }
    @GetMapping("/{id}")
    LeaveAppDto one(@PathVariable Long id) {
        return leaveAppService.findById(id);
    }
    @PostMapping("/")
    LeaveAppDto newApp(@RequestBody LeaveAppDto newLeaveAppDto) {
        return leaveAppService.save(newLeaveAppDto);
    }



    @PutMapping("/{id}")
     public LeaveAppDto replaceApp(@RequestBody LeaveAppDto newLeaveAppDto, @PathVariable Long id) {

        return leaveAppService.update(newLeaveAppDto,id);

    }

    @DeleteMapping("/{id}")
    void deleteApp(@PathVariable Long id) {
        leaveAppService.deleteById(id);
    }

}
