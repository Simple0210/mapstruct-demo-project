package com.simplecode.mapstructexample.mapper;

import com.simplecode.mapstructexample.dto.EmpReqDto;
import com.simplecode.mapstructexample.dto.EmpResDto;
import com.simplecode.mapstructexample.entity.Employee;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring",uses = {AddressMapper.class})
public interface EmployeeMapper {

    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    @Mapping(source = "employee.id", target = "idishka")
    @Mapping(source = "employee.address",target = "addressResDto")
    EmpResDto toEmpResDto(Employee employee);

    @InheritInverseConfiguration
    Employee toEmployee(EmpReqDto empReqDto);

    List<EmpResDto> toEmpResDtos(List<Employee> employees);
}
