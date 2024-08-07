package com.levi.microservices.client.domain.service.impl;

import com.levi.microservices.client.api.dto.IndividualClientDTO;
import com.levi.microservices.client.domain.model.Address;
import com.levi.microservices.client.domain.model.IndividualClient;
import com.levi.microservices.client.domain.repository.AddressRepository;
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
    private final AddressRepository addressRepository;

    @Override
    public Optional<IndividualClientDTO> save(IndividualClient individualClient) {
        final var byDocument = individualClientRepository.findByDocument(individualClient.getDocument());

        if (byDocument.isPresent()) {
            final var individualClientSaved = byDocument.get();
            final var address = byDocument.get().getAddress();
            log.info("Updating existing client with document: {}", individualClientSaved.getDocument());
            individualClientSaved.updateModel(individualClient);
            addressRepository.save(address);
            individualClientRepository.save(individualClientSaved);
        } else {
            log.info("Creating new client with document: {}", individualClient.getDocument());
            final var address = individualClient.getAddress();
            individualClient.setIsDeleted(false);
            addressRepository.save(address);
            individualClientRepository.save(individualClient);
        }
        return IndividualClient.from(individualClient);
    }

    @Override
    public IndividualClient update(Long id, IndividualClient individualClient) {
        final IndividualClient referenceById = individualClientRepository.getReferenceById(id);
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
