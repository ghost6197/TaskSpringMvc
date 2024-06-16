package org.example.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.example.dto.OrderRequest;
import org.example.dto.OrderResponse;
import org.example.dto.UserRequest;
import org.example.dto.UserResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
//@RequiredArgsConstructor(onConstructor_ = @Autowired)
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    UserRequest userRequest1,userRequest2,userRequest3;

    OrderRequest orderRequest1,orderRequest2,orderRequest3;

    @BeforeEach
    void init() {
        userRequest1=new UserRequest("firstName","lastName","email@qwe.wer","contactNumber");
        userRequest2=new UserRequest("firstName2","lastName2","email@qwe.wer2","contactNumber2");
        userRequest3=new UserRequest("firstName3","lastName3","email@qwe.wer3","contactNumber3");
        orderRequest1=new OrderRequest("products","shippingAddress",55.0);
        orderRequest2=new OrderRequest("products2","shippingAddress2",552.0);
        orderRequest3=new OrderRequest("products3","shippingAddress3",553.0);
    }

    @Test
    @SneakyThrows
    @Transactional
    void post() {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest1)))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        UserResponse userResponse = objectMapper.readValue(
                result.getResponse().getContentAsString(StandardCharsets.UTF_8),
                UserResponse.class);
        Assertions.assertAll(
                () -> Assertions.assertEquals(userResponse.getFirstName(), userRequest1.getFirstName()),
                () -> Assertions.assertEquals(userResponse.getEmail(), userRequest1.getEmail()),
                () -> Assertions.assertEquals(userResponse.getContactNumber(), null)
        );
    }

    @Test
    @SneakyThrows
    @Transactional
    void put() {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest1)))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        UserResponse userResponse = objectMapper.readValue(
                result.getResponse().getContentAsString(StandardCharsets.UTF_8),
                UserResponse.class);
        String urlPut = "/user/" + userResponse.getUserId();
        MvcResult resultPut = mockMvc.perform(MockMvcRequestBuilders
                        .put(urlPut)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest2)))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        UserResponse userResponse2 = objectMapper.readValue(
                resultPut.getResponse().getContentAsString(StandardCharsets.UTF_8),
                UserResponse.class);
        Assertions.assertAll(
                () -> Assertions.assertEquals(userResponse2.getFirstName(), userRequest2.getFirstName()),
                () -> Assertions.assertEquals(userResponse2.getEmail(), userRequest2.getEmail()),
                () -> Assertions.assertEquals(userResponse2.getUserId(), userResponse.getUserId()),
                () -> Assertions.assertEquals(userResponse2.getContactNumber(), null)
        );
    }

    @Test
    @SneakyThrows
    @Transactional
    void get() {
        MvcResult result1 = mockMvc.perform(MockMvcRequestBuilders
                        .post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest1)))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        UserResponse userResponse = objectMapper.readValue(
                result1.getResponse().getContentAsString(StandardCharsets.UTF_8),
                UserResponse.class);
        String urlPostOrder = "/order/" + userResponse.getUserId();
        MvcResult resultOrder = mockMvc.perform(MockMvcRequestBuilders
                        .post(urlPostOrder)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderRequest1)))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        OrderResponse orderResponse = objectMapper.readValue(
                resultOrder.getResponse().getContentAsString(StandardCharsets.UTF_8),
                OrderResponse.class);
        String urlGet = "/user/" + userResponse.getUserId();
        MvcResult resultGet = mockMvc.perform(MockMvcRequestBuilders
                        .get(urlGet))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        UserResponse userResponse1 = objectMapper.readValue(
                resultGet.getResponse().getContentAsString(StandardCharsets.UTF_8),
                UserResponse.class);
        Assertions.assertAll(
                () -> Assertions.assertEquals(userResponse1.getFirstName(), userRequest1.getFirstName()),
                () -> Assertions.assertEquals(userResponse1.getEmail(), userRequest1.getEmail()),
                () -> Assertions.assertEquals(userResponse1.getContactNumber(), userRequest1.getContactNumber())
        );
    }

    @Test
    @SneakyThrows
    @Transactional
    void delete() {
        MvcResult result1 = mockMvc.perform(MockMvcRequestBuilders
                        .post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest1)))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        UserResponse userResponse = objectMapper.readValue(
                result1.getResponse().getContentAsString(StandardCharsets.UTF_8),
                UserResponse.class);
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest2)))
                .andExpect(status().is2xxSuccessful());
        String urlDelete = "/user/" + userResponse.getUserId();
        mockMvc.perform(MockMvcRequestBuilders
                        .delete(urlDelete))
                .andExpect(status().is2xxSuccessful());
        MvcResult result2=mockMvc.perform(MockMvcRequestBuilders
                        .get("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest3)))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        List<UserResponse> userResponseList= objectMapper.readValue(
                result2.getResponse().getContentAsString(StandardCharsets.UTF_8),
                new TypeReference<List<UserResponse>>(){});
        Assertions.assertAll(
                () -> Assertions.assertEquals(userResponseList.size(), 1)
        );
    }

    @Test
    @SneakyThrows
    @Transactional
    void getall() {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest1)))
                .andExpect(status().is2xxSuccessful());
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest2)))
                .andExpect(status().is2xxSuccessful());
        MvcResult result1=mockMvc.perform(MockMvcRequestBuilders
                        .get("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest3)))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        List<UserResponse> userResponseList= objectMapper.readValue(
                result1.getResponse().getContentAsString(StandardCharsets.UTF_8),
                new TypeReference<List<UserResponse>>(){});
        Assertions.assertAll(
                () -> Assertions.assertEquals(userResponseList.size(), 2),
                () -> Assertions.assertEquals(userResponseList.get(0).getContactNumber(), null)
        );
    }
}