package com.levi.microservices.client.api.controller;

import com.levi.microservices.client.api.dto.IndividualClientDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/client")
public class IndividualClientController {


    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid final IndividualClientDTO clientDTO) {
        return null;
    }

    //TODO: finish this
}
