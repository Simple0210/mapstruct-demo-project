package com.simplecode.mapstructexample.rest;

import com.simplecode.mapstructexample.dto.ApiResponse;
import com.simplecode.mapstructexample.dto.EmpReqDto;
import com.simplecode.mapstructexample.dto.EmpResDto;
import com.simplecode.mapstructexample.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/get")
    public ResponseEntity<ApiResponse<List<EmpResDto>>> getAllEmployee() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ApiResponse<EmpResDto>> getEmployeeById(@PathVariable Integer id) {
        return employeeService.getEmployeeById(id);
    }

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<String>> saveEmployee(@RequestBody EmpReqDto empReqDto) {
        return employeeService.saveEmployee(empReqDto);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<ApiResponse<String>> editEmployee(@PathVariable Integer id, @RequestBody EmpReqDto empReqDto) {
        return employeeService.editEmployee(id, empReqDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<String>> deleteEmployee(@PathVariable Integer id) {
        return null;
    }
}
