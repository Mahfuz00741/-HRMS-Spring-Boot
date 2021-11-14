package com.ibcs.attendance.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MonthlyProc extends BaseEntity {


    @Column(name="Employee_ID")
    private Long EmpId;

    @Column()
    private LocalDate OnDate;

    @Column(name="Total_Present_Day")
    private String totalPresentDay;

    @Column(name="Total_Absent_Day")
    private String totalAbsentDay;

    @Column( name="Total_Leave_Day")
    private String totalLeaveDay;

    @Column( name="Total_Working_Hour")
    private String totalWorkingHr;

    @Column(name="Total_Late_Min")
    private String totalLateMin;

    @Column()
    private String totalAbsentMin;





}
