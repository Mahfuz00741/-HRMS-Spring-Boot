package com.ibcs.attendance.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

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

    @Column(name = "ADM_USER_ID", nullable = false)
    private Long userId;

    @Column(name = "HR_EMP_ID", nullable = false)
    private Long empId;

    @Column(name = "ON_MOMENT", nullable=false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalDateTime OnMoment;

    @Column(name = "_IN", nullable=false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalDateTime in;

    @Column(name = "_OUT", nullable=false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalDateTime out;

    @Column(length=254)
    private String remarks;

}
