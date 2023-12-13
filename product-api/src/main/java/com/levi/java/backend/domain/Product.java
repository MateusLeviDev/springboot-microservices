package com.levi.java.backend.domain;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nome")
    private String name;
    @Column(name = "preco")
    private Float price;
    @Column(name = "descricao")
    private String description;
    @Column(name = "product_identifier")
    private String productIdentifier;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
