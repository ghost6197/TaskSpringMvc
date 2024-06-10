package org.example.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
public class BookResponse {

    Long id;
    String title;
    AuthorResponse authorResponse;
}