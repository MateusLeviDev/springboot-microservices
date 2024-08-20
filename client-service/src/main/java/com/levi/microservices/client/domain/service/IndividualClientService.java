package com.levi.microservices.client.domain.service;

import com.levi.microservices.client.api.dto.IndividualClientDTO;
import com.levi.microservices.client.domain.model.IndividualClient;

import java.util.Optional;

public interface IndividualClientService {

    IndividualClient save(IndividualClient individualClient);

}
