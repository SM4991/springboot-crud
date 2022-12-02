package com.java.crudbolierplate.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUserDto {
    private String name;
    private String mobile;
    private String email;
    private String password;
    private String dob;
}
