package com.elsevier.techassociates.springcrudapi.integrationtests;

import com.elsevier.techassociates.springcrudapi.BaseIntegrationTestServer;
import com.elsevier.techassociates.springcrudapi.api.requests.BookRequest;
import com.elsevier.techassociates.springcrudapi.business.model.Book;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.HttpStatus;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith({BaseIntegrationTestServer.class})
public class BooksResourceIntegrationIntegrationTest {

    private static final String API_BASE_URL = "http://localhost:8081/api/";
    private static final ObjectMapper mapper = new ObjectMapper();


    @Test
    public void shouldReturn400WhenPublishYearInBadFormatWhenCreatingABook() {
        // Given
        String createPath = "books";

        String body = "{\"title\": \"good-title\", \"publishYear\": \"badPublishYear\" }";

        // When
        final Response response = RestAssured
                .given()
                .body(body)
                .contentType(ContentType.JSON)
                .post(API_BASE_URL + createPath)
                .then()
                .extract()
                .response();

        // Then
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCode());
    }

    @Test
    public void shouldReturn200WhenGettingExistingBook() throws Exception {
        // Given
        Book expectedBook = createBook();
        String readPath = "books/" + expectedBook.getId();

        // When
        final Response response = RestAssured
                .given()
                .get(API_BASE_URL + readPath)
                .then()
                .extract()
                .response();

        // Then
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());

        Book actualBook = response.getBody().as(Book.class);
        assertEquals(expectedBook.getId(), actualBook.getId());
        assertEquals(expectedBook.getTitle(), actualBook.getTitle());
        assertEquals(expectedBook.getPublishYear(), actualBook.getPublishYear());
    }

    @Test
    public void shouldReturn404WhenGettingNonExistentBook() {
        // Given
        String readPath = "books/" + randomUUID();

        // When
        final Response response = RestAssured
                .given()
                .get(API_BASE_URL + readPath)
                .then()
                .extract()
                .response();

        // Then
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode());
    }

    @Test
    public void shouldReturn201WhenCreatingABook() throws Exception {
        // Given
        String createPath = "books";

        // When
        final Response response = RestAssured
                .given()
                .body(mapper.writeValueAsString(new BookRequest("book-1", 1970)))
                .contentType(ContentType.JSON)
                .post(API_BASE_URL + createPath)
                .then()
                .extract()
                .response();

        // Then
        assertEquals(HttpStatus.CREATED.value(), response.getStatusCode());
    }

    @Test
    public void shouldReturn204WhenDeletingAnyBook() {
        // Given
        String deletePath = "books/" + randomUUID();

        // When
        final Response response = RestAssured
                .given()
                .delete(API_BASE_URL + deletePath)
                .then()
                .extract()
                .response();

        // Then
        assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatusCode());
    }

    private Book createBook() throws Exception {
        // Given
        String createPath = "books";

        // When
        final Response response = RestAssured
                .given()
                .body(mapper.writeValueAsString(new BookRequest("book-1", 1970)))
                .contentType(ContentType.JSON)
                .post(API_BASE_URL + createPath)
                .then()
                .extract()
                .response();

        // Then
        assertEquals(HttpStatus.CREATED.value(), response.getStatusCode());

        return response.getBody().as(Book.class);
    }
}
