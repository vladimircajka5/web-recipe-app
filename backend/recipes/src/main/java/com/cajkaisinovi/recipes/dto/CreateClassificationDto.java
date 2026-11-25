package com.cajkaisinovi.recipes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateClassificationDto {

    @NotBlank(message = "Name is required.")
    @Size(max = 255, message = "Name must not exceed 255 characters.")
    private String name;

    public CreateClassificationDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}