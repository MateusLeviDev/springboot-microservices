package com.levi.microservices.client.api.dto;

public record IndividualClientDTO(

        String name,
        String document,
        String mothersName,
        AddressDTO address
) {
}
