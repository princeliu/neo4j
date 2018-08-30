package com.semptian.neo4j.repository;

import com.semptian.neo4j.entity.Person;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * @Author Princeliu
 * @Date 2018/8/4 21:04
 * @Description
 */
@Repository
public interface PersonRepository extends Neo4jRepository<Person, Long> {

    List<Person> findByName(@Param("name") String name);

    @Query("MATCH (t:Person) WHERE t.name =~ ('(?i).*'+{name}+'.*') RETURN t")
    Collection<Person> findByPersonName(@Param("name") String name);
}
