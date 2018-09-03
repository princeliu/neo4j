package com.semptian.neo4j.repository;

import com.semptian.neo4j.entity.Book;
import org.springframework.data.neo4j.annotation.Depth;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author Princeliu
 * @Date 2018/8/4 22:04
 * @Description
 */
@Repository
public interface BookRepository extends Neo4jRepository<Book, Long>{

//    @Query("MATCH (n:Book) WHERE n.name = {0} WITH n RETURN n limit 1")
    @Query("MATCH (n:Book) WHERE n.name = {0} WITH n RETURN n," +
            "[ [ (n)<-[r_r1:`READER_OF`]-(p1:`Person`) | [ r_r1, p1 ] ], " +
            "[ (n)<-[r_w1:`WRITER_OF`]-(p1:`Person`) | [ r_w1, p1 ] ] ], ID(n) limit 1")
    List<Book> customQuery(String bookName);

//    @Query("MATCH (n:Book) WHERE n.name = {0} WITH n RETURN n limit 1")
    Book findByName(String name, @Depth int depth);
}


