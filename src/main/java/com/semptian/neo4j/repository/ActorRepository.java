package com.semptian.neo4j.repository;

import com.semptian.neo4j.entity.Actor;
import org.springframework.data.neo4j.annotation.Depth;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author Princeliu
 * @Date 2018/8/31 15:05
 * @Description
 */
@Repository
public interface ActorRepository extends Neo4jRepository<Actor, Long> {

    @Query("MATCH (n:Actor) WHERE n.name = {0} RETURN n")
    List<Actor> customQuery(String name);

    Actor findByName(String name, @Depth int depth);
}
