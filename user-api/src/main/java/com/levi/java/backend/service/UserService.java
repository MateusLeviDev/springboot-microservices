package com.levi.java.backend.service;

import com.levi.java.backend.domain.User;
import com.levi.java.backend.mapper.requests.UserPostRequest;
import com.levi.java.backend.mapper.requests.UserPutRequest;
import com.levi.java.backend.mapper.response.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    User save(UserPostRequest userPostRequest);
    Page<User> findAllPageable(Pageable pageable);
    User findByIdOrThrowBadRequestException(long id);
    UserResponse findByCpf(String cpf);
    List<UserPostRequest> queryByName(String name);
    void delete(long id);
    void replace(UserPutRequest userPutRequest);
}
