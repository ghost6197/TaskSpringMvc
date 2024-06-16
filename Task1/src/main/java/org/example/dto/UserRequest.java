package org.example.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
public class UserRequest {

    @Schema(description = "имя покупателя", example = "firstName")
    String firstName;

    @Schema(description = "фамилия покупателя", example = "lastName")
    String lastName;

    @Schema(description = "адрес электронной почты покупателя", example = "email@qwe.wer")
    @Email
    String email;

    @Schema(description = "контактный номер телефона покупателя", example = "contactNumber")
    String contactNumber;
}
