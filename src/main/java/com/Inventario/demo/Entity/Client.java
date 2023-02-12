package com.Inventario.demo.Entity;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;


import java.util.Date;


@Document(value = "clients")
@Getter
@Setter
@NoArgsConstructor
@Validated

public class Client {
    @Id
    private String id;
    @NotNull(message = "El campo no debe ser nulo")
    private  String name;
    private String description;
    private Date createdAt;
    @NotNull(message = "El campo no debe ser nulo")
    @Pattern(regexp = "^([A-ZÑ\\\\x26]{3,4}([0-9]{2})(0[1-9]|1[0-2])(0[1-9]|1[0-9]|2[0-9]|3[0-1])([A-Z]|[0-9]){2}([A]|[0-9]){1})?$",message = "RFC invalido")
    private String RFC;
    private String address;
    @Pattern(regexp="\\+\\d{2}-\\d{3}-\\d{4}", message="El número de teléfono debe estar en formato internacional")
    private String telephone;
    @NotNull(message = "El campo no debe ser nulo")
    @Email
    private String email;
}
