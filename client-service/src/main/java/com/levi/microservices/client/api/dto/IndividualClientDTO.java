package com.levi.microservices.client.api.dto;

public record IndividualClientDTO(
        Long id,
        String name,
        String document,
        String mothersName,
        String                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         birthdate,
        Boolean isDeleted,
        AddressDTO addressDTO
) {
}
