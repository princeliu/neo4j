package com.semptian.neo4j.repository;

import com.semptian.neo4j.entity.Person;
import org.springframework.data.neo4j.annotation.Depth;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author Princeliu
 * @Date 2018/8/4 21:04
 * @Description
 */
@Repository
public interface PersonRepository extends Neo4jRepository<Person, Long> {

    List<Person> findByName(@Param("name") String name, @Depth int depth);

    @Query("match(n:Person {name:{0}}) return n;")
    List<Person> findByPersonName(@Param("name") String name);

    @Query("match(p:Person {name: {0}})-[r:INVESTED]->(p2:Person)" +
            "where r.investCount >={1}" +
            "return p2;")
    List<Person> getPersonByQuery(String name, double investedCount);
}
