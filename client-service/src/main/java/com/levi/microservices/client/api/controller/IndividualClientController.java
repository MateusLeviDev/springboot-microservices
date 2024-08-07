package com.levi.microservices.client.api.controller;

import com.levi.microservices.client.api.dto.IndividualClientDTO;
import com.levi.microservices.client.domain.model.IndividualClient;
import com.levi.microservices.client.domain.service.IndividualClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/client")
public class IndividualClientController {

    private final IndividualClientService service;


    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid final IndividualClientDTO dto) {
        return ResponseEntity.ok(service.save(IndividualClient.toEntity(dto)));
    }

    @GetMapping
    public ResponseEntity<?> getByDocument(@RequestParam final String document) {
        return ResponseEntity.ok(service.findByDocument(document));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable final Long id) {
        return ResponseEntity.ok(service.findById(id));
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable final Long id, @RequestBody @Valid final IndividualClientDTO dto) {
        return ResponseEntity.ok(service.update(id, IndividualClient.toEntity(dto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable final Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
