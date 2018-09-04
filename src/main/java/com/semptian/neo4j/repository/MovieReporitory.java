package com.semptian.neo4j.repository;

import com.semptian.neo4j.entity.Movie;
import org.springframework.data.neo4j.annotation.Depth;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author Princeliu
 * @Date 2018/8/31 15:08
 * @Description
 */
@Repository
public interface MovieReporitory extends Neo4jRepository<Movie, Long> {

    @Query("MATCH (n:Movie) WHERE n.name = {0} RETURN n")
    List<Movie> customQuery(String name);

    Movie findByName(String name, @Depth int depth);

    @Query("match(p:Person {name: {0}})-[r:DIRECTED]->(m:Movie)" +
            "where m.rating >{1}" +
            "return m;")
    List<Movie> getMovieByQuery(String name, int rating);
}
