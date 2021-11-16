package com.ibcs.attendance.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDate;
import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MonthlyProcDto extends BaseDto {



    private Long EmpId;


    private LocalDate OnDate;


    private String totalPresentDay;


    private String totalAbsentDay;


    private String totalLeaveDay;


    private String totalWorkingHr;


    private String totalLateMin;


    private String totalAbsentMin;
}
