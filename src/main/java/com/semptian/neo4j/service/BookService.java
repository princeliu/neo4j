package com.semptian.neo4j.service;

import com.semptian.neo4j.entity.Book;

import java.util.List;
import java.util.Optional;

/**
 * @Author Princeliu
 * @Date 2018/8/6 16:37
 * @Description
 */
public interface BookService {

    List<Book> customQuery(String bookName);

    Book findByName(String name, int depth);

    Optional<Book> findOneById(long id, int depth);

    Book save(Book book);
}
