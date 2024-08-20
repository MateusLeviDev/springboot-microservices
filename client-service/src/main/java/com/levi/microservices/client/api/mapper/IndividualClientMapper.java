package com.levi.microservices.client.api.mapper;

import com.levi.microservices.client.api.dto.IndividualClientDTO;
import com.levi.microservices.client.domain.model.IndividualClient;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IndividualClientMapper {

    IndividualClient toModel(IndividualClientDTO individualClientDTO);

    IndividualClientDTO toDTO(IndividualClient individualClient);

}
