package com.java.crudbolierplate.dto;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private String name;
    private String mobile;
    private String email;
    private String password;
    private String dob;
    private String role;
}
