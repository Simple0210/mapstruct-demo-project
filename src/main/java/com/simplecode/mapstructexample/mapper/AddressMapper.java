package com.simplecode.mapstructexample.mapper;

import com.simplecode.mapstructexample.dto.AddressReqDto;
import com.simplecode.mapstructexample.dto.AddressResDto;
import com.simplecode.mapstructexample.entity.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    @Mapping(source = "address.id",target = "idishka")
    @Mapping(source = "address.region", target = "regionName")
    @Mapping(source = "address.district", target = "districtName")
    @Mapping(source = "address.street", target = "streetName")
    @Mapping(source = "address.homeNumber", target = "homeNum")
    AddressResDto addressToDto(Address address);
    
    @Mapping(source = "addressReqDto.regionName", target = "region")
    @Mapping(source = "addressReqDto.districtName", target = "district")
    @Mapping(source = "addressReqDto.streetName", target = "street")
    @Mapping(source = "addressReqDto.homeNum", target = "homeNumber")
    Address dtoToAddress(AddressReqDto addressReqDto);

    List<AddressResDto> addressesToDtos(List<Address> addresses);
}
