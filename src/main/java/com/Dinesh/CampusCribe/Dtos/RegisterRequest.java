package com.Dinesh.CampusCribe.Dtos;


import lombok.Data;

@Data
public class RegisterRequest {
    private String email;
    private String userName;
    private String password;
    private String role;
}

