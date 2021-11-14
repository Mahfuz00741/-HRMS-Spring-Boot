package com.ibcs.attendance.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "MONTHLY_LINE_PROC")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MonthlyLineProc extends BaseEntity {

    public enum Status {
        PENDING,APPROVED,REJECTED,CANCELLED
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "Attn_Status",length = 9, nullable = false)
    private Status  attnStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "Attn_Sub_Status",length = 9, nullable = false)
    private Status  attnSubStatus;

    @Column(nullable = false, length = 5)
    private Long dayCount;

    @Column(name= "MONTHLY_PROC_ID")
    private Long monthlyProcId;

}
