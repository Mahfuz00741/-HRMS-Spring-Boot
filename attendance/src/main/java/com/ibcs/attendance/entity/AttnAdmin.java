package com.ibcs.attendance.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "ATTN_ADMIN")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class AttnAdmin extends BaseEntity{

    @Column(name = "APP_DATE", nullable=false)
    private LocalDate appDate;

    @Column(unique = true, name = "ADM_USER_ID")
    private Long userId;

    @Column(name = "HR_EMP_ID", nullable = false)
    private Long empId;

    @Column(name = "ON_MOMENT", nullable=false)
    private LocalDateTime OnMoment;

    @Column(name = "IN_OUT", nullable=false)
    private LocalDateTime inOut;

    @Column(length=254)
    private String remark;


}
