package com.simplecode.mapstructexample.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmpReqDto {
    private String fullName;
    private Double salary;
    private Integer addressId;
}
