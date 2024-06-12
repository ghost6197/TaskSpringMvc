package org.example.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.example.dto.AuthorRequest;
import org.example.dto.AuthorResponse;
import org.example.exceptions.BadRequestException;
import org.example.model.Customer;
import org.example.service.CustomerService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {

    private final ObjectMapper objectMapper;

    private final CustomerService customerService;

    @Operation(summary = "создание")
    @PostMapping
    public String post(@RequestBody String json) {
        try {
            Customer customer = objectMapper.readValue(json, Customer.class);
            customer=customerService.post(customer);
            String jsonAnswer = objectMapper.writeValueAsString(customer);
            return jsonAnswer;
        } catch (JsonProcessingException e){
            throw new BadRequestException("Ошибка в json");
        }
    }

    @Operation(summary = "обновление")
    @PutMapping(value = "/{id}")
    public String put(@RequestBody String json,@PathVariable Long id) {
        try {
            Customer customer = objectMapper.readValue(json, Customer.class);
            customer=customerService.put(customer,id);
            String jsonAnswer = objectMapper.writeValueAsString(customer);
            return jsonAnswer;
        } catch (JsonProcessingException e){
            throw new BadRequestException("Ошибка в json");
        }
    }

    @Operation(summary = "чтение")
    @GetMapping(value = "/{id}")
    public String get(@PathVariable Long id) {
        try {
            Customer customer=customerService.get(id);
            String jsonAnswer = objectMapper.writeValueAsString(customer);
            return jsonAnswer;
        } catch (JsonProcessingException e){
            throw new BadRequestException("Ошибка в json");
        }
    }

    @Operation(summary = "удаление")
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id) {
        customerService.delete(id);
    }
}