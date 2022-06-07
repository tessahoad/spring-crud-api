package com.elsevier.techassociates.springcrudapi.dao.books;

import com.elsevier.techassociates.springcrudapi.business.model.Book;

import java.util.List;

public interface BooksDao {
    List<Book> list();

    Book get(String id);

    void delete(String id);

    Book create(String title, Integer publishYear);
}
