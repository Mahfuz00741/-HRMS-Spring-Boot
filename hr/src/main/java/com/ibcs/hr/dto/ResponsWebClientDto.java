package com.ibcs.hr.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponsWebClientDto {

    private String userMessage;
    private  EmpDto empDto;
    private UserDto userDto;
}
