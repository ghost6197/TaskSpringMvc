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
@Table(name = "products")
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "идентификатор продукта")
    @Column(name = "product_id")
    Long productId;

    @Schema(description = "название продукта")
    @Column(name = "name")
    String name;

    @Schema(description = "описание продукта")
    @Column(name = "description")
    String description;

    @Schema(description = "цена продукта")
    @Column(name = "price")
    Double price;

    @Schema(description = "количество на складе")
    @Column(name = "quantity_in_stock")
    Long quantityInStock;
}
