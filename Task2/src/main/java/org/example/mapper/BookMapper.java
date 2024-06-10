package org.example.mapper;

import org.example.dto.BookRequest;
import org.example.dto.BookResponse;
import org.example.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface BookMapper {

    //@Mapping(source = "author", target = "author.name")
    Book toBook(BookRequest bookRequest);

    @Mapping(source = "author", target = "authorResponse")
    BookResponse toBookResponse(Book book);

    @Mapping(source = "author", target = "authorResponse")
    List<BookResponse> toBookResponseSet(List<Book> books);
}