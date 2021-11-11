package com.ibcs.report.api;


import com.ibcs.report.dto.ReportDto;
import com.ibcs.report.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.FileNotFoundException;
import java.sql.SQLException;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/reportApi")
@Slf4j
public class ReportApi {

    @Autowired
    private ReportService reportService;

    //   http://localhost:9094/report/reportApi/reportDept?deptId=1&type=pdf
    @PostMapping("/report")
    public ResponseEntity exportReport( @RequestBody ReportDto reportDto) throws FileNotFoundException,
            JRException, SQLException {
        System.out.println("reportDto"+ reportDto);

        return reportService.exportReport(reportDto);
    }




}
