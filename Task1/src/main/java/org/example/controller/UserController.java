package org.example.controller;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.dto.UserRequest;
import org.example.dto.UserResponse;
import org.example.dto.Views;
import org.example.mapper.UserMapper;
import org.example.model.User;
import org.example.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserMapper userMapper;

    private final UserService userService;

    @Operation(summary = "создание")
    @PostMapping
    @JsonView(Views.UserSummary.class)
    public UserResponse post(@RequestBody @Valid UserRequest userRequest) {
        User user = userMapper.toUser(userRequest);
        user = userService.post(user);
        return userMapper.toUserResponse(user);
    }

    @Operation(summary = "обновление")
    @PutMapping(value = "/{id}")
    @JsonView(Views.UserSummary.class)
    public UserResponse put(@RequestBody @Valid UserRequest userRequest, @PathVariable Long id) {
        User user = userMapper.toUser(userRequest);
        user = userService.put(user, id);
        return userMapper.toUserResponse(user);
    }

    @Operation(summary = "чтение")
    @GetMapping(value = "/{id}")
    @JsonView(Views.UserDetails.class)
    public UserResponse get(@PathVariable Long id) {
        User user = userService.get(id);
        UserResponse userResponse = userMapper.toUserResponse(user);
        return userResponse;
    }

    @Operation(summary = "удаление")
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

    @Operation(summary = "чтение вскех пользователей")
    @GetMapping
    @JsonView(Views.UserSummary.class)
    public List<UserResponse> getall() {
        List<User> users = userService.getAll();
        return userMapper.toUserResponseList(users);
    }
}