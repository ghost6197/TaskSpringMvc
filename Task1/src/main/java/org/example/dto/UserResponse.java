package org.example.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
public class UserResponse {

    @JsonView(Views.UserSummary.class)
    String firstName;

    @JsonView(Views.UserSummary.class)
    String lastName;

    @JsonView(Views.UserSummary.class)
    String email;

    @JsonView(Views.UserDetails.class)
    String contactNumber;

    @JsonView(Views.UserDetails.class)
    List<OrderResponse> orders;
}
