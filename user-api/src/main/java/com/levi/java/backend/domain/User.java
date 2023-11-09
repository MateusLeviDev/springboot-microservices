package com.levi.java.backend.domain;

import com.levi.java.backend.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nome")
    private String name;
    private String cpf;
    @Column(name = "endereco")
    private String address;
    private String email;
    @Column(name = "telefone")
    private String telephone;
    @Column(name = "data_cadastro")
    private LocalDateTime createdAt = LocalDateTime.now();

    public static User convert(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setAddress(userDTO.getAddress());
        user.setCpf(userDTO.getCpf());
        user.setEmail(userDTO.getEmail());
        user.setTelephone(userDTO.getTelephone());
        user.setCreatedAt(userDTO.getCreatedAt());
        return user;
    }
}
