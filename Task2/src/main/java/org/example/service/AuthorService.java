package org.example.service;

import org.example.model.Author;
public interface AuthorService {

    Author post(Author author);

    Author put(Author author,Long id);

    Author get(Long id);

    void delete(Long id);

    Author search(String name);
}
