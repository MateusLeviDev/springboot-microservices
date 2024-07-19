package com.levi.microservices.client.domain.repository;

import com.levi.microservices.client.domain.model.IndividualClient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IndividualClientRepository extends JpaRepository<IndividualClient, Long> {

    Optional<IndividualClient> findByDocument(String document);

    Optional<IndividualClient> findByDocumentAndIsDeleted(String document, boolean isDeleted);

    Optional<IndividualClient> findByIdAndIsDeleted(Long id, boolean isDeleted);

}
