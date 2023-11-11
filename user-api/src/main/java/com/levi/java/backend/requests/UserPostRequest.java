package com.levi.java.backend.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserPostRequest {
    @NotBlank(message = "Nome é obrigatório")
    private String name;
    @NotBlank(message = "CPF é obrigatório")
    private String cpf;
    private String address;
    @NotBlank(message = "E-mail é obrigatório")
    private String email;
    private String telephone;
    private LocalDateTime createdAt;
}
