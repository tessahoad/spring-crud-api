package com.elsevier.techassociates.springcrudapi.business.books;

import com.elsevier.techassociates.springcrudapi.api.requests.BookRequest;
import com.elsevier.techassociates.springcrudapi.business.model.Book;
import com.elsevier.techassociates.springcrudapi.dao.books.BooksDao;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class BooksServiceImpl implements BooksService {

    @Inject
    private BooksDao booksDao;

    @Override
    public List<Book> list() {
        return booksDao.list();
    }

    @Override
    public Book get(String bookId) {
        return booksDao.get(bookId);
    }

    @Override
    public void delete(String bookId) {
        booksDao.delete(bookId);
    }

    @Override
    public Book create(BookRequest bookRequest) {
        return booksDao.create(bookRequest.getTitle(), bookRequest.getPublishYear());
    }
}
