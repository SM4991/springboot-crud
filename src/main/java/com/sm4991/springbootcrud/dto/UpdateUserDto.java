package com.sm4991.springbootcrud.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateUserDto {
    private String name;
    private String password;
    private String dob;
}
