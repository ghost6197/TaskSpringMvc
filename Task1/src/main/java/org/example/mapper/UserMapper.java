package org.example.mapper;

import org.example.dto.UserRequest;
import org.example.dto.UserResponse;
import org.example.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "orderList", target = "orders")
    UserResponse toUserResponse(User user);

    User toUser(UserRequest userRequest);

    List<UserResponse> toUserResponseList(List<User> users);
}
