package com.levi.microservices.product.domain;

import com.levi.microservices.product.dto.ProductRequest;
import com.levi.microservices.product.dto.ProductResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

@Document(value = "product")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Product {

    @Id
    private String id;
    private String name;
    private String description;
    private BigDecimal price;

    public static Optional<ProductResponse> from(Product product) {
        if (Objects.isNull(product))
            return Optional.empty();

        return Optional.of(new ProductResponse(product.getId(), product.getName(),
                product.getDescription(), product.getPrice()));
    }

    public Product updateModel(ProductRequest other) {
        if (other.name() != null)
            this.name = other.name();

        if (other.description() != null)
            this.description = other.description();

        if (other.price() != null)
            this.price = other.price();

        return this;
    }
}
