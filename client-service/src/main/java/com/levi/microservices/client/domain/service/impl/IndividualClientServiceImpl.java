package com.levi.microservices.client.domain.service.impl;

import com.levi.microservices.client.domain.model.IndividualClient;
import com.levi.microservices.client.domain.repository.IndividualClientRepository;
import com.levi.microservices.client.domain.service.IndividualClientService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class IndividualClientServiceImpl implements IndividualClientService {

    private static final String UNABLE_TO_FIND_OBJECT = "Unable to find object with %s %s";
    private final IndividualClientRepository individualClientRepository;

    @Override
    public IndividualClient save(IndividualClient individualClient) {
        final var byDocument = individualClientRepository.findByDocument(individualClient.getDocument());

        if (byDocument.isPresent()) {
            final var individualClientSaved = byDocument.get();
            log.info("Updating existing client with document: {}", individualClientSaved.getDocument());
            individualClientSaved.updateModel(individualClient);
            return individualClientRepository.save(individualClientSaved);
        } else {
            log.info("Creating new client with document: {}", individualClient.getDocument());
            individualClient.setIsDeleted(false);
            return individualClientRepository.save(individualClient);
        }
    }

    @Override
    public IndividualClient update(IndividualClient individualClient) {
        final IndividualClient referenceById = individualClientRepository.getReferenceById(individualClient.getId());
        log.info("Updating individual client: {} to {}", referenceById, individualClient);

        referenceById.updateModel(individualClient);

        return individualClientRepository.save(referenceById);
    }

    @Override
    public IndividualClient findById(Long id) {
        return individualClientRepository.findByIdAndIsDeleted(id, false)
                .orElseThrow(() -> new EntityNotFoundException(UNABLE_TO_FIND_OBJECT.formatted("id", id)));
    }

    @Override
    public Optional<IndividualClient> findByDocument(String document) {
        return individualClientRepository.findByDocumentAndIsDeleted(document, false);
    }

    @Override
    public void deleteById(Long id) {
        final var individualClient = individualClientRepository.getReferenceById(id);
        individualClient.setIsDeleted(true);
        individualClientRepository.save(individualClient);
        log.info("Disabling individual client with id: {} and document: {}", id, individualClient.getDocument());
    }
}
