package com.ibcs.hr.service;

import com.ibcs.hr.client.UserConsumer;
import com.ibcs.hr.dto.EmpDto;
import com.ibcs.hr.dto.ResponsFeignClientDto;
import com.ibcs.hr.dto.UserDto;
import com.ibcs.hr.entity.Desg;
import com.ibcs.hr.entity.Emp;
import com.ibcs.hr.repo.DeptRepo;
import com.ibcs.hr.repo.DesgRepo;
import com.ibcs.hr.repo.EmpRepo;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.reactive.function.client.WebClient;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.ByteArrayOutputStream;
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

@Slf4j
@Service
public class EmpService {

    @Autowired
    private EmpRepo empRepo;
    @Autowired
    private DeptRepo deptRepo;
    @Autowired
    private DesgRepo desgRepo;
    @Autowired
    private UserConsumer consumer;

    @Autowired
    private DataSource dataSource;

    private EmpDto conv(Emp emp) {
        EmpDto empDto = new EmpDto();
        BeanUtils.copyProperties(emp, empDto, "deptId", "desgId", "supervisorId", "gender");
        empDto.setDeptId(emp.getDeptId().getId());
        empDto.setDesgId(emp.getDesgId().getId());
        empDto.setSupervisorId(emp.getSupervisorId().getId());
        empDto.setGender(emp.getGender().name());

        return empDto;
    }

    public EmpDto update(EmpDto empDto, Long id) {

        Emp emp = empRepo.getById(id);
        BeanUtils.copyProperties(empDto, emp, "id", "deptId", "desgId", "supervisorId", "gender");
        emp.setDeptId(deptRepo.getById(empDto.getDeptId()));
        emp.setDesgId(desgRepo.getById(empDto.getDesgId()));
        emp.setSupervisorId(empRepo.getById(empDto.getSupervisorId()));
        emp.setGender(Emp.Gender.valueOf(empDto.getGender()));

        return conv(empRepo.save(emp));
    }

    public EmpDto save(EmpDto empDto) {

        Emp emp = new Emp();
        BeanUtils.copyProperties(empDto, emp, "deptId", "desgId", "supervisorId", "gender");
        emp.setDeptId(deptRepo.getById(empDto.getDeptId()));
        emp.setDesgId(desgRepo.getById(empDto.getDesgId()));
        emp.setSupervisorId(empRepo.getById(empDto.getSupervisorId()));
        emp.setGender(Emp.Gender.valueOf(empDto.getGender()));

        return conv(empRepo.save(emp));
    }

    public Page<EmpDto> findAll(Pageable pageable, String sText) {
        Page<Emp> emp = empRepo.findAllCustom(pageable, sText);
        //PageRequest<EmpDto> empDtos= PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());

        List<EmpDto> ss = new ArrayList(pageable.getPageSize());
        for (Emp pp : emp.getContent()) {
            ss.add(conv(pp));
        }

        Page<EmpDto> empDtos = new PageImpl(ss, pageable, emp.getTotalElements());


        return empDtos;
    }


    public ResponsFeignClientDto findUserFeignClient(Long id) {
        try {
            ResponsFeignClientDto responsFeignClientDto = new ResponsFeignClientDto();
            Emp emp = empRepo.getById(id);
            if (emp == null) {
                return new ResponsFeignClientDto("User not found", null, null);
            } else {

                responsFeignClientDto.setEmpDto(conv(emp));
                System.out.println("UserID:: " + emp.getUserId());
                UserDto userDto = consumer.getUser(emp.getUserId());
                System.out.println("UserConsumerID:: " + userDto);
                responsFeignClientDto.setUserDto(userDto);

                responsFeignClientDto.setUserMessage("Successfully get user information.");

                return responsFeignClientDto;
            }
        } catch (Exception e) {
            log.error("Exception occurred during getting user info", e);
            return new ResponsFeignClientDto(e.getMessage(), null, null);
        }
    }

    public UserDto findByUserWebId(Integer id) {

        WebClient webClient = WebClient.builder()
                .baseUrl("http://localhost:9091")
                .build();

        return webClient.get()
                .uri("/admin/user/" + id)
                .retrieve()
                .bodyToMono(UserDto.class)
                .block();
    }

    public EmpDto findById(Long id) {

        try {
            EmpDto empDto = new EmpDto();
            Emp emp = empRepo.getById(id);
            if (emp == null) {
                return new EmpDto(null, null, null, null, null, null, null, null, null, null, false, null, null, null, null, "User not found");
            } else {

                BeanUtils.copyProperties(emp, empDto);
                empDto.setUserMessage("Successfully get user information.");

                return empDto;
            }
        } catch (Exception e) {
            log.error("Exception occurred during getting user info", e);
            return new EmpDto(null, null, null, null, null, null, null, null, null, null, false, null, null, null, null, "User not found");

        }
    }

    public void deleteById(Long id) {
        empRepo.deleteById(id);
    }

}