package com.levi.java.backend.mapper.util;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @NotBlank
    protected long id;
    protected String name;
}
