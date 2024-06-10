package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.dto.AuthorRequest;
import org.example.dto.AuthorResponse;
import org.example.mapper.AuthorMapper;
import org.example.model.Author;
import org.example.service.AuthorService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/author")
public class AuthorController {

    private final AuthorService authorService;

    private final AuthorMapper authorMapper;

    @Operation(summary = "создание")
    @PostMapping
    public AuthorResponse post(@Valid @RequestBody AuthorRequest authorRequest) {
        Author author = authorMapper.toAuthor(authorRequest);
        author=authorService.post(author);
        return authorMapper.toAuthorResponse(author);
    }

    @Operation(summary = "обновление")
    @PutMapping(value = "/{id}")
    public AuthorResponse put(@Valid @RequestBody AuthorRequest authorRequest,@PathVariable Long id) {
        Author author = authorMapper.toAuthor(authorRequest);
        author=authorService.put(author,id);
        return authorMapper.toAuthorResponse(author);
    }

    @Operation(summary = "чтение")
    @GetMapping(value = "/{id}")
    public AuthorResponse get(@PathVariable Long id) {
        Author author=authorService.get(id);
        return authorMapper.toAuthorResponse(author);
    }

    @Operation(summary = "удаление")
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id) {
        authorService.delete(id);
    }
}
