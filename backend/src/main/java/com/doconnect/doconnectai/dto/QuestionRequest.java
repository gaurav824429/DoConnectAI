package com.doconnect.doconnectai.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class QuestionRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String description;
}