package org.example.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
@Table(name = "users")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "идентификатор покупателя")
    @Column(name = "user_id")
    Long userId;

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

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    List<Order> orderList;
}