package com.levi.java.backend.service;

import com.levi.java.backend.domain.User;
import com.levi.java.backend.dto.UserDTO;
import com.levi.java.backend.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository repository;

    public List<UserDTO> getAll() {
        List<User> users = repository.findAll();

        return users.stream()
                .map(UserDTO::convert)
                .collect(Collectors.toList());
    }

    public UserDTO findById(long userId) {
        User user = repository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return UserDTO.convert(user);
    }

    public UserDTO save(UserDTO userDTO) {
        userDTO.setCreatedAt(LocalDateTime.now());
        User savedUser = repository.save(User.convert(userDTO));
        return UserDTO.convert(savedUser);
    }

    public UserDTO delete(long userId) {
        User user = repository.findById(userId).orElseThrow(RuntimeException::new);
        repository.delete(user);
        return UserDTO.convert(user);
    }

    public UserDTO findByCpf(String cpf) {
        User byCpf = repository.findByCpf(cpf);
        if (byCpf != null) {
            return UserDTO.convert(byCpf);
        }
        return null;
    }

    public List<UserDTO> queryByName(String name) {
        List<User> users = repository.queryByNameLike(name);
        return users
                .stream()
                .map(UserDTO::convert)
                .collect(Collectors.toList());
    }

    public UserDTO editUser(Long userId, UserDTO userDTO) {
        User user = repository
                .findById(userId).orElseThrow(RuntimeException::new);
        if (userDTO.getEmail() != null &&
                !user.getEmail().equals(userDTO.getEmail())) {
            user.setEmail(userDTO.getEmail());
        }
        if (userDTO.getTelephone() != null &&
                !user.getTelephone().equals(userDTO.getTelephone())) {
            user.setTelephone(userDTO.getTelephone());
        }
        if (userDTO.getAddress() != null &&
                !user.getAddress().equals(userDTO.getAddress())) {
            user.setAddress(userDTO.getAddress());
        }
        user = repository.save(user);
        return UserDTO.convert(user);
    }
}
