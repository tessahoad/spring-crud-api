package com.elsevier.techassociates.springcrudapi.api.resources.books;

import com.elsevier.techassociates.springcrudapi.api.requests.BookRequest;
import com.elsevier.techassociates.springcrudapi.business.books.BooksService;
import com.elsevier.techassociates.springcrudapi.business.model.Book;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.io.IOException;
import java.util.List;

import static com.elsevier.techassociates.springcrudapi.api.resources.RootResource.*;

@RestController
public class BooksResource {
    public static final String PATH_PARAM_BOOK_ID = "book_id";

    public static final String BOOKS_PATH = BASE_URL + "books";
    public static final String BOOK_PATH = BOOKS_PATH + "/{" + PATH_PARAM_BOOK_ID + "}";

    private final BooksService booksService;

    @Inject
    public BooksResource(BooksService booksService) { this.booksService = booksService; }

    @GetMapping(value = BOOKS_PATH, produces = CONTENT_TYPE_JSON)
    public ResponseEntity<List<Book>> list() {
        return new ResponseEntity<>(booksService.list(), HttpStatus.OK);
    }

    @PostMapping(value = BOOKS_PATH, consumes = CONTENT_TYPE_JSON, produces = CONTENT_TYPE_JSON)
    public ResponseEntity<Book> create(@RequestBody BookRequest bookRequest) {
        return new ResponseEntity<>(booksService.create(bookRequest), HttpStatus.CREATED);
    }

    @GetMapping(value = BOOK_PATH, produces = CONTENT_TYPE_JPEG)
    public ResponseEntity<InputStreamResource> getImage(@PathVariable(PATH_PARAM_BOOK_ID) String bookId) throws IOException {
        ClassPathResource classPathResource = new ClassPathResource("kitten.jpg");
        return ResponseEntity
                .ok()
                .body(new InputStreamResource(classPathResource.getInputStream()));
    }

    @GetMapping(value = BOOK_PATH, produces = CONTENT_TYPE_JSON)
    public ResponseEntity<Book> get(@PathVariable(PATH_PARAM_BOOK_ID) String bookId) {
        Book book = booksService.get(bookId);
        return book == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(book, HttpStatus.OK);
    }

    @DeleteMapping(BOOK_PATH)
    public ResponseEntity<Book> delete(@PathVariable(PATH_PARAM_BOOK_ID) String bookId) {
        booksService.delete(bookId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
