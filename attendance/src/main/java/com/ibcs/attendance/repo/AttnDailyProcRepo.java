package com.ibcs.attendance.repo;

import com.ibcs.attendance.model.AttnDailyProc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AttnDailyProcRepo  extends JpaRepository<AttnDailyProc, Long> {

    @Query("SELECT m FROM AttnDailyProc m WHERE :sText   LIKE '%lower(:sText)%' ")
    Page<AttnDailyProc> findAllCustom(Pageable pageable, @Param("sText") String sText);


}
