package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.exceptions.DataNotFoundException;
import org.example.model.Book;
import org.example.repository.BookRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public Book post(Book book){
        return bookRepository.save(book);
    }

    public Book put(Book book,Long id){
        get(id);
        book.setId(id);
        return bookRepository.save(book);
    }

    public Book get(Long id){
        return bookRepository.findById(id).orElseThrow(()-> new DataNotFoundException("Нет автора с id="+id));
    }

    public void delete(Long id){
        get(id);
        bookRepository.deleteById(id);
    }

    public List<Book> getAll(Pageable pageable){
        return bookRepository.findAllBy(pageable);
    }
}
