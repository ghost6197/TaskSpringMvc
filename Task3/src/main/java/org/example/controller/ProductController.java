package org.example.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.example.exceptions.BadRequestException;
import org.example.model.Customer;
import org.example.model.Product;
import org.example.service.CustomerService;
import org.example.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ObjectMapper objectMapper;

    private final ProductService productService;

    @Operation(summary = "создание")
    @PostMapping
    public String post(@RequestBody String json) {
        try {
            Product product = objectMapper.readValue(json, Product.class);
            product=productService.post(product);
            String jsonAnswer = objectMapper.writeValueAsString(product);
            return jsonAnswer;
        } catch (JsonProcessingException e){
            throw new BadRequestException("Ошибка в json");
        }
    }

    @Operation(summary = "обновление")
    @PutMapping(value = "/{id}")
    public String put(@RequestBody String json,@PathVariable Long id) {
        try {
            Product product = objectMapper.readValue(json, Product.class);
            product=productService.put(product,id);
            String jsonAnswer = objectMapper.writeValueAsString(product);
            return jsonAnswer;
        } catch (JsonProcessingException e){
            throw new BadRequestException("Ошибка в json");
        }
    }

    @Operation(summary = "чтение")
    @GetMapping(value = "/{id}")
    public String get(@PathVariable Long id) {
        try {
            Product product=productService.get(id);
            String jsonAnswer = objectMapper.writeValueAsString(product);
            return jsonAnswer;
        } catch (JsonProcessingException e){
            throw new BadRequestException("Ошибка в json");
        }
    }

    @Operation(summary = "удаление")
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

    @Operation(summary = "чтение всех")
    @GetMapping
    public String getAll() {
        try {
            List<Product> products=productService.getAll();
            String jsonAnswer = objectMapper.writeValueAsString(products);
            return jsonAnswer;
        } catch (JsonProcessingException e){
            throw new BadRequestException("Ошибка в json");
        }
    }
}
