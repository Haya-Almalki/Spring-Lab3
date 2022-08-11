package com.example.SpringLab3.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor @Data
public class ApiResponse {

    private String message;
    private int status;
}
