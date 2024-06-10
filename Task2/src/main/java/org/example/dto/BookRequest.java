package org.example.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
public class BookRequest {

    @Schema(description = "Название книги", example = "FFFFFF")
    String title;

    @Schema(description = "автор книги", example = "Шекспир")
    String authorName;
}