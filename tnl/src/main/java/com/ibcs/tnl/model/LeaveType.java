package com.ibcs.tnl.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="TNL_LEAVE_TYPE")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaveType extends BaseEntity {

    @Column(nullable=false, length=35)
    private String name;

    @Column(name="ALLOW_NO_OF_LEAVE_MONTH", nullable = false)
    private Integer allowedLeaveNoMonthly;

    @Column(name="ALLOW_NO_OF_LEAVE_YEARLY",nullable = false )
    private Integer allowNoOfLeaveYearly;

    @Column(name = "IS_ACTIVE", nullable = false)
    private boolean active;




}
