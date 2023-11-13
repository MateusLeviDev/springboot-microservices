package com.levi.java.backend.controller;

import com.levi.java.backend.domain.User;
import com.levi.java.backend.mapper.requests.UserPostRequest;
import com.levi.java.backend.mapper.requests.UserPutRequest;
import com.levi.java.backend.mapper.response.UserResponse;
import com.levi.java.backend.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/user")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl service;

    @GetMapping("/hello")
    public String hello() {
        return "Hello";
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> findById(@PathVariable long userId) {
        return ResponseEntity.ok(service.findByIdOrThrowBadRequestException(userId));
    }

    @GetMapping("/pageable")
    public ResponseEntity<Page<User>> getAllPageable(@PageableDefault(sort = "id", direction = Sort.Direction.DESC, size = 15) Pageable pageable) {
        return ResponseEntity.ok(service.findAllPageable(pageable));
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<UserResponse> findByCpf(@PathVariable String cpf) {
        return ResponseEntity.ok(service.findByCpf(cpf));
    }

    @GetMapping("/search")
    public ResponseEntity<List<UserPostRequest>> findByName(@RequestParam(name = "name") String name) {
        return ResponseEntity.ok(service.queryByName(name));
    }

    @PostMapping
    public ResponseEntity<User> save(@RequestBody @Valid UserPostRequest userPostRequest) {
        return new ResponseEntity<>(service.save(userPostRequest), HttpStatus.CREATED);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> delete(@PathVariable long userId) {
        service.delete(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping()
    public ResponseEntity<?> replace(@RequestBody UserPutRequest userPutRequest) {
        service.replace(userPutRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
