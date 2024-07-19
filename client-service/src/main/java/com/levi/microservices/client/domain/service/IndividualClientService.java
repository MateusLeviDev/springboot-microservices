package com.levi.microservices.client.domain.service;

import com.levi.microservices.client.domain.model.IndividualClient;

import java.util.Optional;

public interface IndividualClientService {

    IndividualClient save(IndividualClient individualClient);

    IndividualClient update(IndividualClient individualClient);

    IndividualClient findById(Long id);

    Optional<IndividualClient> findByDocument(String document);

    void deleteById(Long id);

}
