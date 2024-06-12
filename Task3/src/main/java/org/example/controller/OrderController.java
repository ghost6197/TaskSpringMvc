package org.example.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.example.exceptions.BadRequestException;
import org.example.model.Order;
import org.example.service.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final ObjectMapper objectMapper;

    private final OrderService orderService;

    @Operation(summary = "создание")
    @PostMapping
    public String post(@RequestBody String json) {
        try {
            Order order = objectMapper.readValue(json, Order.class);
            order=orderService.post(order);
            String jsonAnswer = objectMapper.writeValueAsString(order);
            return jsonAnswer;
        } catch (JsonProcessingException e){
            throw new BadRequestException("Ошибка в json");
        }
    }

    @Operation(summary = "обновление")
    @PutMapping(value = "/{id}")
    public String put(@RequestBody String json,@PathVariable Long id) {
        try {
            Order order = objectMapper.readValue(json, Order.class);
            order=orderService.put(order,id);
            String jsonAnswer = objectMapper.writeValueAsString(order);
            return jsonAnswer;
        } catch (JsonProcessingException e){
            throw new BadRequestException("Ошибка в json");
        }
    }

    @Operation(summary = "чтение")
    @GetMapping(value = "/{id}")
    public String get(@PathVariable Long id) {
        try {
            Order order=orderService.get(id);
            String jsonAnswer = objectMapper.writeValueAsString(order);
            return jsonAnswer;
        } catch (JsonProcessingException e){
            throw new BadRequestException("Ошибка в json");
        }
    }

    @Operation(summary = "удаление")
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id) {
        orderService.delete(id);
    }
}
