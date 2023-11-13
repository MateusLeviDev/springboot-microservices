package com.levi.java.backend.mapper.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserPutRequest {
    private long id;
    private String name;
    private String cpf;
    private String address;
    private String email;
    private String telephone;
}
