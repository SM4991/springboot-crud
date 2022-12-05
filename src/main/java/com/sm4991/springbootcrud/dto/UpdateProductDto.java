package com.sm4991.springbootcrud.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateProductDto {
    private String name;
    private String dob;
}
