package com.simplecode.mapstructexample.service;

import com.simplecode.mapstructexample.dto.ApiResponse;
import com.simplecode.mapstructexample.dto.EmpReqDto;
import com.simplecode.mapstructexample.dto.EmpResDto;
import com.simplecode.mapstructexample.entity.Address;
import com.simplecode.mapstructexample.entity.Employee;
import com.simplecode.mapstructexample.mapper.EmployeeMapper;
import com.simplecode.mapstructexample.repository.AddressRepository;
import com.simplecode.mapstructexample.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final AddressRepository addressRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper, AddressRepository addressRepository) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
        this.addressRepository = addressRepository;
    }

    @Override
    public ResponseEntity<ApiResponse<List<EmpResDto>>> getAllEmployees() {
        List<Employee> all = employeeRepository.findAll();
        if (!all.isEmpty()) {
            List<EmpResDto> empResDtos = employeeMapper.toEmpResDtos(all);
            return new ResponseEntity<>(new ApiResponse<>(true, empResDtos, ""), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse<>(false, new ArrayList<>(), "Employees not found!"), HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<ApiResponse<EmpResDto>> getEmployeeById(Integer id) {
        EmpResDto empResDto = employeeMapper.toEmpResDto(employeeRepository.findById(id).orElse(null));
        if (empResDto != null) {
            return new ResponseEntity<>(new ApiResponse<>(true, empResDto, ""), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse<>(false, null, "Employee not found!"), HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<ApiResponse<String>> saveEmployee(EmpReqDto empReqDto) {
        if (empReqDto != null) {
            if (empReqDto.getAddressId()!=null){
                Address address = addressRepository.findById(empReqDto.getAddressId()).orElse(null);
                if (address!=null){
                    Employee employee = employeeMapper.toEmployee(empReqDto);
                    employee.setAddress(address);
                    employeeRepository.save(employee);
                    return new ResponseEntity<>(new ApiResponse<>(true, "Employee saved!", ""), HttpStatus.OK);
                }
                return new ResponseEntity<>(new ApiResponse<>(false, "Address not found!", ""), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(new ApiResponse<>(false, "Address not found!", ""), HttpStatus.BAD_REQUEST);

        }
        return new ResponseEntity<>(new ApiResponse<>(false, "Employee not saved!", ""), HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<ApiResponse<String>> editEmployee(Integer id, EmpReqDto empReqDto) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee != null) {
            Employee employee1 = employeeMapper.toEmployee(empReqDto);
            employee1.setId(id);
            employeeRepository.save(employee1);
            return new ResponseEntity<>(new ApiResponse<>(true, null, "Employee changed successfully!"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse<>(true, null, "Employee not found!"), HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<ApiResponse<String>> deleteEmployee(Integer id) {
        return null;
    }
}
