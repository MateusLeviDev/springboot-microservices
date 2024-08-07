package com.levi.microservices.client.domain.repository;

import com.levi.microservices.client.domain.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
