package com.example.session08.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class DepartmentDTO {

    @NotBlank(message = "Tên phòng ban không được để trống")
    @Size(min = 5, max = 50, message = "Tên phòng ban phải có từ 5-50 kí tự")
    private String name;

    @Size(max = 100, message = "Mô tả không được vượt quá 100 kí tự")
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DepartmentDTO(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public DepartmentDTO() {
    }
}
