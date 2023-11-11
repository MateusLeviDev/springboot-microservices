package com.levi.java.backend.service;

import com.levi.java.backend.domain.User;
import com.levi.java.backend.mapper.UserMapper;
import com.levi.java.backend.repository.UserRepository;
import com.levi.java.backend.requests.UserPostRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private ModelMapper modelMapper;

    private final UserRepository repository;
//
//    public List<UserCreateDTO> getAll() {
//        List<User> users = repository.findAll();
//        return users.stream()
//                .map(DTOConverter::convert)
//                .collect(Collectors.toList());
//    }
//
//
//    public Page<UserCreateDTO> getAllPageable(Pageable page) {
//        Page<User> users = repository.findAll(page);
//        return users.map(DTOConverter::convert);
//    }
//
//    public UserCreateDTO findById(Long userId) {
//        User user = repository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
//        return DTOConverter.convert(user);
//    }

    public User save(UserPostRequest userPostRequest) {
        userPostRequest.setCreatedAt(LocalDateTime.now());
        return repository.save(UserMapper.INSTANCE.toMapper(userPostRequest));
    }
//
//    public void delete(Long userId) {
//        User user = repository.findById(userId).orElseThrow(RuntimeException::new);
//        repository.delete(user);
//    }
//
//    public UserCreateDTO findByCpf(String cpf) {
//        User user = repository.findByCpf(cpf);
//        if (user != null) {
//            return DTOConverter.convert(user);
//        }
//
//        return null;
//    }
//
//    public List<UserCreateDTO> queryByName(String name) {
//        List<User> users = repository.queryByNameLike(name);
//        return users.stream()
//                .map(DTOConverter::convert)
//                .collect(Collectors.toList());
//    }
//
//    public UserCreateDTO replace(Long userId, UserCreateDTO userDTO) {
//        User user = repository
//                .findById(userId).orElseThrow(RuntimeException::new);
//        if (userDTO.getEmail() != null &&
//                !user.getEmail().equals(userDTO.getEmail())) {
//            user.setEmail(userDTO.getEmail());
//        }
//        if (userDTO.getTelephone() != null &&
//                !user.getTelephone().equals(userDTO.getTelephone())) {
//            user.setTelephone(userDTO.getTelephone());
//        }
//        if (userDTO.getAddress() != null &&
//                !user.getAddress().equals(userDTO.getAddress())) {
//            user.setAddress(userDTO.getAddress());
//        }
//        user = repository.save(user);
//        return DTOConverter.convert(user);
//    }


}
