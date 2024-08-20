package com.levi.microservices.client.domain.service.impl;

import com.levi.microservices.client.domain.model.IndividualClient;
import com.levi.microservices.client.domain.repository.IndividualClientRepository;
import com.levi.microservices.client.domain.service.IndividualClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class IndividualClientServiceImpl implements IndividualClientService {

    private static final String UNABLE_TO_FIND_OBJECT = "Unable to find object with %s %s";
    private final IndividualClientRepository individualClientRepository;

    @Override
    @Transactional
    public IndividualClient save(IndividualClient individualClient) {
        log.info("Creating new client with document: {}", individualClient.getDocument());
        individualClient.setIsDeleted(false);
        return individualClientRepository.save(individualClient);
    }

}
