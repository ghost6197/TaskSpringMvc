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
@Table(name = "orders")
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "идентификатор заказа")
    @Column(name = "order_id")
    Long orderId;

    @Schema(description = "ссылка на объект User")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    User user;

    @Schema(description = "список продуктов в заказе")
    @Column(name = "products")
    String products;

    @Schema(description = "адрес доставки")
    @Column(name = "shipping_address")
    String shippingAddress;

    @Schema(description = "общая стоимость заказа")
    @Column(name = "total_price")
    Double totalPrice;

    @Schema(description = "статус заказа")
    @Column(name = "order_status")
    @Enumerated(EnumType.STRING)
    OrderStatus orderStatus;
}
