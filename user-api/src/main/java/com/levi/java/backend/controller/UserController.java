package com.levi.java.backend.controller;

import com.levi.java.backend.domain.User;
import com.levi.java.backend.requests.UserPostRequest;
import com.levi.java.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/user")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping("/hello")
    public String hello() {
        return "Hello";
    }

//    @GetMapping()
//    public ResponseEntity<List<UserCreateDTO>> getAll() {
//        return ResponseEntity.ok(service.getAll());
//    }
//
//    @GetMapping("/{userId}")
//    public ResponseEntity<UserCreateDTO> findById(@PathVariable Long userId) {
//        return ResponseEntity.ok(service.findById(userId));
//    }

    @PostMapping
    public ResponseEntity<User> save(@RequestBody @Valid UserPostRequest userPostRequest) {
        return new ResponseEntity<>(service.save(userPostRequest), HttpStatus.CREATED);
    }
//
//    @DeleteMapping
//    public ResponseEntity<Void> delete(@PathVariable long userId) {
//        service.delete(userId);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
//
//    @GetMapping("/{cpf}/cpf")
//    public ResponseEntity<UserCreateDTO> findByCpf(@PathVariable String cpf) {
//        return ResponseEntity.ok(service.findByCpf(cpf));
//    }
//
//    @GetMapping("/search")
//    public List<UserCreateDTO> queryByName(@RequestParam(name = "nome", required = true) String nome) {
//        return service.queryByName(nome);
//    }
//
//    @PatchMapping("/{id}")
//    public ResponseEntity<UserCreateDTO> replace(@PathVariable long userId, @RequestBody UserCreateDTO userDTO) {
//        service.replace(userId, userDTO);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
//
//    @GetMapping("/pageable")
//    public Page<UserCreateDTO> getUsersPage(Pageable pageable) {
//        return service.getAllPageable(pageable);
//    }
}
