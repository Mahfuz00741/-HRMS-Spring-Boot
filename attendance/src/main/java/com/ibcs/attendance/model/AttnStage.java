package com.ibcs.attendance.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@Table(name = "ATTN_STAGE")
@NoArgsConstructor
@AllArgsConstructor
public class AttnStage extends BaseEntity{

    @Column()
    private Long empCode;

    @Column()
    private Long onMoment;

    @Column()
    private Long deviceId;

    @Column()
    private String remark;

}
