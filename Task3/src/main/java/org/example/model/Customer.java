package org.example.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
@Table(name = "customers")
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "идентификатор покупателя")
    @Column(name = "customer_id")
    Long customerId;

    @Schema(description = "имя покупателя")
    @Column(name = "first_name")
    String firstName;

    @Schema(description = "фамилия покупателя")
    @Column(name = "last_name")
    String lastName;

    @Schema(description = "адрес электронной почты покупателя")
    @Column(name = "email")
    String email;

    @Schema(description = "контактный номер телефона покупателя")
    @Column(name = "contact_number")
    String contactNumber;
}