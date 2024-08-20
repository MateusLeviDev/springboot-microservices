package com.levi.microservices.client.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

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

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "address_id", referencedColumnName = "id", nullable = false)
    private Address address;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    private Instant updatedAt;

    public void updateModel(IndividualClient other) {
        this.isDeleted = false;
        this.name = other.name;
        this.document = other.document;
        this.mothersName = other.mothersName;
        address.updateModel(other.address);
    }

}
