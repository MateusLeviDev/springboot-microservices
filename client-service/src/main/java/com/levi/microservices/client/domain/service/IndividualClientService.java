package com.levi.microservices.client.domain.service;

import com.levi.microservices.client.api.dto.IndividualClientDTO;
import com.levi.microservices.client.domain.model.IndividualClient;

import java.util.Optional;

public interface IndividualClientService {

    Optional<IndividualClientDTO> save(IndividualClient individualClient);

    IndividualClient update(Long id, IndividualClient individualClient);

    IndividualClient findById(Long id);

    Optional<IndividualClient> findByDocument(String document);

    void deleteById(Long id);

}
