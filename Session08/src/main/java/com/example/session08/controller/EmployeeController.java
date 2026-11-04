package com.example.session08.controller;

import com.example.session08.dto.EmployeeCreateDTO;
import com.example.session08.entity.Employee;
import com.example.session08.response.ApiResponse;
import com.example.session08.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Employee>> createEmmployee(
            @Valid @RequestBody EmployeeCreateDTO dto){
        Employee employee = employeeService.createEmployee(dto);

        ApiResponse<Employee> response = new ApiResponse<>(
                "SUCCESS",
                "Thêm nhân viên thành công",
                employee
        );

        return ResponseEntity.ok(response);
    }
}
