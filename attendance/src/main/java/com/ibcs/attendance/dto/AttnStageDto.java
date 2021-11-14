package com.ibcs.attendance.dto;

import com.ibcs.attendance.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttnStageDto extends BaseEntity {

    private String empCode;

    private LocalDateTime onMoment;

    private String deviceId;

    private String remark;

    private String userMessage;

}
