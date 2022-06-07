package com.elsevier.techassociates.springcrudapi.business.books;

import com.elsevier.techassociates.springcrudapi.api.requests.BookRequest;
import com.elsevier.techassociates.springcrudapi.business.model.Book;

import java.util.List;

public interface BooksService {
    List<Book> list();

    Book get(String bookId);

    void delete(String bookId);

    Book create(BookRequest bookRequest);
}
