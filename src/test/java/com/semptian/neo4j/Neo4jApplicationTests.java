package com.semptian.neo4j;

import com.semptian.neo4j.entity.*;
import com.semptian.neo4j.entity.base.DirectorLevelEnum;
import com.semptian.neo4j.entity.relationships.*;
import com.semptian.neo4j.repository.BookRepository;
import com.semptian.neo4j.repository.PersonRepository;
import com.semptian.neo4j.service.*;
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
    MovieService movieService;
    @Autowired
    DirectorService directorService;
    @Autowired
    ActorService actorService;
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
        person = personService.save(person);

        assertNotNull(person);
        assertNotNull(person.getId());
        assertEquals("杰克", person.getName());
    }

    @Test
    public void testCRUDBook() {

        Book book = new Book();
        book.setName("你的模样");
        book = bookService.save(book);

        assertNotNull(book);
        assertNotNull(book.getId());
        assertEquals("你的模样", book.getName());

        Long originalId = book.getId();

        Optional<Book> books = bookService.findOneById(originalId, 0);
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
        bookService.save(book1);
        Object books = bookService.findByName("花样年华", 0);
        person1 = personService.save(person1);
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
        book1 = bookService.save(book1);
        person1 = personService.save(person1);
        person2 = personService.save(person2);
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

        Optional<Book> bookOptional = bookService.findOneById(25L, 0);
        Book book = bookOptional.get();

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2017);
        calendar.set(Calendar.MONDAY, 8);
        calendar.set(Calendar.DAY_OF_MONTH, 9);

        Person person1 = new Person();
        person1.setName("杰克");
        person1.setPhone("18998988888");
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

        personService.save(person1);
        bookService.save(book);
    }

    @Test
    public void addBook(){

        Optional<Person> personOptional = personService.findById( 81L);
        Person person = personOptional.get();

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2017);
        calendar.set(Calendar.MONDAY, 10);
        calendar.set(Calendar.DAY_OF_MONTH, 9);

        Set<WriterOf> writerOfs = new HashSet<>();
        WriterOf writerOf = new WriterOf();
        writerOf.setStartDate(calendar.getTime());
        calendar.add(Calendar.DAY_OF_MONTH, -10);
        writerOf.setEndDate(calendar.getTime());

        Book book1 = new Book();
        book1.setName("花样年华");
        writerOf.setBook(book1);
        writerOf.setWriter(person);

        writerOfs.add(writerOf);

        person.setWritings(writerOfs);
        book1.setWriters(writerOfs);

        bookService.save(book1);
        personService.save(person);

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

        List<Person> people = personService.findByName("洛克", 2);

        people.forEach(person -> {

            Set<WriterOf> writerOfs = person.getWritings();
            Set<ReaderOf> readerOfs = person.getReadBooks();
            if(readerOfs != null && readerOfs.size() > 0){
                readerOfs.forEach(readerOf -> {

                    System.out.println(readerOf.getBook().getName());

                    Set<WriterOf> writerOfs1 = readerOf.getBook().getWriters();
                    if (writerOfs1 != null && writerOfs1.size() > 0) {
                        writerOfs1.forEach(writerOf1 -> {
                            System.out.println(writerOf1.getWriter().getName());
                        });
                    }

                });
            }

            if (writerOfs != null && writerOfs.size() > 0){
                writerOfs.forEach(writerOf -> {

                    System.out.println(writerOf.getBook().getName());
                    Set<ReaderOf> readerOfs1 = writerOf.getBook().getReaders();

                    if(readerOfs1 != null && readerOfs1.size() > 0){
                        readerOfs1.forEach(readerOf1 -> {

                            System.out.println(readerOf1.getReader().getName());
                        });
                    }
                });
            }

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

    @Test
    public void createActor(){

        Actor actor = new Actor();
        actor.setName("汉克斯");
        actor.setFans(600);
        actor.setMoviePayment(2000);
        actor.setPhone("12345678998");
        actor.setSex("男");

        actorService.save(actor);
    }

    @Test
    public void createDirector(){

        Director director = new Director();
        director.setName("卡梅隆");
        director.setLevel(DirectorLevelEnum.HIGH_LEVEL.getLevel());
        director.setDescription(DirectorLevelEnum.HIGH_LEVEL.getDescription());
        director.setSex("男");

        directorService.save(director);
    }

    @Test
    public void createMovie(){

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2018);
        calendar.set(Calendar.MONDAY, 10);
        calendar.set(Calendar.DAY_OF_MONTH, 9);

        Movie movie = new Movie();
        movie.setRating(9);
        movie.setName("阿凡达");
        movie.setReleaseDate(calendar.getTime());
        movie.setDescription("史上第一");

        movieService.save(movie);
    }

    @Test
    public void addRelation(){

        Actor actor = actorService.findOneById(33,0).get();
        Director director = directorService.findOneById(3, 0).get();
        Movie movie = movieService.findOneById(26, 0).get();

        Set<ActorIn> actorIns = new HashSet<>();
        ActorIn actorIn = new ActorIn();
        actorIn.setActor(actor);
        actorIn.setMovie(movie);
        actorIn.setRole("杰克");
        actorIns.add(actorIn);

        Set<Directed> directeds = new HashSet<>();
        Directed directed = new Directed();
        directed.setDirector(director);
        directed.setMovie(movie);
        directeds.add(directed);

        actor.setActorIns(actorIns);
        movie.setActors(actorIns);
        director.setDirecteds(directeds);
        movie.setDirectors(directeds);

        actorService.save(actor);
        directorService.save(director);
        movieService.save(movie);
    }

    @Test
    public void addInvested(){

        Person person = personService.findById(156).get();
        Director director = directorService.findOneById(3, 0).get();

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2017);
        calendar.set(Calendar.MONDAY, 10);
        calendar.set(Calendar.DAY_OF_MONTH, 9);

        Set<Invested> investeds = new HashSet<>();
        Invested invested = new Invested();
        invested.setInvestCount(2000);
        invested.setInvestTime(calendar.getTime());
        invested.setPerson(person);
        invested.setDirector(director);
        investeds.add(invested);

        person.setInvesteds(investeds);
        director.setInvesteds(investeds);

        personService.save(person);
        directorService.save(director);
    }

}
