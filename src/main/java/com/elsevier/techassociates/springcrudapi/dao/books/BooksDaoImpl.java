package com.elsevier.techassociates.springcrudapi.dao.books;

import com.elsevier.techassociates.springcrudapi.business.model.Book;
import com.elsevier.techassociates.springcrudapi.dao.mapper.BookMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class BooksDaoImpl implements BooksDao {

    private static final String PARAM_BOOK_ID = "bookId";
    private static final String PARAM_BOOK_TITLE = "bookTitle";
    private static final String PARAM_BOOK_PUBLISH_YEAR = "bookPublishYear";

    private static final String SQL_WHERE_BOOK_ID = " WHERE book_id =:" + PARAM_BOOK_ID;

    private static final String SQL_LIST_BOOKS = "SELECT book_id, book_title, book_publish_year" +
            " FROM books";
    private static final String SQL_GET_BOOK = SQL_LIST_BOOKS +
            SQL_WHERE_BOOK_ID;
    private static final String SQL_DELETE_BOOK = "DELETE FROM books" +
            SQL_WHERE_BOOK_ID;
    private static final String SQL_INSERT_BOOK = "INSERT INTO books (book_id, book_title, book_publish_year)" +
            " VALUES (:" + PARAM_BOOK_ID + ", :" + PARAM_BOOK_TITLE + ", :" + PARAM_BOOK_PUBLISH_YEAR + ")";

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Inject
    public BooksDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    @Override
    public List<Book> list() {
        return jdbcTemplate.query(SQL_LIST_BOOKS, Map.of(), new BookMapper());
    }

    @Override
    public Book get(String id) {
        Map<String, String> paramMap = Map.of(PARAM_BOOK_ID, id);
        List<Book> results = jdbcTemplate.query(SQL_GET_BOOK, paramMap, new BookMapper());
        return results.isEmpty() ? null : results.get(0);
    }

    @Override
    public void delete(String id) {
        Map<String, String> paramMap = Map.of(PARAM_BOOK_ID, id);
        jdbcTemplate.update(SQL_DELETE_BOOK, paramMap);
    }

    @Override
    public Book create(String title, Integer publishYear) {
        String bookId = UUID.randomUUID().toString();
        Map<String, Object> paramMap = Map.of(
                PARAM_BOOK_ID, bookId,
                PARAM_BOOK_PUBLISH_YEAR, publishYear,
                PARAM_BOOK_TITLE, title
        );
        jdbcTemplate.update(SQL_INSERT_BOOK, paramMap);
        return new Book(bookId, title, publishYear);
    }
}
