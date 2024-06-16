package org.example.dto;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.model.OrderStatus;

@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
@JsonView(Views.UserDetails.class)
public class OrderResponse {

    Long orderId;

    @Schema(description = "ссылка на объект User")
    UserResponseFromOrder user;

    @Schema(description = "список продуктов в заказе")
    String products;

    @Schema(description = "адрес доставки")
    String shippingAddress;

    @Schema(description = "общая стоимость заказа")
    Double totalPrice;

    @Schema(description = "статус заказа")
    OrderStatus orderStatus;
}
