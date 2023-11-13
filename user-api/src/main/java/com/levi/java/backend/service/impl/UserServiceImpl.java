package com.levi.java.backend.service.impl;

import com.levi.java.backend.controller.exceptions.BusinessError;
import com.levi.java.backend.domain.User;
import com.levi.java.backend.mapper.UserMapper;
import com.levi.java.backend.mapper.requests.UserPutRequest;
import com.levi.java.backend.mapper.response.UserResponse;
import com.levi.java.backend.repository.UserRepository;
import com.levi.java.backend.mapper.requests.UserPostRequest;
import com.levi.java.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private ModelMapper modelMapper;

    @Override
    public User save(UserPostRequest userPostRequest) {
        userPostRequest.setCreatedAt(LocalDateTime.now());
        return repository.save(UserMapper.INSTANCE.toMapper(userPostRequest));
    }

    @Override
    public Page<User> findAllPageable(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public List<UserResponse> findAll() {
        List<User> users = repository.findAll();
        return UserMapper.INSTANCE.toMapperUserResponse(users);
    }

    @Override
    public User findByIdOrThrowBadRequestException(long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BusinessError("user not found."));
    }

    @Override
    public UserResponse findByCpf(String cpf) {
        User user = repository.findByCpf(cpf);
        return UserMapper.INSTANCE.toMapperUserResponse(user);
    }

    @Override
    public List<UserPostRequest> queryByName(String name) {
        List<User> users = repository.queryByNameLike(name);

        return users.stream()
                .map(user -> new UserPostRequest(user.getName(), user.getCpf(), user.getEmail(), user.getAddress(), user.getTelephone(), user.getCreatedAt()))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(long id) {
        repository.delete(this.findByIdOrThrowBadRequestException(id));
    }

    @Override
    public void replace(UserPutRequest userPutRequest) {
        User savedUser = this.findByIdOrThrowBadRequestException(userPutRequest.getId());
        User user = UserMapper.INSTANCE.toMapper(userPutRequest);
        user.setId(savedUser.getId());
        user.setCpf(savedUser.getCpf());
        user.setCreatedAt(savedUser.getCreatedAt());
        user.setName(userPutRequest.getName() != null ? userPutRequest.getName() : savedUser.getName());
        user.setAddress(userPutRequest.getAddress() != null ? userPutRequest.getAddress() : savedUser.getAddress());
        user.setEmail(userPutRequest.getEmail() != null ? userPutRequest.getEmail() : savedUser.getEmail());
        user.setTelephone(userPutRequest.getTelephone() != null ? userPutRequest.getTelephone() : savedUser.getTelephone());
        repository.save(user);
    }


}
