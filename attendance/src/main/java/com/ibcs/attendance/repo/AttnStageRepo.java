package com.ibcs.attendance.repo;

import com.ibcs.attendance.model.AttnStage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AttnStageRepo extends JpaRepository<AttnStage, Long> {

    //@Query("SELECT m FROM AttnAdmin m WHERE :sText is null or lower( m.appDate || m.OnMoment ) LIKE '%lower(:sText)%' ORDER BY m.appDate")
//    @Query("SELECT m FROM AttnStage m WHERE :sText   LIKE '%lower(:sText)%' ")
    @Query("SELECT m FROM AttnStage m ")
    Page<AttnStage> findAllCustom(Pageable pageable, @Param("sText") String sText);

}
