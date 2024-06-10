package org.example.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.model.Book;

import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
public class AuthorResponse {

    Long id;
    String name;
    List<BookResponse> books;
}