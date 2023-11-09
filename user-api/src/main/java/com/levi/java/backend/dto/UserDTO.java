package com.levi.java.backend.dto;

import com.levi.java.backend.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO {
    @NotBlank(message = "Name cannot be null")
    private String name;
    @NotBlank(message = "Cpf cannot be null")
    private String cpf;
    private String address;
    @NotBlank(message = "Email cannot be null")
    private String email;
    private String telephone;
    private LocalDateTime createdAt;

    public static UserDTO convert(User user) {
        UserDTO userDTO = new UserDTO();
        user.setName(userDTO.getName());
        user.setAddress(userDTO.getAddress());
        user.setCpf(userDTO.getCpf());
        user.setEmail(userDTO.getEmail());
        user.setTelephone(userDTO.getTelephone());
        user.setCreatedAt(userDTO.getCreatedAt());
        return userDTO;
    }
}
