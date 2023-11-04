package com.levi.java.backend.dto;

import com.levi.java.backend.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO {
    private String name;
    private String cpf;
    private String address;
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
