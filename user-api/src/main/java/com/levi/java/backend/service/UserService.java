package com.levi.java.backend.service;

import com.levi.java.backend.domain.User;
import com.levi.java.backend.dto.UserDTO;
import com.levi.java.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public List<UserDTO> getAll() {
        List<User> users = repository.findAll();
        return users.stream()
                .map(UserDTO::convert)
                .collect(Collectors.toList());
    }

    public UserDTO findById(Long userId) {
        User user = repository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return UserDTO.convert(user);
    }

    public UserDTO save(UserDTO userDTO) {
        userDTO.setCreatedAt(LocalDateTime.now());
        User savedUser = repository.save(User.convert(userDTO));
        return UserDTO.convert(savedUser);
    }

    public void delete(Long userId) {
        User user = repository.findById(userId).orElseThrow(RuntimeException::new);
        repository.delete(user);
    }

    public UserDTO findByCpf(String cpf) {
        User user = repository.findByCpf(cpf);
        if (user != null) {
            return UserDTO.convert(user);
        }

        return null;
    }

    public List<UserDTO> queryByName(String name) {
        List<User> users = repository.queryByNameLike(name);
        return users.stream()
                .map(UserDTO::convert)
                .collect(Collectors.toList());
    }

    public UserDTO replace(Long userId, UserDTO userDTO) {
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

    public Page<UserDTO> getAllPageable(Pageable page) {
        Page<User> users = repository.findAll(page);
        return users.map(UserDTO::convert);
    }

}
