package com.levi.java.backend.mapper.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data//getters, setters, equals, hashCode and toString
@Builder //build()
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private String name;
    private String cpf;
    private String email;
    private String telephone;
}
