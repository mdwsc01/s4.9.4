package com.example.session08.controller;

import com.example.session08.dto.DepartmentDTO;
import com.example.session08.entity.Department;
import com.example.session08.response.ApiResponse;
import com.example.session08.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Department>> createDepartment(
            @Valid @RequestBody DepartmentDTO dto) {
        Department department = departmentService.createDepartment(dto);

        ApiResponse<Department> response = new ApiResponse<>(
                "SUCCESS",
                "Thêm phòng ban thành công",
                department
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
