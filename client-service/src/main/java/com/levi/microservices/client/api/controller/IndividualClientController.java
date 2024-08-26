package com.levi.microservices.client.api.controller;

import com.levi.microservices.client.api.dto.IndividualClientDTO;
import com.levi.microservices.client.api.mapper.IndividualClientMapper;
import com.levi.microservices.client.domain.service.IndividualClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/client")
public class IndividualClientController {

    private final IndividualClientService service;
    private final IndividualClientMapper individualClientMapper;


    @PostMapping
    public ResponseEntity<IndividualClientDTO> save(@RequestBody @Valid final IndividualClientDTO dto) {
        log.info("Started to save client with request: [{}]", dto);
        final var model = individualClientMapper.toModel(dto);
        final var save = service.save(model);

        final var response = individualClientMapper.toDTO(save);

        log.info("Saved successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IndividualClientDTO> findById(@PathVariable final Long id) {
        log.info("Finding customer id: {}", id);
        final var individualClient = service.findById(id);

        return ResponseEntity.ok(individualClientMapper.toDTO(individualClient));
    }


}
