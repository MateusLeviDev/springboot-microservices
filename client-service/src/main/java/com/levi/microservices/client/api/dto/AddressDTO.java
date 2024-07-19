package com.levi.microservices.client.api.dto;

public record AddressDTO(
        String street,
        String number,
        String complement,
        String neighborhood,
        String city,
        String state,
        String zipCode
) {
}
