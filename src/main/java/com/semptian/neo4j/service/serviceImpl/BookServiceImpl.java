package com.semptian.neo4j.service.serviceImpl;

import com.semptian.neo4j.entity.Book;
import com.semptian.neo4j.repository.BookRepository;
import com.semptian.neo4j.service.BookService;
import org.neo4j.ogm.model.GraphModel;
import org.neo4j.ogm.result.ResultGraphRowListModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

/**
 * @Author Princeliu
 * @Date 2018/8/6 16:38
 * @Description
 */
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;

    @Override
    public List<Book> customQuery(String bookName) {
        return bookRepository.customQuery(bookName);
    }

    @Override
    public Book findByName(String name, int depth) {
        return bookRepository.findByName(name, depth);
    }

    @Override
    public Optional<Book> findOneById(long id, int depth) {

        return bookRepository.findById(id, depth);
    }
}
