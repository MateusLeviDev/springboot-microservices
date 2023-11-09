package com.levi.java.backend.converter;

import com.levi.java.backend.domain.User;
import com.levi.java.backend.dto.UserDTO;

public class DTOConverter {
    public static UserDTO convert(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setName(user.getName());
        userDTO.setAddress(user.getAddress());
        userDTO.setCpf(user.getCpf());
        userDTO.setEmail(user.getEmail());
        userDTO.setTelephone(user.getTelephone());
        userDTO.setCreatedAt(user.getCreatedAt());
        return userDTO;
    }
}
