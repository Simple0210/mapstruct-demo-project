package com.simplecode.mapstructexample.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmpResDto {
    private Integer idishka;
    private String fullName;
    private Double salary;
    private AddressResDto addressResDto;

}
