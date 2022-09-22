package com.simplecode.mapstructexample.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressResDto {
    private Integer idishka;
    private String regionName;
    private String districtName;
    private String streetName;
    private String homeNum;
}
