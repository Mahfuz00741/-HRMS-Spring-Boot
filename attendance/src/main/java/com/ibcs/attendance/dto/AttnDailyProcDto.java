package com.ibcs.attendance.dto;


import com.ibcs.attendance.model.AttnDailyProc;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttnDailyProcDto extends BaseDto {


    private String src;


    private Long employeeId;


    private LocalDate onDate;


    private LocalDateTime inMoment;


    private LocalDateTime outMoment;


    private LocalDateTime totalWorkingHr;


    private LocalDateTime lateMin;


    private LocalDateTime earlyOutMin;


    private String attnStatus;


    private String attnSubStatus;


    private String remarks;


}
