package com.semptian.neo4j.repository;

import com.semptian.neo4j.entity.Director;
import org.springframework.data.neo4j.annotation.Depth;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author Princeliu
 * @Date 2018/8/31 15:07
 * @Description
 */
@Repository
public interface DirectorRepository extends Neo4jRepository<Director, Long> {

    @Query("MATCH (n:Director) WHERE n.name = {0} RETURN n")
    List<Director> customQuery(String name);

    Director findByName(String name, @Depth int depth);
}
