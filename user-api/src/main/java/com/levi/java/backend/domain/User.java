package com.levi.java.backend.domain;

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
@Entity(name = "user")
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

}
