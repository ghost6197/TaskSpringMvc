package org.example.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

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

    @Schema(description = "ссылка на объект Customer")
    // @Column(name = "customer")
    @OneToOne
    @JoinColumn(name = "customer_id")
    Customer customer;

    @Schema(description = "список продуктов в заказе")
    @OneToMany
    @JoinTable(name = "order_products",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    List<Product> products;

    @Schema(description = "дата размещения заказа")
    @Column(name = "order_date")
    LocalDateTime orderDate;

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
