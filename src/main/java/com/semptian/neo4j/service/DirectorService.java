package com.semptian.neo4j.service;

import com.semptian.neo4j.entity.Director;

import java.util.List;
import java.util.Optional;

/**
 * @Author Princeliu
 * @Date 2018/8/31 15:33
 * @Description
 */
public interface DirectorService {

    List<Director> customQuery(String name);

    Director findByName(String name, int depth);

    Optional<Director> findOneById(long id, int depth);

    Director save(Director director);
}
