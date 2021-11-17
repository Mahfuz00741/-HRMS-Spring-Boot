package com.ibcs.attendance.dto.request;


import com.ibcs.attendance.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttnAdminDto extends BaseDto {

    private LocalDate appDate;

    private Long userId;

    private Long empId;

    private LocalDateTime onMoment;

    private LocalDateTime in;

    private LocalDateTime out;

    private String remarks;

}
