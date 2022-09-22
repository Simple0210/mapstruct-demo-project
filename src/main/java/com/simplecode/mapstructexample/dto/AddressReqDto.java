package com.simplecode.mapstructexample.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressReqDto {
    private String regionName;
    private String districtName;
    private String streetName;
    private String homeNum;
}
