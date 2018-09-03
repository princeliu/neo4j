package com.semptian.neo4j.service;

import com.semptian.neo4j.entity.Actor;

import java.util.List;
import java.util.Optional;

/**
 * @Author Princeliu
 * @Date 2018/8/31 15:31
 * @Description
 */
public interface ActorService {

    List<Actor> customQuery(String name);

    Actor findByName(String name, int depth);

    Optional<Actor> findOneById(long id, int depth);

    Actor save(Actor actor);
}
