package org.example.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
public class OrderRequest {

    @Schema(description = "покупки", example = "products")
    String products;

    @Schema(description = "адрес доставки", example = "shippingAddress")
    String shippingAddress;

    @Schema(description = "общая стоимость заказа", example = "55")
    Double totalPrice;
}
