package com.example.session08.dto;

import jakarta.validation.constraints.*;

public class EmployeeCreateDTO {
    @NotBlank(message = "Họ tên không được để trống")
    private String fullName;

    @Email(message = "Email không đúng định dạng")
    private String email;

    @Pattern(
            regexp = "^(03|05|07|08|09)[0-9]{8}$",
            message = "Số điện thoại không hợp lệ"
    )
    private String phone;

    @Min(
            value = 5000000,
            message = "Lương phải từ 5.000.000 trở lên"
    )
    private Long salary;

    @NotNull(message = "Phòng ban không được để trống")
    private Long departmentId;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public EmployeeCreateDTO(String fullName, String email, String phone, Long salary, Long departmentId) {
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
        this.departmentId = departmentId;
    }

    public EmployeeCreateDTO() {
    }
}
