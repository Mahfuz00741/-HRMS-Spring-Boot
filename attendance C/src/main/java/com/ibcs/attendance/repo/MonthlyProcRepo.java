package com.ibcs.attendance.repo;

import com.ibcs.attendance.model.MonthlyProc;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonthlyProcRepo extends JpaRepository<MonthlyProc,Long> {
}
