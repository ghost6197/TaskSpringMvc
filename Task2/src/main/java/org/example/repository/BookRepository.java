package org.example.repository;

import org.example.model.Book;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllBy(Pageable pageable);
}
