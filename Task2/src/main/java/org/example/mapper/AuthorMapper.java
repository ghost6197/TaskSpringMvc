package org.example.mapper;

import org.example.dto.AuthorRequest;
import org.example.dto.AuthorResponse;
import org.example.model.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    @Mapping(source = "books", target = "books")
    AuthorResponse toAuthorResponse(Author author);

    Author toAuthor(AuthorRequest authorRequest);
}
