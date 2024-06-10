package org.example.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
@Table(name = "books")
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID книги")
    @Column(name = "id")
    Long id;

    @Schema(description = "Название")
    @Column(name = "title")
    String title;

    @Schema(description = "Автор")
    @ManyToOne
    @JoinColumn(name = "author_id")
    Author author;
}