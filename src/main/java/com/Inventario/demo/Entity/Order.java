package com.Inventario.demo.Entity;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import org.hibernate.validator.constraints.Range;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.util.Date;

@Document(value = "Orders")
@Getter
@Setter
@NoArgsConstructor
@Validated

public class Order {
    @Id
    private String id;
    @DBRef
    @NotNull
    private Client client;
    @DBRef
    @NotNull
    private Product product;
    @NotNull
    private Date date;
    @Min(1)
    @NotNull
    private int quantity;
}
