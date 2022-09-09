package com.example.vabatahtlikud.infrastructure.error;

import lombok.Data;

@Data
public class ApiError {
    private String title;
    private Integer statusCode;
    private String detail;
}

