package com.semptian.neo4j.service;

import com.semptian.neo4j.entity.Person;

import java.util.List;

/**
 * @Author Princeliu
 * @Date 2018/8/4 21:20
 * @Description
 */
public interface PersonService {
    List<Person> findByName(String name, int depth);
}
