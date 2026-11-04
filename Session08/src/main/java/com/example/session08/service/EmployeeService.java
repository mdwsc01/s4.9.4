package com.example.session08.service;

import com.example.session08.dto.EmployeeCreateDTO;
import com.example.session08.entity.Department;
import com.example.session08.entity.Employee;
import com.example.session08.exception.DuplicateResourceException;
import com.example.session08.exception.ResourceNotFoundException;
import com.example.session08.repository.DepartmentRepository;
import com.example.session08.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public EmployeeService(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    @Transactional
    public Employee createEmployee(EmployeeCreateDTO dto) {
        Department department = departmentRepository
                .findById(dto.getDepartmentId())
                .orElseThrow(()-> new ResourceNotFoundException("Phòng ban không tồn tại"));

        if(dto.getEmail() != null &&
            employeeRepository.existsByEmail(dto.getEmail())) {
            throw new DuplicateResourceException(
                    "Email đã được sử dụng"
            );
        }

        Employee employee = new Employee();

        employee.setFullName(dto.getFullName());
        employee.setEmail(dto.getEmail());
        employee.setPhone(dto.getPhone());
        employee.setSalary(
                dto.getSalary() == null
                ? null : dto.getSalary().doubleValue()
        );

        employee.setDepartment(department);

        return employeeRepository.save(employee);
    }

}
