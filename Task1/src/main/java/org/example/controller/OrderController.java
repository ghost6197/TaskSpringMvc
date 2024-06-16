package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.example.dto.OrderRequest;
import org.example.dto.OrderResponse;
import org.example.mapper.OrderMapper;
import org.example.model.Order;
import org.example.model.OrderStatus;
import org.example.model.User;
import org.example.service.OrderService;
import org.example.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {


    private final OrderService orderService;

    private final UserService userService;

    private final OrderMapper orderMapper;

    @Operation(summary = "создание")
    @PostMapping("/{userId}")
    public OrderResponse post(@RequestBody OrderRequest orderRequest, @PathVariable Long userId) {
        User user = userService.get(userId);
        Order order = orderMapper.toOrder(orderRequest);
        order.setUser(user);
        order.setOrderStatus(OrderStatus.NEW);
        order = orderService.post(order);
        return orderMapper.toOrderResponse(order);
    }

    @Operation(summary = "обновление")
    @PutMapping("/{userId}/{id}")
    public OrderResponse put(@RequestBody OrderRequest orderRequest, @PathVariable Long userId, @PathVariable Long id) {
        User user = userService.get(userId);
        Order order = orderMapper.toOrder(orderRequest);
        order.setUser(user);
        order = orderService.put(order, id);
        return orderMapper.toOrderResponse(order);
    }

    @Operation(summary = "чтение")
    @GetMapping("/{userId}/{id}")
    public OrderResponse get(@PathVariable Long id, @PathVariable Long userId) {
        userService.get(userId);
        Order order = orderService.get(id);
        return orderMapper.toOrderResponse(order);
    }

    @Operation(summary = "удаление")
    @DeleteMapping("/{userId}/{id}")
    public void delete(@PathVariable Long id, @PathVariable Long userId) {
        userService.get(userId);
        orderService.delete(id);
    }
}
