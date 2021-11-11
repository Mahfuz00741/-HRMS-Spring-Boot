package com.ibcs.tnl.dto;

import com.ibcs.tnl.entity.LeaveType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaveTypeDto extends BaseDto{

    private String name;

    private Integer allowedLeaveNoMonthly;

    private Integer allowNoOfLeaveYearly;

    private boolean active;

    private String userMessage;

}
