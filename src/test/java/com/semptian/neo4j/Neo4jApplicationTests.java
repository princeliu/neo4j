package com.semptian.neo4j;

import com.semptian.neo4j.entity.Book;
import com.semptian.neo4j.entity.Person;
import com.semptian.neo4j.entity.relationships.ReaderOf;
import com.semptian.neo4j.entity.relationships.WriterOf;
import com.semptian.neo4j.repository.BookRepository;
import com.semptian.neo4j.repository.PersonRepository;
import com.semptian.neo4j.service.BookService;
import com.semptian.neo4j.service.PersonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.neo4j.ogm.model.GraphModel;
import org.neo4j.ogm.model.Node;
import org.neo4j.ogm.result.ResultGraphRowListModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Neo4jApplicationTests {

    @Autowired
    PersonRepository personRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    BookService bookService;
    @Autowired
    PersonService personService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testPersonName() {

        Person person = new Person();
        person.setName("杰克");
        person = personRepository.save(person);

        assertNotNull(person);
        assertNotNull(person.getId());
        assertEquals("杰克", person.getName());
    }

    @Test
    public void testCRUDBook() {

        Book book = new Book();
        book.setName("你的模样");
        book = bookRepository.save(book);

        assertNotNull(book);
        assertNotNull(book.getId());
        assertEquals("你的模样", book.getName());

        Long originalId = book.getId();

        Optional<Book> books = bookRepository.findById(originalId);
        book = books.get();
        assertNotNull(book);
    }


    @Test
    public void testRichRelationship() {
        Date timestamp = new Date();
        Person person1 = new Person();
        person1.setName("洛克");

        // Test create with writers
        Book book1 = new Book();
        Set<WriterOf> writings = new HashSet<WriterOf>();
        WriterOf writer = new WriterOf();
        writer.setBook(book1);
        writer.setWriter(person1);
        writer.setStartDate(timestamp);
        writer.setEndDate(timestamp);
        writings.add(writer);
        person1.setWritings(writings);

        book1.setName("花样年华");
        book1.setWriters(writings);
        bookRepository.save(book1);
        Object books = bookService.findByName("花样年华", 0);
        person1 = personRepository.save(person1);
//        books.getNodes().forEach(book -> {
//            assertNotNull(book1);
//            assertNotNull(book1.getWriters());
//            assertEquals(1, book1.getWriters().size());
//        });

        for (WriterOf writerOf : person1.getWritings()) {
            assertNotNull(writerOf.getStartDate());
            assertNotNull(writerOf.getEndDate());
        }
    }

    @Test
    public void testCRUDRelationshipEntities() {
        Person person1 = new Person();
        person1.setName("乔治");
        person1.setPhone("19888899989");
        person1.setSex("男");

        Person person2 = new Person();
        person2.setName("露西");
        person2.setSex("女");
        person2.setPhone("19888899989");
        // Test create with writers
        Book book1 = new Book();
        book1.setName("那时明月");
        Set<WriterOf> writings = new HashSet<>();
        Set<ReaderOf> readers = new HashSet<>();


        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2017);
        calendar.set(Calendar.MONDAY, 8);
        calendar.set(Calendar.DAY_OF_MONTH, 9);

        WriterOf writer = new WriterOf();
        writer.setBook(book1);
        writer.setWriter(person1);
        writer.setStartDate(calendar.getTime());
        ReaderOf readerOf = new ReaderOf();
        readerOf.setBook(book1);
        readerOf.setReader(person2);
        readerOf.setStartDate(calendar.getTime());

        calendar.set(Calendar.YEAR, 2018);
        writer.setEndDate(calendar.getTime());
        readerOf.setEndDate(calendar.getTime());
        writings.add(writer);
        readers.add(readerOf);
        person1.setWritings(writings);
        person2.setReadBooks(readers);

        book1.setWriters(writings);
        book1.setReaders(readers);
        book1 = bookRepository.save(book1);
        person1 = personRepository.save(person1);
        person2 = personRepository.save(person2);
//        assertNotNull(book1);
//        assertNotNull(book1.getWriters());
//        assertEquals(1, book1.getWriters().size());
//
//        // Test add readers
//        Book book2 = new Book();
//        book2.setName("梦里看花");
//        book2 = bookRepository.save(book2);
//        assertNotNull(book2);
//        assertNull(book2.getReaders());
//
//        writings = new HashSet<WriterOf>();
//        writer = new WriterOf();
//        writer.setBook(book2);
//        writer.setWriter(person1);
//        writings.add(writer);
//
//
//        book2.setWriters(writings);
//        book2 = bookRepository.save(book2);
//        assertNotNull(book2.getWriters());
//        assertEquals(1, book2.getWriters().size());
//
//        // Verify that the person entity is in sync
//        person1 = personRepository.findById(person1.getId()).get();
//        assertNotNull(person1.getWritings());
//        assertEquals(2, person1.getWritings().size());
//
//        // Test remove writers
//        person1.getWritings().removeAll(book1.getWriters());
//        person1.getWritings().removeAll(book2.getWriters());
//        book2.setWriters(null);
//        book2 = bookRepository.save(book2);
//        book1.setWriters(null);
//        book1 = bookRepository.save(book1);
//
//        person1 = personRepository.findById(person1.getId()).get();
//        assertNotNull(person1.getWritings());
    }

    @Test
    public void addReaderAndWriter(){

        Optional<Book> bookOptional = bookRepository.findById(108L);
        Book book = bookOptional.get();

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2017);
        calendar.set(Calendar.MONDAY, 8);
        calendar.set(Calendar.DAY_OF_MONTH, 9);

        Person person1 = new Person();
        person1.setName("洛克");
        person1.setPhone("18998989789");
        person1.setSex("男");

        Set<ReaderOf> readers = new HashSet<>();
        ReaderOf readerOf = new ReaderOf();
        readerOf.setBook(book);
        readerOf.setReader(person1);
        readerOf.setStartDate(calendar.getTime());

        calendar.set(Calendar.YEAR, 2018);
        readerOf.setEndDate(calendar.getTime());
        readers.add(readerOf);
        person1.setReadBooks(readers);

        book.setReaders(readers);

        personRepository.save(person1);
        bookRepository.save(book);
    }

    @Test
    public void customQuery() {

        List<Book> books = bookService.customQuery("那时明月");

        books.forEach(book -> {
            Set<WriterOf> writerOfs = book.getWriters();
            Set<ReaderOf> readerOfs = book.getReaders();
            if (writerOfs != null && writerOfs.size() > 0) {
                writerOfs.forEach(writerOf -> {
                    System.out.println(writerOf.getWriter().getName());
                });
            }

            if (readerOfs != null && readerOfs.size() > 0) {
                readerOfs.forEach(readerOf -> {
                    System.out.println(readerOf.getReader().getName());
                });
            }
        });
    }

    @Test
    public void findByName() {

        List<Person> people = personService.findByName("洛克");

        people.forEach(person -> {

            System.out.println(person.getName());
        });
    }

    @Test
    public void findBookByName() {

        Object books = bookService.customQuery("那时明月");

        System.out.println(books);
    }

    @Test
    public void findOneById() {

        Optional<Book> book = bookService.findOneById(101, 0);

        System.out.println(book.get().getName());
    }

}
