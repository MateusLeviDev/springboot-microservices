package com.levi.microservices.client.domain.model;

import com.levi.microservices.client.api.dto.AddressDTO;
import com.levi.microservices.client.api.dto.IndividualClientDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.Objects;
import java.util.Optional;

@Data
@Entity
@Table(name = "individual_client")
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class IndividualClient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "individual_client_id_seq")
    //@SequenceGenerator(name = "individual_client_id_seq", sequenceName = "individual_client_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "document", nullable = false)
    private String document;

    @Column(name = "mothers_name", nullable = false)
    private String mothersName;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;

    @ManyToOne
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    private Instant updatedAt;

    public void updateModel(IndividualClient other) {
        this.name = other.name;
        this.document = other.document;
        this.mothersName = other.mothersName;
        this.isDeleted = other.isDeleted;
        this.address = other.address;
    }

    public static Optional<IndividualClientDTO> from(IndividualClient individualClient) {
        if (Objects.isNull(individualClient))
            return Optional.empty();

        AddressDTO addressDTO = Address.from(individualClient.getAddress())
                .orElse(null);

        return Optional.of(new IndividualClientDTO(individualClient.name, individualClient.document,
                individualClient.mothersName, addressDTO));
    }

    public static IndividualClient toEntity(IndividualClientDTO dto) {
        final var addressBuilder = Address.builder()
                .state(dto.addressDTO().state())
                .city(dto.addressDTO().city())
                .street(dto.addressDTO().street())
                .number(dto.addressDTO().number())              // I might consider using mapstruct //
                .complement(dto.addressDTO().complement())
                .neighborhood(dto.addressDTO().neighborhood())
                .zipCode(dto.addressDTO().zipCode())
                .build();

        return IndividualClient.builder()
                .name(dto.name())
                .document(dto.document())
                .mothersName(dto.mothersName())
                .address(addressBuilder)
                .build();
    }
}
