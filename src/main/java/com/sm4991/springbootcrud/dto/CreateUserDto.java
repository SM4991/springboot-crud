package com.sm4991.springbootcrud.dto;

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
