package org.example.mapper;

import org.example.dto.OrderRequest;
import org.example.dto.OrderResponse;
import org.example.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    Order toOrder(OrderRequest orderRequest);

    @Mapping(source = "user", target = "user")
    OrderResponse toOrderResponse(Order order);
}
