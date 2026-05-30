package com.doconnect.doconnectai.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AnswerRequest {

    @NotBlank
    private String content;

    private Long questionId;
}