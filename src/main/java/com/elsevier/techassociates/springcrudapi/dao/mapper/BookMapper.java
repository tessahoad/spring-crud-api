package com.elsevier.techassociates.springcrudapi.dao.mapper;

import com.elsevier.techassociates.springcrudapi.business.model.Book;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Book(
                rs.getString("book_id"),
                rs.getString("book_title"),
                rs.getInt("book_publish_year"));
    }
}
