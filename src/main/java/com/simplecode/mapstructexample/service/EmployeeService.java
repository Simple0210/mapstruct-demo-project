package com.simplecode.mapstructexample.service;

import com.simplecode.mapstructexample.dto.ApiResponse;
import com.simplecode.mapstructexample.dto.EmpReqDto;
import com.simplecode.mapstructexample.dto.EmpResDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EmployeeService {

    ResponseEntity<ApiResponse<List<EmpResDto>>> getAllEmployees();

    ResponseEntity<ApiResponse<EmpResDto>> getEmployeeById(Integer id);

    ResponseEntity<ApiResponse<String>> saveEmployee(EmpReqDto empReqDto);

    ResponseEntity<ApiResponse<String>> editEmployee(Integer id, EmpReqDto empReqDto);

    ResponseEntity<ApiResponse<String>> deleteEmployee(Integer id);

}
