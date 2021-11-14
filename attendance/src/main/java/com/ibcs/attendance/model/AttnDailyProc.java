<<<<<<< HEAD
package com.ibcs.attendance.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "ATTN_Daiy_Proc")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttnDailyProc extends BaseEntity {

    public enum Src {
        BIOMETRIC,ADMIN
    }

    @Enumerated(EnumType.STRING)
    @Column(length = 9, nullable = false)
    private Src src;

    @Column(name = "EMPLOYEE_ID", nullable = false)
    private Long employeeId;

    @Column(name = "On_Date", nullable = false)
    private LocalDate onDate;

    @Column(name = "In_Moment", nullable = false)
    private LocalDateTime inMoment;

    @Column(name = "Out_Moment", nullable = false)
    private LocalDateTime outMoment;

    @Column(name = "Total_Working_Hr", nullable = false)
    private LocalDateTime totalWorkingHr;

    @Column(name = "Late_Min", nullable = false)
    private LocalDateTime lateMin;

    @Column(name = "Early_Out_Min", nullable = false)
    private LocalDateTime earlyOutMin;

    public enum Status {
        PENDING,APPROVED,REJECTED,CANCELLED
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "Attn_Status",length = 9, nullable = false)
    private Status  attnStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "Attn_Sub_Status",length = 9, nullable = false)
    private Status  attnSubStatus;

    @Column()
    private String remarks;
}
=======
//package com.ibcs.attendance.model;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//import java.time.LocalDate;
//
//@Entity
//@Table(name = "ATTN_Daiy_Proc")
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class AttnDailyProc extends BaseEntity {
//
//    public enum Src {
//        BIOMETRIC,ADMIN
//    }
//
//    @Enumerated(EnumType.STRING)
//    @Column(length = 9, nullable = false)
//    private Src src;
//
//    @Column(name = "HR_EMP_ID", nullable = false)
//    private Long employeeId;
//
//    @Column(name = "On_Date", nullable = false)
//    private LocalDate onDate;
//
//    /*inMoment;
//
//    outMoment;
//
//    TotalWorkingHr;
//
//    LateMin;
//
//    earlyOutMin;*/
//
//
//
//
//
//}
>>>>>>> f41209135ed50c331b2a32a03b0f7164e1b8ae41
