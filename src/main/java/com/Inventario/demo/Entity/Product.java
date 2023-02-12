package com.Inventario.demo.Entity;


import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

import java.util.Date;

@Document(value = "products")
@Getter
@Validated
@Setter
@NoArgsConstructor
public class Product {
    @Id
    private String id;
    @NotNull
    private String name;
    private String description;
    @Min(0)
    private double unitPrice;
    @Min(0)
    private double cost;
    private Date expiryDate;
    @Min(0)
    private int stock;
}
