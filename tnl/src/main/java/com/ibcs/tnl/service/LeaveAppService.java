package com.ibcs.tnl.service;

import com.ibcs.tnl.dto.DesgDto;
import com.ibcs.tnl.dto.EmpDto;
import com.ibcs.tnl.dto.LeaveAppDto;
import com.ibcs.tnl.dto.ResponsFeignClientDto;
import com.ibcs.tnl.entity.LeaveApp;
import com.ibcs.tnl.repo.LeaveAppRepo;
import com.ibcs.tnl.repo.LeaveTypeRepo;
import com.ibcs.tnl.Client.Consumer;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

@Service
@Slf4j
public class LeaveAppService {

    @Autowired
    private LeaveAppRepo leaveAppRepo;

    @Autowired
    private LeaveTypeRepo leaveTypeRepo;

    @Autowired
    private Consumer consumer;



    private LeaveAppDto conv(LeaveApp leaveApp) {//converting<-------------
        LeaveAppDto leaveAppDto = new LeaveAppDto();
        BeanUtils.copyProperties(leaveApp, leaveAppDto, "leaveTypeId");
        leaveAppDto.setLeaveTypeId(leaveApp.getLeaveTypeId().getId());
        return leaveAppDto;
    }

    private LeaveApp conv(LeaveAppDto leaveAppDto) {//converting<--------------

        LeaveApp leaveApp = new LeaveApp();

        BeanUtils.copyProperties(leaveAppDto, leaveApp, "leaveTypeId");
        leaveApp.setLeaveTypeId(leaveTypeRepo.getById(leaveAppDto.getLeaveTypeId()));

        return leaveApp;
    }


    public Page<LeaveAppDto> findAll(Pageable pageable, String sText) {//all
        Page<LeaveApp> leaveApp = leaveAppRepo.findAllCustom(pageable, sText);
        //PageRequest<LeaveAppDto> deptDtos= PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());

        List<LeaveAppDto> ss = new ArrayList(pageable.getPageSize());
        for (LeaveApp pp : leaveApp.getContent()) {
            ss.add(conv(pp));
        }

        Page<LeaveAppDto> deptDtos = new PageImpl(ss, pageable, leaveApp.getTotalElements());

        return deptDtos;
    }

    public ResponsFeignClientDto findUserFeignClient(Long id){
        try {
            ResponsFeignClientDto responsFeignClientDto = new ResponsFeignClientDto();
            LeaveApp leaveApp = leaveAppRepo.getById(id);
            if(leaveApp == null){
                return new ResponsFeignClientDto("User not found", null, null);
            }else {

                responsFeignClientDto.setLeaveAppDto(conv(leaveApp));

                EmpDto empDto = consumer.getEmp(leaveApp.getId());
                responsFeignClientDto.setEmpDto(empDto);
                responsFeignClientDto.setUserMessage("Successfully get user information.");

                return responsFeignClientDto;
            }
        }
        catch (Exception e) {
            log.error("Exception occurred during getting user info", e);
            return new ResponsFeignClientDto(e.getMessage(), null, null);
        }
    }

    public LeaveAppDto findById(Long id) { //byId

        try {
            LeaveAppDto leaveAppDto = new LeaveAppDto();
            LeaveApp leaveApp = leaveAppRepo.getById(id);
            if (leaveApp == null) {
                return new LeaveAppDto(null, null, null, null, null, null, null, null, null, false, null, "User not found");
            } else {
                BeanUtils.copyProperties(leaveApp, leaveAppDto);
                leaveAppDto.setUserMessage("Successfully get user information.");

                return leaveAppDto;
            }

        } catch (Exception e) {
            log.error("Exception occurred during getting user info", e);
            return new LeaveAppDto(null, null, null, null, null, null, null, null, null, false, null, "User not found");
        }
    }

    public LeaveAppDto save(LeaveAppDto leaveAppDto) {//post
        return conv(leaveAppRepo.save(conv(leaveAppDto)));
    }

    public LeaveAppDto update(LeaveAppDto leaveAppDto, Long id) {

        LeaveApp leaveApp = leaveAppRepo.getById(id);
        BeanUtils.copyProperties(leaveAppDto, leaveApp, "id");

        return conv(leaveAppRepo.save(leaveApp));
    }

    public void deleteById(Long id) {//delete
        leaveAppRepo.deleteById(id);
    }

    ////////////////////////////////////////
    //////Report Part Start///////////
////////////////////////////////////////
    // Export Report With Department
    public ResponseEntity exportReportWithLeaveStutas(String reportFormat, Integer deptId, Date fromDate, Date toDate) throws FileNotFoundException, JRException {
        String path = "D:\\Projects\\JavaProject\\IBCS-TNL\\tnl\\src\\main\\resources";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Qwer123#");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //load file and compile it
        File file = ResourceUtils.getFile("classpath:AllEmployeeLeaveSummary.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("P_EMP_HR_DEPT_ID", deptId);
        parameters.put("P_TO_DATE", toDate);
        parameters.put("P_FROM_DATE", fromDate);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);
        int i = (int) Math.random();
        System.out.println("Random::"+i);
        if (reportFormat.equalsIgnoreCase("html")) {
            path = path + "\\employeeLeaveSummary"+i+".html";
            JasperExportManager.exportReportToHtmlFile(jasperPrint, path);
        }else {
            path = path + "\\employeeLeaveSummary"+i+".pdf";
            JasperExportManager.exportReportToPdfFile(jasperPrint, path);
        }
        File file1 = new File(path);
        Path fPath1 = Paths.get(file1.getAbsolutePath());
        ByteArrayResource resource = null;
        try {
            resource = new ByteArrayResource(Files.readAllBytes(fPath1));
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("content-disposition", "attachment; filename=" + "employeeLeaveSummary.pdf");
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file1.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    // Export Report With Designation
    public ResponseEntity exportReportWithDesignation(String reportFormat, Integer desgId) throws FileNotFoundException, JRException {
        String path = "D:\\Projects\\JavaProject\\IBCS-TNL\\hr\\src\\main\\resources";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Qwer123#");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //load file and compile it
        File file = ResourceUtils.getFile("classpath:ListOfEmployeeDesignation.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("P_DESG", desgId);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);

        int i = (int) Math.random();
        System.out.println("Random::"+i);

        if (reportFormat.equalsIgnoreCase("html")) {
            path = path + "\\employeeDesg"+i+".html";
            JasperExportManager.exportReportToHtmlFile(jasperPrint, path);
        }else {
            path = path + "\\employeeDesg"+i+".pdf";
            JasperExportManager.exportReportToPdfFile(jasperPrint, path);
        }

        File file1 = new File(path);
        Path fPath1 = Paths.get(file1.getAbsolutePath());

        ByteArrayResource resource = null;
        try {
            resource = new ByteArrayResource(Files.readAllBytes(fPath1));
        } catch (IOException e) {
            e.printStackTrace();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("content-disposition", "attachment; filename=" + "employeeDesg.pdf");
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file1.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
////////////////////////////////////////
    //////Report Part End///////////
////////////////////////////////////////



}
