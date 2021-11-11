package com.ibcs.tnl.Client;

import com.ibcs.tnl.dto.DesgDto;
import com.ibcs.tnl.dto.EmpDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="HR-SERVICE")
public interface Consumer {

    @GetMapping("/hr/empApi/{id}")
    public EmpDto getEmp(@PathVariable Long id);
}
